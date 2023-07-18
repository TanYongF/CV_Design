<template>
  <el-form label-width="auto">
    <el-form-item label="公司名称">
      <el-input v-model="job.companyName" />
    </el-form-item>
    <el-form-item label="招聘人数">
      <el-input v-model="job.headCount" />
    </el-form-item>
    <el-form-item label="岗位名称">
      <el-input v-model="job.positionName" />
    </el-form-item>

    <el-form-item label="工资薪水">
      <el-col :span="11" class="mb-0">
        <el-input-number
          v-model="job.minSalary"
          placeholder="最低薪酬"
          style="width: 100%"
        />
      </el-col>
      <el-col :span="2" class="text-center mb-0">
        <span class="text-gray-500">-</span>
      </el-col>
      <el-col :span="11" class="mb-0">
        <el-input-number
          v-model="job.maxSalary"
          placeholder="最高薪酬"
          style="width: 100%"
        />
      </el-col>
    </el-form-item>
    <el-form-item label="最低要求学历">
      <el-select
        v-model="job.minDegree"
        placeholder="候选人最低要求学历"
        class="w-full"
      >
        <el-option label="博士" :value="0"></el-option>
        <el-option label="硕士" :value="1"></el-option>
        <el-option label="本科" :value="2"></el-option>
        <el-option label="大专" :value="3"></el-option>
        <el-option label="中专" :value="4"></el-option>
        <el-option label="高中" :value="5"></el-option>
        <el-option label="初中" :value="6"></el-option>
        <el-option label="小学" :value="7"></el-option>
        <el-option label="其他" :value="8"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="最低工作年限">
      <el-input
        v-model="job.minWorkYears"
        placeholder="候选人最低要求工作年限"
      ></el-input>
    </el-form-item>
    <el-form-item label="岗位要求">
      <el-input v-model="job.remark" type="textarea" />
    </el-form-item>
    <el-form-item label="岗位介绍">
      <el-input v-model="job.info" type="textarea" />
    </el-form-item>
  </el-form>
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
</template>


<script setup>
import { ref, reactive } from "vue";
import { toast } from "~/composables/util.js";
import { postJob } from "~/api/mannager";
import { useRouter } from "vue-router";
const props = defineProps(["dialogVisible"]);
const job = reactive({
  id: "",
  companyName: "",
  headCount: "",
  positionName: "",
  info: "",
  maxSalary: "",
  minSalary: "",
  remark: "",
  createAt: "",
  updateAt: "",
  minDegree: "8",
  minWorkYears: "",
  userName: "",
});
const loading = ref(false);

const onSubmit = () => {
  loading.value = true;
  postJob(job)
    .then((res) => {
      toast(res.msg);
    })
    .finally(() => {
      // location.reload()
      loading.value = false;
    });
};
</script>