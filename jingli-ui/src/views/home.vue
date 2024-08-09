<template>
  <div class="home home-box">
    <iframe :src="url" width="100%" height="1000px"  scrolling="no" id="myiframe"  frameborder="0"></iframe>
    <el-dialog :close-on-click-modal="false"  :show-close="false" :visible.sync="centerDialogVisible" width="44%" center>
      <div style="display:flex;justify-content:space-around;align-items:center;">
        <div style="width:18%;text-align:center">
          <img style="width:100%;" src="../../public/images/gzh.png" alt="">
          <div style="padding-top:20px">公众号二维码</div>
        </div>
        <div style="width:18%;text-align:center">
          <img style="width:100%;" src="../../public/images/xcx.png" alt="">
          <div style="padding-top:20px">小程序二维码</div>
        </div>
        <div style="width:18%;text-align:center">
          <img style="width:100%;" src="../../public/images/khd.png" alt="">
          <div style="padding-top:20px">客服二维码</div>
        </div>
      </div>
      <img style="position:absolute;bottom: -60px;left: 48%;width: 30px;height: 30px;cursor:pointer;" src="../../public/images/close.png" @click="centerDialogVisible = false"/>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      centerDialogVisible : false,
      url: process.env.VUE_APP_BASE_API + '/jingli-admin/index'
    }
  },
  created() {
    if(this.$route.query.isShow)this.centerDialogVisible = true
  },
}
function changeFrameHeight(){
  var ifm= document.getElementById("myiframe");
  ifm.height=document.documentElement.clientHeight;
}
window.onresize=function(){
  changeFrameHeight();
  this.centerDialogVisible = true;
}

</script>
<style lang="scss" scoped>
  #app .main-container{
    background: #ECEEF1;
  }

</style>
<style>
.home-box .el-dialog{
  margin-top: 14% !important;
  border-radius: 10px;
  background: #fff !important;
}
.el-popup-parent--hidden .v-modal{
  opacity: 0.8 !important;
}
</style>
