<template>
<div>
  <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/siteMap">主站管理</el-breadcrumb-item>
          <el-breadcrumb-item>主站{{mainsiteId}}</el-breadcrumb-item>
          <el-breadcrumb-item>入库请求审核</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <!-- <div class="display-box">
    <BasicCard header="审核入库申请">
      <el-table :data="checkInItems">
        <el-table-column label="记录编号" prop="recordId"></el-table-column>
        <el-table-column>
          <template slot-scope="items">
            <el-tag v-if="items.row.type==1" type="success">补货</el-tag>
            <el-tag v-if="items.row.type==2">调货</el-tag>
            <el-tag v-if="items.row.type==3" type="danger">退货</el-tag>
            <el-tag v-if="items.row.type==4" type="warning">换货</el-tag>
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
      </el-table>
    </BasicCard>
  </div>-->
  <div class="display-box">
    <el-row v-show="checkInItems.length == 0">暂无待审核入库请求</el-row>
    <el-row v-for="item in checkInItems" :key="item.recordId">
      <BasicCard :header="'入库请求 '+item.recordId">
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
                  <el-tag v-if="item.type==1" type="success">补货</el-tag>
                  <el-tag v-if="item.type==2">调货</el-tag>
                  <el-tag v-if="item.type==3" type="danger">退货</el-tag>
                  <el-tag v-if="item.type==4" type="warning">换货</el-tag>
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
import {patchMainsiteInRecord} from '../../api/storage';
import mq from "@/plugins/rabbitmq";

export default {
  components: { BasicCard },
  data: () => {
    return {
      mainsiteId: "",
      checkInItems: []
    };
  },
  methods: {
    passOnClick: function(item) {
      this.$confirm("确认通过入库请求" + item.recordId + "？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      }).then(()=> {
        console.log("确认通过入库请求" + item.recordId)
        this.confirmCheck(item, 'Y')
      })
    },
    refuseOnClick: function(item) {
      this.$confirm("确认拒绝入库请求" + item.recordId + "？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      }).then(()=> {
        console.log("确认拒绝入库请求" + item.recordId)
        this.confirmCheck(item, 'F')
      });
    },
    showDetails(record) {
      router.push({
        path:
          "/main/mainsites/" +
          this.mainsiteId +
          "/inventory/sitein/" +
          record.recordId,
        query: { record }
      });
    },
    onMessage(frame) {
      console.log("msg" + frame.body);
      var obj = JSON.parse(frame.body);
      obj.ack = frame.ack;
      this.checkInItems.push(obj);
    },
    onFailed(frame) {
      console.log("err:" + frame);
    },
    confirmCheck(item, status){
      var msg; //提示消息
      if (status == "Y") {
        msg = "入库请求 " + item.recordId + " 成功通过!";
      } else if (status == "F") {
        msg = "入库请求 " + item.recordId + " 已拒绝";
      } else {
        msg = "approvalStatus:'" + status + "' 不存在!";
      }
      //向后端发送消息
      patchMainsiteInRecord(this.mainsiteId, item.recordId, {
        approvalStatus: status,
        warehouseId: ""
      })
      .then(res => {
        if(res.status == 200){
          item.ack();
          this.checkInItems = this.checkInItems.filter(tempItem => {
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
            message: "入库请求确认失败, 服务端报错"
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
    }
  },
  mounted() {
    this.mainsiteId = this.$route.params.mainsiteId;
    mq.client.heartbeat.outgoing = 0;
    mq.client.heartbeat.incoming = 0;
    mq.connect("unreviewed item in", this.onMessage, this.onFailed);
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