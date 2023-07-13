import { useCookies } from '@vueuse/integrations/useCookies';
import axios from '~/axios';


const cookie = useCookies()
export function login(username, password) {
    return axios.post("/user/login", {
        username: username,
        password: password
    })
}

//以下是用户相关接口
export function getUserById(user_id) {
    return axios.get("/user/" + user_id, {})
}
export function getInfoByToken() {
    return axios.get("/getInfo", {})
}
export function logout() {
    return axios.get("/user/logout", {
        params: {
            token: cookie.get("token")
        }
    })
}
export function register(user) {
    return axios.post('/user/register', {
        username: user.username,
        password: user.password,
        is_admin: user.isAdmin == true ? 1 : 0
    })
}
export function updateUserInfo(user){
    return axios.put('/user',{}, {
        params:{
            "nickname":user.nickname,
            "gender": user.gender,
            "phone": user.phone
        }
    }) 
}



//以下是简历相关接口
export function postCV(cv) {
    return axios.post('/cv/upload/v2', cv)
}

export function deleteCV(cvId) {
    return axios.delete('/cv/' + cvId, {})
}

export function getCvs(pageNo, pageSize) {
    return axios.get('/cv', {
        params: {
            page_no: pageNo,
            page_size: pageSize
        }
    })
}
export function getCvById(cvId){
    return axios.get('/cv/' + cvId);
} 


//以下是关于工作相关的接口
export function getJobs() {
    return axios.get('/job')
}
export function getJob(job_id) {
    return axios.get('/job/' + job_id)
}
export function postJob(job) {
    return axios.post('/job', job)
}
export function deleteJob(job_id) {
    return axios.delete('/job/' + job_id);
}
export function searchJob(job, pageSize, pageNum) {
    return axios.post('/job/search', job, {
        params: {
            page_no: pageNum,
            page_size: pageSize
        }
    })
}
export function proposeJob(jobId, pageSize, pageNo) {
    return axios.get('/job/propose/' + jobId, {
        params: {
            page_no: pageNo,
            page_size: pageSize
        }
    })
}


//以下是标签相关接口
export function getTagByResumeId(resumeId){

    return axios.get('/cv/tag/'+ resumeId);
}