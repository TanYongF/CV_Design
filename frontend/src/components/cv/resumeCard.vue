<template>
    <el-card class="box-card" :body-style="{padding: '0px'}" shadow="hover">
        <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image"
        />
        <div style="padding: 14px" class="flex">
            <span class="font-bold mr-2" >{{ cv.name }} </span>
            
            <span class="ml-auto"><el-tag>{{ cv.intention }}</el-tag>
            </span>
      
        </div>
        
        <div class="card-header">     
        </div>
          <div class="bottom flex">
            <span class="ml-3">创建于 {{ transferDatestamp(cv.createAt)}}</span>
            <el-button class="button ml-auto " text>查看详情</el-button>
          </div>
    </el-card>
    

  </template>
  
<style>
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    width: 400px;
}


.image {
  width: 100%;
  display: block;
}


</style>

<script setup>
import {transferDatestamp} from '~/composables/util';
const props = defineProps(['cv'])

</script>