/**
 * 查看结果-近期上传
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <!-- <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>交易订单</el-breadcrumb-item>
    </el-breadcrumb> -->
    <!--列表-->
    <el-table size="small" :data="listData" highlight-current-row border style="width: 100%;">
      <!-- <el-table-column align="center" type="index" width="60">
      </el-table-column> -->
      <el-table-column sortable prop="id" label="编号" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column sortable prop="logname" label="日志名称" width="120" show-overflow-tooltip>
              <!-- <template slot-scope="scope">
                    <span>{{scope.row[scope.column.property]}}</span>
              </template>         -->
      </el-table-column>
      <el-table-column sortable prop="uptime" label="上传日期" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column sortable prop="loginf" label="日志描述" width="140" show-overflow-tooltip>
      </el-table-column>
      <el-table-column sortable prop="cname" label="所属企业" width="120" show-overflow-tooltip>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <!-- <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination> -->
    <!-- 编辑界面 -->
  </div>
</template>

<script>
import Pagination from '../../components/Pagination'
export default {
  data() {
    return {
      listData: [{
        cname:'',
        loginf:'',
        logname:'',
        uptime:''
       }
      ]
    }
  },
  // 注册组件
  components: {
  },

  /**
   * 创建完毕
   */
  // created() {
  //   var that=this;
  //   this.$axios.get('http://localhost:8081/log/getRecent').then(Response=>{
  //     this.listData=Response.data;
  //     console.log(Response);
  //   })
  // }
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

 
 

 