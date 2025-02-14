<script setup>
import {inject, onMounted, reactive, ref} from "vue";
import {ElLoading} from "element-plus";

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
  const loading = ElLoading.service({
    lock: true,
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.7)'
  })

  const res = await api({
    method: 'get',
    url: '/Utils/GetWeather',
    params: {
      ADCode: '110115'
    }
  })
  TodayWeather.value = JSON.parse(res.data.data)
  WeatherIcon.value = 'qi-' + TodayWeather.value.now.icon + '-fill'
  setTimeout(()=>{
    loading.close()
  },500)
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
        <span class="WTemp">{{ TodayWeather.now.temp }}°C</span>
        <span class="WText">{{ TodayWeather.now.text }}</span>
        <span class="WWind">{{ TodayWeather.now.windDir }} {{
            TodayWeather.now.windScale
          }}级 {{ TodayWeather.now.windSpeed }}m/s</span>
        <span class="WRain" v-if="TodayWeather.now.precip == 0">无降雨</span>
        <span class="WRain" v-if="TodayWeather.now.precip != 0">降雨量:{{ TodayWeather.now.precip }}mm</span>
        <span class="WHum">空气湿度:{{ TodayWeather.now.humidity }}</span>


        <i style="font-size:200px" :class='WeatherIcon' class="WIcon"></i>
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
  position: relative;
}

.WIcon {
  position: absolute;
  right: 20px;
  top: 20px;
}

.WTemp {
  font-size: 100px;
}

.WText {
  margin-left: 20px;
  font-size: 50px;
}

.WWind {
  font-size: 30px;
  margin-left: 30px;
}
.WRain{
  font-size: 20px;
  margin-left: 35px;
}
.WHum{
  font-size: 20px;
  margin-left: 40px;
}
</style>