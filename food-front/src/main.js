import Vue from 'vue'
import App from './App'
import Vuex from 'vuex'
import base from './store/vuex.js'
import router from './router/router.js'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
//import 'vue-material/dist/theme/default-dark.css' 

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
//导入百度地图
import BaiduMap from 'vue-baidu-map'
//引入echarts
import echarts from 'vue-echarts/components/ECharts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'


Vue.use(BaiduMap, {
	// ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
	ak: 'amczsAgEUKG9F893UGTxkYBK2onrCWGf'
  })
Vue.use(Vuex)
Vue.use(MintUI)
Vue.use(VueMaterial);
//Vue.use(VueAxios, axios)

//Vue.prototype.$http = axios
//console.log(axios)

const store = new Vuex.Store({
	modules: {
		base: base
	}
})

new Vue({
	router,
	store,
	render: h => h(App)
}).$mount('#app')

