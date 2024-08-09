<template>
  <div class="main">
    <div class="content-box">
      <div class="title">
        <el-input
          style="width:300px;"
          placeholder="搜索关键字"
          v-model="keyWord"
        >
          <el-button
            slot="suffix"
            @click="searchData"
            type="primary"
            icon="el-icon-search"
            style="border-radius:0 4px 4px 0;margin-right:-5px"
            >搜索</el-button
          >
        </el-input>
      </div>
    </div>
    <div class="content-box">
      <el-table
        v-loading="loading"
        border
        :data="tableData"
        style="width: 100%"
      >
        <el-table-column
          type="index"
          label="序号"
          align="center"
          width="80"
        ></el-table-column>
        <el-table-column
          prop="plotName"
          label="站点名称"
          align="center"
          min-width="100"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="address"
          label="地址 "
          align="center"
          min-width="150"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="parkCarInfo"
          label="停车收费信息 "
          align="center"
          min-width="150"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间 "
          align="center"
          min-width="100"
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
import { getPlotList } from "@/api/plot";

export default {
  data() {
    return {
      keyWord: "",
      loading: false,
      tableData: [],
      page: 1,
      pageSize: 10,
      total: 0,
    };
  },
  created() {
    this.getDataList();
  },
  methods: {
    searchData() {
      this.page = 1;
      this.getDataList();
    },
    getDataList() {
      this.loading = true;
      let param = {
        keyWord: this.keyWord,
        pageNo: this.page,
        pageSize: this.pageSize,
      };
      getPlotList(param).then((res) => {
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
<style scoped></style>
