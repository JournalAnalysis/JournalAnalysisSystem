/**
 * 个人中心-管理员
 */
<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
    
    <el-form-item label="用户名">
      <el-col span="10">  
        <el-input disabled v-model="form.name"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="注册日期"><el-col span="10">  
        <el-input disabled v-model="form.date"></el-input></el-col>
    </el-form-item>
    <el-form-item label="企业名称"><el-col span="10">  
        <el-input disabled v-model="form.companyname"></el-input></el-col>
    </el-form-item>
    <el-form-item label="企业信息"><el-col span="10">  
        <el-input disabled v-model="form.companyinf"></el-input></el-col>
    </el-form-item>
    <el-form-item label="邀请码"><el-col span="12">  
        <el-input disabled v-model="form.companycode"></el-input></el-col>
    </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onMod">修改企业信息</el-button>
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
            name: '用户名',
            date:'注册日期',
            companyname:'用户类型',
            companyinf:'所属企业',
            companycode:'AAAA-BBBB-CCCC'
        },
    }
  },
  methods: {
    onMod(){
      this.$router.push({path:'/admin/setting/AdmMod'})
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