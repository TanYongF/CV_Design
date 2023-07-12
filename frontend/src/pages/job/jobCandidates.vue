<template>
    <div v-cloak>
        <div v-if="candidates.length == 0">
            <el-empty description="该岗位暂无候选人推荐" :image-size="screenHeight">
                <el-button type="primary" @click="$router.push('/job/my')">返回</el-button>
            </el-empty>
        </div>
        <div v-else v-loading="loading">
            <el-row justify="center">
                <el-col :span="24">
                    <el-card>
                        <el-table stripe
                        :cell-style="setCellStyle" 
                        :cell-class-name = "setCellClassName"
                        :data="candidates" size="large">
                            <el-table-column prop="key.id" label="用户编号" width="210px"></el-table-column>
                            <el-table-column prop="key.name" label="候选人姓名" width="150px"></el-table-column>
                            <el-table-column prop="key.highestDegree" width="100px" :formatter="highestDegreeFormatter"
                                label="最高学历"> </el-table-column>
                            <el-table-column prop="key.age" label="年龄" width="100px"
                                :formatter="(row, column, cellValue, index) => cellValue + '岁'"></el-table-column>

                            <el-table-column prop="key.workingYears" width="100px" :formatter="workingYearsFormatter"
                                label="工作年限"></el-table-column>
                            <el-table-column prop="key.intention" label="工作意向"></el-table-column>
                            <el-table-column prop="key.email" label="邮箱"> </el-table-column>
                            <el-table-column prop="value" label="岗位匹配度" :formatter="similarityFormatter">
                            </el-table-column>
                            <el-table-column label="操作">
                                <template #default="scope">
                                    <el-button size="large" type="primary"
                                        @click="handleView(scope.$index, scope.row)">查看</el-button>
                                </template>
                            </el-table-column>

                        </el-table>
                    </el-card>
                </el-col>
            </el-row>

        </div>
        <el-dialog v-model="dialogVisible" title="简历查看" width="45%" class="dialog-bg">
            <template #header>
                <div style="font-family: 'JMH'; padding-bottom: 0px;" class="text-2xl">简历查看</div>
            </template>
            <div class="rounded-3xl p-4 form-bg mx-4">
                <CVForm :cv="detail.cv" :showButton="false" :isDisabled="true"></CVForm>
            </div>


        </el-dialog>
    </div>
</template>



<style scope>
el-card {
    @apply my-3;
}

.container {
    @apply
}

[v-cloak] {
    display: none !important;
}
</style>




<script setup>
import { onMounted, ref, reactive } from 'vue'
import { proposeJob, getCvById, getTagByResumeId } from '~/api/mannager'
import { useRoute } from 'vue-router'
import CVForm from '~/components/cv/CVForm.vue'

const candidates = ref([{}])
const route = useRoute()
const loading = ref(true)
const showDetails = ref(true);
const dialogVisible = ref(false)
onMounted(() => {
    proposeJob(route.params.id, 10, 1).then((res) => {

        candidates.value = res.data
        loading.value = false
    })
})



//表格格式化
const workingYearsFormatter = (row, column, cellValue, index) => {
    // console.log(cellValue)
    return cellValue + "年"
}
const similarityFormatter = (row, column, cellValue, index) => {
    const percentage = (cellValue * 100).toFixed(2);
    return `${percentage}%`;
}
const highestDegreeFormatter = (row, column, cellValue, index) => {
    switch (cellValue) {
        case 0: return "博士"
        case 1: return "硕士"
        case 2: return "本科"
        case 3: return "大专"
        case 4: return "中专"
        case 5: return "高中"
        case 6: return "初中"
        case 7: return "小学"
        case 8: return "其他"
    }
}
const setCellStyle = ({row, column, rowIndex, columnIndex})=>{
    if(column.label == "岗位匹配度"){
        const styleJson = { 
            'font-family':'DIN',
            'font-size':'21px',
            'color': '#00000'
        }
        
        if(candidates.value[rowIndex].value > 0.8){
            styleJson['color'] = 'red'
        }
        console.log(styleJson);
       

        return styleJson;

    }else{
        return ""
    }
}

const setCellClassName = ({row, column, rowIndex, columnIndex})=>{
    if(column.label == "岗位匹配度"){
        return ""
   }else{
       return ""
   }
}

//表格按钮
const detail = reactive({
    "cv": {}
})
const handleView = (index, row) => {
    const curId = candidates.value[index].key.id
    dialogVisible.value = true
    getCvById(curId).then((res1) => {
        detail.cv = res1.data
        getTagByResumeId(curId).then((res2) => {
            detail.cv.tags = res2.data


        })
    })
}
</script>