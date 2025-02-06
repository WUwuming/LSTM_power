<template>
  <div>
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
            <el-button type="success" @click="clickRegister">注册账号</el-button>
            <el-button type="primary" @click="clickManage">管理面板</el-button>
          </div>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";

const router = useRouter()
let clickRegister = ()=>{
  router.push('/Register')
}

let clickManage = ()=>{
  router.push('/Manage')
}
</script>

<style scoped>
span{
  color: white;
}

#buttonBar {
  height: 100%;
}

#buttonBar > button {
  height: 80%;
  width: 14vw;
}
</style>