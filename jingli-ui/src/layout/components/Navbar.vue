<template>
  <div class="navbar">
    <div class="left-menu">
      <hamburger
        style="padding-left: 0px"
        id="hamburger-container"
        :is-active="sidebar.opened"
        class="hamburger-container"
        @toggleClick="toggleSideBar"
      />
      <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
    </div>
    <div class="right-menu">
      <search id="header-search" class="right-menu-item" />
      <Screenfull id="screenfull" class="right-menu-item hover-effect" />
      <el-tooltip content="布局大小" effect="dark" placement="bottom">
        <size-select id="size-select" class="right-menu-item hover-effect" />
      </el-tooltip>
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <div><i class="el-icon-arrow-down" />&nbsp;&nbsp;{{ adminName }}</div>
          <img src="../../assets/touxiang.png" class="user-avatar" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <el-dropdown-item>
            <span style="display:block;" @click="logout">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import { mapGetters } from "vuex";
import Hamburger from "@/components/Hamburger";
import Search from "@/components/HeaderSearch";
import Screenfull from "@/components/Screenfull";
import SizeSelect from "@/components/SizeSelect";
import ErrorLog from "@/components/ErrorLog";

export default {
  data() {
    return {
      dlogo: this.global.apiUrl + "dlogo.png",
      adminName: "",
    };
  },
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    SizeSelect,
    ErrorLog,
    Search,
  },
  computed: {
    ...mapGetters(["sidebar", "avatar", "device"]),
  },

  methods: {
    handleClickOutside() {
      this.$emit("toggleClick");
    },
    toggleSideBar() {
      this.$store.dispatch("app/toggleSideBar");
    },
    async logout() {
      await this.$store.dispatch("user/logout");
      location.href = `#/login?redirect=${this.$route.fullPath}`;
    },
  },
  created() {
    this.adminName = this.$store.state.user.name;
  },
};
</script>

<style lang="scss" scoped>
// #081220
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }
  .left-menu {
    left: 0;
    display: flex;
    align-items: center;
    padding: 0 20px;
    img {
      height: 30px;
      width: 71px;
    }
  }
  .right-menu {
    height: 100%;
    display: flex;
    align-items: center;
    line-height: 50px;

    .svg-box {
      font-size: 20px;
      margin-right: 16px;
    }
    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      margin-right: 16px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        color: #5a5e66;
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;
      height: 100%;
      .avatar-wrapper {
        position: relative;
        height: 100%;
        display: flex;
        font-size: 14px;
        justify-content: space-between;
        align-items: center;
        color: #666666;
        font-weight: 500;
        cursor: pointer;
        div:nth-child(1) {
          margin-right: 20px;
        }
        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 50%;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
