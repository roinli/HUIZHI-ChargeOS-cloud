<template>
  <div ref="rightPanel" class="rightPanel-container">
    <div v-if="show" class="rightPanel-background"></div>
    <div class="rightPanel" :style="{ width: show ? '100%' : 0 }">
      <div
        class="handle-button"
        @click="handleClick"
        :style="{
          'background-color': theme,
          top: '250px',
        }"
      >
        <i v-if="!show" class="el-icon-s-tools"></i>
        <i v-else class="el-icon-close"></i>
      </div>
      <div class="rightPanel-items">
        <slot />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "RightPanel",
  props: {
    clickNotClose: {
      default: false,
      type: Boolean,
    },
  },
  computed: {
    ...mapState({
      theme: (state) => state.settings.theme,
    }),
    show: {
      get() {
        return this.$store.state.settings.showSettings;
      },
      set(val) {
        this.$store.dispatch("settings/changeSetting", {
          key: "showSettings",
          value: val,
        });
      },
    },
  },
  watch: {
    show(value) {
      if (value && !this.clickNotClose) {
        // this.addEventClick();
      }
    },
  },
  mounted() {
    // this.addEventClick();
  },
  beforeDestroy() {
    const elx = this.$refs.rightPanel;
    elx.remove();
  },
  methods: {
    handleClick() {
      this.show = !this.show;
    },
    addEventClick() {
      window.addEventListener("click", this.closeSidebar);
    },
    closeSidebar(evt) {
      const parent = evt.target.closest(".el-drawer__body");
      if (!parent) {
        this.show = false;
        window.removeEventListener("click", this.closeSidebar);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.handle-button {
  transition: all 0.3s;
  width: 48px;
  height: 48px;
  position: absolute;
  text-align: center;
  font-size: 24px;
  border-radius: 6px 0 0 6px !important;
  z-index: 0;
  pointer-events: auto;
  cursor: pointer;
  color: #fff;
  line-height: 48px;
}
.rightPanel-background {
  z-index: 20000;
  opacity: 1;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  transition: opacity 0.3s cubic-bezier(0.7, 0.3, 0.1, 1);
  background: rgba(0, 0, 0, 0.2);
}

.rightPanel {
  width: 100%;
  max-width: 260px;
  height: 100vh;
  position: absolute;
  top: 0;
  right: 0;
  box-shadow: 0px 0px 15px 0px rgba(0, 0, 0, 0.05);
  transition: all 0.25s cubic-bezier(0.7, 0.3, 0.1, 1);
  // transform: translate(100%);
  background: #fff;
  z-index: 40000;
}

.handle-button {
  width: 48px;
  height: 48px;
  position: absolute;
  left: -48px;
  text-align: center;
  font-size: 24px;
  border-radius: 6px 0 0 6px !important;
  z-index: 0;
  pointer-events: auto;
  cursor: pointer;
  color: #fff;
  line-height: 48px;
  i {
    font-size: 24px;
    line-height: 48px;
  }
}
</style>
