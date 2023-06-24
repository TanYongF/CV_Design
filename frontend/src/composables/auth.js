import { useCookies } from "@vueuse/integrations/useCookies";


const TokenKey = "token"
const cookie = useCookies()


//Get Token
export function getToken(){
    return cookie.get(TokenKey)
}


//Set Token
export function setToken(value){
    return cookie.set(TokenKey, value)
}



//remove Token
export function removeToken(){
    return cookie.remove(TokenKey)
}