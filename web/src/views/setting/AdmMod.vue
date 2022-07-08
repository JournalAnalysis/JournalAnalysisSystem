/**
 * 修改信息-管理员
 */
<template>
  <div>
<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="企业信息"><el-col span="10">  
        <el-input v-model="form.companyinf" type="textarea"></el-input></el-col>
    </el-form-item>
  <el-form-item label="密码" prop="pass"><el-col span="10"><el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input></el-col>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkPass"><el-col span="10"><el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input></el-col>
  </el-form-item>
  <el-form-item><el-button type="primary" @click="onSubmit">发送验证码</el-button></el-form-item>
  <el-form-item label="输入验证码" prop="code"><el-col span="10"><el-input type="code" v-model="ruleForm.checkPass" autocomplete="off"></el-input></el-col>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">确认修改</el-button>
    <el-button type="primary" @click="onSubmit">取消</el-button>
  </el-form-item>
</el-form>


  </div>
</template>

<script>
import { deptList, deptSave, deptDelete } from '../../api/userMG'
import Pagination from '../../components/Pagination'
  export default {
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
            }
        };
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        form: {
            companyname:'企业名称',
            companyinf:'企业信息',
            pass:'',
            checkpass:''
        },
        ruleForm: {
          pass: '',
          checkPass: '',
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          age: [
            { validator: checkAge, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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