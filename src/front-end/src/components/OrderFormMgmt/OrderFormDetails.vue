<template>
  <div>
    <el-row>
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>订单管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/orderForm">订单查询</el-breadcrumb-item>
          <el-breadcrumb-item>订单{{orderId}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <BasicCard header="订单详情">
          <el-col style="text-align: left;">
            <el-row>
              <el-col>
                <b>订单编号：</b>
                {{order.orderId}}
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <b>客户编号：</b>
                {{order.customerId}}
              </el-col>
              <el-col :span="12">
                <b>发货人姓名：</b>
                {{order.billName}}
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <b>付款状态：</b>
                {{order.payStatus}}
              </el-col>
              <el-col :span="12">
                <b>订单状态：</b>
                {{order.processStatus}}
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <b>卖家地区：</b>
                {{order.billPro}}-{{order.billCity}}-{{order.billDistrict}}
              </el-col>
              <el-col :span="12">
                <b>卖家详细地址：</b>
                {{order.billAddr}}
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <b>订单总价：</b>
                {{order.totalPrice}}
              </el-col>
              <el-col :span="12">
                <b>运费：</b>
                {{order.shippingCost}}
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <b>订单创建时间：</b>
                {{order.createDateTime}}
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <b>订单付款时间：</b>
                {{order.payDateTime}}
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <b>支付方式：</b>
                {{order.payMethod}}
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <b>订单备注：</b>
                {{order.note}}
              </el-col>
            </el-row>

            <el-row>
              <el-row>
                <el-col>
                  <b>订单商品详情：</b>
                  <br />
                </el-col>
              </el-row>
              <el-collapse
                style="width: 98%;padding-left: 3%"
                v-for="orderItem in orderItemList"
                v-bind:key="orderItem.item.itemId"
              >
                <el-collapse-item :title="orderItem.item.name">
                  <el-row>
                    <el-col :span="12">
                      <b>商品大类ID：</b>
                      {{orderItem.item.categoryId}}
                    </el-col>
                    <el-col :span="12">
                      <b>商品ID：</b>
                      {{orderItem.item.itemId}}
                    </el-col>
                    <el-col :span="12">
                      <b>任务单ID：</b>
                      {{orderItem.taskId}}
                    </el-col>
                    <el-col :span="12">
                      <b>任务单状态：</b>
                      {{orderItem.status}}
                    </el-col>
                    <el-col :span="12">
                      <b>商品下单数量：</b>
                      {{orderItem.itemNum}}
                    </el-col>
                    <el-col :span="12">
                      <b>商品状态：</b>
                      {{orderItem.item.status}}
                    </el-col>
                    <el-col :span="12">
                      <b>商品成本：</b>
                      {{orderItem.item.unitCost}}
                    </el-col>
                    <el-col :span="12">
                      <b>商品售价：</b>
                      {{orderItem.item.listPrice}}
                    </el-col>
                    <el-col :span="12">

                      <b>商品图片：</b><br />

                      <el-image style="width: 90%;height: 90%" :src="orderItem.item.imgUrl"></el-image>
                    </el-col>
                    <el-col :span="12">
                      <br /><br /><br /><br />
                      <b>商品描述：</b>
                      {{orderItem.item.descn}}
                    </el-col>
                  </el-row>
                </el-collapse-item>
              </el-collapse>
            </el-row>
          </el-col>
        </BasicCard>
      </el-col>
      <el-col :span="12">
        <el-row>
          <BasicCard header="任务单信息">
            <el-table :data="taskForms">
              <el-table-column label="任务单ID" prop="taskId"></el-table-column>
              <el-table-column label="配送站ID" prop="subSiteId"></el-table-column>
              <el-table-column label="任务单状态">
                <!-- 缺货等待调货（W），未发出（U），运输中（O），未配送（N），已签收（Y） -->
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.status == 'W'" type="danger">缺货</el-tag>
                  <el-tag v-else-if="scope.row.status == 'U'" type="info">未发货</el-tag>
                  <el-tag v-else-if="scope.row.status == 'O'" type="primary">运输中</el-tag>
                  <el-tag v-else-if="scope.row.status == 'N'" type="warning">未配送</el-tag>
                  <el-tag v-else-if="scope.row.status == 'Y'" type="success">已签收</el-tag>
                </template>
              </el-table-column>
              <el-table-column>
                <template slot-scope="scope">
                  <el-button type="primary" @click="clickToTaskDetails(scope.row.taskId)">查看详情</el-button>
                </template>
              </el-table-column>
            </el-table>
          </BasicCard>
        </el-row>
        <el-row>
          <BasicCard header="退货单信息">
            <el-table :data="returnForm">
              <el-table-column label="退货单ID" prop="formId"></el-table-column>
              <el-table-column label="退货商品" prop="item.name"></el-table-column>
              <el-table-column label="类型">
                <template slot-scope="scope">
                  <p v-if="scope.row.isChange == 1">换货</p>
                  <p v-else>退货</p>
                </template>
              </el-table-column>
              <el-table-column label="退货金额" prop="returnMoney"></el-table-column>
            </el-table>
          </BasicCard>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getOrderDetail, getReturnForms, getTaskForms } from "../../api/orders";

export default {
  components: {
    BasicCard
  },
  data: () => {
    return {
      //订单详情数据
      orderId: "",
      order: {
        orderId: "",
        customerId: "",
        payStatus: "",
        processStatus: "",
        billPro: "",
        billCity: "",
        billDistrict: "",
        billAddr: "",
        totalPrice: "",
        billName: "",
        createDateTime: "",
        payDateTime: "",
        shippingCost: null,
        payMethod: "",
        note: ""
      },
      orderItemList: [],

      //任务单数据
      taskForms: [],

      //退单数据
      returnForm: []
    };
  },
  methods: {
    getUniqueOrder: function() {
      // 获取唯一订单号后
      console.log(this.$route.params.orderId);

      //通过 $route.params.orderId 获取订单详情 分配到 this.order  this.orderItemList
    },
    fetchData() {
      getOrderDetail(this.orderId).then(res => {
        this.order = res.data.order;
        this.orderItemList = res.data.orderItemList;
      });
      getTaskForms(this.orderId).then(res => {
        this.taskForms = res.data;
      });
      getReturnForms(this.orderId).then(res => {
        this.returnForm = res.data;
      });
    },
    clickToTaskDetails: function(taskId) {
      this.$router.push("/main/taskforms/" + taskId + "/details");
    }
    // heightChange:function (e) {
    //   console.log(e);
    //   var height = 738;
    //     if(e.length>0){
    //         height +=300;
    //     }
    //     else {
    //         height -=300;
    //     }
    //     height = height.toString() + "px";
    //     console.log(height);
    //     document.getElementById("page").style.height = height;
    // }
  },
  mounted() {
    this.orderId = this.$route.params.orderId;
    this.fetchData();
  }
};
</script>

<style scoped>
.el-col {
  padding: 20px;
}
</style>