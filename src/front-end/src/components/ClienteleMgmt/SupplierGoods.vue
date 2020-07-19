<template>
<div>
  <el-row>
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>客户管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/suppliers">供应商管理</el-breadcrumb-item>
          <el-breadcrumb-item>供应商{{supplierId}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <el-row>
    <el-col :span="22" :offset="1">
      <BasicCard header="供应商商品信息">
        <el-row style="text-align: left">
          <b>供应商ID：</b>
          {{supplierId}}
        </el-row>
        <br />
        <el-table :data="itemSupplyList" border style="width: 100%">
          <el-table-column fixed prop="name" label="商品名称" width="150"></el-table-column>
          <el-table-column prop="categoryId" label="商品大类ID" width="200"></el-table-column>
          <el-table-column prop="itemId" label="商品ID" width="200"></el-table-column>
          <el-table-column prop="unitCost" label="商品成本" width="180"></el-table-column>
          <el-table-column prop="listPrice" label="商品售价" width="180"></el-table-column>
          <el-table-column label="商品图片" width="120">
            <template slot-scope="scope">
              <img style="width: 80px; height: 80px" :src="scope.row.imgUrl" />
            </template>
          </el-table-column>
          <el-table-column prop="status" label="商品状态" width="120"></el-table-column>
          <el-table-column prop="descn" label="商品描述" width="120"></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                typeof="success"
                @click="clickToGoodsDetails(scope.row.itemId)"
                type="text"
                round
                plain
              >查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </BasicCard>
    </el-col>
  </el-row>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getSupplierItems } from "../../api/clientele";

export default {
  name: "SupplierGoods",
  components: {
    BasicCard
  },
  methods: {
    fetchData() {
      getSupplierItems(this.supplierId).then(res => {
        this.itemSupplyList = res.data.itemSupplyList;
      });
    },
    clickToGoodsDetails(itemId) {
      //查看商品详情
      this.$router.push("/main/goods/items/" + itemId);
    }
  },
  mounted() {
    this.supplierId = this.$route.params.supplierId;
    this.fetchData();
  },
  data() {
    return {
      supplierId: "74",
      itemSupplyList: [
        {
          itemId: "650000201509226873",
          categoryId: "220000197603219400",
          name: "qvgle",
          descn: "更现问想已增般及极王南团验七象。",
          unitCost: 66275087.3437762,
          listPrice: 36669345.40612913,
          imgUrl: "http://dummyimage.com/88x31",
          status: "sunt cillum"
        },
        {
          itemId: "810000201408032535",
          categoryId: "710000197412308677",
          name: "tdfuou",
          descn: "组才近立国算进元厂路节清派型界。",
          unitCost: 52843318.234364346,
          listPrice: 25816709.344290573,
          imgUrl: "http://dummyimage.com/468x60",
          status: "aliqua Lorem"
        }
      ]
    };
  }
};
</script>

<style scoped>
.el-col {
  padding: 20px;
}
</style>