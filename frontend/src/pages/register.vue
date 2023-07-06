<template>
    <div class="min-h-screen">
        <el-row class="h-auto">
            <el-col :span = "24">
                <div class="flex items-center justify-center my-5">
                    <span class="line"></span>
                    <span class="title">账户注册</span>
                    <span class="line"></span>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span = "24">
                <div class="flex items-center justify-center">
                    <el-form :rules="rules" :model="formLabelAlign" ref="formRef" class="w-[250px]">
                        <el-form-item prop="username">
                            <el-input v-model="formLabelAlign.username" placeholder="请输入用户名">
                                <template #prefix>
                                    <el-icon class="el-input__icon">
                                    <User />
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input v-model="formLabelAlign.password" type="password" placeholder="请输入密码">
                                <template #prefix>
                                    <el-icon class="el-input__icon">
                                    <Key />
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="是否注册为企业用户">
                            <el-switch  v-model="formLabelAlign.isAdmin"></el-switch>
                        </el-form-item>
                        <el-form-item>
                            <el-button  class="w-[250px]"  type="primary" @click="onSubmit" :loading="loading">注册</el-button>
                        </el-form-item>
                    </el-form>
            </div>
            </el-col>

        </el-row>


    </div>
</template>
  
<script setup>
import { reactive, ref } from 'vue'
import { register } from "~/api/mannager";
import { toast } from '~/composables/util';
import { useRouter } from "vue-router";
const loading = ref(false)
const formRef = ref(null);
const formLabelAlign = reactive({
    username: '',
    password: '',
    idAdmin: ''
})


const rules = {
  username: [
    { required: true, message: "用户名不能为空", trigger: "blur" },
    { min: 3, max: 20, message: "用户名长度必须是3-20个字符", trigger: "blur" },
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: "blur",
    },
  ],
};


const router = useRouter()
const onSubmit = () => {
    formRef.value.validate((valid) => {
        loading.value = true
        register(formLabelAlign)
        .then((res => {
            toast("恭喜你，注册成功！正在跳转到登录页...")
            setTimeout(function () {
                location.reload()
            }, 1000);

        }))
        .finally(() => {
            loading.value = false
        })
    })
}

</script>

<style>
.el-form-item{
    margin-bottom: 18px !important
}
.title{
    @apply text-5xl;
}
</style>
