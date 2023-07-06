<template>
    <el-upload
        class="upload-demo"
        drag
        :multiple="false"
        action="/api/cv/upload"
        :on-success="handleSuccess"
        :before-upload="beforeResumeUpload"
        :limit=1
        :on-exceed="handleExceed"
        :on-preview="handlePreview"> 
            <i class="el-icon-upload"></i>
            <el-icon class="el-input__icon">
                    <Upload />
            </el-icon>
            <div class="el-upload__text"><span class="font-bold">简历自动解析</span><br> 将简历文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png、pdf、doc文件，且不超过500kb</div>
    </el-upload>


    <div></div>


    <!-- TODO -->
    <embed
    v-if="embedSrc"
    type="video/webm"
    :src="embedSrc"
    width="100%"
    style="max-height: 50rem; min-height: 20rem"
    />
    <CVForm :cv = "form.resume" :showButton = "true"></CVForm>
</template>

<script setup>

import { ref, reactive} from 'vue'
import {toast} from '~/composables/util.js'
import { useRouter } from 'vue-router'
import CVForm from '~/components/cv/CVForm.vue'

/**
 * 定义整个表单的值
 */
let form = reactive({
    resume: {
        "id": "",
        "fileUrl": "",
        "fileName": "",
        "name": null,
        "highestDegree": null,
        "tel": null,
        "wechat": null,
        "email": null,
        "age": null,
        "school": null,
        "intention": null,
        "gender": null,
        "avatarUrl": null,
        "workingYears": null,
        "uploadUser": "jzwgyhbxke",
        "selfEvaluation": null,
        "politicalAppearance": null,
        "ethnicity": null,
        "createAt": "",
        "updateAt": ""
    }
})


function handleSuccess(response, file, fileList){
    form.resume = response.data
}

/**
 * 校验上传文件格式以及大小等
 * @param {File} file 
 */
function beforeResumeUpload(file){
    console.log(file)
    const isPhoto = (file.type === 'image/jpeg' || file.type === 'image/png');
    const isPdf = (file.type === 'application/pdf')
    var fileExtension = file.name.substring(file.name.lastIndexOf('.') + 1);
    const isWord = (fileExtension == 'doc' || fileExtension == 'docx')
    const isLt2M = file.size / 1024 / 1024 < 2;
    const isRightType = (isPdf || isPhoto || isWord)
    
    if (!isRightType) {
        toast('上传简历文件只能是图片、Word或者Pdf格式!', "error")
    }
    if (!isLt2M) {
        toast('上传简历文件大小不能超过 2MB!')
    }
    return isRightType && isLt2M;
}


function handleExceed(files, fileList){
    toast('请等待解析完成，不要重复上传！', 'error')
}


// const embedSrc = reactive({})
function handlePreview(file){
     console.log(URL.createObjectURL(file))
}
</script>

<style scoped>
.upload-demo{
    @apply my-5;
}
</style>