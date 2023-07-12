<template>
    <div>

        <el-form inline :model="searchCondition">
            <el-form-item label="公司名称">
                <el-input placeholder="任意公司" v-model="searchCondition.companyName"></el-input>
            </el-form-item>
            <el-form-item label="岗位">
                <el-input placeholder="任意岗位" v-model="searchCondition.positionName"></el-input>
            </el-form-item>
            <el-form-item label="薪酬标准">
                <el-input placeholder="任意最低期望薪酬" v-model="searchCondition.minSalary"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button :icon="Search" type="primary" @click="handlePageChange(1)">搜索</el-button>

            </el-form-item>
        </el-form>

        <el-table border class="w-full" stripe fit :data="jobs" v-loading="loading"
            :default-sort="{ prop: 'minSalary', order: 'descending' }">
            <el-table-column prop="id" label="岗位"></el-table-column>
            <el-table-column prop="companyName" label="公司名称" width="200px"></el-table-column>
            <el-table-column prop="headCount" label="招聘人数" width="150"></el-table-column>
            <el-table-column prop="positionName" label="岗位名称" width="150"></el-table-column>
            <el-table-column sortable prop="minSalary" label="最低工资" width="150"></el-table-column>
            <el-table-column sortable prop="updateAt" label="更新时间" :formatter="UpdateTimeFormatter"> </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" @click="handleDetail(scope.row.id)">详情</el-button>

                </template>

            </el-table-column>
        </el-table>
        <el-pagination background :page-size="15" class="mt-5 justify-center items-center" layout="prev, pager, next"
            :total="totalItems" @current-change="handlePageChange" @prev-click="handlePageChange"
            @next-click="handlePageChange">
        </el-pagination>
    </div>
</template>



<style>
.example-showcase .el-loading-mask {
    z-index: 9;
}
</style>


<script setup>

import { ref, reactive, watch, onMounted, onBeforeUnmount } from 'vue'
import { toast, transferDatestamp } from '~/composables/util.js'
import { useRouter } from 'vue-router'
import { Search} from '@element-plus/icons-vue'
import jobForm from '~/components/job/jobForm.vue'
import { getJobs, searchJob } from '~/api/mannager'
var jobScheme = {
    "id": "",
    "companyName": "",
    "headCount": 0,
    "positionName": "",
    "info": "",
    "maxSalary": 0,
    "minSalary": 0,
    "remark": "",
    "createAt": "",
    "updateAt": ""
}

var jobs = ref([{}])
const totalItems = ref(0)
const loading = ref(true)
const searchCondition = reactive({
    "minSalary": '',
    "companyName": '',
    "positionName": '',
})
const router = useRouter()


//表格按钮功能 --详情按钮
const handleDetail = (jobId) => {
    router.push('/job/' + jobId)
}




//分页插件相关函数
const handlePageChange = (currentPage) => {
    loading.value = true
    searchJob(searchCondition, 15, currentPage)
        .then((res) => {
            jobs.value = res.data.records
            totalItems.value = res.data.total
        })
        .finally(() => {
            loading.value = false
        })
}



//TODO
//表格格式化相关参数
const UpdateTimeFormatter = (row, column) => {
    return row.updateAt
}

//键盘控制
function onKeyUp(e) {
    if (e.key == "Enter") {
        handlePageChange(1)
    }
}

//添加页面监听
onMounted(() => {
    //准备数据
    searchJob(searchCondition, 15, 1).then((res) => {
        jobs.value = res.data.records
        totalItems.value = res.data.total
    }).finally(() => {
        loading.value = false
    })
    //绑定事件监听
    document.addEventListener("keyup", onKeyUp)
})

//卸载页面监听
onBeforeUnmount(() => {
    document.removeEventListener("keyup", onKeyUp)
})


</script>