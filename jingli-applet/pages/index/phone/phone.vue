<template>
  <view class="main">
    <view class="main-swiper">
      <swiper
        class="swiper"
        circular
        indicator-dots
        autoplay
        :interval="5000"
      >
      </swiper>
    </view>
    <view class="login-info">
      <view class="login-info-item">
        <view>
          <button
            open-type="getPhoneNumber|agreePrivacyAuthorization"
            @getphonenumber="getPhoneNumber"
            class="button"
          >
            手机号快捷登录
          </button>
        </view>
        <!-- #endif -->
      </view>
    </view>
    <u-loading-page
      :loading="loading"
      :loading-text="loadingText"
    ></u-loading-page>
  </view>
</template>
<script>
import {
  appletWeChatLogin,
  appletBindMobile,
  getAccountInfo,
} from "@/config/api.js";
export default {
  data() {
    return {
      access_token: "",
      phoneNumber: "",
      headImage: "",
      openid: "",
      loading: false,
      loadingText: "",
      platform: "",
    };
  },
  onLoad() {
    let port = uni.getSystemInfoSync().platform;
    this.platform = port;
    wx.getPrivacySetting({
      success: (res) => {
        if (res.needAuthorization) {
          // 需要弹出隐私协议
          this.showPrivacy = true;
        } else {
        }
      },
      fail: () => {},
      complete: () => {},
    });
  },
  onHide() {},
  methods: {
    getPhoneNumber(e) {
      if (e.detail.code) {
        var that = this;
        uni.login({
          provider: "weixin",
          success: (res) => {
            let params = {};
            params.code = res.code;
            uni.getUserInfo({
              provider: "weixin", //使用微信登录
              success: function (loginRes) {
                if (loginRes && loginRes.errMsg == "getUserInfo:ok") {
                  params.encryptedData = loginRes.encryptedData;
                  params.iv = loginRes.iv;
                  that.doLogin(params, e);
                }
              },
              fail: function (err) {
                that.doLogin(url, e);
              },
            });
          },
        });
      }
    },

    doLogin(param, e) {
      let that = this;
      appletWeChatLogin({
        params: param,
      })
        .then((result) => {
          this.openid = result.result.openId;
          this.id = result.result.id;
          uni.setStorageSync("openid", this.openid);
          uni.setStorageSync("id", this.id);
          if (result.result.unionId) {
            uni.setStorageSync("unionId", result.result.unionId);
          }
          if (result.result.mobile) {
            uni.setStorageSync("mobile", result.result.mobile);
          }
          appletBindMobile({
            params: {
              code: e.detail.code,
              search_openId: this.openid,
            },
          })
            .then((res) => {
              if (res.code == 0) {
                this.getUserInfo();
              }
            })
            .catch((err) => {
              uni.showToast({
                title: "绑定手机号失败:" + JSON.stringify(err),
                icon: "error",
              });
            });
        })
        .catch((err) => {
          uni.showToast({
            title: "微信登录失败:" + JSON.stringify(err),
            icon: "error",
          });
        });
    },
    getUserInfo() {
      var that = this;
      getAccountInfo({
        params: {
          search_openId: this.openid,
        },
      })
        .then((res) => {
          uni.setStorageSync("mobile", res.result.mobile);
          uni.setStorageSync("userName", res.result.userName);
          if (uni.getStorageSync("mobile") !== "") {
            uni.switchTab({
              url: "/pages/index/indexNew",
            });
          } else {
            uni.showToast({
              title: "网络错误！请重新获取手机号",
              icon: "error",
            });
          }
        })
        .catch((err) => {
          uni.showToast({
            title: "网络错误！" + JSON.stringify(err),
            icon: "error",
          });
        });
    },
  },
};
</script>

