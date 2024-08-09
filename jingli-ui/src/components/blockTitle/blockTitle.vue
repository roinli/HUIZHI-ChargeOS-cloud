<template>
  <div class="title">
    <span class="block"></span>
    <span class="text">{{ title }}</span>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: "",
    },
  },
};
</script>

<style lang="scss" scoped>
.title {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  & > span {
    display: inline-block;
  }
  .text {
    font-size: 24px;
    color: #000000;
  }
}
</style>
