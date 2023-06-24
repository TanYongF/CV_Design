<template>
    <div class="min-h-screen">
        <div class="justify-center items-center">
            <el-form :label-position="labelPosition" label-width="200px" style="max-width: 460px">
                <el-form-item label="用户名">
                    <el-input v-model="formLabelAlign.username" />
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="formLabelAlign.password" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit" :loading="loading">注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
  
<script setup>
import { reactive, ref } from 'vue'
import { register } from "~/api/mannager";
import { toast } from '~/composables/util';
import { useRouter } from "vue-router";
const loading = ref(false)
const labelPosition = ref("right")
const formLabelAlign = reactive({
    username: '',
    password: ''
})

const router = useRouter()
const onSubmit = () => {
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
}

</script>