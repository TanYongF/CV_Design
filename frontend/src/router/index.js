import { createRouter, createWebHashHistory } from "vue-router"


import Login from '~/pages/login.vue'
import Index from '~/pages/index.vue'
import About from '~/pages/about.vue'
import NotFound from '~/pages/404.vue'
import Admin from "~/layout/admin.vue"
import CV from '~/pages/cv/CV.vue'
import AddCV from '~/pages/cv/addCV.vue'
import jobDetail from '~/pages/job/jobDetail.vue'
import Jobs from "~/pages/job/jobs.vue"
import myJobs from "~/pages/job/myJobs.vue"
import jobCandidates from "~/pages/job/jobCandidates.vue"
import tags from '~/pages/tag/tags.vue'
const routes = [{
    path: "/",
    component: Admin,
    // 子路由
    children: [{
        path: "/",
        component: Index,
        meta: {
            title: "后台首页"
        }
    }, {
        path: "/about",
        component: About,
        meta: {
            title: "关于"
        }
    }, {
        path: "/cvs",
        component: CV,
        meta: {
            title: "简历管理"
        }
    }, {
        path: "/cv/add",
        component: AddCV,
        meta: {
            title: "添加简历"
        }
    }, {
        path: "/job",
        component: Jobs,
        meta: {
            title: "工作岗位"
        }
    }, {
        path: "/job/my",
        component: myJobs,
        meta: {
            title: "我的发布"
        }
    }, {
        path: "/job/:id",
        component: jobDetail,
        meta: {
            title: "职位查看"
        }
    }, {
        path: "/recommend/:id",
        component: jobCandidates,
        meta: {
            title: "推荐人选"
        }
    }, {
        path: "/tag",
        component: tags,
        meta: {
            title: "标签管理"
        }
    }]

}, {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound
}, {
    path: '/login',
    component: Login,
    meta: {
        title: "登录"
    }

}]
const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router