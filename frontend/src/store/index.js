import { createStore } from 'vuex'
import { login, getInfo } from "~/api/mannager";
import {removeToken} from "~/composables/auth"
// 创建一个新的 store 实例
const store = createStore({
    state () {
      return {
            //用户信息
            user: {},

            //侧边宽度
            asideWidth: "250px",

            register: "false"
      }
    },
    mutations: {

        SET_USERINFO(state, user){
            state.user = user
        },

        //展开/缩起侧边
        handleAsideWidth(state){
            state.asideWidth = state.asideWidth == "250px" ? "64px" : "250px"
        },

        //注册界面是否关闭
        handleRegister(register){
            state.register = register
            
        }
    },
    actions: {
        getInfo({commit}){
            return new Promise((resolve, reject)=>{
                getInfo(1).then(res=>{
                    commit("SET_USERINFO", res.user)
                    resolve(res)
                }).catch(err=>reject(err))
            })
        },
        //退出登录
        Logout({commit}){
            removeToken()
            commit("SET_USERINFO", {})
        }
        
    }
  })

  export default store