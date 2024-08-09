<template>
  <view class="index-box">
    <Nav
      class="nav-box"
      title="首页"
      :backIcon="false"
      bgColor="linear-gradient(to bottom, #7794FE, #7491fd);"
    />

    <view class="menu-box"> </view>
    <view
      class="filter-box"
      :style="{ top: navBarHeight + 35 + topHeight + 'px' }"
    >
    </view>
    <view class="content-box">
      <view class="content-list">
        <view
          class="content-item"
          v-for="(item, index) in contentList"
          :key="index"
        >
          <view class="title-box">
            <view class="title">
              {{ item.plotName }}
            </view>
            <view class="area-box">
              <image src="/static/index/icon-area.png" mode=""></image>
              <span class="area-value">{{
                Number(item.distance / 1000).toFixed(2)
              }}</span>
              <span class="area-km">km</span>
            </view>
          </view>
          <view class="price-box">
            <span class="unit">¥</span> {{ item.price }} <span>起</span>
          </view>
          <view class="info-box">
            <view class="info-list">
              <view class="info-item" v-if="item.fastNum && deviceType === 4">
                <view class="info-icon"> 快 </view>
                <view class="info-content">
                  空闲<span>{{ item.fastNum }}</span
                  >/共{{ item.fastNum }}
                </view>
              </view>
              <view class="info-item" v-if="item.slowNum">
                <view class="info-icon"> 慢 </view>
                <view class="info-content">
                  空闲<span>{{ item.slowNum }}</span
                  >/共{{ item.slowNum }}
                </view>
              </view>
              <view class="info-item" v-if="deviceType === 4">
                <view class="info-icon" style="font-size: 28rpx"> P </view>
                <view class="info-content" style="color: #19181e">
                  {{ item.parkCarInfo || "暂无停车信息，以实际收费为准" }}
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>
    <u-divider text="到底了"></u-divider>
    <view class="fixed-box" @click="handleNextInput">
      <image src="../../static/index/touch.png" mode="widthFix"></image>
    </view>
    <privacy />
    <Tabbar :currentPage="0" />
  </view>
</template>
<script>
import { getPlotInfoPage, queryNewsNameList } from "@/config/api.js";
import Nav from "../../components/nav/nav.vue";
import Tabbar from "../../components/tabbar/tabbar.vue";
export default {
  components: {
    Tabbar,
    Nav,
  },
  data() {
    return {
      navBarHeight: 87,
      topHeight: 62,
      keyword: "",
      swiperList: [],
      typeList: [
        {
          value: 4,
          label: "四轮车",
        },
        {
          value: 2,
          label: "二轮车",
        },
      ],
      contentList: [],
      deviceType: 4, //4轮 2轮
      areaName: "北京市",
      areaSearch: false,
      openid: null,
      latitude: 39.9045035,
      longitude: 116.408788,
      pages: {
        pageNo: 1,
        pageSize: 10,
      },
      total: 0,
    };
  },
  methods: {
    loadCity(longitude, latitude) {
      uni.request({
        header: {
          "Content-Type": "application/text",
        },
        url:
          "https://apis.map.qq.com/ws/geocoder/v1/?location=" +
          latitude +
          "," +
          longitude +
          "&key=" +
          "你的腾讯地图key",
        success: (res) => {
          if (res.data.status === 0) {
            this.areaName = res.data.result.address_component.city;
          }
          this.getPlotInfo();
        },
      });
    },
    getBanner() {
      queryNewsNameList({
        params: {
          type: 3,
        },
      }).then((res) => {
        if (res.code == 0 && res.result?.length) {
          this.swiperList = res.result.map((item) => item.remark);
        }
      });
    },
    getPlotInfo() {
      uni.showLoading({
        mask: true,
        title: "加载中",
      });
      getPlotInfoPage({
        params: {
          distance: 5500000,
          search_openId: this.openid,
          deviceType: this.deviceType,
          lat: this.latitude,
          lng: this.longitude,
          pageNo: this.pages.pageNo,
          pageSize: this.pages.pageSize,
          // 'city': this.areaName
        },
      }).then((result) => {
        if (result.code == 0) {
          uni.stopPullDownRefresh();
          if (this.pages.pageNo === 1) {
            this.contentList = result?.result.list;
            this.total = result?.result.total;
          } else {
            this.contentList = this.contentList.concat(result.result.list);
          }
        }
      });
    },
    handleNextInput() {
      uni.navigateTo({
        url: `/pages/index/indexCode/indexCode?deviceType=${this.deviceType}`,
      });
    },
    handleCheck(item) {
      this.deviceType = item.value;
      this.contentList = [];
      this.getPlotInfo();
    },
    handleSearch() {
      this.pages.pageNo = 1;
      this.getPlotInfo();
    },
    handleClear() {
      this.keyword = null;
      this.handleSearch();
    },
  },
  onShow() {
    let areaSearch = uni.getStorageSync("areaSearch") || false;
    this.openid = uni.getStorageSync("openid");
    uni.authPrivateMessage();
    this.getBanner();
    this.pages.pageNo = 1;
    let locationEnable = uni.getStorageSync("haveLocation");
    if (!locationEnable) {
      this.getPlotInfo();
      setTimeout(() => {
        uni.getLocation({
          type: "gcj02",
          success: async (res) => {
            uni.setStorageSync("haveLocation", true);
            this.latitude = res.latitude;
            this.longitude = res.longitude;
            if (!areaSearch) {
              this.loadCity(res.longitude, res.latitude);
            }
          },
        });
      }, 4000);
    }
    uni.getLocation({
      type: "gcj02",
      success: async (res) => {
        uni.setStorageSync("haveLocation", true);
        this.latitude = res.latitude;
        this.longitude = res.longitude;
        if (!areaSearch) {
          this.loadCity(res.longitude, res.latitude);
        }
      },
    });
  },
  onPullDownRefresh() {
    this.pages.pageNo = 1;
    this.getPlotInfo();
  },
  onReachBottom() {
    if (this.pages.pageNo * this.pages.pageSize < this.total) {
      this.pages.pageNo++;
      this.getPlotInfo();
    }
  },
  onLoad() {
    uni.removeStorageSync("areaSearch");
    uni
      .createSelectorQuery()
      .select(".nav-box")
      .boundingClientRect((data) => {
        this.navBarHeight = data.height;
      })
      .exec();
    uni
      .createSelectorQuery()
      .select(".index-top")
      .boundingClientRect((data) => {
        this.topHeight = data.height;
      })
      .exec();
  },
};
</script>
<style lang="scss" scoped>
image {
  width: auto;
  height: auto;
}

.index-box {
  padding-bottom: 180rpx;
  min-height: 100vh;
  box-sizing: border-box;

  .index-top {
    max-height: 120rpx;
    position: sticky;
    z-index: 999;

    .index-top-box {

      .search-box {
        flex: 1;
      }

      .location-box {

        span {
          flex: 1;
          display: inline-block;
        }

        .location-icon {
          margin-left: 8rpx;
          height: 14rpx;
        }
      }
    }
  }

  .swiper-box {
    padding: 0 20rpx;
    min-height: 190rpx;

    ::v-deep swiper {
      z-index: 10;
      position: relative;
    }

    ::v-deep .u-swiper__indicator {
      z-index: 10;
    }
  }

  .menu-box {
    transform: translateY(-35px);
    margin-bottom: 20rpx;
    padding: 104rpx 36rpx 22rpx;

    .menu-list {

      .menu-item {

        .menu-img-box {
          position: relative;
        }

        .menu-img {
          height: 46rpx;
          margin-bottom: 10rpx;
        }

        .menu-bg {
        }
      }
    }
  }

  .content-box {
    transform: translateY(-35px);
    padding: 20rpx 26rpx;

    .content-list {
      margin-bottom: 20rpx;

      .content-item {

        .title-box {
          display: flex;
          align-items: center;
          justify-content: space-between;

          .title {
          }

          .area-box {
            display: flex;
            align-items: center;
            font-size: 28rpx;

            .area-km {
              margin-left: 4rpx;
            }

            image {
              width: 36rpx;
              height: 36rpx;
              margin-right: 12rpx;
            }
          }
        }

        .price-box {
          position: absolute;
          top: 80rpx;
          right: 24rpx;
          font-weight: 600;
          font-size: 40rpx;

          .unit {
            font-weight: 400;
            font-size: 20rpx;
            margin-right: 6rpx;
          }

          span {
            font-weight: 400;
            margin-left: 6rpx;
            font-size: 26rpx;
          }
        }

        .info-box {
          margin-top: 22rpx;

          .info-list {
            .info-item {
              margin-bottom: 20rpx;
              display: flex;
              align-items: center;

              .info-content {
                font-size: 26rpx;
                line-height: 34rpx;

                span {
                  margin: 0 6rpx;
                  font-weight: 600;
                  font-size: 30rpx;
                }
              }

              .info-icon {
              }
            }
          }
        }
      }
    }
  }

  .fixed-box {
    display: flex;
    align-items: center;
    justify-content: center;
    bottom: 320rpx;
    position: fixed;
    z-index: 999;
    right: 20rpx;

    image {
      width: 100rpx;
      height: 100%;
    }
  }
}
</style>
