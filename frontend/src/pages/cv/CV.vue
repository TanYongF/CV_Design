<template>
    <el-row :gutter = "20" class="flex">

        <el-col :lg="8" :md = "12" :sm ="24" v-for="cvItem in cvss.records" :key = "cvItem.id" class="flex justify-center items-center">     
            <el-skeleton style="width: 240px" :loading="loading" animated >
                <template #template>
                    <el-skeleton-item variant="image" style="width: 400px; height: 267px" />
                    <div style="padding: 14px">
                        <div class="flex"></div>
                        <el-skeleton-item variant="h3" style="width: 50%" />
                        <el-skeleton-item variant="text" />
                        <div
                            style="
                            display: flex;
                            align-items: center;
                            justify-items: space-between;
                            margin-top: 16px;
                            height: 16px;
                            "
                        >
                            <el-skeleton-item variant="text" style="width: 30%" />
                        </div>
                    </div>
                </template>
                <template #default>
                    <resumeCard :cv = "cvItem"></resumeCard>
                </template>
            </el-skeleton> 
        </el-col>
    </el-row>
</template>


<script setup>

import {onMounted, ref, reactive} from 'vue'
import {getCvs} from '~/api/mannager'
import resumeCard from '~/components/cv/resumeCard.vue'

const loading = ref(true)

const cvss = reactive({
    records: [],
})

onMounted(()=>{
    getCvs().then(res=>{
        cvss.records = res.data.records
        loading.value = false;
    })
   

})


</script>

<style scoped>
.upload-demo{
    @apply my-5;
}
</style>