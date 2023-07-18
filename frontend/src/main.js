import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as echarts from 'echarts';
import router from './router'
import store from './store'
import App from './App.vue'
import "nprogress/nprogress.css"
const app = createApp(App)
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(ElementPlus)
app.use(router)
app.use(store)
import 'virtual:windi.css'


import "./permission"
app.config.globalProperties.echarts = echarts
app.mount('#app')
