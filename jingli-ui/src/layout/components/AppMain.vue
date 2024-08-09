<template>
  <section
    class="app-main"
    :style="{
      height: fixedHeader
        ? 'calc(100vh)'
        : needTagsView
        ? 'calc(100vh - 80px)'
        : 'calc(100vh - 50px)',
      '--padding-top': needTagsView ? '80px' : '50px',
    }"
  >
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" />
    </transition>
  </section>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "AppMain",
  computed: {
    key() {
      return this.$route.path;
    },
    ...mapState({
      theme: (state) => state.settings.theme,
      sidebar: (state) => state.app.sidebar,
      device: (state) => state.app.device,
      needTagsView: (state) => state.settings.tagsView,
      fixedHeader: (state) => state.settings.fixedHeader,
    }),
  },
};
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  height: calc(100vh - 80px);
  width: 100%;
  position: relative;
  overflow-y: auto;
}
.fixed-header + .app-main {
  padding-top: var(--padding-top);
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
