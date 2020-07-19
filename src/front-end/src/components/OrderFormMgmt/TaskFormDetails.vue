<template>
<div>
  <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配送管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/taskForm">任务单列表</el-breadcrumb-item>
          <el-breadcrumb-item>任务单{{taskFromData.taskFormId}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col> 
    </el-row>
  <el-row>
    <el-col :span="22" :offset="1" style="text-align: left;padding-top: 2%">
      <BasicCard header="任务单详情">
        <el-col :span="11" :offset="1">
          <el-row>
            <el-col>
              <b>任务单编号：</b>
              {{taskFromData.taskFormId}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>配送站ID：</b>
              {{taskFromData.subSiteId}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>收件人：</b>
              {{taskFromData.receiverName}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>收件地址：</b>
              {{taskFromData.receiverAddress}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>商品列表：</b>
            </el-col>
          </el-row>
          <br />
        </el-col>
        <el-col :span="11" :offset="1">
          <el-row>
            <el-col>
              <b>订单编号：</b>
              {{taskFromData.orderId}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>配送时间：</b>
              {{taskFromData.shipTime}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>任务单状态：</b>
              {{taskFromData.status}}
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-col>
              <b>收件人电话：</b>
              {{taskFromData.receiverTel}}
            </el-col>
          </el-row>
          <br />
        </el-col>

        <!--商品列表-->
        <el-col :span="17" :offset="1">
          <el-collapse
            style="width: 98%;padding-left: 3%"
            v-for="orderItem in taskFromData.orderItems"
            v-bind:key="orderItem.item.itemId"
          >
            <el-collapse-item :title="orderItem.item.name">
              <el-row>
                <el-col :span="5">
                  <img style="width: 100px; height: 100px" :src="orderItem.item.imgUrl" />
                </el-col>
                <el-col :span="19">
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
                    <el-tag v-if="orderItem.taskId=='O'">运输中</el-tag>
                    <el-tag v-else-if="orderItem.taskId=='Y'" type="success">已签收</el-tag>
                    <el-tag v-else-if="orderItem.taskId=='W'" type="info">缺货待调货</el-tag>
                    <el-tag v-else-if="orderItem.taskId=='U'" type="warning">未发出</el-tag>
                    <el-tag v-else-if="orderItem.taskId=='N'" type="danger">未配送</el-tag>
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
                    <b>商品购买总价：</b>
                    {{orderItem.total}}
                  </el-col>
                  <el-col :span="12">
                    <b>商品描述：</b>
                    {{orderItem.item.descn}}
                  </el-col>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>
        </el-col>
        <el-col :span="2" :offset="3" style="padding-top: 3.7%">
          <el-button type="primary" @click="clickToDeliverMap">查看配送情况</el-button>
        </el-col>
      </BasicCard>
    </el-col>
  </el-row>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getTaskFormDetail } from "../../api/taskform";
export default {
  name: "TaskFormDetails",
  components: {
    BasicCard
  },
  data: function() {
    return {
      taskFromData: [
        {
          taskId: 45,
          orderId: 75,
          subSiteId: "69",
          courier: "aliqua",
          status: "#",
          deliveryDateTime: "1983-06-03 08:57:03",
          shipName: "芳",
          shipTel: "18152878687",
          shipPro: "重庆",
          shipCity: "桂林市",
          shipDis: "长洲区",
          shipAddr: "华北",
          billName: "平",
          billTel: "18140216783",
          billCity: "三亚市",
          billPro: "上海",
          billDis: "治多县",
          billAddr: "东北",
          totalPrice: -7644671113060768,
          note: "exercitation pariatur",
          orderItems: [
            {
              orderId: 46,
              itemNum: 650590,
              status: "exercitation consectetur quis nisi dolore",
              total: 27,
              item: {
                itemId: "450000201307035852",
                categoryId: "410000200801299472",
                name: "wowg",
                descn: "除干入流这说联间万识对人到数种作回。",
                unitCost: 65553381.06607393,
                listPrice: 5059590.89829814,
                imgUrl: "http://dummyimage.com/180x150",
                status: "cupidatat adipisicing Excepteur"
              },
              taskId: null
            },
            {
              orderId: 68,
              itemNum: 993719,
              status: "sunt velit laborum",
              total: 57,
              item: {
                itemId: "120000199412258998",
                categoryId: 460000202005070456,
                name: "rjhmg",
                descn: "部图头的月听科书重好根三化内听千干亲。",
                unitCost: 90021496.94778481,
                listPrice: 51334387.68736999,
                imgUrl: "http://dummyimage.com/120x60",
                status: "ut in dolor culpa"
              },
              taskId: null
            }
          ]
        }
      ]
    };
  },
  methods: {
    clickToDeliverMap: function() {
      let path =
        "/main/taskforms/" + this.$route.params.taskFormId + "/deliverMap";
      this.$router.push(path);
    },
    fecthData: function() {
      //写入import的js函数即可
      getTaskFormDetail(this.taskFromData.taskFormId).then(res => {
        this.taskFromData = res.data;
      });
    }
  },
  mounted() {
    this.taskFromData.taskFormId = this.$route.params.taskFormId;
    this.fecthData();
  }
};
</script>

<style scoped>
</style>