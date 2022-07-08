<template>
  <div id="app">
    <ul class="charts">
      <li><ISPChart /></li>
      <li><TopNChart /></li>
      <li><AccessTimeChart /></li>
      <li><AccessSourceChart /></li>
      <li><SearchEnginesChart /></li>
      <li><AccessOSChart /></li>
      <li><AccessAreaChart /></li>
    </ul>  
  </div>

  <!-- <div id="result">
    <ul class="charts">
    <el-row :gutter="23" class="elrow">
      <el-col :span="8" class="text-c">
      <li><ISPChart /></li>
      </el-col>
      <el-col :span="8" class="text-c">
      <li><AccessTimeChart /></li>
      </el-col>
    </el-row>

    <el-row :gutter="23" class="elrow">
      <el-col :span="8" class="text-c">
      <li><TopNChart /></li>
      </el-col>
      <el-col :span="8" class="text-c">
      <li><AccessSourceChart /></li>
      </el-col>
      <el-col :span="8" class="text-c">
      <li><SearchEnginesChart /></li>
      </el-col>
      <el-col :span="8" class="text-c">
      <li><AccessOSChart /></li>
      </el-col>
    </el-row>

    <el-row :gutter="23" class="elrow">
      <el-col :span="8" class="text-c">
      <li><AccessAreaChart /></li>
      </el-col>
    </el-row>
    </ul>  
  </div> -->
</template>
<script>
import ISPChart from './components/ISPChart.vue'
import TopNChart from './components/TopNChart.vue'
import AccessTimeChart from './components/AccessTimeChart.vue'
import AccessSourceChart from './components/AccessSourceChart.vue'
import SearchEnginesChart from './components/SearchEnginesChart.vue'
import AccessOSChart from './components/AccessBroswerChart.vue'
import AccessAreaChart from './components/AccessAreaChart.vue'
export default {
  name: 'Result',
  components: {
    AccessAreaChart,
    ISPChart,
    TopNChart,
    AccessTimeChart,
    AccessSourceChart,
    SearchEnginesChart,
    AccessOSChart
  },
 mounted() {
    
  },
  // 里面的函数只有调用才会执行
  methods: {
    // getArea(){
    //   this.chart = Chart.init(this.$refs.Areachart)
    //   this.chart.setOption(this.AREAoption)
    // },
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.stbox {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.ip{
    width: 500px;
    height: 300px;
}
.charts{
  width: 1000px;
  list-style: none;
  margin: 0 auto;
  overflow: hidden;
}
.charts li{
  width: 400px;
  height: 300px;
  float: left;
  background-color: white;
  margin-bottom: 10px;
  overflow: hidden;
  border: 1px solid #d3d3d3;
}
.charts li:nth-child(2){
  margin-left: 10px;
}
.charts li:nth-child(3){
  margin-left: 10px;
}
.charts li:nth-child(5){
  margin-left: 10px;
}
.charts li:nth-child(6){
  margin-left: 10px;
} 
.charts li:nth-last-child(1){
  width: 100%;
  height: 400px;
}
body{
    background-color: #f5f5f5;
}
</style>
