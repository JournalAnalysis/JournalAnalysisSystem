/**
 * 提交日志-上传文件
 */
<template>
  <div>
    <p>{{getuname}}</p>
    <p>{{getutype}}</p>
    <el-form ref="form" :model="form" label-width="80px">
    <el-upload
    class="upload-demo"
    drag
    action="https://jsonplaceholder.typicode.com/posts/"
    multiple>
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip">只能上传log/csv文件</div>
    </el-upload>
            <el-form-item></el-form-item>
        <el-col span="15">  
        <el-input v-model="form.addr" placeholder="请输入日志名称"></el-input>
        </el-col>
        <el-col span="15">  
        <el-input
          type="textarea"
          v-model="form.inf"
          maxlength="30"
          placeholder="请输入描述"
          show-word-limit>
          </el-input></el-col>
    <el-form-item></el-form-item>
    <el-button type="primary" @click="onSubmit">提交</el-button>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form:{
          addr:'',
          inf:''
        },
            uname:this.getuname,
            utype:this.getutype
        }
    },
    computed:{
    getuname(){
      return this.$store.state.uname
    }, 
    getutype(){
      return this.$store.state.utype
    }
  },
    methods: {

    },
    created(){
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