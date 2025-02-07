import {createApp} from 'vue'
import './style.css'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import axiosInstance from './utils/request.js'

const app = createApp(App)

app.provide('$api',axiosInstance)

app.use(router)
app.use(ElementPlus)
app.mount('#app')