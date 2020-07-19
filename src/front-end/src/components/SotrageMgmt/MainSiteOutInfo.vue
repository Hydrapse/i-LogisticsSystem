<template>
<div>
  <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/siteMap">主站管理</el-breadcrumb-item>
          <el-breadcrumb-item>主站{{mainsiteId}}</el-breadcrumb-item>
          <el-breadcrumb-item>出库请求审核</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <div class="display-box">
    <!-- <el-table :data="checkOutItems">
        <el-table-column label="记录编号" prop="recordId"></el-table-column>
        <el-table-column>
          <template slot-scope="items">
            <el-tag v-if="items.row.type==1" type="warning">退货</el-tag>
            <el-tag v-if="items.row.type==2">调货</el-tag>
            <el-tag v-if="items.row.type==3" type="success">发货</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="商品ID" prop="itemId"></el-table-column>
        <el-table-column label="商品数量" prop="itemNum"></el-table-column>
        <el-table-column label="操作">
          <template>
            <el-button type="danger">拒绝</el-button>
            <el-button type="success">通过</el-button>
          </template>
        </el-table-column>
    </el-table>-->
    <el-row v-show="checkOutItems.length == 0">暂无待审核出库请求</el-row>
    <el-row v-for="item in checkOutItems" :key="item.recordId">
      <BasicCard :header="'出库请求 '+item.recordId">
        <el-row type="flex" align="center">
          <el-col :span="18">
            <el-row class="form-line">
              <el-col :span="12">
                <div class="text-label">商品ID：</div>
                <div class="text-value">{{item.itemId}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-label">商品数量：</div>
                <div class="text-value">{{item.itemNum}}</div>
              </el-col>
            </el-row>
            <el-row class="form-line">
              <el-col :span="12">
                <div class="text-label">退货类型：</div>
                <div class="text-value">
                  <el-tag v-if="item.type==1" type="warning">退货</el-tag>
                  <el-tag v-if="item.type==2">调货</el-tag>
                  <el-tag v-if="item.type==3" type="success">发货</el-tag>
                </div>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="6">
            <el-button type="danger" @click="refuseOnClick(item)">拒绝</el-button>
            <el-button type="success" @click="passOnClick(item)">通过</el-button>
            <el-button type="primary" @click="showDetails(item)">详细信息</el-button>
          </el-col>
        </el-row>
      </BasicCard>
    </el-row>
  </div>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import router from "../../plugins/router";
import { patchMainsiteOutRecord } from "../../api/storage";
import mq from "@/plugins/rabbitmq";

export default {
  components: { BasicCard },
  data: () => {
    return {
      mainsiteId: "",
      checkOutItems: []
    };
  },
    methods: {
      fetchData() {},
      passOnClick: function(item) {
        this.$confirm("确认通过出库请求" + item.recordId + "？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "success"
        }).then(()=> {
          console.log("确认通过 出库请求" + item.recordId)
          this.confirmCheck(item, 'Y')
        });
      },
      refuseOnClick: function(item) {
        this.$confirm("确认拒绝出库请求" + item.recordId + "？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "success"
        }).then(()=> {
          console.log("拒绝 出库请求" + item.recordId)
          this.confirmCheck(item, 'F')
        });
      },
      showDetails(record) {
        router.push({
          path:
            "/main/mainsites/" +
            this.mainsiteId +
            "/inventory/siteout/" +
            record.recordId,
          query: { record }
        });
      },
      onMessage(frame) {
        console.log("msg" + frame.body);
        var obj = JSON.parse(frame.body);
        obj.ack = frame.ack;
        this.checkOutItems.push(obj);
      },
      onFailed(frame) {
        console.log("err:" + frame);
      },
      confirmCheck(item, status){
        var msg; //提示消息
        if (status == "Y") {
          msg = "出库请求 " + item.recordId + " 成功通过!";
        } else if (status == "F") {
          msg = "出库请求 " + item.recordId + " 已拒绝";
        } else {
          msg = "approvalStatus:'" + status + "' 不存在!";
        }
        //向后端发送消息
        patchMainsiteOutRecord(this.mainsiteId, item.recordId, {
          approvalStatus: status,
          warehouseId: ""
        })
        .then(res => {
          if(res.status == 200){
            item.ack();
            this.checkOutItems = this.checkOutItems.filter(tempItem => {
              return tempItem.recordId !== item.recordId;
            });
            this.$message({
              type: "success",
              message: msg
            });
          }
          else{
            this.$message({
              type: "error",
              message: "出库请求确认失败, 服务端异常"
            });
          }
        })
      }
    },
    mounted() {
      this.mainsiteId = this.$route.params.mainsiteId;
      mq.client.heartbeat.outgoing = 0;
      mq.client.heartbeat.incoming = 0;
      mq.connect("unreviewed item out", this.onMessage, this.onFailed);
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
.form-line {
  text-align: left;
}
</style>