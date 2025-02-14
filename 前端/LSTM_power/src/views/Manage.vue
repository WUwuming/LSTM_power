<template>
  <div style="height: 100%">
    <header id="header">
      <h1 @click="toGate">LSTM预测系统</h1>
      <div id="bar">
        <ul>
          <li>
            <router-link to="/manage/total" class="ManageRouter">总览</router-link>
          </li>
          <li>
            <router-link to="/manage/site" class="ManageRouter">站点</router-link>
          </li>
          <li>
            <router-link to="/manage/setting" class="ManageRouter">设置</router-link>
          </li>
        </ul>
      </div>
      <div id="user" @click="toUser" @mouseover="showHoverBox = true" @mouseleave="showHoverBox = false">
        <img src="../assets/Manage/User.svg" alt="用户">
        <span>{{ UserNameAndId.UserName }}</span>
        <div id="hoverBox" v-show="showHoverBox">
          <ul>
            <li><span>用户ID：{{ UserNameAndId.UserId }}</span></li>
            <li><input id="exitLog" type="button" value="退出登入" @click="exitLogin"></li>
          </ul>
        </div>
      </div>
    </header>
    <router-view style="background-color: #fafafa"></router-view>
  </div>
</template>

<script setup>
import {ref, inject, onMounted, reactive} from "vue";
import {useRouter} from "vue-router";

const api = inject('$api')
const Router = useRouter()
const UserNameAndId = reactive({
  UserName: null,
  UserId: null,
})
let showHoverBox = ref(false)

onMounted(async () => {
  const res = await api({
    url: '/User/GetUserName',
    method: 'get',
  })
  UserNameAndId.UserName = res.data.data.split(',')[0]
  UserNameAndId.UserId = res.data.data.split(',')[1]
})
const toGate = () => {
  Router.push('/gate')
}

const toUser = () => {

}

const exitLogin = async () => {
  await api({
    method:'get',
    url:'/User/ExitLogin'
  })
  await Router.push('/gate')
}

</script>

<style scoped>
* {
  user-select: none;
}

#header {
  background-color: black;
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
}

#header > h1 {
  color: #fafafa;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 20%;
  overflow: hidden;
  white-space: nowrap;
}

#bar {
  height: 100%;
  width: 70%;
}

#bar > ul {
  display: flex;
  flex-direction: row;
  height: 100%;
  width: 100%;

}

#bar > ul > li {
  list-style: none;
  height: 100%;
  width: 15%;
}

#user {
  height: 100%;
  width: 11%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 0 20px 0 20px;
  color: #fafafa;
  border-radius: 10px;
  position: relative;
}

#user > img {
  margin-right: auto;
}

#user > span {
  margin-right: auto;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  font-size: 20px;
}

#hoverBox {
  z-index: 1;
  position: absolute;
  background-color: #fafafa;
  height: 100px;
  width: 250px;
  right: 1px;
  top: 78px;
  border-radius: 5px;
  box-shadow: 0 0 3px 0 #333;
  transition: 1s;
}

#hoverBox > ul {
  height: 100%;
  width: 100%;
}

#hoverBox > ul > li {
  height: 50px;
}

#hoverBox > ul > li > span {
  position: absolute;
  margin-top: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: black;
}

#exitLog {
  background-color: rgba(0, 0, 0, 0%);
  border: none;
  position: absolute;
  height: 40px;
  width: 100%;
  top: 50px;
  font-size: 20px;
  font-weight: bold;
  transition: 0.2s;
}

#exitLog:hover {
  box-shadow: 0 0 2px 0 #333;
  transition: 0.1s;
}


.ManageRouter {
  color: #fafafa;

  font-size: 30px;
  text-decoration: none;
  height: 100%;
  width: 20%;
  display: flex;
  align-items: center;
  justify-content: center;
  white-space: nowrap;
  margin-left: 5rem;
}

.ManageRouter:hover {
  color: #0066FF;
  box-shadow: 0 0 3px 0 #333;
  border-bottom: #0066FF 10px;
}

.router-link-exact-active{
    color: #0066FF;
}
</style>