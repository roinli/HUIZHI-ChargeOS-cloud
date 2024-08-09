<template>
	<view class="main">
		<view class="middle">
			<view class="logo">
				<u-input v-model="password"  :type="'number'" :focus="true" input-align="center"
					:maxlength="passwordLength" :placeholder="`请输入充电桩编码`" clearable />
			</view>
			<view :class="[password?'button-active button':'button']" @click="onsubmit">
				确定
			</view>
		</view>
		<view class="fixed-code">
		</view>

		<u-popup :show="modalShow" @close="modalShow = false" mode="center" :safeAreaInsetBottom="false" :round="10">
			<view class="infobox infobox-common">
				<view class="info-txt" style="color: #EB3713;font-size: 32rpx;">
					用户未登录
				</view>
				<view class="info-txt">
					您还未登录，无法使用此功能。
				</view>
				<view class="info-login">
					<image src="/static/index/icon-login.png" mode="heightFix"></image>
				</view>
				<view class="info-btn-box">
					<view class="btn-cancel" @click="modalShow = false">
						我在看看
					</view>
					<view class="btn-submit" @click="modalConfirm()">
						立即登录
					</view>
				</view>
			</view>
		</u-popup>
	</view>
</template>

<script>
	import {
		getChargingPileData
	} from '@/config/api.js'
	export default {
		data() {
			return {
				deviceType: "",
				password: "202307123",
				passwordLength: 0,
				showInfo: false,
				modalShow: false,
			}
		},
		onLoad(option) {
			this.deviceType = option.deviceType
			this.passwordLength = option.deviceType == 2 ? 8 : 9;
			this.openid = uni.getStorageSync('openid')
		},
		methods: {
			modalConfirm() {
				this.modalShow = false;
				uni.navigateTo({
					url: '/pages/index/phone/phone'
				})

			},

			onsubmit() {
				if (!this.password || this.password.length != this.passwordLength) {
					// this.error = '请输入8位数字设备码！'
					uni.showToast({
						title: '请输入' + this.passwordLength + '位数字设备码！',
						icon: 'error',
					})
				} else {
					this.error = ''
					this.charging();
				}
			},
			charging() {
				getChargingPileData({
					params: {
						'key': this.password
					}
				}).then(res => {
					if (res.code == 0) {
						if (res.result.deviceType == 2) {
							uni.navigateTo({
								url: '/pages/newPages/success?key=' + this.password
							});
						} else {
							uni.navigateTo({
								url: '/pages/newPages/success4?key=' + this.password
							});
						}
					} else {
						if (res.code == -1) {
							this.modalShow = true;
						} else {
							this.showInfo = true;
						}
					}
				}).catch(err => {
					uni.showToast({
						title: '网络错误！',
						icon: 'error',
					})
				})

			},
		},

	}
</script>

<style lang="scss" scoped>
	.main {
		height: 100vh;
		width: 100%;
		position: relative;

		.middle {

			.logo {
				width: 646rpx;
				height: 101rpx;
			}
		}

		.button {
			width: 646rpx;
			height: 101rpx;
			border-radius: 54rpx;
			line-height: 101rpx;
			text-align: center;
		}

		.button-active {
			background: #002BB6 !important;
		}

		::v-deep .u-input {

			input {
				font-weight: 600;
				font-size: 28rpx !important;
			}
		}
	}

	.fixed-code {
		margin: 300rpx auto;
		display: flex;
		justify-content: center;

		image {
			height: 136rpx;
			width: 136rpx;
		}
	}

	.infobox {

		.top-infod {
			margin-bottom: 24rpx;

			image {
				width: 306rpx;
				height: 268rpx;
			}

		}

		.info-login {
			margin-top: 64rpx;

			image {
				width: 250rpx;
				height: 324rpx;
			}
		}

		.info-txt {
			text-align: center;
			font-size: 28rpx;
			margin-top: 20rpx;

			.other {
				margin-top: 12rpx;
			}
		}

		.info-btn {
		}

		.info-btn-box {

			.btn-cancel {
			}


			.btn-submit {
			}
		}
	}

</style>
