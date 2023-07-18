<template>
  <el-row :gutter="20" justify="center">
    <el-col :span="19" :offset="0">
      <div class="mb-4">
        <el-page-header
          :icon="ArrowLeft"
          class="mr-0"
          @back="$router.push('/job')"
        >
          <template #content>
            <div class="flex items-center">
              <span class="font-600 text-14px mr-3">
                {{ job.data.companyName }} {{ job.data.positionName }}岗位
              </span>
            </div>
          </template>
        </el-page-header>
      </div>
    </el-col>
  </el-row>

  <div>
    <el-row :gutter="20" justify="center">
      <el-col :span="12" class="my-col">
        <el-card class="pl-4" v-loading="loading">
          <template #header>
            <div class="card-header block mt-3">
              <div class="font-bold text-30px">{{ job.data.positionName }}</div>
              <div class="job-info flex items-center">
                <el-space :size="3" :spacer="spacer">
                  <span>职位ID：{{ job.data.id }}</span>
                  <span>创建时间：{{ job.data.createAt }}</span>
                </el-space>
              </div>
            </div>
          </template>

          <div class="detail-title">职位描述</div>
          <div class="detail-content">{{ job.data.info }}</div>
          <div class="detail-title">职位要求</div>
          <div class="detail-content">
            <p v-html="jobRequirements.value"></p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="7" class="my-col">
        <el-card>
          <div class="detail-title">全部职位</div>
          <el-tag type="success" size="large" class="mx-2 my-2"
            >后端开发工程师</el-tag
          >
          <el-tag type="warning" size="large" class="mx-2 my-2"
            >Java开发工程师</el-tag
          >
          <el-tag type="danger" size="large" class="mx-2 my-2"
            >PHP开发工程师</el-tag
          >
          <el-tag type="" size="large" class="mx-2 my-2">文员</el-tag>
          <el-tag type="success" size="large" class="mx-2 my-2"
            >销售人员</el-tag
          >
          <el-tag type="" size="large" class="mx-2 my-2">项目主管</el-tag>
          <el-tag type="warning" size="large" class="mx-2 my-2"
            >市场营销</el-tag
          >
          <el-tag type="success" size="large" class="mx-2 my-2">财务</el-tag>
          <el-tag type="danger" size="large" class="mx-2 my-2"
            >平面设计师</el-tag
          >
          <el-tag type="success" size="large" class="mx-2 my-2"
            >风控专员</el-tag
          >
          <el-tag type="" size="large" class="mx-2 my-2">人力资源管理</el-tag>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>



<script setup>
import { getJob } from "~/api/mannager";
import { reactive, watch, onMounted, ref, h } from "vue";
import { useRoute } from "vue-router";
import { ElDivider } from "element-plus";
import { ArrowLeft } from "@element-plus/icons-vue";
const spacer = h(ElDivider, { direction: "vertical" });
const route = useRoute();
const job = reactive({
  data: {},
});
const loading = ref(true);

const degreeName = [
  "博士",
  "硕士",
  "本科",
  "大专",
  "中专",
  "高中",
  "初中",
  "小学",
];

const jobRequirements = reactive({
  value: "",
});

onMounted(() => {
  getJob(route.params.id)
    .then((res) => {
      job.data = res.data;
      generateJobRequire();
    })
    .finally(() => {
      loading.value = false;
    });
});

//生成岗位需求
const generateJobRequire = () => {
  let i = 1;
  if (job.data.minDegree != null && job.data.minDegree != 8) {
    jobRequirements.value +=
      i + "、要求" + degreeName[job.data.minDegree] + "以上学历<br>";
    i++;
  }
  if (job.data.minWorkYears != null && job.data.minWorkYears != 0)
    jobRequirements.value +=
      i + "、要求" + job.data.minWorkYears + "年以上工作经验<br>";
  jobRequirements.value +=
    "其他要求: " + (job.data.remark == null ? "无" : job.data.remark);
};
</script>


<style>
.detail-title {
  position: relative;
  @apply font-bold py-0 mb-2 mt-2 pl-2.5;
  font-size: 20px;
  height: 40px;
  line-height: 40px;
}

.detail-title:before {
  @apply bg-indigo-500;
  position: absolute;
  content: "";
  width: 4px;
  height: 18px;
  left: 0;
  top: 50%;
  margin-top: -9px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
}

.detail-content {
  @apply mb-6 text-15px text-gray-500;
  line-height: 2em;
}

.el-page-header__title,
.el-page-header__icon {
  @apply text-blue-500;
}
</style>

<style scoped>
.job-info {
  @apply mt-5 text-13px h-13px text-gray-500;
}

.lineDivider {
  @apply mx-2 bg-gray-400 w-0.25 h-13px;
}
</style>