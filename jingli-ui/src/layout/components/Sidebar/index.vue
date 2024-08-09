<template>
  <div :class="{ 'has-logo': showLogo } + ' sidbar'">
    <!-- <div class="sidbar-title" v-if="!isCollapse&&showLogo">
      <img src="@/assets/logo-title.png" alt="" />
      <span> {{ this.title }}</span>
    </div> -->
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="theme"
        :collapse-transition="false"
        mode="vertical"
        ref="menu"
      >
        <sidebar-item
          v-for="route in routes"
          :key="route.path"
          :item="route"
          :base-path="route.path"
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters,mapState } from "vuex";
import store from "@/store";
import Logo from "./Logo";
import SidebarItem from "./SidebarItem";
import variables from "@/styles/variables.scss";

export default {
  components: { SidebarItem, Logo },
  computed: {
    ...mapGetters(["sidebar", "addRouters"]),
    ...mapState({
      theme: (state) => state.settings.theme,
    }),
    routes() {
      return store.getters.routers;
    },
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    variables() {
      return variables;
    },
    isCollapse() {
      return !this.sidebar.opened;
    },
    handleSelect(e) {
    },
  },
};
</script>
<style>
.sidbar .el-scrollbar__view {
  margin-bottom: 140px !important;
}
</style>
