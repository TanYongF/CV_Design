<template>
  <el-card class="box-card" :body-style="{ padding: '0px' }" shadow="hover">
    <div class="mt-0">
      <img
        src="https://kauizhaotan.oss-accelerate.aliyuncs.com/img/cv-gf96be2e73_1280.png?x-oss-process=style/water"
        class="image"
        @click="dialogVisible = true"
      />
    </div>

    <div style="padding: 12px" class="flex">
      <span class="font-bold mr-2">{{ cv.fileName }} </span>
      <span class="ml-auto"
        ><el-tag>{{ cv.intention ? cv.intention : "未填写" }}</el-tag>
      </span>
    </div>

    <div class="card-bottom flex">
      <span>创建于 {{ transferDatestamp(cv.createAt) }}</span>

      <el-button-group class="ml-auto">
        <el-button type="danger" @click="onDelete">删除简历</el-button>
        <el-button type="primary" @click="dialogVisible = true"
          >查看详情</el-button
        >
      </el-button-group>
    </div>

    <template #footer> </template>
  </el-card>

  <el-dialog
    v-model="dialogVisible"
    title="简历查看"
    width="800px"
    :before-close="handleClose"
    @open="onOpen"
    class="dialog-bg"
  >
    <template #header>
      <div style="font-family: 'JMH'; padding-bottom: 0px" class="text-2xl">
        简历查看
      </div>
    </template>
    <div class="rounded-3xl p-4 form-bg mx-4">
      <CVForm
        :cv="newCV.cv"
        :showButton="false"
        :isDisabled="$store.state.user.username != cv.uploadUser"
      ></CVForm>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button
          @click="dialogVisible = false"
          type="danger"
          :disabled="btnDisable"
          >取消更新
          <el-icon class="el-icon--right">
            <Delete />
          </el-icon>
        </el-button>
        <el-button type="primary" @click="onSubmit" :disabled="btnDisable">
          确认更新
          <el-icon class="el-icon--right">
            <Upload />
          </el-icon>
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
  
<script setup>
import { ref, reactive } from "vue";
import { transferDatestamp, toast } from "~/composables/util";
import { showModal } from "~/composables/util.js";
import CVForm from "~/components/cv/CVForm.vue";
import { postCV, deleteCV } from "~/api/mannager";
import { useRouter } from "vue-router";
const router = useRouter();
const props = defineProps(["cv"]);
const loading = ref(false);
const dialogVisible = ref(false);
const btnDisable = ref(false);

const handleClose = (done) => {
  showModal("注意，取消后已进行的变更将丢失！")
    .then(() => {
      done();
    })
    .catch(() => {
      // catch error
    });
};

//todo
const newCV = reactive({
  cv: {},
});
const onOpen = () => {
  Object.assign(newCV.cv, props.cv);
  newCV.cv.name = props.cv.name;
};
const onDelete = (cvId) => {
  loading.value = true;
  deleteCV(props.cv.id)
    .then((res) => {
      toast("简历删除成功！");
      location.reload();
    })
    .finally(() => {
      loading.value = false;
    });
};

const onSubmit = () => {
  btnDisable.value = true;
  loading.value = true;
  postCV(newCV.cv)
    .then((res) => {
      toast("更新简历成功！");
      Object.assign(props.cv, newCV.cv);
      dialogVisible.value = false;
      // router.push("/cvs")
    })
    .finally(() => {
      btnDisable.value = false;
    });
};
</script>

<style scoped>
.box-card {
  @apply mb-5 mx-auto rounded-3xl pb-0.5em;
}
.box-card:hover {
  @apply mb-5 mx-auto rounded-3xl pb-0.5em shadow-2xl shadow-blue-700;
}
.image {
  width: 100%;
  display: block;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-bottom {
  @apply my-1 mx-3;
}

.item {
  margin-bottom: 1px;
}
</style>

<style>
.dialog-bg {
  background: #edc0bf;
  background: linear-gradient(90deg, #edc0bf 0, #c4caef 58%);
}

.form-bg {
  /* other styles */
  background: rgba(255, 255, 255, 0.7);
  -webkit-backdrop-filter: blur(10px);
  backdrop-filter: blur(10px);
}

.el-dialog {
  @apply rounded-2xl;
}
</style>
