<template>
    <div>
        <div class="detail-title ">
            基本信息
        </div>
        <el-card shadow="never">
            <el-row :gutter="15">
                <!-- card body -->

                <el-col :lg="3" :md="24" style="margin-bottom:0px!important">
                    <div class="flex justify-center items-center"><el-avatar :size="100" :src=avatarUrl></el-avatar></div>
                </el-col>
                <el-col :lg="20" :md="24" style="margin-bottom:0px!important">
                    <el-descriptions>
                        <el-descriptions-item label="用户名">{{ $store.state.user.username }}</el-descriptions-item>
                        <el-descriptions-item label="手机号"> {{ $store.state.user.phone }}</el-descriptions-item>
                        <el-descriptions-item label="昵称"> {{ $store.state.user.nickname }} </el-descriptions-item>
                        <el-descriptions-item label="性别">{{ $store.state.user.gender ? "男" : "女" }}</el-descriptions-item>
                        <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                        <el-descriptions-item label="备注">
                            <el-tag size="small">学校</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                        <el-descriptions-item label="身份">
                            <el-tag>
                                {{ $store.state.user.role == 1 ? '企业用户' : '普通用户' }}
                            </el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="">
                            <a class="text-blue-500 cursor-pointer" @click="dialogVisible = true">修改个人信息</a>
                        </el-descriptions-item>
                    </el-descriptions>
                </el-col>


            </el-row>
        </el-card>
        <!-- 统计开始 -->
        <div class="detail-title ">
            简历统计
        </div>
        <el-row :gutter="20">
            <el-col :lg="6" :md="24"  :sm="24" :offset="0">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex">
                            <span> 简历总数 </span>
                            <span class="ml-auto">
                                <el-tag size="normal" effect="light">全部</el-tag>
                            </span>
                        </div>
                    </template>
                    <div class="card-count">
                        <CountTo :value="statistics.value.resumeCount">
                        </CountTo>

                    </div>
                    <!-- card body -->
                </el-card>

            </el-col>
            <el-col :lg="6" :md="24"  :sm="24" :offset="0">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex">
                            <span> 用户总数 </span>
                            <span class="ml-auto">
                                <el-tag type="danger" size="normal" effect="light">全部</el-tag>
                            </span>
                        </div>
                    </template>
                    <div class="card-count">
                        <CountTo :value="statistics.value.userCount"></CountTo>

                    </div>

                </el-card>
            </el-col>
            <el-col :lg="6" :md="24"  :sm="24" :offset="0">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex">
                            <span> 标签总数 </span>
                            <span class="ml-auto">
                                <el-tag type="warning" size="normal" effect="light">全部</el-tag>
                            </span>
                        </div>
                    </template>
                    <div class="card-count">
                        <CountTo :value="statistics.value.tagCount"></CountTo>

                    </div>

                </el-card>
            </el-col>
            <el-col :lg="6" :md="24"  :sm="24" :offset="0">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex">
                            <span> 岗位总数 </span>
                            <span class="ml-auto">
                                <el-tag type="success" size="normal" effect="light">全部</el-tag>
                            </span>
                        </div>
                    </template>
                    <div class="card-count">
                        <CountTo :value="statistics.value.jobCount"></CountTo>
                    </div>

                </el-card>
            </el-col>
        </el-row>



        <!--  简历分析开始  -->
        <div class="detail-title ">
            简历分析
        </div>
        <el-row :gutter="15">
            <el-col :lg="8" :md="12" :sm="24" :offset="0">
                <IndexChart :chartData="statistics.value.ages" v-if="flag" :chartId="123" chartTitle="年龄分布扇形图"></IndexChart>
            </el-col>

            <el-col :lg="8" :md="12" :sm="24" :offset="0">
                <IndexChart :chartData="statistics.value.schools" v-if="flag" :chartId="124" chartTitle="学校分布扇形图">
                </IndexChart>
            </el-col>

            <el-col :lg="8" :md="12" :sm="24" :offset="0">
                <IndexChart :chartData="statistics.value.genders" v-if="flag" :chartId="125" chartTitle="性别分布扇形图">
                </IndexChart>
            </el-col>



        </el-row>



    </div>



    <!-- 更改个人信息表单开始 -->
    <el-dialog v-model="dialogVisible" title="岗位查看" width="45%" @open="onOpen" class="dialog-bg">
        <template #header>
            <div style="font-family: 'JMH'; padding-bottom: 0px;" class="text-2xl">信息修改</div>
        </template>
        <div class="rounded-3xl p-4 form-bg mx-4">
            <!-- <CVForm :cv=newCV.cv :showButton="false"></CVForm> -->
            <infoForm :info="newInfo.info"></infoForm>
        </div>
    </el-dialog>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { useStore } from "vuex";
import infoForm from '~/components/user/infoForm.vue';
import IndexChart from "~/components/IndexChart.vue";
import { getStatistics } from "~/api";
import CountTo from "~/components/CountTo.vue";


const statistics = reactive({
    value: ''
})
const flag = ref(false)
const store = useStore();
const dialogVisible = ref(false)
let avatarUrl = ref(store.state.user.avatar == null ? "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" : store.state.user.avatar);



//更改表单相关
const closeDialog = () => {
    dialogVisible.value = false
}
const newInfo = reactive({
    info: {}
})
const onOpen = () => {
    Object.assign(newInfo.info, store.state.user);
}


onMounted(() => {
    //加载核心数据
    getStatistics().then((res) => {
        statistics.value = res.data
        flag.value = true

    })
})


</script>

<style scoped>
.segamentTitle {
    @apply text-xl font-bold mb-5 border-l-5 border-indigo-500 pl-2;
}

.progressInfo {
    @apply ml-6;
}

.el-col {
    @apply mb-5;
}

.card-count {
    @apply flex justify-center items-center h-90px text-6xl;
    font-family: "DIN"
}

.el-card__header {
    @apply border-indigo-500;
}

.el-card {
    @apply border-0;
}
</style>