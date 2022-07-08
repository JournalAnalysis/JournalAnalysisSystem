<template>
  <div id="time"></div>
</template>
<script>
import * as echarts from "echarts";
import Vue from "vue";

export default {
  data: function () {
    return {
      option: {
        title: {
          text: "访问时间",
          left: "center",
          top: "5%",
          textStyle: {
            fontSize: "14",
            color: "#666",
          },
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: "line",
            smooth: true,
          },
        ],
      },
    };
  },
  mounted: function () {
    // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById("time"));
    // Vue.axios.get("http://localhost:8081/web/time").then((response) => {
    //   this.option.xAxis.data = response.data.map((item) => item.name);
    //   this.option.series[0].data = response.data.map((item) => item.value);
      // 绘制图表
      this.option = { ...this.option };
      myChart.setOption(this.option);
    // });
  },
};
</script>
<style scoped>
#time {
  width: 100%;
  height: 100%;
}
</style>