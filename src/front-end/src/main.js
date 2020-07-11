import Vue from 'vue'
import App from './App.vue'

//引入element-ui
import './plugins/element.js'

//引入font-awesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon, FontAwesomeLayers, FontAwesomeLayersText }
  from '@fortawesome/vue-fontawesome'

library.add(fas, far, fab)

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('font-awesome-layers', FontAwesomeLayers)
Vue.component('font-awesome-layers-text', FontAwesomeLayersText)

Vue.config.productionTip = false


new Vue({
  render: h => h(App),
}).$mount('#app')

Vue.component('my-container',{
  template:`
    <el-row>
        <el-col :xs="0" :sm="1" :md="2" :lg="3" :xl="4">&nbsp;</el-col>
        <el-col :xs="24" :sm="22" :md="20" :lg="18" :xl="18">
            <slot></slot>
        </el-col>
        <el-col :xs="0" :sm="1" :md="2" :lg="3" :xl="4">&nbsp;</el-col>
    </el-row>
    `
});