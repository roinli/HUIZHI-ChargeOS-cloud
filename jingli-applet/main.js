import App from './App'
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
import uView from "uview-ui";
import MinCache from '@/static/js/cache.js'
import { mixin } from "./utils/mixin.js";
Vue.mixin(mixin);
Vue.use(uView);
Vue.use(MinCache)
// 如此配置即可
const app = new Vue({
    ...App
})
require('config/request.js')(app)
app.$mount()