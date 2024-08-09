import app from '@/static/js/app.js'
import md5Utils from '@/config/md5Utils.js'
// 此vm参数为页面的实例，可以通过它引用vuex中的变量
module.exports = (vm) => {
	// 初始化请求配置
	uni.$u.http.setConfig((config) => {
		/* config 为默认全局配置*/
		config.baseURL = app.api; /* 根域名 */
		return config
	})

	// 请求拦截
	uni.$u.http.interceptors.request.use((config) => { // 可使用async await 做异步操作
		// 初始化请求拦截器时，会执行此方法，此时data为undefined，赋予默认{}
		uni.showLoading({
			title: '加载中'
		})
		// #ifdef  APP-PLUS
		config.header.platform = 'APP'
		// #endif
		// #ifdef  MP-WEIXIN
		config.header.platform = 'MP-WEIXIN'
		// #endif
		const timestamp = new Date().getTime();
		config.header.pid = uni.getStorageSync('openid')
		config.header.uid = uni.getStorageSync('unionId')
		config.header.mobile = uni.getStorageSync('mobile')
		config.header.qid = uni.$u.guid(20)
		config.params.timestamp = timestamp
		const sign = md5Utils.getSign(config.url, config.params)
		config.header.sign = sign;
		config.header.timestamp = timestamp;
		config.data = config.data || {}
		// 根据custom参数中配置的是否需要token，添加对应的请求头
		if (config?.custom?.auth) {
			// 可以在此通过vm引用vuex中的变量，具体值在vm.$store.state中
			config.header.token = vm.$store.state.userInfo.token
		}
		return config
	}, config => { // 可使用async await 做异步操作
		return Promise.reject(config)
	})

	// 响应拦截
	uni.$u.http.interceptors.response.use((response) => {

		/* 对响应成功做点什么 可使用async await 做异步操作*/
		const data = response.data
		uni.hideLoading()
		return data.result === undefined ? {} : data
	}, (response) => {
		// 对响应错误做点什么 （statusCode !== 200）
		return Promise.reject(response)
	})
}