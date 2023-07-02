import router from '~/router'
import { getToken } from '~/composables/auth'
import { toast, showFullLoading, hideFullLoading } from '~/composables/util'

import store from './store';
// 全局前置守卫
router.beforeEach(async (to, from, next) => {

    //显示loading
    showFullLoading()

    const token = getToken()

    //没有登录，强制跳转为登录页
    if (!token && to.path != '/login') {
        toast("请登录！", "error")
        return next({
            path: "/login"
        })
    }

    //防止重复登录
    if (token && to.path == '/login') {
        toast("请勿重新登录!, 正在自动跳转...", "warning")
        return next({
            path: from.path ? from.path : "/"
        })
    }


    //如果用户登录，自动获取个人信息 并自动存储再vuex当中
    if (token) {
        await store.dispatch("getInfo")
    }

    //设置页面标题
    let title = (to.meta.title ? to.meta.title : "") + "-测试平台"
    document.title = title
    
    next()
});

//全局后置钩子
router.afterEach((to, from) => {
    hideFullLoading()
})