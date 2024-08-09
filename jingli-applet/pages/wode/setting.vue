<template>
	<view class="setting-box">
		<view class="setting-btn" @click="doLogout()">
			退出登录
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				openId: null,
				list: [],
			}
		},
		onLoad(options) {
			this.openId = options?.openId
		},
		onShow() {},
		methods: {
			doLogout() {
				try {
					uni.clearStorageSync();
					setTimeout(function() {
						uni.navigateTo({
							url: '/pages/index/phone/phone'
						})
					}, 500)
				} catch {
				}

			},

		}

	}
</script>
<style lang='scss' scoped>
	.setting-box {
		background: #F5F5F5;
		min-height: 100vh;
		padding-top: 24rpx;

		.setting-top-box {
			background-color: #fff;
		}

		.setting-btn {
			background-color: #fff;
			height: 108rpx;
			font-size: 28rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			color: #353535;
		}
	}
</style>
