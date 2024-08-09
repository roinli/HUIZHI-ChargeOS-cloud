<template>
  <div class="main">
    <div class="content-box">
      <div class="title">
        <el-input
          maxlength="15"
          style="width:300px;margin-left:20px;"
          placeholder="搜索设备名/站点名/设备编号"
          v-model="keyWord"
        >
          <el-button
            slot="suffix"
            @click="searchData"
            type="primary"
            icon="el-icon-search"
            style="border-radius:0 4px 4px 0;margin-right:-5px"
            >搜索
          </el-button>
        </el-input>
      </div>
    </div>
    <div class="content-box">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column
          type="index"
          label="序号"
          align="center"
          width="80"
        ></el-table-column>
        <el-table-column
          prop="id"
          label="充电桩编号"
          align="center"
          min-width="80"
        ></el-table-column>

        <el-table-column
          prop="pkname"
          label="充电桩名称"
          align="center"
          min-width="100"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="pileType"
          align="center"
          label="充电桩类型 "
          :formatter="cpPileType"
          min-width="80"
        ></el-table-column>

        <el-table-column
          prop="plotName"
          align="center"
          label="站点名称  "
          min-width="80"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="realName"
          align="center"
          label="所属代理商  "
          min-width="80"
        ></el-table-column>
        <el-table-column
          prop="pileStatus"
          align="center"
          label="状态 "
          :formatter="cpPileStatus"
          min-width="80"
        ></el-table-column>

        <el-table-column
          prop="runningStatus"
          align="center"
          label="运行状态 "
          :formatter="forState"
          min-width="80"
        ></el-table-column>

        <el-table-column
          prop="totalPowerConsumption"
          label="总耗电量"
          align="center"
          min-width="80"
        ></el-table-column>
      </el-table>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-size="pageSize"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import { getChargingPileList } from "@/api/device";

export default {
  data() {
    return {
      keyWord: "",
      tableData: [],
      page: 1,
      pageSize: 10,
      loading: true,
      total: 0,
    };
  },
  created() {
    this.getDataList();
  },
  watch: {},
  methods: {
    forState(row) {
      if (row.runningStatus == 0) {
        return "运行";
      } else if (row.runningStatus == 1) {
        return "离线";
      }
    },
    cpFeeType(row) {
      if (row.cpFeeType == 2001) {
        return "收费";
      } else if (row.cpFeeType == 2002) {
        return "免费";
      }
    },
    cpPileType(row) {
      if (row.pileType == 1) {
        return "快充";
      } else if (row.pileType == 0) {
        return "慢充";
      }
    },
    cpPileStatus(row) {
      if (row.pileStatus == 1) {
        return "启用";
      } else if (row.pileStatus == 0) {
        return "禁用";
      }
    },
    searchData() {
      this.page = 1;
      this.getDataList();
    },
    getDataList() {
      this.loading = true;
      let param = {
        keyWord: this.keyWord,
        deviceType: 4,
        pageNo: this.page,
        pageSize: this.pageSize,
      };
      getChargingPileList(param).then((res) => {
        if (res.data && res.data.records.length >= 0) {
          this.tableData = [...res.data.records];
          this.total = res.data.total;
        }
        this.loading = false;
      });
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getDataList();
    },
  },
};
</script>
<style lang="scss" scoped></style>
