/**
 * 历史数据-企业数据
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <!-- <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>交易订单</el-breadcrumb-item>
    </el-breadcrumb> -->
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.machineNo" placeholder="输入用户名称"></el-input>
      </el-form-item>
        <el-select v-model="value" clearable placeholder="选择用户权限"></el-select>
      <el-form-item></el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
      </el-form-item>
      <el-button size="small" type="primary" icon="el-icon-search" @click="adj()">调整</el-button>
    </el-form>
    <!--列表-->
    <el-table size="small" :data="listData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column align="center" type="index" width="60">
      </el-table-column>
      <el-table-column sortable prop="machineNo" label="用户名" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column sortable prop="orderNo" label="用户权限" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column sortable prop="transId" label="用户邮箱" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">预览</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.$index, scope.row)">退款</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!-- 编辑界面 -->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="50%" @click="closeDialog('editForm')">
      <el-form label-width="120px" :model="editForm" ref="editForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="货道号">
              <el-input size="small" v-model="editForm.aisleNo" auto-complete="off" placeholder="请输入加密类型" disabled></el-input>
            </el-form-item>
            <el-form-item label="买家标识">
              <el-input size="small" v-model="editForm.openId" auto-complete="off" placeholder="请输入商户签名密钥" disabled></el-input>
            </el-form-item>
            <el-form-item label="子商户号">
              <el-input size="small" v-model="editForm.subMchId" auto-complete="off" placeholder="请输入支付宝卖家" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公司名称">
              <el-input size="small" v-model="editForm.deptName" auto-complete="off" placeholder="请输入名称" disabled></el-input>
            </el-form-item>
            <el-form-item label="交易单号">
              <el-input size="small" v-model="editForm.transId" auto-complete="off" placeholder="请输入商户号" disabled></el-input>
            </el-form-item>
            <el-form-item label="子支付方式">
              <el-input size="small" v-model="editForm.subPayType" auto-complete="off" placeholder="请输入商户号" disabled></el-input>
            </el-form-item>
            <el-form-item label="终端编号">
              <el-input size="small" v-model="editForm.machineNo" auto-complete="off" placeholder="请输入微信子商户" disabled></el-input>
            </el-form-item>
            <el-form-item label="商品价格">
              <el-input size="small" v-model="editForm.goodsPrice" auto-complete="off" placeholder="请输入应用ID" disabled></el-input>
            </el-form-item>
            <el-form-item label="商品名称">
              <el-input size="small" v-model="editForm.goodsName" auto-complete="off" placeholder="请输入通知回调" disabled></el-input>
            </el-form-item>
            <el-form-item label="订单状态">
              <el-input size="small" v-model="editForm.orderStatus" auto-complete="off" placeholder="请输入加密类型" disabled></el-input>
            </el-form-item>
            <el-form-item label="商户号">
              <el-input size="small" v-model="editForm.mchId" auto-complete="off" placeholder="请输入商户签名密钥" disabled></el-input>
            </el-form-item>
            <el-form-item label="编辑用户">
              <el-input size="small" v-model="editForm.editUser" auto-complete="off" placeholder="请输入支付宝卖家" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注">
          <el-input size="small" v-model="editForm.remark" auto-complete="off" placeholder="请输入微信证书路径" disabled></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '../../components/Pagination'
export default {
  data() {
    return {
        value2:'',
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
      loading: false, //是显示加载
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: '预览',
      editForm: {
        id: '',
        name: '',
        payType: 1,
        partner: '',
        subMchId: '',
        appid: '',
        notifyUrl: '',
        signType: '',
        partnerKey: '',
        sellerUserId: '',
        certPath: '',
        certPassword: '',
        rsaKey: '',
        token: localStorage.getItem('logintoken')
      },
      formInline: {
        page: 1,
        limit: 10,
        machineNo: '',
        orderNo: '',
        transId: '',
        payType: 0,
        orderStatus: 0,
        token: localStorage.getItem('logintoken')
      },
      // 删除部门
      seletedata: {
        ids: '',
        token: localStorage.getItem('logintoken')
      },
      userparm: [], //搜索权限
      listData: [], //用户数据
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      }
    }
  },
  // 注册组件
  components: {
    Pagination
  },
  /**
   * 数据发生改变
   */

  /**
   * 创建完毕
   */
  created() {
    this.getdata(this.formInline)
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    adj(){
        this.$router.push({path:'/adm/Authority'})
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.getdata(this.formInline)
    },
    // 搜索事件
    search() {
      this.getdata(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(index, row) {
      this.editFormVisible = true
      this.editForm = row
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          ConfigSave(this.editForm)
            .then(res => {
              this.editFormVisible = false
              this.loading = false
              if (res.success) {
                this.getdata(this.formInline)
                this.$message({
                  type: 'success',
                  message: '公司保存成功！'
                })
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.editFormVisible = false
              this.loading = false
              this.$message.error('支付配置信息保存失败，请稍后再试！')
            })
        } else {
          return false
        }
      })
    },
    // 删除公司
    deleteUser(index, row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          ConfigDelete(row.deptId)
            .then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: '公司已删除!'
                })
                this.getdata(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('支付配置信息删除失败，请稍后再试！')
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    // 关闭编辑、增加弹出框
    closeDialog(formName) {
      this.editFormVisible = false
      this.$refs[formName].resetFields()
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

 
 

 