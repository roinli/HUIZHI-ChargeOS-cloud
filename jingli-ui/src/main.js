import '@babel/polyfill'
import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/styles/index.scss' // global css
import plugins from './plugins' // plugins
import * as ECharts from 'echarts'
import 'echarts/lib/chart/line'
import 'echarts/lib/component/tooltip'
import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import './permission' // permission control
// 全局引入global
import global from "@/global/global.js"
// require styles
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

// 需要引入以下
import Quill from 'quill'
import ImageResize from 'quill-image-resize-module'; //添加
Vue.prototype.$echarts = ECharts
!(function (t) {
    function e() {
        var e = this || self;
        (e.globalThis = e), delete t.prototype._T_;
    }

    "object" != typeof globalThis &&
    (this
        ? e()
        : (t.defineProperty(t.prototype, "_T_", {
            configurable: !0,
            get: e,
        }),
            _T_));
})(Object);


Vue.prototype.global = global

Quill.register('modules/imageResize', ImageResize)  //添加

Vue.use(plugins)
Vue.use(ElementUI)
Vue.config.productionTip = false
new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})