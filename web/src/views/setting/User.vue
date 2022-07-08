/**
 * 个人中心-用户
 */
<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
    
    <el-form-item label="用户名"><el-col span=8>  
        <el-input disabled v-model="form.name"></el-input></el-col>
    </el-form-item>
    <el-form-item label="注册日期"><el-col span=8>  
        <el-input disabled v-model="form.date"></el-input></el-col>
    </el-form-item>
    <el-form-item label="用户类型"><el-col span=8>  
        <el-input disabled v-model="form.type"></el-input></el-col>
    </el-form-item>
    <el-form-item label="所属企业"><el-col span=8>  
        <el-input disabled v-model="form.company"></el-input></el-col>
    </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onMod">修改个人信息</el-button>
  </el-form-item>
</el-form>
  </div>
</template>

<script>
import { deptList, deptSave, deptDelete } from '../../api/userMG'
import Pagination from '../../components/Pagination'
export default {
  data() {
    return {
        form: {
            name: '',
            date:'',
            type:'',
            company:''
        },
        userinf:{
          uname: '',
          utype: '',
          uauth: '',
        }
    }
    },
    created(){
      var that = this;
      this.userinf.uname = this.$store.state.uname;
      this.userinf.utype = this.$store.state.utype;
      this.userinf.uauth = this.$store.state.uauth;
      this.$axios.post("http://localhost:8081/user/inf",this.userinf).then(function(response){
        that.form.name = response.data[0].uname;
        that.form.date = response.data[0].utime;
        that.form.type = response.data[0].type;
        that.form.company = response.data[0].cname;
    },)},
        
        methods: {
          onMod(){
            if(this.$store.state.utype=="idi"){
              this.$router.push({path:'/setting/UserMod'});
            }else if(this.$store.state.utype=="staff"){
              this.$router.push({path:'/staff/setting/UserMod'});
            }
        }
      }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userRole {
  width: 100%;
}
</style>

 
 