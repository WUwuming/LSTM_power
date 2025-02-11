<script setup>
import {regionData} from "element-china-area-data";
import {ref} from "vue";

const selectedCity = ref([])
</script>

<template>
  <div id="Setting">
    <el-text class="mx-1" type="primary" size="large">现居城市：</el-text>
    <el-cascader size="large" :options="regionData" v-model="selectedCity"></el-cascader>
  {{selectedCity[2]}}
  </div>
</template>

<style scoped>
#Setting{
  height: calc(100vh - 80px);
  margin: 50px;
}
</style>