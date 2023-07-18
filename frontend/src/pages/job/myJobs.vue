<template>
  <div>
    <el-form
      inline
      :model="searchCondition"
      :default-sort="{ prop: 'updateAt', order: 'descending' }"
    >
      <el-form-item label="公司名称">
        <el-input
          placeholder="任意公司"
          v-model="searchCondition.companyName"
        ></el-input>
      </el-form-item>
      <el-form-item label="岗位">
        <el-input
          placeholder="任意岗位"
          v-model="searchCondition.positionName"
        ></el-input>
      </el-form-item>
      <el-form-item label="薪酬标准">
        <el-input
          placeholder="任意最低期望薪酬"
          v-model="searchCondition.minSalary"
        ></el-input>
      </el-form-item>
      <el-form-item label="工作年限">
        <el-input
          placeholder="任意最低工作年限"
          v-model="searchCondition.minWorkYears"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handlePageChange(1)">搜索</el-button>
        <el-button
          type="primary"
          @click="dialogVisible = true"
          v-if="$store.state.user.role == 1"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      border
      class="w-full"
      stripe
      fit
      :data="jobs"
      v-loading="loading"
      :default-sort="{ prop: 'minSalary', order: 'descending' }"
    >
      <el-table-column prop="id" label="岗位编号" width="200"></el-table-column>
      <el-table-column
        prop="headCount"
        label="招聘人数"
        width="100"
      ></el-table-column>
      <el-table-column
        prop="positionName"
        label="岗位名称"
        width=""
      ></el-table-column>
      <el-table-column
        sortable
        prop="minSalary"
        label="最低工资"
        width="150"
      ></el-table-column>
      <el-table-column
        sortable
        prop="maxSalary"
        label="最高工资"
        width="150"
      ></el-table-column>
      <el-table-column
        sortable
        prop="updateAt"
        label="更新时间"
        :formatter="UpdateTimeFormatter"
      >
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="handleDetailUpdate(scope.row.id)"
            >更新</el-button
          >
          <el-button
            type="danger"
            @click="handleDelete(scope.$index, scope.row.id)"
            >删除</el-button
          >
          <el-button type="success" @click="handlePropose(scope.row.id)"
            >匹配人选</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :page-size="15"
      class="mt-5 justify-center items-center"
      layout="prev, pager, next"
      :total="totalItems"
      @current-change="handlePageChange"
      @prev-click="handlePageChange"
      @next-click="handlePageChange"
    >
    </el-pagination>
  </div>

  <!-- 新增表单开始 -->
  <el-dialog
    v-model="dialogVisible"
    title="岗位查看"
    width="45%"
    :before-close="handleClose"
    @open="onOpen"
    class="dialog-bg"
  >
    <template #header>
      <div style="font-family: 'JMH'; padding-bottom: 0px" class="text-2xl">
        岗位新增
      </div>
    </template>
    <div class="rounded-3xl p-4 form-bg mx-4">
      <!-- <CVForm :cv=newCV.cv :showButton="false"></CVForm> -->
      <jobForm :dialogVisible="dialogVisible"></jobForm>
    </div>
  </el-dialog>
  <!-- 更改表单开始 -->
  <el-dialog
    v-model="dialogUpdateVisible"
    title="岗位查看"
    width="45%"
    :before-close="handleClose"
    @open="onOpen"
    class="dialog-bg"
  >
    <template #header>
      <div style="font-family: 'JMH'; padding-bottom: 0px" class="text-2xl">
        岗位新增
      </div>
    </template>
    <div class="rounded-3xl p-4 form-bg mx-4">
      <!-- <CVForm :cv=newCV.cv :showButton="false"></CVForm> -->
      <jobForm></jobForm>
    </div>
  </el-dialog>
</template>



<style scope>
.example-showcase .el-loading-mask {
  z-index: 9;
}
</style>


<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { toast, transferDatestamp, showModal } from "~/composables/util.js";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import jobForm from "~/components/job/jobForm.vue";
import { searchJob, deleteJob, proposeJob } from "~/api/mannager";

var jobScheme = {
  id: "",
  companyName: "",
  headCount: 0,
  positionName: "",
  info: "",
  maxSalary: 0,
  minSalary: 0,
  remark: "",
  createAt: "",
  updateAt: "",
};

var jobs = ref([{}]);
const store = useStore();
const totalItems = ref(0);
const loading = ref(true);
const searchCondition = reactive({
  minSalary: "",
  companyName: "",
  positionName: "",
  userName: store.state.user.username,
  minWorkYears: "",
});
const router = useRouter();
const dialogVisible = ref(false);
const dialogUpdateVisible = ref(false);

/**
 * 表格按钮功能
 */
//详情按钮
const handleDetailUpdate = (jobId) => {
  router.push("/job/" + jobId);
};

//删除按钮
const handleDelete = (row, jobId) => {
  deleteJob(jobId)
    .then((res) => {
      location.reload();
    })
    .finally(() => {
      toast("删除成功！");
    });
};

//推荐候选人按钮
const handlePropose = (jobId) => {
  router.push("/recommend/" + jobId);
};

//dialog 回调函数
const handleClose = (done) => {
  showModal("注意，取消后已进行的变更将丢失！")
    .then(() => {
      done();
    })
    .catch(() => {
      // catch error
    });
};

//分页插件相关函数
const handlePageChange = (currentPage) => {
  loading.value = true;
  searchJob(searchCondition, 15, currentPage)
    .then((res) => {
      jobs.value = res.data.records;
      totalItems.value = res.data.total;
    })
    .finally(() => {
      loading.value = false;
    });
};

//TODO
//表格格式化相关参数
const UpdateTimeFormatter = (row, column) => {
  console.log(store.state.user.username);
  return row.updateAt;
};

//键盘控制
function onKeyUp(e) {
  if (e.key == "Enter") {
    handlePageChange(1);
  }
}

//添加页面监听
onMounted(() => {
  //准备数据
  searchJob(searchCondition, 15, 1)
    .then((res) => {
      jobs.value = res.data.records;
      totalItems.value = res.data.total;
    })
    .finally(() => {
      loading.value = false;
    });
  //绑定事件监听
  document.addEventListener("keyup", onKeyUp);
});

//卸载页面监听
onBeforeUnmount(() => {
  document.removeEventListener("keyup", onKeyUp);
});
</script>