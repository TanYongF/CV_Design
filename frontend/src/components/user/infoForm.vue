<template>
  <el-form label-width="auto" ref="formRef" :model="info">
    <el-form-item label="昵称" required>
      <el-input v-model="info.nickname" />
    </el-form-item>
    <el-form-item label="性别" required>
      <el-select v-model="info.gender" placeholder="您的性别" class="w-full">
        <el-option label="男" :value="true"></el-option>
        <el-option label="女" :value="false"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="电话" required>
      <el-input v-model="info.phone" />
    </el-form-item>
    <div class="flex justify-center items-center">
      <el-button
        type="primary"
        @click="onSubmit"
        size="large"
        :loading="loading"
        round
      >
        提交</el-button
      >
    </div>
  </el-form>
</template>


<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { toast } from "~/composables/util.js";
import { updateUserInfo } from "~/api/mannager";
import { useStore } from "vuex";

// const info = reactive({
//     "gender": "",
//     "phone": "",
//     "nickname": "",
//     "username":""
// })
const props = defineProps(["info"]);
const loading = ref(false);
const formRef = ref(null);
const store = useStore();

//提交按钮事件
const onSubmit = () => {
  formRef.value.validate((valid) => {
    if (!valid) {
      return false;
    }
    loading.value = true;
    updateUserInfo(props.info)
      .then((res) => {
        //更改全局状态信息
        store.commit("SET_USERINFO", res.data);
        toast(res.msg);
      })
      .finally(() => {
        // location.reload()
        loading.value = false;
        this.dialogVisible = false;
      });
  });
};

function onKeyUp(e) {
  if (e.key == "Enter") {
    onSubmit();
  }
}

//添加页面监听
onMounted(() => {
  document.addEventListener("keyup", onKeyUp);
});

//卸载页面监听
onBeforeUnmount(() => {
  document.removeEventListener("keyup", onKeyUp);
});
</script>

<style scoped></style>

