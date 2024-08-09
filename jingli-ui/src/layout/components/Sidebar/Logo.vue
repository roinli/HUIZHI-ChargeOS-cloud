<template>
  <div class="sidebar-logo-container" :class="{ collapse: collapse }">
    <transition name="sidebarLogoFade">
      <router-link
        v-if="collapse"
        key="collapse"
        class="sidebar-logo-link"
        to="/"
      >
        <img src="@/assets/logo-title.png" class="sidebar-logo" />
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <img src="@/assets/login-title.png" class="sidebar-title" />
      </router-link>
    </transition>
  </div>
</template>

<script>
import { getInfoByKey } from "@/api/config";
export default {
  name: "SidebarLogo",
  props: {
    collapse: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {
      title: "",
    };
  },
  mounted() {
    this.getConfigData("sys_title");
  },
  methods: {
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
  },
};
</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: var(--current-color);
  overflow: hidden;
  .else-link {
    text-align: left !important;
    padding-left: 25px;
  }

  & .sidebar-logo-link {
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 17px;
      height: 17px;
      vertical-align: middle;
      margin-right: 7px;
    }

    & .sidebar-title {
      width: 165px;
      height: 23px;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>
