import { useCookies } from "@vueuse/integrations/useCookies";


const TokenKey = "token"
const MaxExpireTime = 60 * 60 * 24
const cookie = useCookies()


//Get Token
export function getToken(){
    return cookie.get(TokenKey)
}


//Set Token
export function setToken(value){
    return cookie.set(TokenKey, value, {
        maxAge: MaxExpireTime
    })
}



//remove Token
export function removeToken(){
    return cookie.remove(TokenKey)
}