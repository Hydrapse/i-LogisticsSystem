import Vue from 'vue'
import Vuex from 'vuex'

//挂载Vuex
Vue.use(Vuex)

//创建VueX对象
const store = new Vuex.Store({
  state:{
    //是否刷新过-废弃
    refresh: false,
    orderMsgList: []
  },
  mutations:{
    setRefresh(state, payload){
      state.refresh = payload
    },
    setOrderMsgList(state, list){
      state.orderMsgList = list
    }
  },
  actions:{
    setAsyncRefresh(context, payload){
      setTimeout(()=>{
        context.commit('setRefresh', payload)
      }, 500)
    }
  }
})

export default store