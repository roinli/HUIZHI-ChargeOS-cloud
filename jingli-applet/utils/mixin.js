import app from '@/static/js/app.js';
export const mixin = {
  data() {
    return {
      share: {
        // 转发的标题
        title: "李文慧的充电桩物联网系统",
        // 转发的路径，默认是当前页面，必须是以‘/’开头的完整路径，/pages/index/index
        path: "",
        //自定义图片路径，可以是本地文件路径、代码包文件路径或者网络图片路径，
        //支持PNG及JPG，不传入 imageUrl 则使用默认截图。显示图片长宽比是 5:4
		mpId: app.appid,
        imageUrl: "",
      },
    };
  },
  // 分享到微信
  onShareAppMessage: function () {
    // 获取加载的页面
    //转发参数
    return "";
  },
  // 分享到朋友圈
  onShareTimeline() {
    // 获取加载的页面
      return "";
  },
};
