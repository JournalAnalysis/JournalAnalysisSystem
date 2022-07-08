/**
 * 提交日志-输入地址
 */
<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
    
    <el-form-item>
        <el-radio v-model="form.radio" label="1">输入网址</el-radio>
        <el-radio v-model="form.radio" label="2">输入数据库地址</el-radio>
    </el-form-item>
    <el-form-item label="地址"><el-col span="15">  
        <el-input v-model="form.addr" placeholder="请输入地址"></el-input></el-col>
    </el-form-item>
    <el-form-item label="日志名称"><el-col span="15">  
        <el-input v-model="form.name" placeholder="请输入日志名称"></el-input></el-col>
    </el-form-item>
    <el-form-item label="日志描述">
        <el-col span="15">  
        <el-input
          type="textarea"
          v-model="form.inf"
          maxlength="30"
          placeholder="请输入描述"
          show-word-limit>
          </el-input></el-col>
    </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">提交</el-button>
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
            radio: '1',
            date:'注册日期',
            companyname:'用户类型',
            companyinf:'所属企业',
            companycode:'AAAA-BBBB-CCCC'
        },
    }
  },
  methods: {
    onMod(){
      this.$router.push({path:'/setting/UserMod'})
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