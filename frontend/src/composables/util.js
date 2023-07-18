import { ElNotification, ElMessageBox, ElMessage } from "element-plus";
import nprogress from "nprogress"
//消息提示
export function toast(message, type = "success") {
    ElNotification({
        message,
        type,
        duration: 3000
    })
}


export function showModal(content = "提示内容", type = "warning", title = "提示") {
    return ElMessageBox.confirm(
        content,
        title,
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
}

// 显示全屏loading
export function showFullLoading() {
    nprogress.start()
}

//隐藏全屏loading
export function hideFullLoading() {
    nprogress.done()
}


/**
 * 将Timestamp格式日期转化为易读格式输出
 * @param {Data} date 
 * @returns 
 */
export function transferDatestamp(date) {
    return new Date(date).toLocaleDateString("zh-CN");
}