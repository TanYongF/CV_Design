<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="16" class="my-col">
                <el-card class="pl-4">
                    <template #header>
                        <div class="card-header block mt-3">
                            <div class="font-bold text-30px">{{ job.data.positionName }}</div>
                            <div class="job-info flex items-center">
                                <span>职位ID：{{ job.data.id }}</span>    
                                <span class="lineDivider"></span>
                                <span>创建时间：{{ job.data.createAt }}</span>    
                        
                            </div>
                        </div>
                    </template>
                    <div class="detail-title">职位描述</div>
                    <div class="detail-content">{{ job.data.info }}</div>
                    <div class="detail-title">职位要求</div>
                    <div class="detail-content">{{ job.data.remark }}</div>
                </el-card>
            </el-col>
            <el-col :span="8" class="my-col">
                <el-card>
                    推荐岗位
                </el-card>
            </el-col>

        </el-row>


    </div>
</template>



<script setup>
import { getJob } from '~/api/mannager'
import { reactive, watch, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const job = reactive({
    data:{}
})

onMounted(()=>{
    getJob(route.params.id).then((res)=>{
        job.data = res.data
    })
})

</script>


<style>
.card-header {}

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
    @apply mb-6;
}
.job-info{
    @apply mt-5  text-gray-500 text-13px h-13px;
    line-height: 13px;
}
.lineDivider{
    @apply  mx-2 bg-gray-400 w-0.25 h-13px;
}
</style>