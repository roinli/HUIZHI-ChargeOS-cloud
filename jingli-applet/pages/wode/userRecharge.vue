<template>
	<view>
		<uni-card class="view-title" :title="title">
			<view class="view-content">
				<rich-text space="nbsp" :nodes="content" class="uni-body "> </rich-text>
			</view>
		</uni-card>
	</view>
</template>

<script>
	import {
		queryNewsByNewsConfigKey
	} from '@/config/api.js'
	export default {
		data() {
			return {
				openid: '',
				content: '',
			}
		},
		onLoad(options) {
			this.openid = uni.getStorageSync("openid");
			this.queryNewsByNewsConfigKey();
		},
		methods: {
			queryNewsByNewsConfigKey(value, value2) {
				queryNewsByNewsConfigKey({
					params: {
						'search_openId': this.openid,
						'newsConfigKey': 300,
						'type': 2
					}
				}).then(res => {
					if (res.code == 0 && res.result) {
						this.content = res.result.content;
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'error',
						})
					}
				}).catch(err => {
					uni.showToast({
						title: '网络错误！',
						icon: 'error',
					})
				})
			},


		}
	}
</script>

<style scoped>
	page {
		background-color: #ffffff;
	}

	.view-title {
		font-weight: bold;
	}

	.view-content {
		box-sizing: border-box;
		font-size: 26rpx;
		padding: 24rpx 10rpx 28rpx;
		color: #333;
		line-height: 48rpx;
		font-weight: normal;
	}
</style>
