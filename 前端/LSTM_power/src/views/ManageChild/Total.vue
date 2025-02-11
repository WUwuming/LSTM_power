<script setup>

</script>

<template>
  <div id="total">
    <div id="small">
      <!--      今日发电量 -- 目前为止的发电量-->
      <div class="smallBox">
        <span class="smallTitle">今日发电量:</span>
        <div class="smallValue">1mwh</div>
      </div>
      <!--      发电功率-->
      <div class="smallBox">
        <span class="smallTitle">当前发电功率:</span>
        <div class="smallValue">

          1mwh</div>
      </div>
      <!--      预计今日发电总量-->
      <div class="smallBox">
        <span class="smallTitle">预计今日发电:</span>
        <div class="smallValue">1mwh</div>
      </div>

      <!--      总发电量-->
      <div class="smallBox">
        <span class="smallTitle">发电总量:</span>
        <div class="smallValue">1mwh</div>
      </div>
    </div>
    <div id="big">
      <div class="box"></div>
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
}

.box {
  height: 25rem;
  width: 40rem;
  margin: 2rem 4rem 2rem 4rem;
  background-color: white;
  border-radius: 10px;
}

.smallTitle{
  font-size: 20px;
  height: 35%;
  margin-top: 15px;
  margin-left: 15px;
  position: absolute;
  font-weight: bold;
}

.smallValue{
  position: absolute;
  font-weight: bold;
  margin-top: 75px;
  margin-left: 30px;
  font-size: 30px;
}

</style>