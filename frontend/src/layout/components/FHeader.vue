<template>
    <div class="f-header">
        <span class="logo">
            <el-icon class="mr-2" @click="$router.push('/')"><eleme-filled /></el-icon>
            简历分析系统
        </span>
        <el-icon class="icon-btn" @click="$store.commit('handleAsideWidth')">
            <Fold v-if = "$store.state.asideWidth == '250px'"/>
            <Expand v-else />
        </el-icon>
        <el-tooltip effect="dark" content="刷新页面" placement="bottom">
            <el-icon class="icon-btn" @click="refresh">
                <Refresh />
            </el-icon>
        </el-tooltip>
        <div class="ml-auto flex items-center">
            <el-icon class="icon-btn" @click="toggle">
                <FullScreen v-if="!isFullscreen" />
                <Aim v-else/>
            </el-icon>
            <el-dropdown class="dropdown" @command="handleCommand">
                <span class="el-dropdown-link flex items-center text-light-50">
                    <el-avatar class=" mr-2" :size="30"
                        :src="$store.state.user.avatar" />
                    {{ $store.state.user.username }}
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="about">修改个人信息</el-dropdown-item>
                        <el-dropdown-item command="logout">注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>

    </div>
</template>

<script setup>
import { ArrowDown, Expand } from '@element-plus/icons-vue'
import { showModal, toast } from "~/composables/util"
import { useFullscreen } from '@vueuse/core'
import { useLogout, useAbout } from "~/composables/useMannager"
import { useRouter } from "vue-router";
const { isFullscreen, toggle} = useFullscreen()
const router = useRouter()

const{
     handleLogout 
} = useLogout()

const{
    handleAbout
} = useAbout()


const handleCommand = (c) => {
    switch (c) {
        case "logout":
            handleLogout();
            break;
        case "about":
            handleAbout();
            break;
            
    }
}

const refresh = ()=>{
    location.reload()
}

</script>
<style scoped>
@import '~/assets/fonts/fonts.css';
.f-header {
    @apply flex items-center z-1 bg-indigo-600 text-light-50 fixed top-0 right-0 left-0;
    height: 64px;
}

.logo {
    width: 250px;
    @apply flex justify-center items-center text-xl font-thin;
    font-family: 'JDGY'
}

.icon-btn {
    @apply flex justify-center items-center;
    width: 42px;
    height: 64px;
    cursor: pointer;
}
.icon-btn:hover{
    @apply bg-indigo-500
}
.f-header .dropdown {
    height: 64px;
    cursor: pointer;
    @apply flex justify-center items-center mx-5 border-0;
}
</style>
