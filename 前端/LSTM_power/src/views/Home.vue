<template>
  <div id="Home">
    <ul id="HomeBar">
      <li v-for="value in BarValue">
        <button class="BarItem">
          <img :src='BarImgURL+value+".svg"' alt="value">
          <span>{{ value }}</span>
        </button>
      </li>
    </ul>

    <el-container>
      <el-aside width="20vw"></el-aside>
      <el-container>
        <el-header height="25vh"></el-header>
        <el-container>
          <span style="font-size: 5vh">光伏预测管理平台</span>
          <el-footer><span style="font-size: 3vh">基于LSTM模型</span></el-footer>
        </el-container>
        <el-footer>
          <div id="buttonBar">
            <el-button type="success">注册账号</el-button>
            <el-button type="primary">管理面板</el-button>
          </div>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
const BarValue = ['首页', '介绍', '定价', '关于']
const BarImgURL = 'src/assets/HomeBar/'

</script>

<style scoped>
#Home {
  height: 100vh;
  background-image: url("../assets/HomeBackGroundImg.jpg"),linear-gradient(rgba(0,0,0,0.7),rgba(0,0,0,0.7));
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
}

.BarItem > img {
  font-size: 24px;
}

.BarItem:hover {
  background-color: #999999;
}

#buttonBar {
  height: 100%;
}

#buttonBar > button {
  height: 80%;
  width: 14vw;
}
</style>