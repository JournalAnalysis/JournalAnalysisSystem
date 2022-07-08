/**
 * 修改信息-个人用户
 */
<template>

 <div>
<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="200px" class="demo-ruleForm">
  <el-form-item label="修改密码" prop="pass"><el-col span="10"><el-input type="password" v-model="ruleForm.pass" autocomplete="off" show-password></el-input></el-col>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkpass"><el-col span="10"><el-input type="password" v-model="ruleForm.checkpass" autocomplete="off" show-password></el-input></el-col>
  </el-form-item>
    <el-form-item label="输入邀请码绑定企业" prop="infcode"><el-col span="10"><el-input v-model="ruleForm.infcode" autocomplete="off"></el-input></el-col>
  </el-form-item>
<el-form-item><el-button type="primary" @click="onSend">发送验证码</el-button></el-form-item>
  <el-form-item label="输入验证码" prop="emailcode"><el-col span="10"><el-input type="code" v-model="ruleForm.emailcode" autocomplete="off"></el-input></el-col>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">确认修改</el-button>
    <el-button type="primary" @click="goBack">返回</el-button>
  </el-form-item>
</el-form>


  </div>
</template>
<script>
import { deptList, deptSave, deptDelete } from '../../api/userMG'
import Pagination from '../../components/Pagination'
  export default {
    data() {
    var validatePass = (rule, value, callback) => {
        if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        }else {
          callback();
        }
      };
      return {
        ruleForm: {
            pass:'',
            checkpass:'',
            infcode:'',
            emailcode:''
        },
        rules: {
        pass: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        checkpass: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur'}
        ],
        emailcode: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
        }
      };
    },
    methods: {
      goBack(){
        this.$router.go(-1);
      },
      onSubmit(){
        
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