<template>
  <div
    v-loading.fullscreen.lock="loading"
    element-loading-text="已提交解析队列，正在解析，请稍后...（预计60s解析完成）"
  >
    <el-upload
      class="upload-demo"
      drag
      :element-loading-spinner="svg"
      :multiple="false"
      action="/ai/analysis"
      :on-success="handleSuccess"
      :before-upload="beforeResumeUpload"
      :limit="1"
      :on-change="handleChange"
      :on-exceed="handleExceed"
      :on-preview="handlePreview"
      :on-error="handleError"
      :show-file-list="false"
    >
      <i class="el-icon-upload"></i>
      <el-icon class="el-input__icon">
        <Upload />
      </el-icon>
      <div class="el-upload__text">
        <span class="font-bold">简历自动解析</span><br />
        将简历文件拖到此处，或<em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">
        只能上传jpg/png、pdf、doc文件，且不超过500kb
      </div>
    </el-upload>

    <!-- TODO -->

    <CVForm :cv="form.resume" :showButton="true"></CVForm>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { toast } from "~/composables/util.js";
import { useRouter } from "vue-router";
import CVForm from "~/components/cv/CVForm.vue";
const loading = ref(false);
/**
 * 定义整个表单的值
 */
let form = reactive({
  resume: {
    id: "",
    fileUrl: "",
    fileName: "",
    name: null,
    highestDegree: null,
    tel: null,
    wechat: null,
    email: null,
    age: null,
    school: null,
    intention: null,
    gender: null,
    avatarUrl: null,
    workingYears: null,
    selfEvaluation: null,
    politicalAppearance: null,
    ethnicity: null,
  },
});

function handleSuccess(response, file, fileList) {
  form.resume = response;
  form.resume.fileName = file.name;
  toast("解析完成！");
}

const handleChange = (ffile, files) => {
  loading.value = !loading.value;
};

/**
 * 校验上传文件格式以及大小等
 * @param {File} file
 */
function beforeResumeUpload(file) {
  console.log(file);
  const isPhoto = file.type === "image/jpeg" || file.type === "image/png";
  const isPdf = file.type === "application/pdf";
  var fileExtension = file.name.substring(file.name.lastIndexOf(".") + 1);
  const isWord =
    fileExtension == "doc" || fileExtension == "docx" || fileExtension == "txt";
  const isLt2M = file.size / 1024 / 1024 < 2;
  const isRightType = isPdf || isPhoto || isWord;

  if (!isRightType) {
    toast("上传简历文件只能是图片、Word或者Pdf格式!", "error");
    file = null;
  }
  if (!isLt2M) {
    toast("上传简历文件大小不能超过 2MB!");
    file = null;
  }
  return isRightType && isLt2M;
}
const handleError = (error, file, files) => {
  toast("上传文件失败！" + error, "error");
  file = null;
};

function handleExceed(files, fileList) {
  toast("请等待解析完成，不要重复上传！", "error");
}

// const embedSrc = reactive({})
function handlePreview(file) {
  console.log(URL.createObjectURL(file));
}

const svg = `
        <path class="path" d="
          M 30 15
          L 28 17
          M 25.61 25.61
          A 15 15, 0, 0, 1, 15 30
          A 15 15, 0, 1, 1, 27.99 7.5
          L 15 15
        " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
      `;
</script>

<style scoped>
.upload-demo {
  @apply my-5;
}
</style>