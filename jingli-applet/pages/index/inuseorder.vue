<template>
	<view class="main">
		<image v-if="advert.imageUrl" :src="advert.imageUrl" style="width: 100vw;" mode="widthFix"></image>
		<view class="mu-tabs">
			<view class="mu-tabs-box">
				<view class="order-tabs" v-bind:class="status==1?'active-tab':''" v-on:click="sTab(1,0)">正在使用
				</view>
				<view class="order-tabs" v-bind:class="status==3?'active-tab':''" v-on:click="sTab(3,1)">
					已完成
				</view>
				<view class="order-tabs" v-bind:class="status==0?'active-tab':''" v-on:click="sTab(0,2)">全部订单
				</view>

			</view>
		</view>
		<!-- 订单列表 -->
		<view v-show="list.length>0 && listCode!=null">
			<u-loadmore :status="loadStatus" />
		</view>
		<Tabbar :currentPage="3" />
	</view>
</template>

<script>
	import Tabbar from '../../components/tabbar/tabbar.vue'
	import {
		endCharge,
		showAdvert,
		orderList
	} from '@/config/api.js'
	export default {
		components: {
			Tabbar
		},
		data() {
			return {
				loadStatus: 'loadmore',
				advert: {},
				openid: '',
				status: 1,
				list: [],
				search_page: 0,
				listCode: null,
				tabIndex: 0,
				totalRows: 0,
				modeFade: "fade-right"
			}
		},
		onShow() {
			this.openid = uni.getStorageSync("openid");
			if (!this.openid) {
				this.doLogin()
			}
			//获取广告
			let type = uni.getStorageSync('orderType')
			if (type !== '') {
				this.sTab(type, type == 1 ? 0 : type == 3 ? 1 : 2)
			} else {
				this.sTab(1, 0);
			}
			this.$nextTick(() => {
				uni.removeStorageSync('orderType')
			})
		},
		watch: {
			tabIndex: function(newValue, oldValue) {
				if (newValue > oldValue) {
					this.modeFade = 'fade-left'
				} else {
					this.modeFade = 'fade-right'
				}
			}
		},
		onReachBottom() {
			if ((this.search_page + 1) * 5 < this.totalRows) {
				this.loadStatus = 'loading';
				this.search_page++
				setTimeout(() => {
					this.orderList()
				}, 100)
			} else {
				this.loadStatus = 'nomore';
			}
		},
		methods: {
			doLogin() {
				uni.showModal({
					title: "登录",
					content: "为了使用小程序全部功能，请先登录",
					showCancel: false,
					success(res) {
						if (res.confirm) {
							uni.navigateTo({
								url: "/pages/index/phone/phone",
							});
						}
					},
				});
			},
			call(mobile) {
				uni.makePhoneCall({
					phoneNumber: mobile, // 这里就是自己要拨打的电话号码
					success: (res) => {
					},
					fail: (res) => {
					}
				})
			},
			/**
			 * 获取广告
			 */
			showAdvert() {
				showAdvert({
					params: {
						'showPage': 1005,
						'search_openId': this.openid
					}
				}).then(res => {
					if (res.code == 0) {
						this.advert = res.result;
					}
				}).catch(err => {
					uni.showToast({
						title: '网络错误！',
						icon: 'error',
					})
				})
			},
			sTab(status, index) {
				this.status = status;
				this.list = [];
				this.search_page = 0;
				this.tabIndex = index
				this.orderList();
			},
			/**
			 * 获取订单列表
			 */
			orderList() {
			},


			//计算两个时间之间的时间差 多少天时分秒

		}
	}
</script>

<style lang="scss" scoped>
	image {
		width: auto;
		height: auto;
	}

	.main {
		background: #F2F5FA;
		width: 100vw;
		min-height: 100vh;
		box-sizing: border-box;
		padding-bottom: 180rpx;
	}

	.mu-tabs {
		padding: 20rpx 96rpx 0;
		background-color: #fff;

		.mu-tabs-box {
			box-sizing: border-box;
			padding: 6rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			border-radius: 32rpx;

			.order-tabs {
				text-align: center;
				color: #444444;
				padding-bottom: 16rpx;
				font-size: 26rpx;
			}

			.active-tab {
				border-bottom: 2px solid #333999;
				font-size: 28rpx;
			}
		}
	}


	.main .mu-tabs .item {
		padding: 5px 10px;
		line-height: 35px;
		text-align: center;
		border-bottom: 1px solid #010c3d;
	}

	.main .mu-tabs .active {
		color: #1ec5f1;
		border-bottom: 1px solid #1ec5f1;
	}

	.order-nlist {
		margin: 24rpx;

		.order-content {
			margin-bottom: 24rpx;
			background: #FFFFFF;
			border-radius: 18rpx;
			padding: 24rpx 24rpx 24rpx 24rpx;

			.content-title {
				padding-bottom: 16rpx;
				display: flex;
				align-items: center;
				justify-content: space-between;
				border-bottom: 2rpx solid #F2F5FA;

				.title-box {
					display: flex;
					align-items: center;
					font-size: 32rpx;
					color: #17141B;
					flex: 1;
					overflow: hidden;

					.title {
						flex: 1;
						overflow: hidden;
						white-space: nowrap;
						text-overflow: ellipsis
					}

					image {
						height: 30rpx;
						width: 46rpx;
						margin-right: 18rpx;
					}
				}

				.order-oorice {
					margin-left: 24rpx;
					color: #fa541c;
					font-weight: 600;
					font-size: 40rpx;

					span {
						font-weight: 400;
						font-size: 26rpx;
						margin-right: 6rpx;
					}
				}
			}

			.content-info {
				display: flex;
				align-items: center;
				margin: 24rpx 0;
				font-size: 26rpx;

				.info-charge {
					height: 24rpx;
					margin-right: 16rpx;
				}

				.info-line {
					height: 28rpx;
					width: 2rpx;
					background-color: #000000;
					margin: 0 24rpx;
				}

				.info-time {
					height: 28rpx;
					margin-right: 16rpx;
				}
			}

			.content-bottom {
				display: flex;
				align-items: center;
				justify-content: space-between;
				font-size: 26rpx;
				color: #666666;

				.order-num {
					overflow: hidden;
					white-space: nowrap;
					text-overflow: ellipsis;
				}

				.order-status {
					width: 200rpx;
					text-align: right;
					margin-left: 24rpx;
					color: #E3B34D;

					.finish {
						color: #4764CF;
					}
				}
			}
		}
	}

	.nodata {
		font-size: 28rpx;
		color: #494e61;
		margin-top: 35%;
		position: relative;

		image {
			display: block;
			margin: 0 auto 40rpx;
			height: 410rpx;
		}

		.nodata-text {
			position: absolute;
			top: 312rpx;
			left: 0;
			width: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;

			.nodedata-btn {
				display: flex;
				justify-content: center;
				align-items: center;
				margin-top: 32rpx;
				width: 314rpx;
				height: 76rpx;
				background: #EAEEFF;
				border: 2rpx solid #0022AB;
				border-radius: 38rpx;
			}

		}
	}
</style>
