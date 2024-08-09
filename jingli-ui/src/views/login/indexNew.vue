<template>
  <div class="container-page">
    <div class="container-des">
      <img src="@/assets/login/logo.png" alt="" />
      <span>{{ title }}</span>
    </div>
    <div class="page-left">
      <div class="content-form">
        <div class="wrap-form" :style="{ width: width }">
          <div class="form-title">{{ title }}</div>
          <div class="form-tabs">账号密码</div>
          <el-form
            ref="loginForm"
            :model="loginForm"
            :rules="loginRules"
            auto-complete="on"
            label-position="left"
          >
            <el-form-item prop="username" class="login-form-item">
              <el-input
                ref="username"
                v-model="loginForm.username"
                placeholder="请输入手机号"
                name="username"
                type="text"
                tabindex="1"
                auto-complete="on"
              />
            </el-form-item>

            <el-form-item prop="password" class="login-form-item">
              <el-input
                :key="passwordType"
                ref="password"
                v-model="loginForm.password"
                :type="passwordType"
                placeholder="请输入密码"
                name="password"
                tabindex="2"
                auto-complete="on"
                @keyup.enter.native="handleLogin"
              />
            </el-form-item>
            <div class="btn-box">
              <el-button
                :loading="loading"
                type="primary"
                class="loginBtn"
                @click.native.prevent="handleLogin"
                >登录</el-button
              >
            </div>
          </el-form>
        </div>
      </div>
      <div class="info-email-phone" :style="{ width: width }">
        <span class="info-phone">
          <img src="@/assets/login/call_black.svg" alt="" />
          <span class="info-email-phone-font">18601938676</span>
        </span>
        <span class="info-email">
          <img src="@/assets/login/wx_black.svg" alt="" />
          <span class="info-email-phone-font">18601938676</span>
        </span>
      </div>
      <div class="c-btn-img">
        <img src="@/assets/login/1.svg" class="btm-img" alt="" />
      </div>
      <div class="btm-desc">
        <div>Copyright: 2022-2023 充电桩云平台</div>
        <div class="btm-desc-right">陇ICP备 17005862号</div>
      </div>
    </div>
    <div class="page-right" v-if="!hiddenRight">
      <div class="container-font">
        <div class="font">
          开源充电桩云平台（v2.3.4）支持
          前端uniapp（H5、小程序）、采集端、运营端、代理商端、充电桩硬件（电动自行车、电动汽车）全业务场景，平台目前服务企业1000+，采用SpringBoot、SpringCloud、MySQL、Netty、MQTT、支付宝支付、微信支付、微信退款、支付宝退款等技术栈
        </div>
      </div>
      <img src="@/assets/login/login_page.svg" class="right-img2" alt="" />
    </div>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import { getInfoByKey } from "@/api/config";

export default {
  name: "LoginNew",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("请输入正确的手机号"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码最少为6位"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: "",
        password: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
      isShowQR: false,
      title: "",
      qrUrl: "http://qn.foxchg.com/_f7ee0e228a024eec877a798388c0122e.png",
      width: "455px",
      hiddenRight: false,
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },

  created() {
    this.getConfigData("sys_title");
  },
  mounted() {
    this.getScreenSize();
    window.onresize = () => {
      this.getScreenSize();
    };
  },
  methods: {
    getScreenSize() {
      this.screenWidth = window.innerWidth;
      this.screenHeight = window.innerHeight;
      if (this.screenWidth < 900) {
        this.hiddenRight = true;
      } else {
        this.hiddenRight = false;
      }

      if (this.screenWidth < 550) {
        this.width = "100%";
      } else {
        this.width = "455px";
      }
    },
    getConfigData(value) {
      let param = {
        configKey: value,
      };
      getInfoByKey(param).then((res) => {
        if (res.data) {
          this.title = res.data;
        }
      });
    },

    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container-page {
  display: flex;
  justify-content: flex-start;
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: relative;
  .container-des {
    position: absolute;
    left: 1.7vw;
    top: 1.7vw;
    z-index: 100;
    display: flex;
    align-items: center;
    font-size: 20px;
    color: #333333;
    img {
      width: 25px;
      height: 25px;
      margin-right: 10px;
    }
  }
  .page-left {
    flex-grow: 1;
    background-color: #f3f6ff;
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .content-form {
      width: 100%;
    }
    .wrap-form {
      margin: 0 auto;
      background: #ffffff;
      box-shadow: 0px 5px 13px 0px rgba(183, 195, 219, 0.53);
      border-radius: 10px;
      padding: 45px 49px 50px 49px;
      box-sizing: border-box;
      .form-tabs {
        width: 58px;
        border-bottom: 1px solid #5365ea;
        padding-bottom: 9px;
        margin-bottom: 28px;
        font-size: 14px;
        color: #5365ea;
      }
      .form-title {
        font-size: 30px;
        color: #333333;
        margin-bottom: 35px;
      }
      ::v-deep .el-form {
        .el-form-item__label {
          font-size: 16px;
          color: #000000;
        }
        .el-input__inner {
          background-color: #fff;
          font-size: 16px;
          height: 48px;
          line-height: 48px;
          // border: none;
          color: #999999;
        }
        .el-form-item__content {
          // border-bottom: 1px solid #f5f5f5;
          padding-bottom: 10px;
        }
      }
      .el-button {
        margin-top: 40px;
        width: 100%;
        height: 50px;
        background: #3b57c7;
        border-radius: 3px;
        font-size: 18px;
        color: #ffffff;
      }
    }
    .info-email-phone {
      margin: 24px 20px;
      color: #7c7fa5;
      display: flex;
      justify-content: space-between;
      position: relative;
      z-index: 1;
      .info-email,
      .info-phone {
        display: flex;
        align-items: center;
      }
      img {
        margin-right: 4px;
        width: 20px;
        height: 20px;
      }
    }
    .c-btn-img {
      position: absolute;
      bottom: 0;
      width: 100%;
      .btm-img {
        width: 100% !important;
      }
    }
    .btm-desc {
      width: 100%;
      position: absolute;
      bottom: 1.2vw;
      display: flex;
      justify-content: space-between;
      padding-left: 1.7vw;
      padding-right: 1.7vw;
      font-size: 14px;
      color: #666666;
    }
  }
  .page-right {
    width: 500px;
    position: relative;
    overflow: hidden;
    .container-font {
      padding: 10px 16px;
      background-color: #fff;
      z-index: 999;
      width: 400px;
      height: 156px;
      background-image: url("../../assets/login/2.svg");
      background-repeat: no-repeat;
      background-size: 100%;
      position: absolute;
      top: 11%;
      left: 50%;
      transform: translate(-50%, 0);
      color: #5365ea;
      font-size: 14px;
      line-height: 24px;
      font-weight: 500;
    }
    .right-img2 {
      position: absolute;
      bottom: 3.9%;
      left: 50%;
      transform: translate(-50%, 0);
      width: 415px;
      height: 350px;
    }
  }
}
</style>
