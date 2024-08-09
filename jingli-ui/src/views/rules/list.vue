<template>
  <div class="main">
    <div class="content-box">
      <div class="title">
        <el-input
          maxlength="10"
          style="width:260px;margin-left:20px;"
          placeholder="搜索规则名称"
          v-model="search"
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
          prop="changeName"
          label="规则名称"
          align="center"
          min-width="120"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="realName"
          label="代理商姓名"
          align="center"
          min-width="100"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="isSysGive"
          :formatter="isSysGive"
          label="平台默认"
          align="center"
          min-width="120"
        ></el-table-column>
        <el-table-column
          prop="isGive"
          :formatter="isGive"
          label="代理商默认"
          align="center"
          min-width="120"
        ></el-table-column>
        <el-table-column
          prop="createtime"
          label="创建时间"
          align="center"
          min-width="120"
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
import { getRuleList } from "@/api/rule";

export default {
  data() {
    return {
      search: "",
      tableData: [],
      loading: true,
      total: 0,
      page: 1,
      pageSize: 10,
    };
  },
  created() {
    this.getDataList();
  },
  methods: {
    handleCurrentChange(val) {
      this.page = val;
      this.getDataList();
    },
    isGive(row) {
      if (row.isGive == 1) {
        return "默认";
      } else {
        return "否";
      }
    },
    userNm(row) {
      if (row.userNm) {
        return row.userNm;
      } else {
        return "-";
      }
    },
    isSysGive(row) {
      if (row.isSysGive == 1) {
        return "默认";
      } else {
        return "否";
      }
    },
    searchData() {
      this.page = 1;
      this.getDataList();
    },
    getDataList() {
      this.loading = true;
      let param = {
        userId: this.operatorId,
        ruleNm: this.search,
        deviceType: 4,
        pageNo: this.page,
        pageSize: this.pageSize,
      };
      getRuleList(param).then((res) => {
        if (res.data.records) {
          this.tableData = [...res.data.records];
          this.total = res.data.total;
        }
        this.loading = false;
      });
    },
  },
};
</script>
<style scoped>
.error_input {
  border: 1px solid #f56c6c;
  border-radius: 4px;
}

.tipmincc {
  color: #3b99ed;
  font-size: 12px;
}
</style>
