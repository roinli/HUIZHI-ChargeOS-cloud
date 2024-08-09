<template>
	<view>
		<view id='tabbar-container' class="tabbar-container" :class="isIpx ? 'IpxBot' : ''">
			<view class="tabbar-item" v-for="(item, index) in tabList" :class="[item.centerItem ? 'center-item' : '']"
				@click="changeItem(item)" :key="index">
				<view class="item-top" :style="{ padding: item.id == 2 ? 0 : '8rpx' }">
					<image :style="{
						width: item.imgWidth,
						height: item.imgHeight
					}" :src="tabId == item.id ? item.selectIcon : item.icon" v-if="!item.centerItem" mode="heightFix">
					</image>
					<view class="item-top_center" v-else>
						<template>
							<image :src="require('./menu/index_3.png')" mode=""></image>
							<span>扫码充电</span>
						</template>
					</view>
				</view>
				<view class="item-bottom" :class="[tabId == item.id ? 'item-active' : '']">
					<text>{{ item.text }}</text>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		getChargingPileData
	} from '@/config/api.js'
	import app from '@/static/js/app.js';
	export default {
		props: {
			currentPage: {
				type: Number,
				default: 0,
			},
		},
		data() {
			return {
				//适配IPhoneX
				isIpx: false,
				//底部Tab
				tabId: 0,
				tabList: [{
						id: 0,
						path: "/pages/index/indexNew",
						icon: require("./menu/index_01.png"),
						selectIcon: require("./menu/index_02.png"),
						text: "首页",
						centerItem: false,
						imgWidth: '42rpx',
						imgHeight: '44rpx'
					},
					{
						id: 2,
						path: "/pages/index/wode",
						icon: require("./menu/index_41.png"),
						selectIcon: require("./menu/index_42.png"),
						text: "我的",
						centerItem: false,
						imgWidth: '34rpx',
						imgHeight: '38rpx'
					},
				],
				password: null,
				modalShow: false,
				openid: null,
			};
		},
		computed: {},
		mounted() {
			this.tabId = this.currentPage;
			//隐藏原生tab
			uni.hideTabBar();
		},
		created() {
			// 判断为 iPhone给予底部距离
			let that = this;
			uni.getSystemInfo({
				success: function(res) {
					if (res.model.indexOf("iPhone") !== -1) {
						that.isIpx = true;
					}
				},
			});
		},
		methods: {
			// tab 切换
			changeItem(item) {







					uni.switchTab({
						url: item.path,
					});

			},
			charging() {
				getChargingPileData({
					params: {
						search_openId: this.openid,
						key: this.password
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
						this.showInfo = true;
					}

				}).catch(() => {
					uni.showToast({
						title: '网络错误！',
						icon: 'error',
					})
				})

			},
			modalConfirm() {
				this.modalShow = false;
				uni.navigateTo({
					url: '/pages/index/phone/phone'
				})

			},
		},
	};
</script>
<style scoped lang="scss">
	view {
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}

	.tabbar-container {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		/* height: 100rpx; */
		box-shadow: 0px 0px 3px 0px rgba(232, 232, 232, 0.65);
		display: flex;
		align-items: center;
		// padding: 10rpx 0;
		font-size: 26rpx;
	}

	.tabbar-container .tabbar-item {
		flex: 1;
		height: 112rpx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		text-align: center;
	}

	.tabbar-container .item-active {
		color: #0022AC;
	}

	.tabbar-container .center-item {
		display: block;
		width: 260rpx !important;
		flex: unset;
		position: relative;
		top: -24rpx;
	}

	.tabbar-container .tabbar-item .item-top {
		padding: 4rpx;
	}

	.tabbar-container .center-item .item-top .item-top_center {
	}

	.tabbar-container .tabbar-item .item-top image {
		width: 100%;
		height: 100%;
	}

	.tabbar-container .tabbar-item .item-bottom {
		font-size: 26rpx;
		width: 100%;
	}

	.tabbar-container .center-item .item-bottom {
		position: absolute;
		bottom: 2rpx;
	}

	/* 适配iPhone X */
	.IpxBot {
		padding-bottom: 20rpx !important;
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
			font-size: 28rpx;
			margin-top: 20rpx;
			color: #000000;

			.other {
				margin-top: 12rpx;
				color: #1546C2;
			}
		}

		.info-btn-box {
			width: 100%;
			font-size: 28rpx;
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
				border: 2rpx solid #134FC5;
				border-radius: 14rpx;
			}
		}
	}
</style>
