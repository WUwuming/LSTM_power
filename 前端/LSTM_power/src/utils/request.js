import axios from "axios";

const instance = axios.create({
    baseURL:'http://localhost:8080',//使用环境变量 import.meta.env.VITE_API_BASE_URL
    // timeout:10000,
})
export default instance