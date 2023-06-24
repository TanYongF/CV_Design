import { useCookies } from '@vueuse/integrations/useCookies';
import axios from '~/axios';



const cookie = useCookies()
export function login(username, password){
    return axios.post("/user/login",{},{
        params:{
            username: username,
            password: password
        }
    })
}
 

export function getInfo(user_id){
    return axios.get("/douyin/user/", {
        params:{
           user_id: user_id,
           token: cookie.get("token")
        }
    })
}

export function logout(){
    return axios.get("/douyin/user/logout", {
        params:{
            token: cookie.get("token")
        }
    })
}

export function register(user){
    return axios.post('/douyin/user/register', {}, {
        params:{
            username: user.username,
            password: user.password
        }
    })
}
