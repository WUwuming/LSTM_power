<script setup>
import {inject, onMounted, reactive, ref} from "vue";

const api = inject("$api")

/**
 * TEG 今日发电量
 * CPG 当前发电功率
 * EPG 预计发电量
 * Total 总发电量
 * @type {Reactive<{TEG: number, Total: number, CPG: number, EPG: number}>}
 */
let Value = reactive({
  TEG: 11,
  CPG: 2,
  EPG: 3,
  Total: 4,
})
let TodayWeather = ref()
let WeatherIcon = ref()
onMounted(async () => {
  const res = await api({
    method: 'get',
    url: '/Utils/GetWeather',
    params: {
      ADCode: '110115'
    }
  })
  TodayWeather.value = JSON.parse(res.data.data)
  console.log(TodayWeather.value)
  WeatherIcon.value = 'qi-'+TodayWeather.value.now.icon+'-fill'
})
</script>

<template>
  <div id="total">
    <div id="small">
      <!--      今日发电量 -- 目前为止的发电量-->
      <div class="smallBox">
        <span class="smallTitle">今日发电量:</span>
        <div class="smallValue">{{ Value.TEG }}wh</div>
      </div>
      <!--      发电功率-->
      <div class="smallBox">
        <span class="smallTitle">当前发电功率:</span>
        <div class="smallValue">{{ Value.CPG }}wh</div>
      </div>
      <!--      预计今日发电总量-->
      <div class="smallBox">
        <span class="smallTitle">预计今日发电:</span>
        <div class="smallValue">{{ Value.EPG }}wh</div>
      </div>

      <!--      总发电量-->
      <div class="smallBox">
        <span class="smallTitle">发电总量:</span>
        <div class="smallValue">{{ Value.Total }}wh</div>
      </div>
    </div>
    <div id="big">
      <!--      今日天气-->
      <div class="box Weather" v-if="TodayWeather">
        <span>当前温度:{{ TodayWeather.now.temp }}°C</span>
        <span>当前气候:{{ TodayWeather.now.text }}</span>
        <span>空气湿度:{{ TodayWeather.now.humidity }}</span>
        <span>当前风向:{{ TodayWeather.now.windDir }}</span>
        <span>降雨量:{{ TodayWeather.now.precip }}mm</span>
        <i style="font-size:100px" :class='WeatherIcon'></i>
      </div>

      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
    </div>
  </div>
</template>

<style scoped>
#total {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
}

#small {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}

#big {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}

.smallBox {
  margin: 2rem 4rem 2rem 4rem;
  height: 10rem;
  width: 15rem;
  border-radius: 10px;
  background-color: white;
  position: relative;
  box-shadow: 0 0 2px 0 #333;
}

.box {
  height: 25rem;
  width: 40rem;
  margin: 2rem 4rem 2rem 4rem;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 0 5px 0 #333;
}

.smallTitle {
  font-size: 20px;
  height: 35%;
  margin-top: 15px;
  margin-left: 15px;
  position: absolute;
  font-weight: bold;
}

.smallValue {
  font-weight: bold;
  margin-top: 75px;
  margin-right: 60px;
  font-size: 40px;
  text-align: right;
}

.Weather {
  display: flex;
  flex-direction: column;
}
</style>