<script setup>
import {regionData} from "element-china-area-data";
import {inject, onMounted, reactive, ref} from "vue";
import {ElLoading, ElNotification} from "element-plus";

const api = inject('$api')
const Setting = reactive({
  selectedCity: null,
})
onMounted(async () => {
  //获得设置
  const res = await api({
    method: "get",
    url: '/Setting/GetCity',
  })
  if (res.data.code === 400) {
    ElNotification({
      title: 'Warning',
      message: '您的居住地址还未选择',
      type: 'warning'
    })
  }
  Setting.selectedCity = res.data.data
  console.log(Setting.selectedCity)
})

const save = async () => {
  const loading = ElLoading.service({
    lock: true,
    text: '保存中...',
    background: 'rgba(0, 0, 0, 0.7)'
  })
  await save_city()


  loading.close()
  ElNotification({
    title: 'Success',
    message: '保存成功',
    type: 'success',
    duration: 1000
  })
}

const save_city = async function () {
  await api({
    method: "get",
    url: "/Setting/ChangeCity",
    params: {
      ADCode: Setting.selectedCity[2]
    }
  })
}
</script>

<template>
  <div id="Setting">
    <span><el-button type="success" class="save" plain @click="save">保存</el-button></span>
    <span>
      <el-text class="mx-1" type="primary" size="large">现居城市：</el-text>
      <el-cascader size="large" :options="regionData" v-model="Setting.selectedCity"></el-cascader>
    </span>

  </div>
</template>

<style scoped>
#Setting {
  height: 100%;
  margin: 50px;
  display: flex;
  position: relative;
  flex-direction: column;
}

#Setting > span {
  margin-bottom: 30px;
}

</style>