import { createStore } from 'vuex'
import { login, getInfoByToken } from "~/api/mannager";
import { removeToken, getToken } from "~/composables/auth"
import router from '~/router'
import { toast } from '~/composables/util';
// 创建一个新的 store 实例
const store = createStore({
    state() {
        return {
            //用户信息
            user: {},

            //侧边宽度
            asideWidth: "250px",

            register: "false"
        }
    },
    mutations: {

        SET_USERINFO(state, user) {
            state.user = user
        },

        //展开/缩起侧边
        handleAsideWidth(state) {
            state.asideWidth = state.asideWidth == "250px" ? "64px" : "250px"
        },

        //注册界面是否关闭
        handleRegister(register) {
            state.register = register

        }
    },
    actions: {
        //获取用户信息
        getInfo({ commit }) {
            return new Promise((resolve, reject) => {
                getInfoByToken().then(res => {
                    //如果无法获取用户信息，那么说明token失效，需要重新登录。
                    if (res.data === null) {
                        this.dispatch('Logout').then(() => {
                            router.push("/")
                        })
                    }
                    commit("SET_USERINFO", res.data)
                    resolve(res)
                }).catch(err => reject(err))
            })
        },
        //退出登录
        Logout({ commit }) {
            removeToken()
            commit("SET_USERINFO", {})
        }

    }
})

export default store