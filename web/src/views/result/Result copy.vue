/**
* 查看结果-分析结果
*/ 
<template>
  <!-- 组件主盒子 -->
  <div class="stbox">
    <!-- 搜索，切换 -->    
    <el-select v-model="selectvalue" clearable placeholder="请选择"></el-select>
    <!-- <el-button size="small" type="primary" @click="search">下载结果</el-button> -->

    <!-- <ul class="charts">
      <li>{{AccessAreaChart}}</li>
    </ul> -->
    <!-- 统计图 -->
    <el-row :gutter="23" class="elrow">
      <el-col :span="8" class="text-c">
        <div class="st-gbox">
          <div class="cavasbox" ref="SCEchart"></div>
        </div>
      </el-col>
      <el-col :span="8" class="text-c">
        <div class="st-gbox">
          <div class="cavasbox" ref="SUMEchart"></div>
        </div>
      </el-col>
      <el-col :span="8" class="text-c">
        <div class="st-gbox">
          <div class="cavasbox" ref="ClickEchart"></div>
        </div>
      </el-col>
    </el-row>
    <!-- 统计图 -->
    <div>
      <el-row :gutter="23">
        <el-col :span="12" class="text-c">
          <div class="paybox">
            <div class="cavasbox" ref="topNEchart"></div>
          </div>
        </el-col>
        <el-col :span="12" class="text-c">
          <div class="paybox">
            <div class="cavasbox" ref="payNumEchart"></div>
          </div>
        </el-col>
         <el-col :span="12" class="text-c">
          <div class="paybox">
            <div class="cavasbox" ref="searchEchart"></div>
          </div>
        </el-col>
         <el-col :span="12" class="text-c">
          <div class="paybox">
            <div class="cavasbox" ref="browserEchart"></div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="st-gbox">
      <div class="areabox" ref="Areachart"></div>
    </div>
  </div>
</template>


<script type="text/ecmascript-6">
import AccessAreaChart from '../../components/AccessAreaChart.vue';
import Chart from 'echarts';
import "../../assets/china.js"
export default {
  name: "Result",
  data() {
    return {
      machineNo: '',
      type: 'day',
      selectvalue: '',
    AREAoption:{
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
    },
      //  运营商 
      SCEoption: {
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}月 : {c}"
        },
        legend: {
          data: [{
            name: '运营商',
            icon: 'rect'
          }],
          top: 1,
          left: 1,
          itemGap: 10,
          itemWidth: 12,
          itemHeight: 12,
          textStyle: {
            fontSize: 12,
            color: "#323232"
          }
        },
        grid: {
          left: 50,
          right: 10,
          top: 30,
          bottom: 30,
          borderWidth: 1
        },
        xAxis: {
          type: 'category',
          data: ['00','02', '04', '06', '08', '10', '12', '14', '16', '18', '20', '22'],
          axisLine: {
            lineStyle: {
              color: "#999999",
              width: 1
            }
          },
          axisLabel: {
            margin: 14,
            height: 70,
            interval: 0,
            textStyle: {
              fontSize: 10,
              color: "#999999"
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLine: {
            lineStyle: {
              color: "#999999",
              width: 1
            }
          },
          axisLabel: {
            margin: 14,
            textStyle: {
              fontSize: 10,
              color: "#999999"
            }
          }
        },
        series: [{
          name: '运营商',
          type: 'bar',
          barGap: 0,
          data: [50000, 70000, 80000, 40000, 50000, 30000, 40000, 60000, 50000, 40000, 60000, 40000],
          barWidth: 10,
          itemStyle: {
            normal: {
              color: new Chart.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  { offset: 0, color: '#83bff6' },
                  { offset: 0.5, color: '#188df0' },
                  { offset: 1, color: '#188df0' }
                ]
              )
            },
            emphasis: {
              color: new Chart.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  { offset: 0, color: '#2378f7' },
                  { offset: 0.7, color: '#2378f7' },
                  { offset: 1, color: '#83bff6' }
                ]
              )
            }
          }
        }]
      },
      //  访问时间 
      SUMoption: {
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}月 : {c}"
        },
        legend: {
          data: [{
            name: '访问时间',
            icon: 'rect'
          }],
          top: 1,
          left: 1,
          itemGap: 10,
          itemWidth: 12,
          itemHeight: 12,
          textStyle: {
            fontSize: 12,
            color: "#323232"
          }
        },
        grid: {
          left: 50,
          right: 10,
          top: 30,
          bottom: 30,
          borderWidth: 1
        },
        xAxis: {
          type: 'category',
          data: ['00','02', '04', '06', '08', '10', '12', '14', '16', '18', '20', '22'],
          axisLine: {
            lineStyle: {
              color: "#999999",
              width: 1
            }
          },
          axisLabel: {
            margin: 14,
            height: 70,
            interval: 0,
            textStyle: {
              fontSize: 10,
              color: "#999999"
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLine: {
            lineStyle: {
              color: "#999999",
              width: 1
            }
          },
          axisLabel: {
            margin: 14,
            textStyle: {
              fontSize: 10,
              color: "#999999"
            }
          }
        },
        series: [{
          name: '访问时间',
          //   type: 'bar',
          type: 'line',
          barGap: 0,
          data: [50000, 70000, 80000, 40000, 50000, 30000, 40000, 60000, 50000, 40000, 60000, 40000],
          barWidth: 10,
          itemStyle: {
            color: "#108ff9"
          }
        }]
      },
      //  支付方式统计
      payoption: {
        backgroundColor: '#2c343c',
        title: {
          text: '支付方式统计(金额)',
          left: 10,
          top: 5,
          textStyle: {
            fontSize: 12,
            color: '#ccc'
          }
        },

        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: '支付方式统计(金额)',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: [
              { value: 335, name: '支付宝' },
              { value: 310, name: '银商二维码' },
              { value: 274, name: '会员' },
              { value: 235, name: '微信支付' },
              { value: 100, name: 'Pos通' }
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
              normal: {
                textStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                }
              }
            },
            labelLine: {
              normal: {
                lineStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              }
            },
            itemStyle: {
              normal: {
                color: '#c23531',
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
              return Math.random() * 200;
            }
          }
        ]
      },
      payNumoption: {
        backgroundColor: '#2c343c',
        title: {
          text: '支付方式统计(笔数)',
          left: 10,
          top: 5,
          textStyle: {
            fontSize: 12,
            color: '#ccc'
          }
        },

        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: '支付方式统计(笔数)',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: [
              { value: 335, name: '支付宝' },
              { value: 310, name: '银商二维码' },
              { value: 274, name: '会员' },
              { value: 235, name: '微信支付' },
              { value: 100, name: 'Pos通' }
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
              normal: {
                textStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                }
              }
            },
            labelLine: {
              normal: {
                lineStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              }
            },
            itemStyle: {
              normal: {
                color: '#c23531',
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
              return Math.random() * 200;
            }
          }
        ]
      },
    }
  },
  // 导入组件
  components: {
    AccessAreaChart
  },
  // 创建完毕状态(里面是操作)
  created() { },
  // 挂载结束状态(里面是操作)
  mounted() {
    this.getSCE()
    this.getSUM()
    this.gettopN()
    this.getpayNum()
    this.getArea()
  },
  // 里面的函数只有调用才会执行
  methods: {
    getArea(){
      this.chart = Chart.init(this.$refs.Areachart)
      this.chart.setOption(this.AREAoption)
    },
    // 交易总笔数
    getSCE() {
      this.chart = Chart.init(this.$refs.SCEchart)
      this.chart.setOption(this.SCEoption)
    },
    // 交易总金额
    getSUM() {
      this.chart = Chart.init(this.$refs.SUMEchart)
      this.chart.setOption(this.SUMoption)
    },
    // 支付方式统计
    gettopN() {
      this.chart = Chart.init(this.$refs.topNEchart)
      this.chart.setOption(this.payoption)
    },
    // 支付方式统计
    getpayNum() {
      this.chart = Chart.init(this.$refs.payNumEchart)
      this.chart.setOption(this.payNumoption)
    }

  }
};
</script>
<style>
.elrow{
  margin-bottom: 20px;
  display: flex;
  flex-wrap: wrap;
}
.stbox {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.stbgc {
  background-color: #fff;
  height: 60px;
  line-height: 60px;
  border-radius: 5px;
  padding: 0px 16px;
}
.stsearch {
  text-align: center;
}
.text-c {
  text-align: center;
}
.st-gbox {
  background-color: #fff;
  margin-top: 20px;
  border-radius: 5px;
  height: 30vh;
  box-sizing: border-box;
  padding: 10px;
}
.cavasbox {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
.areabox {
  box-sizing: border-box;
  width: 100%;
  height: 200%;
}
.paybox {
  width: 100%;
  background-color: #fff;
  box-sizing: border-box;
  border-radius: 5px;
  margin-top: 20px;
  height: 32vh;
}
</style>