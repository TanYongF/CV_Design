<template>
  <div>
    <el-row class="login-container">
      <el-col :lg="16" :md="12" class="left">
        <div>
          <div>您好</div>
          <div>这里是简历分析网站</div>
        </div>
      </el-col>
      <el-col :lg="8" :md="12" class="right">
        <h2 class="title">欢迎回来</h2>
        <div class="flex items-center justify-center my-5 text-gray-300">
          <span class="line"></span>
          <span>账户密码登录</span>
          <span class="line"></span>
        </div>
        <el-form :model="form" :rules="rules" ref="formRef" class="w-[250px]">
          <el-form-item prop="username">
            <el-input v-model="form.username" placeholder="输入用户名" type="password" show-password>
              <template #prefix>
                <el-icon class="el-input__icon">
                  <User />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input v-model="form.password" placeholder="输入密码">
              <template #prefix>
                <el-icon class="el-input__icon">
                  <Key />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button round color="#626aef" class="w-[250px]" type="primary" @click="onSubmit" :loading="loading">
              登 录
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button round color="#626aef" class="w-[250px]" type="primary" @click="drawer = true">
              注 册
            </el-button>
            <el-drawer v-model="drawer" size = '100%' direction='btt'
              :before-close="handleClose">

              <register></register>
            </el-drawer>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { login, getUserById} from "~/api/mannager";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { toast } from '~/composables/util';
import { setToken } from '~/composables/auth';
import Register from '~/pages/register.vue';
import { ElMessageBox } from 'element-plus'
const router = useRouter();
const store = useStore();
const drawer = ref(false)
// do not use same name with ref
const form = reactive({
  username: "",
  password: "",
});

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

const formRef = ref(null);
const loading = ref(false);

const onSubmit = () => {
  formRef.value.validate((valid) => {
    console.log("校验通过");
    if (!valid) {
      return false;
    }
    loading.value = true;
    login(form.username, form.password)
      .then((res) => {
        toast("登录成功！")
        console.log(res);
        //存储token和用户相关信息
        setToken(res.data)

        //获取用户相关信息
        getUserById(form.username).then(res2 => {
          store.commit("SET_USERINFO", res2.data)
        })

        //跳转到后台首页
        router.push("/");
      })
      .finally(() => {
        loading.value = false;
      });
  });
}

const handleClose = (done) => {
  ElMessageBox.confirm('确定要取消注册？')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}





function onKeyUp(e) {

  if (e.key == "Enter") {
    onSubmit()
  }
}

//添加页面监听
onMounted(() => {
  document.addEventListener("keyup", onKeyUp)
})

//卸载页面监听
onBeforeUnmount(() => {
  document.removeEventListener("keyup", onKeyUp)
})


</script>


<style>
.login-container {
  @apply bg-indigo-500 min-h-screen;
}

.login-container .left,
.login-container .right {
  @apply flex items-center justify-center;
}

.login-container .right {
  @apply bg-light-50 flex-col;
}

.left>div>div:first-child {
  @apply font-bold text-white text-5xl mb-4;
}

.left>div>div:last-child {
  @apply text-gray-200 text-base;
}

.right .line {
  @apply h-[1px] w-16 bg-gray-200;
}

.right .title {
  @apply text-3xl font-bold text-gray-800;
}
</style>