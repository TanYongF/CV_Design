<template>
    <div>

    </div>
    <el-row :gutter="10">
        <el-col :span="10">
            <el-card>
                <el-table :data="candidates">
                    <el-table-column prop="id" label="用户编号"></el-table-column>
                    <el-table-column prop="name" label="姓名"></el-table-column>
                    <el-table-column prop="name" label="岗位匹配度"></el-table-column>
                    <el-table-column prop="name" label="操作">
                        <el-button type="primary">查看</el-button>
                    </el-table-column>

                </el-table>

            </el-card>

        </el-col>
        <el-col :span="14">
            <el-row>
                <el-col :span="24">
                    <el-card>
                        <template #default="header">
                            基本信息
                        </template>
                    </el-card>

                </el-col>
            </el-row>

            <el-row :gutter="12">
                <el-col :span="12">
                    <el-card>
                        <h1>学历</h1>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card>
                        <h1>工作年限</h1>
                    </el-card>
                </el-col>
            </el-row>

            <el-card>
                <h1>工作年限</h1>
            </el-card>
        </el-col>

    </el-row>

    {{ candidates }}
</template>


<style scope></style>

<script setup>
import { onMounted, ref } from 'vue'
import { proposeJob } from '~/api/mannager'
import { useRoute } from 'vue-router'
const candidates = ref([{}])
const route = useRoute()
onMounted(() => {
    proposeJob(route.params.id, 10, 1).then((res) => {
        // console.log(res.data)
        candidates.value = res.data.records
    })
})

</script>