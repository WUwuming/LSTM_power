import {createApp} from 'vue'
import './style.css'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import axiosInstance from './utils/request.js'
import config from "./utils/config.js";

import 'qweather-icons/font/qweather-icons.css'

const app = createApp(App)

app.provide('$api',axiosInstance)
app.provide('config',config)

app.use(router)
app.use(ElementPlus)
app.mount('#app')