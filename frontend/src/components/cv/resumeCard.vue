<template>
    <el-card class="box-card" :body-style="{padding: '0px'}" shadow="hover">
        <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image" @click="dialogVisible = true"
        />
        <div style="padding: 12px" class="flex">
            <span class="font-bold mr-2" >{{ cv.fileName }} </span>
            <span class="ml-auto"><el-tag>{{ cv.intention ? cv.intention : '未填写' }}</el-tag>
            </span>
      
        </div>

          <div class="bottom flex justify-center items-center">
            <span class="ml-3">创建于 {{ transferDatestamp(cv.createAt)}}</span>
            <el-button class="ml-auto" type="danger" size = "small" @click="onDelete">删除简历</el-button>
            <el-button class="ml-auto" type="primary" size="small" @click="dialogVisible = true">查看详情</el-button>
          </div>

          <template #footer>
            
          </template>
    </el-card>


    <el-dialog
      v-model ="dialogVisible"
      title="简历查看"
      width="45%"
      :before-close="handleClose"
      @open = "onOpen"
      class="dialog-bg"
      >
      <template #header>
        <div style="font-family: 'JMH'; padding-bottom: 0px;" class="text-2xl">简历查看</div>
    
      </template>
        <div class="rounded-3xl p-4 form-bg mx-4">
          <CVForm :cv = newCV.cv :showButton = "false"></CVForm>
        </div>
       
      
      
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false"  type="danger" :disabled="btnDisable">取消更新</el-button>
          <el-button type="primary" @click="onSubmit" :disabled = "btnDisable">
            确认更新 <el-icon class="el-icon--right"><Upload /></el-icon>
          </el-button>
        </span>
      </template>
    </el-dialog>

  </template>
  
<style>
@import '~/assets/fonts/fonts.css';
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}


.item {
    margin-bottom: 1px;
}

.box-card {
    width: 400px;

}

.dialog-bg{
  background: #edc0bf;
  background: linear-gradient(90deg, #edc0bf 0,#c4caef 58%);
}
.form-bg{
  	/* other styles */
	background: rgba(255, 255, 255, .7);
	-webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px);
}
.image {
  width: 100%;
  display: block;
}

.el-dialog{
  @apply rounded-2xl;
}

.bottom{
  @apply my-1 mx-3;
}

</style>

<script setup>
import { ref, reactive} from 'vue'
import {transferDatestamp, toast} from '~/composables/util'
import {showModal} from  '~/composables/util.js'
import CVForm from '~/components/cv/CVForm.vue'
import { postCV, deleteCV } from '~/api/mannager'
import { useRouter } from "vue-router";
const router = useRouter();
const props = defineProps(['cv'])
const loading = ref(false)
const dialogVisible = ref(false)
const btnDisable = ref(false)


const handleClose = (done) => {
  showModal('注意，取消后已进行的变更将丢失！')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}


//todo
const newCV = reactive({
  cv: {}
})
const onOpen = ()=>{
   Object.assign(newCV.cv, props.cv);
   newCV.cv.name = props.cv.name
  
    
}
const onDelete= (cvId)=>{
  loading.value = true
  deleteCV(props.cv.id)
    .then((res)=>{
      toast("简历删除成功！")
      location.reload()
    })
    .finally(()=>{
      loading.value = false
    })
}

const onSubmit = () => {
    btnDisable.value = true;
    loading.value = true
    postCV(newCV.cv)
        .then((res)=>{
            toast("更新简历成功！")
            Object.assign(props.cv, newCV.cv);
            dialogVisible.value = false
            // router.push("/cvs")
        })
        .finally(()=>{

          btnDisable.value = false
        })

}



</script>