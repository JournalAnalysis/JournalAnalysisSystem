import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state = { //要设置的全局访问的state对象
  uname: '',
  utype: '',
  auth: '',
  logid: ''
};
 
const mutations = {
      upname(state, uname) {
        state.uname = uname;
      },uptype(state, utype) {
        state.utype = utype;
    }
};
const actions = {
    upname(context, uname) {
          context.uname = uname;
        },
    uptype(context, utype) {
            context.utype = utype;
        }
};
 
export default new Vuex.Store({
    state: {
        user: false,
        uname: '',
        utype: '',
        auth: ''
    },
    mutations: {
        // 登录
        login(state, user) {
            state.user = user;
            localStorage.setItem("userInfo", user);
        },
        // 退出
        logout(state, user) {
            state.user = "";
            localStorage.setItem("userInfo", "");
        },
        upname(state, uname) {
            state.uname = uname;
        },
        uptype(state, utype) {
            state.utype = utype;
        }
    },
    actions : {
        upname(context, uname) {
            context.uname = uname;
          },
        uptype(context, utype) {
            context.utype = utype;
        }
      },
    store : new Vuex.Store({
        state,
        actions,
        mutations
      }),
}) 