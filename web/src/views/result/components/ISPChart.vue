<template>
  <div id="ip"></div>
</template>
<script>
import * as echarts from "echarts";
import Vue from "vue";

export default {
  data: function () {
    return {
      option: {
        title: {
          text: "运营商",
          left: "center",
          top: "5%",
          textStyle: {
            fontSize: "14",
            color: "#666",
          },
        },
        tooltip: {},
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        yAxis: {
          axisLabel: {
            textStyle: {
              fontSize: "14",
            },
          },
          data: [],
        },
        xAxis: {},
        series: [
          {
            name: "访问次数",
            type: "bar",
            data: [],
          },
        ],
      },
    };
  },
  mounted: function () {
    // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById("ip"));
    // Vue.axios.get("http://localhost:8081/web/company").then((response) => {
    //   var names = response.data.map((item) => item.name);

    //   var values = response.data.map((item) => item.value);
    //   this.option.yAxis.data = names;
    //   this.option.series[0].data = values;
    //   // 绘制图表
      this.option = { ...this.option };
      myChart.setOption(this.option);
    // });
  },
};
</script>
<style scoped>
#ip {
  width: 100%;
  height: 100%;
}
</style>