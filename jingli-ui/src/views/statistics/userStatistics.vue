<template>
  <div class="userStatisticsMain">
    <el-scrollbar style="width:100%;height: 100%;">
      <div
          class="top"
          v-loading="tabLoading"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(255, 255, 255, 1)"
      >
        <div class="content-box">
          <div class="topHeader">
            <div class="titleBox">
              <BlockTitle :title="'数据概览'" />
            </div>
          </div>
          <div class="content-top">
            <div
                class="topItem"
                v-for="(item, index) in isAdmin
                ? topInfoArray
                : topInfoArray.slice(1)"
                :key="index"
                @click="openUrl(item.url)"
            >
              <img :src="item.img" alt="" />
              <div class="flex-right">
                <div class="topItemName">
                  {{ item.name }}
                </div>
                <div class="topItemValue">
                  {{ item.value }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <template v-if="isAdmin">
        <div
            class="chart"
            v-loading="chartLoading"
            element-loading-text="拼命加载中"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(255, 255, 255, 1)"
        >
          <div class="content-box">
            <div class="titleBox">
              <BlockTitle :title="'销售统计'" />
            </div>
            <div class="content">
              <div ref="chart" style="width:100%;height: 100%;"></div>
            </div>
          </div>
        </div>
      </template>
    </el-scrollbar>
  </div>
</template>

<script>
//引入接口
import {
  getUsersAndSales,
  getSalesDetail,
  getSalesData,
  getPilesInfo,
} from "@/api/statistics";
import { getPlotList } from "@/api/plot";
// 引入title组件
import BlockTitle from "@/components/blockTitle/blockTitle";
import MyStorage from "@/utils/cache";

export default {
  components: {
    BlockTitle,
  },
  data() {
    return {
      topInfoArray: [
        {
          name: "累计交易额(元)",
          value: "",
          prop: "sumSales",
          url: "#",
          img: require("../../assets/index/index_1.png"),
        },
        {
          name: "累计订单数 (个)",
          value: "",
          prop: "sumCount",
          url: "/orderList",
          img: require("../../assets/index/index_2.png"),
        },
        {
          name: "退款金额（元）",
          value: "",
          prop: "reSales",
          url: "/refund",
          img: require("../../assets/index/index_3.png"),
        },
        {
          name: "累计注册用户",
          value: "",
          prop: "userCount",
          url: "/registerUser",
          img: require("../../assets/index/index_4.png"),
        },
        {
          name: "累计充值（元）",
          value: "",
          prop: "rechargeAmount",
          url: "#",
          img: require("../../assets/index/index_5.png"),
        },
        {
          name: "总站点数",
          value: "",
          prop: "plotList",
          url: "/device/plotList",
          img: require("../../assets/index/index_6.png"),
        },
        {
          name: "总设备数",
          value: "",
          prop: "deviceList",
          url: "/device/deviceList",
          img: require("../../assets/index/index_7.png"),
        },
        {
          name: "累计充电量(度)",
          value: "",
          prop: "totalPowerConsumption",
          url: "#",
          img: require("../../assets/index/index_8.png"),
        },
        {
          name: "运行设备",
          value: "",
          prop: "onLine",
          url: "#",
          img: require("../../assets/index/index_9.png"),
        },
        {
          name: "离线设备",
          value: "",
          prop: "offLine",
          url: "#",
          img: require("../../assets/index/index_10.png"),
        },
      ],
      isAdmin: MyStorage.getItem("admin") == "Y",
      topInfoObject: {},
      myChart: "",
      barData: [],
      barData2: [],
      lineData: [],
      lineData2: [],
      lineData3: [],
      xData: [],
      tableLoading: true,
      chartLoading: true,
      chart2Loading: true,
      tabLoading: true,
      page_device: 1,
      pageSize_device: 10,
      total_device: 0,
      myChart2: "",
      amountLine: [],
      reAmountLine: [],
      amountXdata: [],
    };
  },
  async mounted() {
    await getSalesData().then((res) => {
      this.tabLoading = true;
      this.topInfoObject = Object.assign(this.topInfoObject, res.data);
    });
    await getUsersAndSales().then((res) => {
      this.topInfoObject = Object.assign(this.topInfoObject, res.data);
      this.tabLoading = false;
    });
    await this.getPilesInfo();
    await this.topInfoArrayFun();
    if (this.isAdmin) {
      await this.getSalesData();
    }
    await this.getPlotList();
  },
  methods: {
    openUrl(url) {
      this.$router.push(url);
    },
    formatTimestamps(timestamps, format) {
      var date = new Date(timestamps);
      var finalstr = format;
      finalstr = finalstr.replace("yyyy", date.getFullYear()); //年
      finalstr = finalstr.replace("mm", this.formatNum(date.getMonth() + 1)); //月
      finalstr = finalstr.replace("dd", this.formatNum(date.getDate())); //天
      finalstr = finalstr.replace("hh", this.formatNum(date.getHours())); //时
      finalstr = finalstr.replace("mi", this.formatNum(date.getMinutes())); //分
      finalstr = finalstr.replace("ss", this.formatNum(date.getSeconds())); //秒
      return finalstr;
    },
    formatNum(arg0) {
      let str = arg0.toString();
      if (str.length == 1) {
        return "0" + str;
      } else {
        return str;
      }
    },
    getPlotList() {
      getPlotList({ pageNo: 1, pageSize: 10 }).then((res) => {
        this.topInfoArray[5].value = res.data.total;
      });
    },
    getPilesInfo() {
      this.tableLoading = true;
      getPilesInfo({
        pageNo: this.page_device,
        pageSize: this.pageSize_device,
      }).then((res) => {
        this.topInfoArray[6].value = res.data.total;
        this.total_device = res.data.total;
        this.tableLoading = false;
      });
    },
    getSalesData() {
      this.chartLoading = true;
      getSalesDetail({
        pageNo: 1,
        pageSize: 7,
      }).then((res) => {
        this.barData = res.data.records.map((item) => {
          return item.sumCount;
        });
        this.barData2 = res.data.records.map((item) => {
          return item.validCount;
        });
        this.lineData = res.data.records.map((item) => {
          return item.sumSales;
        });
        this.lineData2 = res.data.records.map((item) => {
          return item.reSales;
        });
        this.lineData3 = res.data.records.map((item) => {
          return item.reCount;
        });
        this.xData = res.data.records.map((item) => {
          return item.createTime;
        });
        this.chartLoading = false;
        this.barChart();
      });
    },
    barChart() {
      this.myChart = this.$echarts.init(this.$refs.chart);
      let option = {
        grid: {
          top: "15%",
          bottom: "10%",
          left: "2%",
          right: "0%",
        },
        tooltip: {
          trigger: "axis",
          backgroundColor: "rgba(255,255,255,0.9)",
          padding: 10,
          className: "echarts-tooltip",
          textStyle: {
            color: "#000",
          },
        },
        legend: {
          show: false,
          data: ["订单金额", "订单数量"],
          top: "0%",
          right: "5%",
          textStyle: {
            color: "#ffffff",
          },
        },
        xAxis: {
          type: "category",
          data: this.xData,
          axisLine: {
            show: true, //隐藏X轴轴线
            lineStyle: {
              color: "#5A6883",
            },
          },
          axisTick: {
            show: false, //隐藏X轴刻度
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: "#5A6883", //X轴文字颜色
            },
          },
        },
        yAxis: {
          type: "value",
          splitLine: {
            show: true,
            lineStyle: {
              type: "dotted",
            },
          },
          axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          axisLabel: {
            show: true,
            formatter: "{value}", //右侧Y轴文字显示
            textStyle: {
              color: "#5A6883",
            },
          },
        },
        series: [
          {
            name: "销售总额",
            type: "line",
            smooth: false, //平滑曲线显示
            showAllSymbol: true, //显示所有图形。
            symbol: "circle", //标记的图形为实心圆
            symbolSize: 10, //标记的大小
            itemStyle: {
              //折线拐点标志的样式
              color: "#8B23BD",
            },
            lineStyle: {
              color: "#8B23BD",
              width: 3,
            },
            data: this.lineData,
          },
          {
            name: "退款总额",
            type: "line",
            smooth: false, //平滑曲线显示
            showAllSymbol: true, //显示所有图形。
            symbol: "circle", //标记的图形为实心圆
            symbolSize: 10, //标记的大小
            itemStyle: {
              //折线拐点标志的样式
              color: "#EB6100",
            },
            lineStyle: {
              color: "#EB6100",
              width: 3,
            },
            data: this.lineData2,
          },
          {
            name: "退款订单数",
            type: "line",
            smooth: true, //平滑曲线显示
            showAllSymbol: true, //显示所有图形。
            symbol: "circle", //标记的图形为实心圆
            symbolSize: 10, //标记的大小
            itemStyle: {
              //折线拐点标志的样式
              color: "#DF2525",
            },
            lineStyle: {
              color: "#DF2525",
              width: 3,
            },
            data: this.lineData3,
          },
          {
            name: "订单数",
            type: "bar",
            barWidth: 15,
            itemStyle: {
              normal: {
                color: "#1A5CD6",
              },
            },
            data: this.barData,
          },
          {
            name: "有效订单数",
            type: "bar",
            barWidth: 15,
            itemStyle: {
              normal: {
                color: "#148C77",
              },
            },
            data: this.barData2,
          },
        ],
      };
      this.myChart.setOption(option);
      window.addEventListener("resize", () => {
        this.myChart.resize();
      });
    },
    topInfoArrayFun() {
      this.topInfoArray.forEach((currentItem) => {
        for (const key in this.topInfoObject) {
          if (key == currentItem.prop) {
            currentItem.value = this.topInfoObject[key];
          }
        }
      });
    },
    handleCurrentChangeDevice(val) {
      this.page_device = val;
      this.getPilesInfo();
    },
    exporthandler() {},
  },
};
</script>

<style lang="scss" scoped>
.userStatisticsMain {
  background-color: #eceef1;
  width: 100%;
  position: relative;

  .content-box {
    height: 100%;
  }

  .downloadExcel {
    font-size: 14px;
    margin-right: 5px;
    cursor: pointer;
  }

  .downloadExcel:hover {
    color: #1a5cd6;
  }

  ::v-deep .el-scrollbar__view {
    > div {
      margin-bottom: 20px;
    }

    padding: 20px;
    overflow-x: hidden;
  }

  .top {
    width: 100%;

    .content-top {
      width: 100%;
      margin-top: 16px;
      display: flex;
      flex-wrap: wrap;
      align-items: center;

      .topItem {
        img {
          padding-left: 10px;
          height: 66px;
          height: 66px;
          margin-right: 16px;
        }

        &:nth-child(5n) {
          margin-left: 0;
        }

        margin-right: 16px;
        background-color: #fff;
        padding: 10px;
        width: calc(20% - 16px);
        margin-bottom: 16px;
        height: 135px;
        display: flex;
        align-items: center;
        font-size: 26px;
        color: #666666;

        .topItemName {
          font-size: 16px;
          color: #000000;
          opacity: 0.45;
          margin-bottom: 20px;
        }
      }
    }
  }

  .chart {
    height: 400px;
  }

  .chart2 {
    height: 400px;
  }

  .table2 {
    width: 100%;
  }

  .topHeader {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .titleBox {
    width: 200px;
    height: 20px;
  }

  .content {
    padding: 20px;
    background-color: #fff;
    height: calc(100% - 50px);
    width: 100%;
    margin: 25px 0;
  }

  .num-text {
    font-size: 14px;
    color: #337ab7;
  }

  ::v-deep .el-scrollbar__wrap {
    overflow-x: hidden;
  }
}

::v-deep .el-dialog {
  width: 20%;

  .el-button--primary {
    padding: 0 !important;

    a {
      display: inline-block;
      padding: 12px 20px;
    }
  }
}

.echarts-tooltip {
  box-shadow: 0px 2px 6px 0px rgba(175, 175, 175, 0.84);
}
</style>
