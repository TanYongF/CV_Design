import { showModal, toast } from "~/composables/util"
import { logout } from '~/api/mannager'
import { useRouter } from "vue-router";
import { useStore } from "vuex";


export function useLogout() {
    const router = useRouter()
    const store = useStore();
    function handleLogout(){
        showModal("是否确定注销登录？").then(() => {
            logout()
                .finally(() => {
                    store.dispatch("Logout")
                    router.push("/login")
                    toast("注销登录成功")
                })
        })
    }
    return {
        handleLogout
    }
}

export function useAbout(){
    const router = useRouter()
    const handleAbout = ()=>{
        router.push("/about")
    }
    return {
        handleAbout
    }
}
