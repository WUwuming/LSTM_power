<template>
  <div style="background-color: #eeeeee">
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
      <div id="user" @click="toUser">
        <img src="../assets/Manage/User.svg" alt="用户">
        <span>{{ UserName }}</span>
      </div>
    </header>
    <router-view></router-view>
  </div>
</template>

<script setup>
import {ref, inject, onMounted} from "vue";
import {useRouter} from "vue-router";

const api = inject('$api')
const Router = useRouter()
const UserName = ref("")

onMounted(async () => {
  const res = await api({
    url: '/User/GetUserName',
    method: 'get',
  })
  UserName.value = res.data.data
})
const toGate = () => {
  Router.push('/gate')
}

const toUser = () => {

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
  color: #e6e6e6;
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
  width: auto;
}

#user {
  height: 100%;
  width: 10%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 0 20px 0 20px;
  color: #e6e6e6;
  border-radius: 10px;
}

#user:hover {
  color: #0066FF;
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

.ManageRouter {
  color: #e6e6e6;
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
</style>