<template>
    <el-container>
        <el-header>
            <f-header></f-header>
        </el-header>
        <el-container>
            <el-aside :width="$store.state.asideWidth">
                <f-menu></f-menu>
            </el-aside>
            
            <el-main class="z-0">
                <!-- <f-tag-list></f-tag-list> -->
                <router-view></router-view>
            </el-main>
        

        </el-container>
    </el-container>
</template>

<script setup>
    import FHeader from './components/FHeader.vue';
    import FMenu from './components/FMenu.vue';
    import FTagList from './components/FTagList.vue';
</script>   

<style scoped>
.el-aside{
    transition: all 0.2s;
}




</style>

<style>
@import '~/assets/fonts/fonts.css';
</style>


