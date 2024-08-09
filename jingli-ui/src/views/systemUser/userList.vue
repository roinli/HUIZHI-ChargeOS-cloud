<template>
  <div class="main">
    <div class="content-box">
      <el-input
        maxlength="15"
        style="width:260px;"
        placeholder="搜索用户名\手机号"
        v-model="search"
      >
        <el-button
          slot="suffix"
          @click="getDataList"
          type="primary"
          icon="el-icon-search"
          style="border-radius:0 4px 4px 0;margin-right:-5px"
          >搜索</el-button
        >
      </el-input>
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
          prop="useraccount"
          label="登录用户名"
          align="center"
          min-width="100"
        ></el-table-column>
        <el-table-column
          prop="realName"
          label="真实姓名"
          align="center"
          min-width="100"
        ></el-table-column>
        <el-table-column
          prop="mobile"
          label="手机号"
          align="center"
          min-width="150"
        ></el-table-column>
        <el-table-column
          prop="createtime"
          label="创建时间"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column
          prop="rolesArr"
          label="所属岗位"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column
          prop="state"
          label="状态"
          align="center"
          min-width="80"
        >
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.state"
              disabled
              active-value="1"
              inactive-value="0"
            ></el-switch>
          </template>
        </el-table-column>
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
import { getUserList } from "@/api/sysUser";

export default {
  data() {
    return {
      search: "",
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
  methods: {
    getDataList() {
      this.loading = true;
      let param = {
        keyWord: this.search,
        pageNo: this.page,
        pageSize: this.pageSize,
      };
      getUserList(param).then((res) => {
        if (res.data.records && res.data.records.length >= 0) {
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
<style scoped>
.title {
  margin-left: 20px;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}

.data {
  margin-left: 20px;
  margin-top: 20px;
  margin-right: 10px;
}
</style>
