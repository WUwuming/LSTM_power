<template>
  <div id="payList">
    <el-space>
      <el-card id="payBox">
        <template #header>
          <span>免费用户</span>
        </template>
        <template #default>
          <span>基础的节点预测</span><br><br>
          <span>基础的节点预测</span>
        </template>
      </el-card>
      <el-card id="payBox">月费用户</el-card>
    </el-space>
  </div>
</template>

<script setup>

</script>

<style scoped>
#payList {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-top: 20vh;
}

#payBox{
  background-color: rgba(153,153,153,0.5);
  width: 20vw;
  height: 40vh;
  margin-right: 1vw;
  margin-left: 1vw;
}

span {
  color: white;
}
</style>