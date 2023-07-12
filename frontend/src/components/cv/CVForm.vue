<template>
    <el-form :model="cv" :disabled="isDisabled" labe="right" label-width="auto" size="large" class="mx-auto" :rules="rules"
        style="width:95%" ref="formRef">
        <div class="flex justify-center items-center">
            <el-upload class="avatar-uploader" action="/api/user/avatar" :disabled="isDisabled" :show-file-list="false"
                :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :on-error="handleAvatarError">
                <img v-if="cv.avatarUrl" :src="cv.avatarUrl" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon">
                    <Plus />
                </el-icon>
            </el-upload>
        </div>
        <el-form-item label="简历名称" required prop="fileName">
            <el-input v-model="cv.fileName" placeholder="简历名称"></el-input>
        </el-form-item>
        <el-form-item label="姓名" required prop="name">
            <el-input v-model="cv.name" placeholder="真实姓名"></el-input>
        </el-form-item>
        <el-form-item label="基本信息">
            <el-col :span="6">
                <el-form-item class="inline-form" prop="gender" required>
                    <el-select v-model="cv.gender" placeholder="您的性别" class="w-full">
                        <el-option label="男" :value="true"></el-option>
                        <el-option label="女" :value="false"></el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="1" class="inline-form justify-center items-center flex"><span class="text-gray-300">-</span></el-col>
            <el-col :span="8">
                <el-form-item class="inline-form" prop="highestDegree" required>
                    <el-select v-model="cv.highestDegree" placeholder="请选择最高学历" class="w-full">
                        <el-option label="博士" :value="0"></el-option>
                        <el-option label="硕士" :value="1"></el-option>
                        <el-option label="本科" :value="2"></el-option>
                        <el-option label="大专" :value="3"></el-option>
                        <el-option label="中专" :value="4"></el-option>
                        <el-option label="高中" :value="5"></el-option>
                        <el-option label="初中" :value="6"></el-option>
                        <el-option label="小学" :value="7"></el-option>
                        <el-option label="其他" :value="8"></el-option>
                    </el-select>
                </el-form-item>

            </el-col>
            <el-col :span="1" class="inline-form justify-center items-center flex"><span class="text-gray-300">-</span></el-col>
            <el-col :span="8"  >
                <el-form-item prop="politicalAppearance" required class="inline-form">
                    <el-select v-model="cv.politicalAppearance" placeholder="请选择政治面貌" class="w-full">
                        <el-option label="中共党员" :value="0"></el-option>
                        <el-option label="中共预备党员" :value="1"></el-option>
                        <el-option label="共青团员" :value="2"></el-option>
                        <el-option label="其他民主党派" :value="3"></el-option>
                        <el-option label="群众" :value="4"></el-option>
                    </el-select>
                </el-form-item>
            </el-col> 
        </el-form-item>

        <el-form-item label="最后毕业学校" required prop="school">
            <el-input v-model="cv.school" placeholder="一般情况下，这里是你最高学历就读学校"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" required prop="tel">
            <el-input v-model="cv.tel"></el-input>
        </el-form-item>
        <el-form-item label="求职意向" required prop="intention">
            <el-input v-model="cv.intention"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址" required prop="email">
            <el-input v-model="cv.email"></el-input>
        </el-form-item>
        <el-form-item label="微信号">
            <el-input v-model="cv.wechat"></el-input>
        </el-form-item>
        <el-form-item label="民族">
            <el-input v-model="cv.ethnicity"></el-input>
        </el-form-item>
        <el-form-item label="自我介绍" >
            <el-input v-model="cv.selfEvaluation" type="textarea" placeholder="个人简介，包括兴趣等等"></el-input>
        </el-form-item>
        <el-form-item label="标签">
            <el-tag v-for="tag in cv.tags" :key="tag.id" class="mx-1" effect="dark">
                {{ tag.name }}
            </el-tag>
        </el-form-item>
    </el-form>
    <div class="flex justify-center items-center" :class="{ hidden: !showButton }">
        <el-button type="primary" @click="onSubmit" size="large" :loading="loading" round>
            保存简历</el-button>
    </div>
</template>


<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;

}
.inline-form{
    @apply mb-0 !important;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    @apply mb-3;
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 178px;
    text-align: center;
}
</style>

<script setup>
import { ref, reactive } from 'vue'
import { toast } from '~/composables/util.js'
import { postCV } from '~/api/mannager'
import { useRouter } from "vue-router";
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
const props = defineProps(['cv', 'showButton', 'isDisabled'])
const router = useRouter()
const loading = ref(false);
const formRef = ref(null);
const rules = {
    fileName: [
        { required: true, message: "简历名称不能为空", trigger: "blur" },
        { min: 3, max: 15, message: "简历名称必须是3-15个字符", trigger: "blur" },
    ],
    name: [
        {
            required: true,
            message: "姓名不能为空",
            trigger: "blur",
        },
    ],
    school: [
        { required: true, trigger: "blur" }

    ],
    intention: [
        { required: true, trigger: 'blur' }
    ],
    email: [
        { required: true, trigger: 'blur' }
    ],
    tel: [
        { required: true, trigger: 'blur' }
    ],
};

/**
 * 简历提交函数
 */
const onSubmit = () => {
    formRef.value.validate((valid) => {
        if (!valid) {
            return false;
        }
        loading.value = true
        postCV(props.cv)
            .then((res) => {
                toast("添加简历成功！")
                router.push('/cvs')
            })
            .finally(() => {
                loading.value = false
            })


    })

}

/**
 * 校验上传文件格式以及大小等
 * @param {File} file 
 */
const beforeAvatarUpload = (file) => {
    console.log(file)
    const isPhoto = (file.type === 'image/jpeg' || file.type === 'image/png');
    var fileExtension = file.name.substring(file.name.lastIndexOf('.') + 1);
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isPhoto) {
        toast('上传简历文件只能是png、jpg格式!', "error")
    }
    if (!isLt2M) {
        toast('上传简历文件大小不能超过 2MB!', 'error')
    }
    return isPhoto && isLt2M
}
/**
 * 上传头像成功逻辑
 * @param {Response} response 
 * @param {*} file 
 * @param {*} fileList 
 */
const handleAvatarSuccess = (response, file, fileList) => {
    props.cv.avatarUrl = response.data
    toast("照片上传成功！")
}
const handleAvatarError = (err) => {
    toast(err, "error")
}


</script>
