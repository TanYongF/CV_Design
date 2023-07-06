<template>
    <div class="detail-title ">
        基本信息
    </div>
    
    <el-row :gutter="15">
        <el-col :lg = "2" :md = "24">
            <div class="flex justify-center items-center"><el-avatar :size="100" :src=avatarUrl></el-avatar></div>
        </el-col>

        <el-col :lg = "20" :md = "24">
            <el-descriptions>
                <el-descriptions-item label="用户名">{{ $store.state.user.username }}</el-descriptions-item>
                <el-descriptions-item label="手机号"> {{ $store.state.user.phone }}</el-descriptions-item>
                <el-descriptions-item label="昵称"> {{ $store.state.user.nickname }} </el-descriptions-item>
                <el-descriptions-item label="性别">{{ $store.state.user.gender ? "男": "女"}}</el-descriptions-item>
                <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                <el-descriptions-item label="备注">
                <el-tag size="small">学校</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                <el-descriptions-item label="身份"> {{ $store.state.user.role == 1 ? '企业用户' : '普通用户' }}</el-descriptions-item>
                <el-descriptions-item label="">
                    <a class="text-blue-500">修改个人信息</a>
                </el-descriptions-item> 
            </el-descriptions>


        </el-col>

    
    </el-row>


    <!--  简历分析开始  -->
    <div class="detail-title ">
        简历分析
    </div>
    <el-row :gutter = "15">
        <el-col :lg = "8" :md = "24">
            <el-card class="box-card" shadow="hover">
                <div class="flex justify-center items-center">
                    <el-progress type="circle" :percentage="20" ></el-progress>
                    <span class="progressInfo">这里是veuili</span>
                </div>
            </el-card>  
        </el-col>
        <el-col :lg = "8" :md = "24">
            <el-card class="box-card" shadow="hover">
                <div class="flex justify-center items-center">
                    <el-progress type="circle" :percentage="20" ></el-progress>
                    <span class="progressInfo">这里是veuili</span>
                </div>
            </el-card>  
        </el-col>
        <el-col :lg = "8" :md = "24"  >
            <el-card class="box-card" shadow="hover">
                <div class="flex justify-center items-center">
                    <el-progress type="circle" :percentage="20" ></el-progress>
                    <span class="progressInfo">这里是veuili</span>
                </div>
            </el-card>  
        </el-col>
    </el-row>


    <div class="detail-title ">
        用户标签
    </div>
    <el-row>
        <el-col :span = "12">
            <el-tag>标签一</el-tag>
            <el-tag type="success">标签二</el-tag>
            <el-tag type="info">标签三</el-tag>
            <el-tag type="warning">标签四</el-tag>
            <el-tag type="danger">标签五</el-tag>
        </el-col>
    </el-row>


    <div class="detail-title ">
        行业竞争力
    </div>
    <el-row>
        <el-col :span = "12">
            <el-progress type="circle" :percentage="0"></el-progress>
            <el-progress type="circle" :percentage="25"></el-progress>
            <el-progress type="circle" :percentage="100" status="success"></el-progress>
            <el-progress type="circle" :percentage="70" status="warning"></el-progress>
            <el-progress type="circle" :percentage="50" status="exception"></el-progress></el-col>
        </el-row>




</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { useStore } from "vuex";
const store = useStore();
let avatarUrl = ref(store.state.user.avatar == null ? "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" : store.state.user.avatar);

</script>

<style>
.segamentTitle{
    @apply text-xl font-bold mb-5 border-l-5 border-indigo-500 pl-2;
}

.progressInfo{
    @apply ml-6;
}

.el-col{
    @apply mb-5;
}

</style>