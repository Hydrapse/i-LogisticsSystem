<template>
<div>
  <el-row>
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>客户管理</el-breadcrumb-item>
          <el-breadcrumb-item>买家管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <el-row>
    <el-col :span="22" :offset="1">
      <BasicCard header="买家管理">
        <el-row>
          <el-col :span="4" :offset="18">
            <el-autocomplete
              placeholder="请输入客户姓名"
              style="width: 100%"
              v-model="keyCustomerId"
              :fetch-suggestions="customerIdAutoCmpl"
              :trigger-on-focus="false"
            ></el-autocomplete>
          </el-col>
          <el-col :span="2">
            <el-button @click="fetchData" type="primary" round>搜索</el-button>
          </el-col>
        </el-row>
        <el-table :data="customers">
          <el-table-column label="ID" prop="customerId"></el-table-column>
          <el-table-column label="姓名" prop="name"></el-table-column>
          <el-table-column label="电话" prop="tel"></el-table-column>
          <el-table-column label="邮箱" prop="email"></el-table-column>
          <el-table-column label="地址">
            <template
              slot-scope="scope"
            >{{scope.row.province+' '}} {{scope.row.city+' '}} {{scope.row.district+' '}} {{scope.row.addr}}</template>
          </el-table-column>
        </el-table>

        <br />
        <el-row>
          <el-pagination
            :current-page.sync="pageNum"
            :page-count="totalPages"
            layout="prev, pager, next"
            @current-change="onPageChange"
          ></el-pagination>
        </el-row>
      </BasicCard>
    </el-col>
  </el-row>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getCustomers, billNameAutoCompl } from "../../api/clientele";

export default {
  components: {
    BasicCard
  },
  data: () => {
    return {
      pageNum: 5,
      pageSize: 59,
      totalSize: 13,
      totalPages: 18,

      keyCustomerId: "",

      customers: [
        {
          customerId: 10000000,
          name: "姜美玉",
          email: "Jiangmy@qq.com",
          tel: "15436780965",
          addr: "东风路灞河桥西",
          district: "未央区",
          city: "西安市",
          province: "陕西省",
          totalCost: null
        },
        {
          customerId: 10000001,
          name: "杨林",
          email: "LinYang@foxmail.com",
          tel: "18890964554",
          addr: "建国新街445号",
          district: "南岗区",
          city: "哈尔滨市",
          province: "黑龙江省",
          totalCost: null
        },
        {
          customerId: 10000002,
          name: "范理",
          email: "LiFan123@qq.com",
          tel: "15670923301",
          addr: "学府东路20号",
          district: "五华区",
          city: "昆明市",
          province: "云南省",
          totalCost: null
        },
        {
          customerId: 10000003,
          name: "蒋惠",
          email: "HuiJiang@qq.com",
          tel: "18996544339",
          addr: "东江源大道42号",
          district: "章贡区",
          city: " 赣州市",
          province: "江西省",
          totalCost: null
        },
        {
          customerId: 10000004,
          name: "杨树",
          email: "ShuYang@foxmail.com",
          tel: "18890964555",
          addr: "秦孟街221号",
          district: "高新区",
          city: "哈尔滨市",
          province: "黑龙江省",
          totalCost: null
        }
      ]
    };
  },
  methods: {
    fetchData: function() {
      //需要把请求结果 的 province  city district addr 合并处理到customers[i].customerAddress
      getCustomers({
        pageNum: this.pageNum,
        pageSize: 10,
        name: this.keyCustomerId
      }).then(res => {
        this.customers = res.data.content;
        this.totalPages = res.data.totalPages;
      });
    },
    onPageChange() {
      this.fetchData();
    },
    customerIdAutoCmpl(queryString, cb) {
      //以queryString为依据获取cb提示  cb( Array )
      billNameAutoCompl(queryString).then(res => {
        let fillRes = res.data.map(billName => {
          return {
            value: billName
          };
        });
        cb(fillRes);
      });
    }
  },
  mounted() {
    this.fetchData();
  }
};
</script>

<style scoped>
.el-col {
  padding: 20px;
}
</style>