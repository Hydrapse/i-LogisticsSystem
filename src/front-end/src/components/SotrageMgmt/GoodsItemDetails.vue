<template>
<div>
  <el-row style="padding-top: 20px">
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/catalogInfo">商品整体信息</el-breadcrumb-item>
          <el-breadcrumb-item>商品{{itemId}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <el-col>
    <el-row>
      <br />
      <br />
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1">
        <BasicCard header="商品详细信息">
          <el-row>
            <el-col :span="7">
              <img
                :src="item.imgUrl"
              />
              <div>
                <div class="itemName">
                  <span>
                    <b>{{item.name}}</b>
                  </span>
                </div>
                <div class="descn">
                  <time>{{item.descn}}</time>
                </div>
                <!-- <el-button type="primary" @click="getUniqueItemId" round>操作按钮</el-button> -->
              </div>
            </el-col>
            <el-col :span="6" :offset="1" class="data-col">
              <el-row>
                <b>商品大类ID：</b>
                {{item.categoryId}}
              </el-row>
              <br />
              <el-row>
                <b>商品成本：</b>
                {{item.unitCost}}
              </el-row>
              <br />
              <el-row>
                <b>商品状态：</b>
                <el-tag v-if="item.status=='P'" type="success">上架</el-tag>
                <el-tag v-else-if="item.status=='F'" type="danger">下架</el-tag>
              </el-row>
              <br />

              <el-row>
                <b>商品ID：</b>
                {{item.itemId}}
              </el-row>
              <br />
              <el-row>
                <b>商品标价：</b>
                {{item.listPrice}}
              </el-row>
              <br />
              <el-row>
                <b>总库存量：</b>
                {{totalInventory}}
              </el-row>
              <br />
            </el-col>

            <el-col :span="6" :offset="1" class="table-col">
              <el-row>
                <b>各主站库存情况：</b>
              </el-row>
              <el-table :data="mainsiteInventoryList" class="inv-table">
                <el-table-column fixed prop="mainsiteId" label="主站ID" width="150"></el-table-column>
                <el-table-column prop="itemInventory" label="主站库存" width="150"></el-table-column>
              </el-table>
            </el-col>

          </el-row>
        </BasicCard>
      </el-col>
    </el-row>
  </el-col>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getItemDetails } from "../../api/storage";

export default {
  name: "GoodsItemDetails",
  components: {
    BasicCard
  },
  data: function() {
    return {
      item: {
        itemId: "",
        categoryId: "",
        name: "",
        descn: "",
        unitCost: 0,
        listPrice: 0,
        imgUrl: "",
        status: "P"
      },
      totalInventory: 157,
      mainsiteInventoryList: [],
      itemId: ""
    };
  },
  methods: {
    getUniqueItemId: function() {
      // 获取了 主站号 和 商品ID  我觉得主站ID唯一的作用可能就是 获取 当前商品在该站 是否处于上架状态
      console.log(this.$route.params.itemId);
      //通过 $route.params.mainsiteId $route.params.goodsItemId 获取商品详情 分配到 this.item  this.totalInventory this.mainsiteInventoryList
    },
    fetchData() {
        getItemDetails(this.itemId).then(res => {
            this.item = res.data.item;
            this.totalInventory = res.data.totalInventory
            this.mainsiteInventoryList = res.data.mainsiteInventoryList
        })
    }
  },
  mounted() {
      this.itemId = this.$route.params.itemId;
      this.fetchData();
  }
};
</script>

<style scoped>
.itemName {
  padding: 10px;
  font-size: 20px;
}
.descn {
  padding-bottom: 8px;
  font-size: 13px;
  color: #999;
}
img {
  width: 100%;
  height: 250px;
  width: 250px;
  border-radius: 0.35rem 0.35rem 0 0;
}
.inv-table{

}
  .data-col{
    text-align: left;
    margin-top: 30px;
  }
  .table-col{
    text-align: left;
    margin-top: 20px;
  }
</style>