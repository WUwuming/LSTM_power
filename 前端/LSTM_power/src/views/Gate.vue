<template>
  <div id="Home">
    <ul id="HomeBar">
      <li v-for="value in BarValue">
        <router-link  class="BarItem" :to='"/gate/"+value.path'>
          <img :src='BarImgURL+value.name+".svg"'>
          <span>{{ value.name }}</span>
        </router-link>
      </li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<script setup>
const BarValue = [{name:'首页',path:'home'}, {name:'介绍',path:'introduce'}, {name: '定价',path:'pay'}, {name:'关于',path: 'about'}]
const BarImgURL = '/src/assets/HomeBar/'

</script>

<style scoped>
#Home {
  height: 100vh;
  background-image: url("../assets/HomeBackGroundImg.jpg"), linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7));
  background-blend-mode: overlay;
  background-size: cover;
}

span {
  color: white;
}

#HomeBar {
  display: flex;
  flex-direction: row;
  height: 8vh;
  width: 100vw;
}

li {
  list-style: none;
  width: 10vw;
  margin: 1vh 1vh 1vh 1vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.BarItem {
  font-size: large;
  width: 100%;
  height: 100%;
  border: 0;
  border-radius: 10px;
  background: rgba(1, 1, 1, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: 0.2s;
}

.BarItem > img {
  font-size: 24px;
}

.BarItem:hover {
  background-color: #999999;
  transition: 0.4s;
}

a{
  text-decoration: none;
}
</style>