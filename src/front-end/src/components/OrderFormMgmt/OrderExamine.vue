<template>
  <div>
    <el-row>
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>订单管理</el-breadcrumb-item>
          <el-breadcrumb-item>订单审核</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
      <el-col :span="1" :offset="21">
        <AddOrder />
      </el-col>
    </el-row>

    <el-row>
    <BasicCard class="display-box" header="待审核订单">
      <el-table :data="orderMsgs.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                :current-page.sync="currentPage">
        <el-table-column label="订单ID" prop="orderId"></el-table-column>
        <el-table-column :width="170" label="收件人姓名" prop="billName"></el-table-column>
        <el-table-column  label="订单创建时间" prop="createDateTime"></el-table-column>
        <el-table-column :width="170" label="订单总价" prop="totalPrice"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="danger" @click="handleRefuse(scope.row)">拒绝</el-button>
            <el-button type="success" @click="handlePass(scope.row)">通过</el-button>
            <!-- <router-link style="padding-left: 3%" :to="orderDetailRoute"><el-button type="primary" @click="clickToExamOrderDetails(scope.row.orderId)">查看详情</el-button>
            </router-link>-->
            <el-button type="primary" @click="toDetail(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </BasicCard>
    <el-pagination
            background
            layout="prev, pager, next"
            @current-change="handleCurrentChange"
            :page-size="pageSize"
            :total="orderMsgs.length"
            :hide-on-single-page="orderMsgs.length < 8"
    >
    </el-pagination>
    </el-row>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import mq from "@/plugins/rabbitmq";
import { patchOrderStatus } from "../../api/orders.js";
import AddOrder from "./add-order";

    export default {
      // inject: ['reload'],
      name: 'OrderExamine',
      components: { BasicCard, AddOrder},
      data: () => {
        return {
          orderMsgs: [],
          orderDetailRoute:"",
          currentPage:1,
          pageSize:7
        };
      },
      mounted() {
        //每次进页面前刷新一次
        // if(location.href.indexOf("#")==-1) {
        //   //在当前页面地址加入"#"，使下次不再进入此判断
        //   location.href = location.href + "#";
        //   location.reload();
        // }

        //从vuex中取出值
        this.orderMsgs = this.$store.state.orderMsgList

        //连接待审核消息队列
        mq.client.heartbeat.outgoing=0;
        mq.client.heartbeat.incoming=0;
        mq.connect('unreviewed order',this.onMessage, this.onFailed);
        // mq.connect('test',this.onMessage, this.onFailed);
      },
      // watch: { //失败
      //   orderMsgs: function (val, oldVal) {
      //     console.log(val.length + ' ' + oldVal.length)
      //     if(val.length >= 5){
      //       mq.client.unsubscribe('test', {})
      //     }
      //     if(val.length == 4 ){
      //       mq.client.subscribe('test', this.onMessage, {})
      //     }
      //   }
      // },
      methods: {
      onMessage(frame){
        // if(this.orderMsgs.length >= 10){
        //   console.warn("unsubcribe Queue")
        //
        //   mq.client.unsubscribe('unreviewed order')
        //   frame.nack()
        //   return
        // }

        // console.log("Whole Frame: " + frame)
        console.log("msg: " + frame.body)

        //处理消息
        var obj = JSON.parse(frame.body);
        obj.ack = frame.ack;

        //在组件中显示
        this.orderMsgs.push(obj);
        //更新vuex
        this.$store.commit('setOrderMsgList', this.orderMsgs)
      },
      onFailed(frame) {
        console.log("err:" + frame);
      },
      handleRefuse(rowFrame) {
        var alertMsg = "是否确认拒绝 订单" + rowFrame.orderId + " ?";

        this.$confirm(alertMsg, "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            console.log("拒绝接收 订单" + rowFrame.orderId);
            this.confirmCheck(rowFrame, "C"); //拒绝接收
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消操作"
            });
          });
      },
      handlePass(rowFrame) {
        var alertMsg = "是否确认接收 订单" + rowFrame.orderId + " ?";

        this.$confirm(alertMsg, "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "success"
        })
          .then(() => {
            console.log("确认接收 订单" + rowFrame.orderId);
            this.confirmCheck(rowFrame, "P"); //确认接收
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消操作"
            });
          });
      },
      confirmCheck(rowFrame, status) {
        var msg; //提示消息
        if (status == "C") {
          msg = "订单" + rowFrame.orderId + " 已拒绝";
        } else if (status == "P") {
          msg = "订单" + rowFrame.orderId + " 成功接收!";
        } else {
          msg = "processStatus:'" + status + "' 不存在!";
        }

        //修改订单状态
        patchOrderStatus(rowFrame.orderId, status)
        .then(resp => {
          if (resp.status == 200) {
            console.log(resp.status);
            this.$message({
              type: "success",
              message: msg
            });
            //向消息队列确认接收消息, 不会再发送已消费成功的订单消息
            rowFrame.ack();
          } else {
            this.$message({
              type: "error",
              message: "订单" + rowFrame.orderId + "确认失败!"
            });
          }
        })
        .catch(res=>{
          console.error(res)
          this.$message({
            type: "error",
            message: "审核提交失败"
          });
        })

        //更新dom组件
        var index = this.orderMsgs.indexOf(rowFrame);
        this.orderMsgs.splice(index, 1);
        //更新vuex
        this.$store.commit('setOrderMsgList', this.orderMsgs)
        mq.connect('unreviewed order',this.onMessage, this.onFailed);
      },
      toDetail: function(rowFrame) {
        console.log(rowFrame)
        this.$router.push({
          path: "orderExamine/"+rowFrame.orderId,
          query: { rowFrame }
        })
      },
      handleCurrentChange(val) {
        this.currentPage = val;
      },
    }
};
</script>

<style scoped>
.display-box {
  margin: .5% 1.5%;
    width: 97.5%;
}
.el-row {
  padding-top: 20px;
}
</style>