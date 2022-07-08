<template>
  <div id="area"></div>
</template>
<script>
import * as echarts from "echarts";
import "../../../assets/china.js";
import Vue from "vue";

export default {
  data:function(){
    return {
      option:{
      backgroundColor: "#FFFFFF",
      title: {
        text: "访问地区",
        left: "center",
        top: "5%",
        textStyle: {
          fontSize: "14",
          color: "#666",
        },
      },
      tooltip: {
        trigger: "item",
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
      //左侧小导航图标
      visualMap: {
        min: 0,
        max: 500,
        text: ["High", "Low"],
        realtime: false,
        calculable: true,
        inRange: {
          color: ["lightskyblue", "yellow", "orangered"],
        },
      },

      //配置属性
      series: [
        {
          name: "数据",
          zoom: 1.2,
          type: "map",
          mapType: "china",
          roam: true,
          label: {
            normal: {
              show: true, //省份名称
            },
            emphasis: {
              show: false,
            },
          },
          data: [{ name: "北京", value: "100" }], //数据
        },
      ],
    }
    }
  },
  mounted: function () {
    var myChart = echarts.init(document.getElementById("area"));
      // 绘制图表
      this.option = { ...this.option };
      myChart.setOption(this.option);
  },
  methods: {
    randomData: function () {
      return Math.round(Math.random() * 500);
    },
  },
};
</script>
<style scoped>
#area {
  width: 100%;
  height: 100%;
}
</style>