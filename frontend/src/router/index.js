import { createRouter, createWebHashHistory } from "vue-router"


import Login from '~/pages/login.vue'
import Index from '~/pages/index.vue'
import About from '~/pages/about.vue'
import NotFound from '~/pages/404.vue'
import Admin from "~/layout/admin.vue"
const routes = [{
    path: "/",
    component: Admin,
    // 子路由
    children:[{
        path: "/",
        component: Index,
        meta:{
            title: "后台首页"
        }
    },{
        path:"/about",
        component: About,
        meta:{
            title: "关于"
        }
    }]

},{ 
    path: '/:pathMatch(.*)*', 
    name: 'NotFound', 
    component: NotFound 
},{
    path: '/login',
    component: Login,
    meta:{
        title: "登录"
    }
    
}]
const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router