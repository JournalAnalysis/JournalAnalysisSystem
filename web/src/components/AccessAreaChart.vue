<template>
  <div id="area"></div>
</template>
<script>
import * as echarts from "echarts";
import "../assets/china.js";
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
    
    var mydata = [
      { name: "北京", value: "100" },
      { name: "天津", value: this.randomData() },
      { name: "上海", value: this.randomData() },
      { name: "重庆", value: this.randomData() },
      { name: "河北", value: this.randomData() },
      { name: "河南", value: this.randomData() },
      { name: "云南", value: this.randomData() },
      { name: "辽宁", value: this.randomData() },
      { name: "黑龙江", value: this.randomData() },
      { name: "湖南", value: this.randomData() },
      { name: "安徽", value: this.randomData() },
      { name: "山东", value: this.randomData() },
      { name: "新疆", value: this.randomData() },
      { name: "江苏", value: this.randomData() },
      { name: "浙江", value: this.randomData() },
      { name: "江西", value: this.randomData() },
      { name: "湖北", value: this.randomData() },
      { name: "广西", value: this.randomData() },
      { name: "甘肃", value: this.randomData() },
      { name: "山西", value: this.randomData() },
      { name: "内蒙古", value: this.randomData() },
      { name: "陕西", value: this.randomData() },
      { name: "吉林", value: this.randomData() },
      { name: "福建", value: this.randomData() },
      { name: "贵州", value: this.randomData() },
      { name: "广东", value: this.randomData() },
      { name: "青海", value: this.randomData() },
      { name: "西藏", value: this.randomData() },
      { name: "四川", value: this.randomData() },
      { name: "宁夏", value: this.randomData() },
      { name: "海南", value: this.randomData() },
      { name: "台湾", value: this.randomData() },
      { name: "香港", value: this.randomData() },
      { name: "澳门", value: this.randomData() },
    ];
    
    
    var myChart = echarts.init(document.getElementById("area"));

      this.option.series[0].data = mydata;
      // 绘制图表
      this.option = { ...this.option };
      myChart.setOption(this.option);

    // Vue.axios.get("http://localhost:8080/web/area").then((response) => {
    //   this.option.series[0].data = response.data;
    //   // 绘制图表
    //   this.option = { ...this.option };
    //   myChart.setOption(this.option);
    // });
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