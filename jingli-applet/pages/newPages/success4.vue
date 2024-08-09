<template>
	<view class="main">
		<Nav :title="showtype==1?'选择充电':'充电中'" bgColor="linear-gradient(to bottom, #5F7DF9, #5F7DF9)"
			backIconColor="#fff" />
		<view v-show="showtype==1">
			<view class="main-top-box"></view>
			<view class="main-content">
				<view class="change">
					<view class="change-con">
						<view class="change-link">
							<view class="change-tit">
								<view class="tit-l">充电桩编号：<span>{{id}}</span></view>
								<view class="tit-r">{{pileType==1?'快充':'慢充'}}</view>
							</view>
						</view>
						<view class="change-btm">
							<view class="con">
								<view class="change-tit">
									<view class="tit-l">选择设备端口</view>
								</view>
								<view class="change-port">
									<view
										:class="portId==item.id?'change-port-list change-port-active':'change-port-list'"
										v-for="(item, index) in items" v-on:click="choosePort(item)" :key="index">
										{{item.name}}
										<view class="top" v-show="item.state =='Y'">正在使用</view>
										<view class="top" v-show="item.state =='F'">端口故障</view>
									</view>
								</view>
							</view>
							<view class="con">
								<view class="change-tit">
									<view class="tit-l">充电时长</view>
								</view>
								<view class="change-time">
									<view :class="index2==time?'change-time-list change-time-active':'change-time-list'"
										v-for="(item,index2) in chargingPilePrice" :key="index2"
										@click="iszhineng=false;checktime(index2,item)">
										<view>智能充满</view>
									</view>
								</view>
							</view>
							<view class="con">


							</view>
						</view>
						<image v-if="advert.imageUrl" :src="advert.imageUrl" style="width: 100%;border-radius: 12rpx;"
							mode="widthFix"></image>
					</view>
				</view>
			</view>
			<view class="change-btn-box">
				<view class="change-btn" @click="start()">开始充电</view>
			</view>
		</view>
		<!-- 弹窗 -->
		<u-popup :show="showbox" @close="showbox = false" mode="center" :safeAreaInsetBottom="false" :round="10">
			<view class="vox-main">
				<view class="title">充电详情</view>
				<view class="contt">
					<view class="item">

						<view>充电中</view>
					</view>


				</view>
				<view class="toos">
					<view class="btnn cancel" v-on:click="cancel()">取消</view>
					<view class="btnn confirm" v-on:click="confirm()">确定</view>
				</view>
			</view>

		</u-popup>




		<u-popup :show="modalShow" @close="showInfo = false" mode="center" :safeAreaInsetBottom="false" :round="10">
			<view class="infobox">
				<view class="top-infod">
					<image style="width: 306rpx;height: 268rpx;" src="/static/index/icon-error.png" mode="heightFix">
					</image>
				</view>
				<view class="info-txt">
					{{modalText}}
				</view>
				<view class="info-btn" @click="showInfo = false">
					我知道了
				</view>
			</view>
		</u-popup>

	</view>
</template>

<script>
	import Nav from "@/components/nav/nav.vue";
	import app from '@/static/js/app.js';
	import {
		getChargingPileData,
		queryAccountBalance,
		showAdvert,
		saveOrder,



	} from '@/config/api.js'
	export default {
		components: {
			Nav,
		},
		data() {
			return {
				modalText: "",
				totTime: 0,
				totTimeTimer: null,
				toDecimal2: app.toDecimal2,
				showbox: false,
				poptt: false,
				ygbzbox: false,
				lowTen: false,
				modalShow: false,
				type: 8001,
				Chargingtime: false,
				time: -1,
				input: "",
				successShow: false,
				iszhineng: false,
				regionName: '',
				province: '',
				city: '',
				plotName: '',
				advert: {},
				showtype: 1,
				openid: '',
				timecc: '',
				timeinput: null,
				items: [],
				id: 61,
				key: '',
				pkaddress: '',
				pkname: '',
				pileType: 0,

				accountAmount: 0.00, //账户余额
				cardAmount: 0.00, //卡余额
				myAmount: 0.00,
				orderAmount: 0.00,
				chargingPilePrice: [{
					id: 1,
					time: "智能充满",
					value: 0
				}, ], //计费方式
				feeTips: [], //计费规则
				portId: 0, //充电端口
				portName: '', //充电端口名称
				orderInfo: {
					address: "",
					chargeStatus: "",
					createtime: "",
					endTime: "",
					hour: "",
					id: "",
					isFee: "",
					orderType: "",
					ordergold: "",
					ordernumber: "",
					orderstate: "",
					parkid: "",
					parklockid: "",
					price: "",
					startTime: "",
				},
				isClose: false,
				scoketData: {
					hasChargePower: 0,
					realTimePower: 0.0,
					electricity: 0.0,
					voltage: 0.0,
					serviceFee: 0.0,
					powerFee: 0.0,
					totalFee: 0.0,
					chargeMin: 0,
					preEndMin: 0,
					soc: 0,
				},

				webtext: '',
				statusCode: null,
				isSaveOrder: false,


				loading: false,
			}
		},
		onHide() {
			this.isClose = true;
		},
		onShow() {
			if (!this.$cache.has('_recently_used_pile')) {
				this.$cache.set('_recently_used_pile', {
					'pileId': this.key
				}, 300)
			}
		},
		destroyed() {
			this.isClose = true;

			clearInterval(this.totTimeTimer);
		},
		onLoad(option) {
			this.key = option.key;
			this.id = this.key
			this.orderInfo.ordernumber = option.ordernumber

			this.openid = uni.getStorageSync('openid')
			//获取设备端口
			this.getChargingPileData();
			// //获取账户余额
			this.queryAccountBalance();
			// //获取广告
			this.showAdvert();
		},
		watch: {
		},
		methods: {
			modalConfirm() {
				this.modalShow = false;
				uni.switchTab({
					url: "/pages/index/indexNew"
				})
			},
			goCz() {
				this.ygbzbox = false;
				this.lowTen = false;
				this.maskpan = false;
			},
			chkcustomize() {
				if (!this.portId) {
					Toast("请选择充电端口");
				}
				if (!this.timecc) {
					Toast("请输入充电时间");
				}
				if (this.portId && this.timecc) {
					this.successShow = true
				}
			},
			fromtTime(intTime) {
				if (intTime.indexOf('.') == -1 && intTime * 1 < 10) {
					return '0' + (intTime * 1) + ':00';
				}
				if (intTime.indexOf('.') == -1 && intTime * 1 >= 10) {
					return (intTime * 1) + ':00';
				}
				if (intTime.indexOf('.') != -1 && intTime.split('.')[0] * 1 < 10) {
					return '0' + (intTime.split('.')[0] * 1) + ':30';
				}
				if (intTime.indexOf('.') != -1 && intTime.split('.')[0] * 1 >= 10) {
					return (intTime.split('.')[0] * 1) + ':30';
				}
			},
			formatTimestamps(timestamps, format) {
				var date = new Date(timestamps);
				var finalstr = format;
				finalstr = finalstr.replace('yyyy', date.getFullYear()); //年
				finalstr = finalstr.replace('mm', this.formatNum(date.getMonth() + 1)); //月
				finalstr = finalstr.replace('dd', this.formatNum(date.getDate())); //天
				finalstr = finalstr.replace('hh', this.formatNum(date.getHours())); //时
				finalstr = finalstr.replace('mi', this.formatNum(date.getMinutes())); //分
				finalstr = finalstr.replace('ss', this.formatNum(date.getSeconds())); //秒
				finalstr = finalstr.replace('SSS', this.formatMilliseconds(date.getMilliseconds())); //毫秒
				//如果不想返回秒和毫秒，注释掉相应行数，传入参数时去掉该参数
				return finalstr;
			},
			formatNum(arg0) {
				let str = arg0.toString();
				if (str.length == 1) {
					return "0" + str;
				} else {
					return str;
				}
			},
			formatMilliseconds(arg) {
				var str = arg.toString();
				if (str.length == 1) {
					return "00" + str;
				} else if (str.length == 2) {
					return "0" + str;
				} else if (str.length == 3) {
					return str;
				}
			},
			toHoursAndMinutes(time) {
				let timer = () => {

					let h = parseInt(time / 60 / 60 % 24)
					h = h < 10 ? '0' + h : h
					let m = parseInt(time / 60 % 60)
					m = m < 10 ? '0' + m : m
					let s = parseInt(time % 60)
					s = s < 10 ? '0' + s : s
					return h + ':' + m + ':' + s;
				}

				return timer()
			},
			inputTimecc(e, val) {
				const inputRule = /^(0+)|[^\d]+/g;
				this.$nextTick(function() {
					this.timeinput = e.target.value.replace(inputRule, '') + '';
					if (this.timeinput) {
						this.iszhineng = false
					}
				})
			},
			//自定义时间
			customize() {
				this.time = -1;
				this.timecc = ''
				this.Chargingtime = true;
				this.iszhineng = false;
			},
			checktime(index, item) {
				this.timecc = item.time;
				this.timeinput = null
				if (item.chargeType == 3001) {
					this.type = 8001;
					this.Chargingtime = true;
				} else {
					this.type = 8002;
					this.Chargingtime = false;
				}
				this.time = index;
				this.input = "";
				if (this.portId) {
					this.successShow = true
				}
			},
			choosePort(item) {
				this.portId = item.id;
				this.portName = item.name;
				this.pkaddress = item.pkaddress;
				if (this.timecc || this.iszhineng) {
					this.successShow = true;
				}
			},

			getChargingPileData() {
				getChargingPileData({
					params: {
						'key': this.key,
						'search_openId': this.openid
					}
				}).then(res => {
					if (res.code == 0) {
						this.items = res.result.list;
						this.id = res.result.id;
						this.pkaddress = res.result.pkaddress;
						this.pkname = res.result.pkname;
						this.regionName = res.result.regionName;
						this.province = res.result.province;
						this.city = res.result.city;
						this.plotName = res.result.plotName;
						this.pileType = res.result.pileType;

					} else {
						uni.showToast({
							title: res.msg,
							icon: 'error',
						})
					}
				}).catch(err => {
					uni.showToast({
						title: JSON.stringify(err),
						icon: 'error',
					})
				})

			},
			/**
			 * 获取账户余额
			 */
			queryAccountBalance() {
			},
			compare(p) { //这是比较函数
				return function(m, n) {
					var a = m[p];
					var b = n[p];
					return a - b; //升序
				}
			},

			/**
			 * 获取广告
			 */
			showAdvert() {
			},
			/**
			 * 开始充电
			 */
			saveOrder() {
				saveOrder({
					params: {
						'order_source': 1,
						'order_order_type': this.type,
						'order_port_id': this.portId,
						'order_hour': this.timeinput || this.timecc,
						'search_openId': this.openid
					}
				}).then(res => {
					if (res.code == 0) {



					}  else {
						uni.showToast({
							title: res.msg,
							icon: 'error',
						})
					}
				}).catch(err => {

				})
				this.getOrderDetailEnd()
				this.isSaveOrder = true
			},

			getOrderDetailEnd(orderNumber) {
				uni.redirectTo({
					url: '/pages/index/indexNew'
				});
			},
			userCancelOrderConfirm() {
				this.poptt = true;
				this.maskpan = true;
			},

			start() {
				if (!this.openid) {
					uni.showModal({
						title: '请登录',
						content: '请先登录再使用全部功能.',
						confirmText: '去登录',
						success(res) {
							if (res.confirm) {
								uni.navigateTo({
									url: '/pages/index/phone/phone'
								})
							}
						}
					})
					return;
				}
				if (!this.portId) {
					uni.showToast({
						title: '请选择充电枪！',
						icon: 'error',
						success() {

						}
					});
					return;
				}
				if (this.iszhineng) {
					this.showbox = true;
					this.maskpan = true;
				} else {
					if (!this.timecc && !this.timeinput) {
						uni.showToast({
							title: '请输入时长！',
							icon: 'error',
							success() {

							}
						});
						return;
					}
					this.showbox = true;
					this.maskpan = true;
				}
			},
			confirm: function() {
				this.showbox = false;
				this.maskpan = false;

				this.saveOrder();
			},
			cancel: function() {
				this.showbox = false;
				this.maskpan = false;
			},
		}
	}
</script>

<style lang="scss" scoped>
	@keyframes right-to-left {

		/* 设置动画进行的从0%到100%的过程 */
		0% {
			transform: translateX(100%);
			/* 元素从右侧进入页面 */
		}

		100% {
			transform: translateX(-200%);
			/* 元素从左侧离开页面 */
		}
	}

	.main {
		min-height: 100vh;
		box-sizing: border-box;
		// padding-bottom: 180rpx;

		.main-top-box {
			position: relative;
			height: 260rpx;
		}

		.main-content {
			transform: translateY(-240rpx);

			.change-con {
				padding: 24rpx;

				.change-top {
					.tit {
						font-weight: bold;
					}

					.tit-msg {
						font-size: 24rpx;

						image {
							margin-top: 2rpx;
							height: 20rpx;
							margin-right: 10rpx;
						}
					}
				}

				.change-link {
					display: flex;
					align-items: center;
					padding: 0 20rpx;
					height: 100rpx;
					border-radius: 18rpx;
					margin-bottom: 20rpx;

					.change-tit {
						flex: 1;
						justify-content: space-between;
						display: flex;
						align-items: center;

						.tit-l {
							font-size: 26rpx;
							color: #000000;

							span {
								font-size: 32rpx;
								font-weight: 600;
							}
						}

						.tit-r {
							margin-left: 20rpx;
							padding: 8rpx 22rpx;
							border-radius: 6rpx;
							font-size: 20rpx;
						}
					}
				}

				.change-btm {
					margin-bottom: 24rpx;

					.con {
						margin-bottom: 20rpx;
						padding: 30rpx 20rpx;
						border-radius: 18rpx;

						.change-tit {
							margin-bottom: 28rpx;

							.tit-l {
								font-size: 28rpx;
								font-weight: bold;

							}
						}

						.change-port {
							display: flex;
							align-items: center;
							flex-wrap: wrap;
							justify-content: space-between;

							.change-port-list {

								&:nth-child(1),
								&:nth-child(2) {
									margin-bottom: 20rpx;
								}


								.top {
									margin-left: 4rpx;
								}
							}

							.change-port-active {
								background: #3B58D5;
								border: 2rpx solid #5F7DF9;
								color: #fff;
							}
						}

						.change-time {
							display: flex;
							align-items: center;
							flex-wrap: wrap;

							.change-time-list:nth-child(3n) {
								margin-right: 0 !important;
								margin-bottom: 28rpx;
							}

							.change-time-input {
								display: flex;
								padding: 0 20rpx;
								align-items: center;
								justify-content: center;
								box-sizing: border-box;
							}

							.change-time-list {

								&:nth-child(1),
								&:nth-child(2) {
									margin-bottom: 28rpx;
								}

								margin-right: 24rpx;
								display: flex;
								align-items: center;
								justify-content: center;
							}

							.change-time-active {
								background: #EAEEFF;
								border: 2rpx solid #0022AB;
								color: #0022AB;
							}
						}

						.font-list {
							.font-item {
								margin-bottom: 12rpx;
								font-size: 26rpx;
								color: #888888;
								line-height: 40rpx;
							}
						}
					}
				}
			}
		}

		.change-btn-box {
			position: fixed;
			bottom: 0;
			left: 0;
			right: 0;
			background-color: #fff;
			padding-bottom: 0;
			padding-bottom: constant(safe-area-inset-bottom);
			padding-bottom: env(safe-area-inset-bottom);

			.change-btn {
				margin: 24rpx 28rpx;
				height: 106rpx;
				background: #0025AF;
				text-align: center;
				color: #FFFFFF;
			}
		}
	}

	// 充电中展示
	.main-czd-top-box {
		position: relative;
		height: 320rpx;
		background: linear-gradient(180deg, #5F7DF9, #F5F5F5);
	}

	.czd-box {
		padding: 24rpx 0;
		transform: translateY(-300rpx);

		.czd-top-info {
			margin: 0 24rpx;
			padding: 24rpx;
			height: 126rpx;
			background: linear-gradient(180deg, #B6C0F3, #CCD4F6);
			border-radius: 18rpx;

			.info-box {
				display: flex;
				align-items: center;
				justify-content: center;
				margin-bottom: 20rpx;

				.info-type {
					padding: 10rpx 16rpx;
					font-size: 22rpx;
					color: #FFFFFF;
					background: #333999;
					border-radius: 6rpx;
				}

				.info {
					font-size: 28rpx;
					color: #444444;
					line-height: 38rpx;
					display: flex;
					align-items: center;
					flex: 1;
					margin-right: 24rpx;

					image {
						height: 26rpx;
						margin-right: 14rpx;
					}
				}
			}

			.info-num {
				margin-left: 20rpx;
				font-size: 22rpx;
				color: #444444;
			}
		}


		.top-bg-box {
			width: 100%;
			padding-bottom: 40rpx;
			text-align: center;
			margin-top: 16rpx;
			position: relative;
			z-index: 10;
			background: linear-gradient(180deg, #CCD4F6, #f5f5f5);

			.txtbb {
				font-size: 72px;
				line-height: 1;
				color: #000000;

				span {
					margin-left: 4rpx;
					font-size: 48px;
					color: #000000;

				}
			}

			.time {
				font-size: 28rpx;
				color: #000000;
			}
		}

		.box-con {
			transform: translateY(-54rpx);

			.box-bg-car {
				image {
					width: 100vw;
				}
			}

			.box-content-box {
				.box-stop {
					overflow: hidden;
					position: relative;
					display: flex;
					align-items: center;
					margin: 22rpx 48rpx 6rpx;
					height: 52rpx;
					background: #E2E9F7;
					border-radius: 26rpx;
					color: #444444;

					.box-stop-box {
						display: flex;
						align-items: center;

						.stop-text {
							font-size: 24rpx;
							animation: right-to-left 8s linear infinite;
							position: absolute;
							right: 12rpx;
							top: 12rpx;
						}
					}

					.stop-box {
						overflow: hidden;
						position: relative;
						z-index: 2;
						width: 100%;
						padding-left: 18rpx;
						height: 100%;
						background: #E2E9F7;

					}

					.stop {
						position: relative;
						z-index: 2;
						margin-right: 18rpx;
						width: 32rpx;
						height: 32rpx;
						background: #337CEE;
						text-align: center;
						line-height: 32rpx;
						font-size: 28rpx;
						font-weight: bold;
						color: #FFFFFF;
						border-radius: 4rpx;

					}
				}

				.box-info {
					padding: 0 24rpx;
					display: flex;
					align-items: center;
					flex-wrap: wrap;

					.info-item {
						margin-top: 48rpx;
						width: 25%;
						text-align: center;

						.info-value {
							font-size: 30rpx;
							font-weight: bold;
							color: #000000;
							margin-bottom: 20rpx;

							span {
								font-size: 26rpx;
							}
						}

						.info-name {
							font-size: 22rpx;
							color: #444444;
						}
					}
				}
			}
		}
	}



	.list-line .list {
		width: 30%;

	}

	.list-line .btn {
		width: 30%;
		color: #fff;
		height: 40px;
		line-height: 40px;
		border-radius: 8px;
		text-align: center;
		background-color: #224170;
		border-radius: 8px;
	}

	.font-list view {
		font-size: 14px;
		line-height: 23px;
		color: #5C6E99;
	}

	.list-c .list {
		width: 45%;
	}

	.change-list .active {
		border-color: #1fc4f2;
		color: #1fc4f2;
	}

	.color .list {
		color: #aebacc;
	}

	.vox-main {
		width: 600rpx;
		background: #fff;
		border-radius: 20rpx;
		box-sizing: border-box;
		box-sizing: border-box;

		.title {
			font-size: 32rpx;
			padding-bottom: 24rpx;
			font-weight: bold;
			color: #000000;
		}

		.contt {
			padding: 0 12rpx 12rpx;

			.item {
				font-size: 26rpx;
				display: flex;
				justify-content: space-between;
				line-height: 40rpx;
				border-bottom: 2rpx solid #f5f5f5;
				margin: 20rpx 0;
				color: #444444;

				.labdett {
					color: #444444;
				}
			}
		}
	}

	.toos {
		margin-top: 48rpx;
		display: flex;
		justify-content: space-between;

		.btnn {
			font-size: 28rpx;
			text-align: center;
			line-height: 72rpx;
			height: 72rpx;
			width: 240rpx;
			border-radius: 10rpx;
		}

		.cancel {
			color: #666666;
			background-color: #fff;
			border: 2rpx solid #A6A8BA;
		}

		.confirm {
			background: #6D86C4;
			border: 2rpx solid #6D86C4;
			color: #fff;
		}
	}


	.infobox {
		width: 600rpx;
		background: #fff;
		border-radius: 20rpx;
		box-sizing: border-box;
		padding: 46rpx 44rpx 44rpx;
		box-sizing: border-box;

		.top-infod {
			text-align: center;
		}

		.top-infod image {
			width: 132rpx;
			height: 132rpx;
		}

		.info-txt {
			font-size: 28rpx;
			color: #444444;
			text-align: center;
			line-height: 50rpx;
		}

		.btn-confirm {
			margin-top: 52rpx;
			height: 84rpx;
			line-height: 84rpx;
			text-align: center;
			color: #fff;
			font-size: 28rpx;
			background: #6D86C4;
			border-radius: 14rpx;
		}

		.info-btn {
			margin-top: 52rpx;
			width: 100%;
			height: 84rpx;
			line-height: 84rpx;
			text-align: center;
			border: 2rpx solid #A6A8BA;
			border-radius: 14rpx;
			font-size: 28rpx;
			color: #666666;
		}

	}






	::v-deep .u-transition {
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
</style>
