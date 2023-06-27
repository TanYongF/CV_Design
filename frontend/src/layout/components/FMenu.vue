<template>
    <div class="sidebar" :style="{ width:$store.state.asideWidth }">
        <el-menu router="true" :unique-opened="true" :default-active="defaultActive == '/about' ? '/' : defaultActive"  :collapse="$store.state.asideWidth == '64px'" :collapse-transition="false" class="el-menu-vertical-demo border-0">
            <el-menu-item index="/">
                <el-icon><Odometer /></el-icon>
                <span>首页</span>
            </el-menu-item>
            <el-menu-item index = "/cvs">
                        <el-icon>
                            <notebook />
                        </el-icon>
                        <span>简历管理</span>
            </el-menu-item>
            <!-- <el-sub-menu index="/">
                <template #title>
                    <el-icon>
                        <notebook />
                    </el-icon>
                    <span>简历管理</span>
                </template>
                <el-menu-item-group title="Group One">
                    <el-menu-item index="/test1-1">item one</el-menu-item>
                    <el-menu-item index="/test1-2">item two</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                    <el-menu-item index="1-3">item three</el-menu-item>
                </el-menu-item-group>
                <el-sub-menu index="1-4">
                    <template #title>item four</template>
                    <el-menu-item index="1-4-1">item one</el-menu-item>
                </el-sub-menu>
            </el-sub-menu> -->
            <el-menu-item index="/jobs">
                <el-icon><house /></el-icon>
                <span>岗位管理</span>
            </el-menu-item>
            <el-menu-item index="/peoples">
                <el-icon><Avatar /></el-icon>
                <span>人才匹配</span>
            </el-menu-item>
            <el-menu-item index="/tags">
                <el-icon><CollectionTag /></el-icon>
                <span>标签管理</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>
  
<script setup>
import {
    Document,
    Menu as IconMenu,
    Setting,
} from '@element-plus/icons-vue'
import {ref} from "vue"
import {useRouter, useRoute} from "vue-router"
const route = useRoute()
const defaultActive = ref(route.path)








</script>

<style scoped>
.sidebar {
    overflow: auto;
    transition: all 0.2s;
    overflow-y: auto;
    overflow-x: hidden;
    @apply shadow-md text-light-50 fixed top-64px left-0 min-h-screen;
}

el-menu-item {
    @apply bg-indigo-500 text-light-50;
}

.menu-item {
    @apply bg-indigo-500 text-light-50;
}
</style>