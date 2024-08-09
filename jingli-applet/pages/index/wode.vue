<template>
	<view class="container">
		<Nav class="nav-box" title="我的" :backIcon="false" titleColor="#000000"
			bgColor="linear-gradient(to bottom, #E2F0FD, #e5f1fd);" />
		<view class="my-info-box">
			<view class="my-info-content">
				<view class="content-info">
					<image :src="avatarUrl?avatarUrl:'/static/index/my-info.png'" mode="heightFix"></image>
					<view class="my-phone" v-if="openid !== ''">
						{{ encryptPhoneNumber(mobile) }}
					</view>
					<view v-else class="my-phone" @tap="toLogin()"> 请登录 </view>
				</view>
				<span @click="handleSetting" class="my-setting" mode="heightFix">退出
				</span>
			</view>
			<view class="my-info-num">
				<view class="num-item">

				</view>


			</view>
		</view>

		<view class="my-card-box">
		</view>


		<u-popup :show="modalShow" @close="modalShow = false" mode="center" :safeAreaInsetBottom="false" :round="10">
			<view class="infobox">
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
		<Tabbar :currentPage="4" />
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import Nav from "../../components/nav/nav.vue";
	import Tabbar from "../../components/tabbar/tabbar.vue";
	import app from "@/static/js/app.js";
	import {
		queryAccountBalance,
		queryConfigByKey

	} from "@/config/api.js";
	export default {
		components: {
			Tabbar,
			Nav,
		},
		data() {
			return {
				toDecimal2: app.toDecimal2,
				openid: "",
				userName: "",
				mobile: "",
				id: "",
				accountAmount: 0, //账户余额
				cardAmount: 0.0, //卡余额
				avatarUrl: "",
				modalShow: false,
				hostVersion: "",
				telephone: "",
				chargeAmount: 0,
				chargeDegree: 0,
				chargeTime: 0,
				orderList: [{
						name: "全部订单",
						img: "/static/index/my-all-order.png",
						url: "/pages/index/inuseorder",
						type: 0,
					},
					{
						name: "充电中",
						img: "/static/index/my-order-do.png",
						url: "/pages/index/inuseorder",
						type: 1,
					},
					{
						name: "已完成",
						img: "/static/index/my-order-finish.png",
						url: "/pages/index/inuseorder",
						type: 3,
					},
				],
			};
		},
		onLoad(option) {},
		onShow() {
			if (!uni.getStorageSync("telephone")) {
				this.queryConfigByKey("sys_mobile", (result) => {
					uni.setStorageSync("telephone", result);
				});
			}
			this.telephone = uni.getStorageSync("telephone");
			this.hostVersion = uni.getAppBaseInfo().appVersionCode;
			this.openid = uni.getStorageSync("openid");
			this.userName = uni.getStorageSync("userName");
			this.mobile = uni.getStorageSync("mobile");
			this.id = uni.getStorageSync("id");
			if (this.openid) {

				this.queryConfigByKey("ma_avater_logo", (result) => {
					this.avatarUrl = result
				});
			}
		},
		methods: {
			toLogin() {
				uni.navigateTo({
					url: "/pages/index/phone/phone",
				});
			},
			doLogin() {
				this.modalShow = true
			},
			encryptPhoneNumber(phone) {
				const reg = /(\d{3})\d{4}(\d{4})/; // 正则表达式
				return String(phone).replace(reg, "$1****$2"); // 返回加密后的电话号码
			},
			queryConfigByKey(value, callback) {
				queryConfigByKey({
					params: {
						'search_openId': "this.openid",
						'configKey': value
					}
				}).then(res => {
					if (res.code == 0 && res.result) {
						callback(res.result);

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

			handleOrder(item) {
				if (!this.openid) {
					return this.doLogin();
				}
				uni.setStorageSync('orderType', item.type)
				uni.reLaunch({
					url: item.url,
				});
			},
			handleSetting() {
				if (!this.openid) {
					return this.doLogin();
				}
				uni.navigateTo({
					url: "/pages/wode/setting?openId=" + this.openid,
				});
			},
			handleNext(item) {
				if (item.type === "phone") {
					uni.makePhoneCall({
						phoneNumber: this.telephone, // 这里就是自己要拨打的电话号码
						success: (res) => {
						},
						fail: (res) => {
						},
					});
				} else {
					if (!this.openid) {
						return this.doLogin();
					}
					uni.navigateTo({
						url: item.url,
					});
				}
			},
			/**
			 * 获取账户余额
			 */
			loginout() {
				uni.removeStorageSync("openid");
				uni.removeStorageSync("mobile");
				uni.removeStorageSync("userName");
				uni.navigateTo({
					url: "/pages/index/phone/phone",
				});
			},
			modalConfirm() {
				this.modalShow = false;
				uni.navigateTo({
					url: "/pages/index/phone/phone",
				});
			},
			doLogout() {
				try {
					uni.clearStorageSync();
					setTimeout(function() {
						uni.navigateTo({
							url: "/pages/index/phone/phone",
						});
					}, 500);
				} catch {
				}
			},
			showTips() {
				this.$refs.uToast.show({
					message: "定制功能请联系管理员",
				})
			},
		},
	};
</script>

<style lang="scss" scoped>
	image {
		width: auto;
		height: auto;
	}

	.container {
		min-height: 100vh;
		box-sizing: border-box;
		padding-bottom: 180rpx;
		background: #f2f5fa;

		.my-info-box {
			width: 100%;
			height: 400rpx;
			background: linear-gradient(to bottom, #e5f1fd, #ffffff);

			.my-info-content {
				padding: 40rpx 28rpx 32rpx 20rpx;
				display: flex;
				align-items: center;
				justify-content: space-between;

				.content-info {
					display: flex;
					align-items: center;

					image {
						width: 124rpx;
						height: 124rpx;
						border-radius: 50%;
						margin-right: 46rpx;
					}

					font-size: 36rpx;
					font-weight: bold;
					color: #000000;
				}

				.my-setting {
					width: 44rpx;
					height: 42rpx;
				}
			}

			.my-info-num {
				display: flex;
				align-items: center;
				padding: 0 110rpx 0 96rpx;
				justify-content: space-between;

				.num-item {
					text-align: center;

					.num-item-value {
						font-size: 34rpx;
						font-weight: bold;
						color: #000000;
						margin-bottom: 20rpx;
					}

					.num-item-label {
						font-size: 28rpx;
						color: #888888;
					}
				}
			}
		}

		.my-order-box {
			background: #ffffff;
			border-radius: 18rpx;
			padding: 24rpx 74rpx 24rpx 56rpx;
			box-sizing: border-box;
			transform: translateY(-40px);
			margin: 0 24rpx 20rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;

			.my-order-item {
				display: flex;
				flex-direction: column;
				align-items: center;
				font-size: 26rpx;
				color: #353535;

				image {
					height: 56rpx;
					margin-bottom: 10rpx;
				}
			}
		}

		.my-card-box {
			transform: translateY(-40px);
			margin: 0 24rpx 24rpx;
			background: #ffffff;
			border-radius: 18rpx;
			padding: 60rpx 36rpx 16rpx 22rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			flex-wrap: wrap;

			.my-card-item {
				width: 25%;
				margin-bottom: 54rpx;
				display: flex;
				flex-direction: column;
				align-items: center;
				font-size: 26rpx;
				color: #444444;

				image {
					height: 42rpx;
					margin-bottom: 10rpx;
				}
			}
		}

		.my-use-box {
			transform: translateY(-40px);
			margin: 0 26rpx 26rpx;
			background: #ffffff;
			border-radius: 18rpx;
			padding: 34rpx 32rpx 52rpx;

			.title {
				font-size: 30rpx;
				font-weight: bold;
				margin-bottom: 56rpx;
				color: #000000;
			}

			.use-list {
				display: flex;
				align-items: center;
				justify-content: space-between;

				.use-item-boder {
					height: 18px;
					width: 1rpx;
					background: #c8d1e0;
				}

				.use-item {
					padding: 0 20rpx;
					display: flex;
					flex-direction: column;
					align-items: center;

					.use-item-label {
						font-size: 26rpx;
						margin-top: 18rpx;
						color: #888888;
					}

					.use-item-value {
						font-size: 28rpx;
						font-weight: bold;
						color: #000000;

						span {
							font-size: 40rpx;
						}
					}
				}
			}
		}
	}


	.infobox {
		width: 640rpx;
		background: #fff;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 56rpx 52rpx 44rpx;
		box-sizing: border-box;

		.info-login {
			margin-top: 64rpx;

			image {
				width: 250rpx;
				height: 324rpx;
			}
		}

		.info-txt {
			text-align: center;
			font-size: 30rpx;
			margin-top: 20rpx;
			color: #000000;

			.other {
				margin-top: 12rpx;
				color: #1546C2;
			}
		}

		.info-btn-box {
			width: 100%;
			font-size: 30rpx;
			margin-top: 36rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;

			.btn-cancel {
				color: #666666;
				width: 240rpx;
				height: 84rpx;
				line-height: 84rpx;
				text-align: center;
				border: 2rpx solid #A6A8BA;
				border-radius: 14rpx;
			}


			.btn-submit {
				color: #FFFFFF;
				width: 240rpx;
				height: 84rpx;
				line-height: 84rpx;
				text-align: center;
				background-color: #134FC5;
				border: 2rpx solid #134FC5;
				border-radius: 14rpx;
			}
		}
	}
</style>
