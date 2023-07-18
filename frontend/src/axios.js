import axios from "axios";
import { ElNotification } from 'element-plus';
import qs from 'qs';
import {
    getToken
} from '~/composables/auth'
import { toast } from '~/composables/util'
const service = axios.create({
    baseURL: "api",
    timeout: 1000,
})
// axios.defaults.withCredentials = true; //true允许跨域
// 添加请求拦截器
service.interceptors.request.use(function (config) {

    if (config.method == "post") {
        if (config.url == '/user/login' || config.url == '/user/register') config.data = qs.stringify(config.data);
    }
    const token = getToken()
    if (token) config.headers["token"] = token
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});


//todo
// 添加响应拦截器
service.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    if (response.data.code != 0) {
        toast(response.data.msg, "warning")
        console.log(response.data)
        return Promise.reject(response)
    }
    return response.data;
}, function (error) {
    console.log(error)
    toast(error.response.data, "warning")
    console.log(error)
    return Promise.reject(error);
});


export default service