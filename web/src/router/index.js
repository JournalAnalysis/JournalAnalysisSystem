// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 注册
import register from '@/views/register';
// 个人用户首页
import index from '@/views/index';
// 企业员工首页
import indexstaff from '@/views/indexstaff';
// 企业管理员首页
import indexadmin from '@/views/indexadmin';


/**
 * 设置
 */
//用户个人中心
import User from '@/views/setting/User';
//管理员个人中心
import Adm from '@/views/setting/Adm';
//用户修改资料
import UserMod from '@/views/setting/UserMod';
//管理员修改资料
import AdmMod from '@/views/setting/AdmMod';
/**
 * 提交日志
 */
// 上传文件
import File from '@/views/upload/File'
// 输入地址
import Address from '@/views/upload/Address'

/**
 * 查看结果
 */
// 近期上传
import Recent from '@/views/result/Recent'
// 分析结果
import Result from '@/views/result/Result'

/**
 * 历史数据
 */
// 分析历史
import History from '@/views/history/History'
// 企业数据
import Company from '@/views/history/Company'

/**
 * 员工管理
 */
// 查询员工信息
import Staff from '@/views/adm/Staff'
// 调整权限
import Authority from '@/views/adm/Authority'

// const originalPush = Router.prototype.push;
// Router.prototype.push = function push(location, onResolve, onReject) {
//   if (onResolve || onReject)
//     return originalPush.call(this, location, onResolve, onReject);
//   return originalPush.call(this, location).catch((err) => err);
// };



// 启用路由
Vue.use(Router);

// 导出路由
export default new Router({
    routes: [
      {
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    },
      {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    },{
        path: '/register',
        name: '注册',
        component: register,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '个人用户首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [
            {
            path: '/setting/User',
            name: '用户个人中心',
            component: User,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/setting/UserMod',
            name: '用户修改资料',
            component: UserMod,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/upload/File',
            name: '上传文件',
            component: File,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/upload/Address',
            name: '输入地址',
            component: Address,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/result/Recent',
            name: '近期上传',
            component: Recent,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/result/Result',
            name: '分析结果',
            component: Result,
            meta: {
                requireAuth: true
            }
        },
         {
            path: '/history/History',
            name: '分析历史',
            component: History,
            meta: {
                requireAuth: true
            }
        }
        ]
    },{
        path: '/indexstaff',
        name: '企业用户首页',
        component: indexstaff,
        iconCls: 'el-icon-tickets',
        children: [
            {
            path: '/staff/setting/User',
            name: '用户个人中心2',
            component: User,
            meta: {
                requireAuth: true
            }
        },
       {
            path: '/staff/setting/UserMod',
            name: '用户修改资料2',
            component: UserMod,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/staff/upload/File',
            name: '上传文件2',
            component: File,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/staff/upload/Address',
            name: '输入地址2',
            component: Address,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/staff/result/Recent',
            name: '近期上传2',
            component: Recent,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/staff/result/Result',
            name: '分析结果2',
            component: Result,
            meta: {
                requireAuth: true
            }
        },
         {
            path: '/staff/history/History',
            name: '分析历史2',
            component: History,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/staff/history/Company',
            name: '企业数据2',
            component: Company,
            meta: {
                requireAuth: true
            }
        }]
    },{
        path: '/indexadmin',
        name: '管理员用户首页',
        component: indexadmin,
        iconCls: 'el-icon-tickets',
        children: [
        {
            path: '/admin/setting/Adm',
            name: '管理员个人中心',
            component: Adm,
            meta: {
                requireAuth: true
            }
        },            
        {
            path: '/admin/setting/AdmMod',
            name: '管理员修改个人信息',
            component: AdmMod,
            meta: {
                requireAuth: true
            }
        },  
        {
            path: '/admin/upload/File',
            name: '上传文件3',
            component: File,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/admin/upload/Address',
            name: '输入地址3',
            component: Address,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/admin/result/Recent',
            name: '近期上传3',
            component: Recent,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/admin/result/Result',
            name: '分析结果3',
            component: Result,
            meta: {
                requireAuth: true
            }
        },
         {
            path: '/admin/history/History',
            name: '分析历史3',
            component: History,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/admin/history/Company',
            name: '企业数据3',
            component: Company,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/adm/Staff',
            name: '员工信息',
            component: Staff,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/adm/Authority',
            name: '调整权限',
            component: Authority,
            meta: {
                requireAuth: true
            }
        }]
    }]
})
