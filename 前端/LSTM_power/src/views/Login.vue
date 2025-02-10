<template>
  <div class="bigBox">
    <div class="box">
      <div class="main-left">
        <img src="../assets/Login.jpg" alt="">
      </div>
      <div class="main-right">
        <div class="text-top">
          登入
        </div>

        <form action="">
          <input class="acc" type="text" v-model="Login.UserEmail" placeholder="邮箱">
          <input class="acc" type="password" v-model="Login.Password" placeholder="密码" autocomplete="off">

          <input class="button" type="button" @click="handleClick" value="登入">
          <div class="but">
            <a><input type="button" value="注册" @click="register"></a>
            <a href=""><input type="button" value="忘记密码" @click="forgetPW"></a>
          </div>
        </form>

      </div>

    </div>
  </div>
</template>

<script setup>
import {reactive, inject} from 'vue';
import {useRouter} from 'vue-router'
import {ElNotification} from "element-plus";

const Router = useRouter()
const api = inject('$api')  //全局的request地址
const Login = reactive({
  UserEmail: "",
  Password: "",
})

//跳转注册界面
const register = () => {
  Router.push("/Register")
}

//跳转忘记密码界面
const forgetPW = () => {
  window.alert("还没写呢")
}

//登入逻辑
const handleClick = async () => {
  const res = await api({
    method: "get",
    url: '/User/Login', //后端地址
    params: {
      UserEmail: Login.UserEmail,
      Password: Login.Password
    }
  })
  if (res.data.code === 201) {
    //登入失败：账号或密码错误
    ElNotification({
      title: '登入消息',
      message: res.data.msg,
      type: 'error'
    })
    Login.UserEmail = ''
    Login.Password = ''
  } else if (res.data.code === 200){
    ElNotification({
      title: '登入消息',
      message: '登入成功',
      type: 'success'
    })
    await Router.push('/manage')
  }


}
</script>

<style scoped>
.bigBox {
  background: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
  background-repeat: no-repeat;
  position: fixed;
  z-index: -1;
  width: 100%;
  height: 100%;
}

.box {
  display: flex;
  overflow: hidden;
  box-shadow: 0 0 1rem .2rem rgb(0 0 0 /10%);
  width: 50rem;
  height: 34rem;
  border-radius: 1rem;
  margin: 10% auto;
  flex-direction: row;
  backdrop-filter: blur(.625rem);
}

.main-left {
  max-height: 100%;
  max-width: 45%;
}

.main-left > img {
  width: 100%;
}

.main-right {
  width: 55%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  overflow: hidden;
  opacity: 0.7;
}

.main-right > .text-top {
  position: relative;
  bottom: 1.25rem;
  font-size: 35px;
  display: flex;
  justify-content: center;
  top: -50px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.acc {
  width: 15rem;
  height: 3.2rem;
  display: flex;
  flex-direction: column;
  margin: auto;
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #333;
  outline: none;
  transition: 0.5s;
}

.acc:hover {
  transition: 0.3s;
  width: 16rem;
}

.button {
  background: #07c160;
  border: none;
  width: 150px;
  height: 40px;
  font-size: 20px;
  border-radius: 20px;
  overflow: hidden;
  position: relative;
  top: 30px;
  /* font-family: 萝莉体; */
}

.button:hover {
  box-shadow: 0 0 3px 0 #333;
}

.but {
  display: flex;
  flex-direction: row;
  position: relative;
  top: 60px;
  left: 110px;
}

.but input {
  border: none;
  outline: none;
  background-color: transparent;
  /* font-family: 萝莉体; */
}

.but > a:first-child {
  position: relative;
  right: 14px;
}

label {
  position: relative;
  top: 10px;
  left: -70px;
  font-size: 15px;
  background-color: transparent;
  /* font-family: 萝莉体; */
}
</style>