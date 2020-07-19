<template>
  <div class="display-box">
    <BasicCard header="订单详情">
      <el-row>
        <el-col :span="12" style="text-align: left">
          <h3>
            <div class="text-label">订单编号:</div>
            <div class="text-value">123</div>
          </h3>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 订单详情 -->
      <el-row>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">下单日期：</div>
          <div class="text-value">1970-01-01</div>
        </el-col>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">付款日期：</div>
          <div class="text-value">2020-12-31</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">订单金额：</div>
          <div class="text-value">120元</div>
        </el-col>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">运费：</div>
          <div class="text-value">0元</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">订单状态：</div>
          <div class="text-value">已处理</div>
        </el-col>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">支付方式：</div>
          <div class="text-value">支付宝</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">收货人：</div>
          <div class="text-value">xxx</div>
        </el-col>
        <el-col :span="12" style="text-align: left">
          <div class="text-label">收货电话：</div>
          <div class="text-value">123 3211 2345</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col style="text-align: left">
          <div class="text-label">收货地址：</div>
          <div class="text-value">北京路南胡同东巷西门123号</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col style="text-align: left">
          <div class="text-label">备注：</div>
          <div
            class="text-value"
          >不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！不是鱼露，是鲈鱼哦！</div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 预分拣结果 -->
      <!-- TODO:这里可能还需要加入一个标题描述下面的表格内容 -->
      <el-table :data="preSortResults">
        <el-table-column type="expand">
          <template slot-scope="results">
            <el-table :data="results.row.items" class="table-expand">
              <el-table-column label="商品ID" prop="itemId"></el-table-column>
              <el-table-column label="商品名称" prop="itemName"></el-table-column>
              <el-table-column label="商品数量" prop="quantity"></el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column label="库房ID" prop="mainSiteId"></el-table-column>
        <el-table-column label="库房地址" prop="mainSiteLoc"></el-table-column>
      </el-table>
    </BasicCard>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
export default {
  components: { BasicCard },
  data: () => {
    return {
      preSortResults: [
        {
          mainSiteId: "123",
          mainSiteLoc: "北京市北京区北京路",
          items: [
            { itemId: 1001, itemName: "忘崽牛奶", quantity: 2 },
            { itemId: 1002, itemName: "猛牛", quantity: 3 }
          ]
        },
        {
          mainSiteId: "312",
          mainSiteLoc: "上海市东城区",
          items: [
            { itemId: 1001, itemName: "忘崽牛奶", quantity: 2 },
            { itemId: 1002, itemName: "伊利", quantity: 3 }
          ]
        }
      ]
    };
  }
};
</script>

<style scoped>
.display-box {
  margin: 50px 50px 100px;
}
.text-label {
  float: left;
  width: 150px;
  min-height: 30px;
  text-align: right;
}
.text-value {
  float: left;
  min-height: 30px;
  max-width: 80%;
  text-align: left;
}
</style>