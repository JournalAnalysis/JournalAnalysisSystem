<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import AccessAreaChartVue from './components/AccessAreaChart.vue';
export default {
    name: "App",
    uname: "",
    utype: "",
    auth: "",
    components:{
        AccessAreaChartVue
    },
    created(){
      
    if (sessionStorage.getItem("store") ) {
        this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    } 

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener("beforeunload",()=>{
        sessionStorage.setItem("store",JSON.stringify(this.$store.state))
    })
    }

};
</script>

<style>
html,
body {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    padding: 0px;
    margin: 0px;
}
#app {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    widows: 90;
    height: 100%;
}
</style>
