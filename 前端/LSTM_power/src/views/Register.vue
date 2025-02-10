<template>
  <div class="bigBox">
    <div class="box">
      <div class="main-left">
        <img src="../assets/1.png" alt="">
      </div>
      <div class="main-right">
        <div class="text-top">
          注册
        </div>

        <form>
          <div id="inRowLine">
            <input class="acc" type="text" v-model="Register.email" placeholder="邮箱">
            <img src="../assets/OK.svg" v-show="Register.isEmail">
          </div>
          <div id="inRowLine">
            <input class="acc" type="password" v-model="Register.password"
                   placeholder="密码(6到16位数，需包含数字和字母)" autocomplete="off">
            <img src="../assets/OK.svg" v-show="Register.isPassword">
          </div>
          <div id="inRowLine">
            <input class="acc" type="password" v-model="Register.repassword" placeholder="重复密码" autocomplete="off">
            <img src="../assets/OK.svg" v-show="Register.samePassword">
          </div>
          <div id="inRowLine">
            <input class="codeInput" type="text" v-model="Register.code" placeholder="验证码">
            <input id="getCode" type="button" :value="codeValue" @click="getCode" :disabled="isDisabled">
          </div>
          <input class="button" type="button" @click="RegisterClick" value="注册">
          <div class="but">
            <a><input type="button" value="我有账号前往登入" @click="ToLogin"></a>
          </div>
        </form>
      </div>

    </div>
  </div>
</template>

<script setup>
import {ref, reactive, watch, inject} from 'vue';
import {useRouter} from 'vue-router'
import {ElNotification} from "element-plus";

const Router = useRouter()
const api = inject('$api')  //全局的request地址
let codeValue = ref('获取验证码')
const isDisabled = ref(false); //按钮是否禁用
const Register = reactive({
  email: "",
  password: "",
  repassword: "",
  code: "",
  isEmail: false,
  isPassword: false,
  samePassword: false
})
const EmailPattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z]{2,4})+$/;
const PasswordPattern = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z]{6,16}$/

watch(Register, (newValue, oldValue) => {
  Register.isEmail = EmailPattern.test(newValue.email);
  Register.isPassword = PasswordPattern.test(newValue.password);
  Register.samePassword = newValue.password === newValue.repassword && Register.isPassword === true;
})

//跳转登入界面
const ToLogin = () => {
  Router.push("/Login")
}

//获取邮箱验证码
const getCode = async () => {
  if (Register.isEmail) {
    afterClickGetCode()
    await api({
      method: 'get',
      url: '/Mail/GetVerificationCode',
      params: {
        userEmail: Register.email
      }
    })
  } else {
    ElNotification({
      title: '注册消息',
      message: '请输入邮箱',
      type: 'error'
    })
  }
}

//注册逻辑
const RegisterClick = async () => {
  let res = null;
  if (Register.isEmail && Register.isPassword && Register.samePassword) {
    res = await api({
      method: 'post',
      url: '/User/Register',
      params: {
        UserName: Register.email,
        UserEmail: Register.email,
        Password: Register.password,
        Code: Register.code
      }
    })
    if (res.data.code === 200) {
      ElNotification({
        title: '注册消息',
        message: '注册成功，请登入',
        type: 'success'
      })
      await Router.push('/Login')
    } else if (res.data.code === 202) {
      ElNotification({
        title: '注册消息',
        message: res.data.msg,
        type: 'error'
      })
    } else if (res.data.code === 203) {
      ElNotification({
        title: '注册消息',
        message: res.data.msg,
        type: 'error'
      })
    }
  } else {
    ElNotification({
      title: '注册消息',
      message: '请按要求填写完整',
      type: 'error'
    })
  }
}

const afterClickGetCode = () => {
  isDisabled.value = true;
  let countdown = 60;
  let time = setInterval(() => {
    if (countdown === 0) {
      clearInterval(time);
      isDisabled.value = false
      codeValue.value = '获取验证码'
    }else {
      countdown = countdown - 1
    codeValue.value = countdown.toString()
    }
  }, 1000)
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

#inRowLine {
  display: flex;
  flex-direction: row;
  height: auto;
  align-items: center;
  justify-content: center;
  width: 16rem;
}

.codeInput {
  width: 11rem;
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

.codeInput:hover {
  height: 3.6rem;
  transition: 0.3s;
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

#getCode {
  margin: 10px 0 0 0;
  border: 0;
  background-color: rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  height: 35px;
  width: 100px;
}

#getCode:hover {
  transition: 0.1s;
  box-shadow: 0 0 3px 0 #333;
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