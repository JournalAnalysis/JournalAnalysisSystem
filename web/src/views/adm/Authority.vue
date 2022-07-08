/**
 * 管理员工-调整权限
 */
<template>
<el-form>
  <el-col span="10">
    <el-form-item>
    <el-input label="用户名"
    placeholder="用户名"
    v-model="input"
    :disabled="true">
    </el-input>
    </el-form-item>
    <el-form-item>
    <el-input label="用户邮箱"
    placeholder="用户邮箱"
    v-model="input"
    :disabled="true">
    </el-input>
    </el-form-item>
    <el-form-item>
    <el-input label="用户当前权限"
    placeholder="权限"
    v-model="input"
    :disabled="true">
    </el-input>
    </el-form-item>
    <el-form-item label="调整权限">
    <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled">
    </el-option>
  </el-select>
  </el-form-item>
  <el-form-item>
    <el-button size="small" type="primary" @click="search">修改</el-button>
  </el-form-item>
    </el-col>
    
</el-form>

</template>

<script>
export default {
  data() {
    return {
      input: ''
    }
  }
}
</script>