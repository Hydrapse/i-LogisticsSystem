<template>
  <div>
    <el-dialog title="编辑信息" :visible.sync="dialogVisible" width="500px">
      <el-form :model="formData" label-position="right" label-width="80px">
        <el-row>
          <el-col :span="14" :offset="4">
            <el-form-item label="运费">
              <el-input v-model="formData.shippingCost" type="number"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-button @click="canceled">取消</el-button>
        <el-button type="primary" @click="updateOnClick">确定</el-button>
      </el-form>
    </el-dialog>
    <div class="display-box">
      <el-row :gutter="80">
        <el-col :span="1" :offset="19">
          <el-button type="danger" @click="onCancel">拒绝</el-button>
        </el-col>
        <el-col :span="1">
          <el-button type="primary" @click="onEdit">编辑</el-button>
        </el-col>
        <el-col :span="1">
          <el-button type="success" @click="onPass">通过</el-button>
        </el-col>
      </el-row>
      <br />
      <el-row :gutter="50">
        <el-col :span="12">
          <BasicCard header="订单基本信息">
            <el-row>
              <el-col :span="12" style="text-align: left">
                <h3>
                  <div class="text-label">订单编号:</div>
                  <div class="text-value">{{orderInfo.order.orderId}}</div>
                </h3>
              </el-col>
              <el-col :span="12" style="text-align: left">
                <h3>
                  <div class="text-label">客户ID:</div>
                  <div class="text-value">{{orderInfo.order.customerId}}</div>
                </h3>
              </el-col>
            </el-row>
            <el-divider></el-divider>
            <!-- 订单详情 -->
            <el-row>
              <el-col :span="12">
                <div class="text-label">订单状态：</div>
                <div class="text-value">
                  <el-tag v-if="orderInfo.order.processStatus=='N'" type="danger">未处理</el-tag>
                  <el-tag v-else-if="orderInfo.order.processStatus=='P'" type="warning">正在处理</el-tag>
                  <el-tag v-else-if="orderInfo.order.processStatus=='D'" type="success">交易成功</el-tag>
                  <el-tag v-else-if="orderInfo.order.processStatus=='C'" type="info">交易关闭</el-tag>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">下单日期：</div>
                <div class="text-value">{{orderInfo.order.createDateTime}}</div>
              </el-col>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">付款日期：</div>
                <div class="text-value">{{orderInfo.order.payDateTime}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">订单金额：</div>
                <div class="text-value">{{orderInfo.order.totalPrice}}</div>
              </el-col>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">运费：</div>
                <div class="text-value">{{orderInfo.order.shippingCost}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">付款状态：</div>
                <div class="text-value">
                  <el-tag v-if="orderInfo.order.payStatus=='P'" type="success">已付款</el-tag>
                  <el-tag v-else-if="orderInfo.order.payStatus=='W'" type="danger">未付款</el-tag>
                </div>
              </el-col>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">支付方式：</div>
                <div class="text-value">{{orderInfo.order.payMethod}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" style="text-align: left">
                <div class="text-label">收货人：</div>
                <div class="text-value">{{orderInfo.order.billName}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col style="text-align: left">
                <div class="text-label">收货地址：</div>
                <div
                  class="text-value"
                >{{orderInfo.order.billPro+orderInfo.order.billCity+orderInfo.order.billDistrict+orderInfo.order.billAddr}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col style="text-align: left">
                <div class="text-label">备注：</div>
                <div class="text-value">{{orderInfo.order.note}}</div>
              </el-col>
            </el-row>
          </BasicCard>
        </el-col>
        <el-col :span="12">
          <BasicCard header="预分拣信息">
            <el-col>
              <PrimaryCard
                v-if="orderInfo.mainsite"
                :title="'主站ID：'+orderInfo.mainsite.mainsiteId"
                :content="'主站地址：'+orderInfo.mainsite.province+orderInfo.mainsite.city+orderInfo.mainsite.district"
              ></PrimaryCard>
              <PrimaryCard v-else title="主站信息" content="未分配主站"></PrimaryCard>
            </el-col>
            <el-col style="padding-top: 20px;">
              <PrimaryCard
                v-if="orderInfo.subSite"
                :title="'配送站ID：'+orderInfo.subsite.subsiteId"
                :content="'配送站：'+orderInfo.subsite.district"
              ></PrimaryCard>
              <PrimaryCard v-else title="配送站信息" content="未分配配送站"></PrimaryCard>
            </el-col>
            <el-col style="padding-top: 20px;">
              <PrimaryCard v-if="orderInfo.msg" title="预分拣结果信息" :content="orderInfo.msg"></PrimaryCard>
              <PrimaryCard v-else title="预分拣结果信息" content="无"></PrimaryCard>
            </el-col>
          </BasicCard>
        </el-col>
      </el-row>
      <el-row style="padding-top: 20px;">
        <BasicCard header="商品信息">
          <el-table :data="orderInfo.orderItemList">
            <el-table-column type="expand">
              <template slot-scope="items">
                <el-row style="text-align: left">
                  <el-col :span="2">
                    <el-image
                      style="width: 100px; height: 100px"
                      :src="items.row.item.imgUrl"
                      fit="fit"
                    ></el-image>
                  </el-col>
                  <el-col :span="22">
                    <el-row>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">商品ID：</div>
                        <div class="text-value">{{items.row.item.itemId}}</div>
                      </el-col>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">大类ID：</div>
                        <div class="text-value">{{items.row.item.categoryId}}</div>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">商品名称：</div>
                        <div class="text-value">{{items.row.item.name}}</div>
                      </el-col>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">文字描述：</div>
                        <div class="text-value">{{items.row.item.descn}}</div>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">原价：</div>
                        <div class="text-value">{{items.row.item.unitCost}}</div>
                      </el-col>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">售价：</div>
                        <div class="text-value">{{items.row.item.listPrice}}</div>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="12" style="text-align: left">
                        <div class="text-label">状态：</div>
                        <div class="text-value">{{items.row.item.status}}</div>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column label="商品ID" prop="itemId"></el-table-column>
            <el-table-column label="商品名称" prop="item.name"></el-table-column>
            <el-table-column label="商品数量" prop="itemNum"></el-table-column>
            <el-table-column label="处理状态">
              <template slot-scope="items">
                <el-tag v-if="items.row.status=='O'" type="warning">缺货</el-tag>
                <el-tag v-else type="success">已分配</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="总额" prop="total"></el-table-column>
          </el-table>
        </BasicCard>
      </el-row>
    </div>
  </div>
</template>

<script>
import PrimaryCard from "../DataCard/PrimaryCard";
import BasicCard from "../PanelCard/BasicCard";
import {
  patchOrderInfo,
  getExamineDetails,
  patchOrderStatus
} from "../../api/orders";

import router from "../../plugins/router"

export default {
  components: { PrimaryCard, BasicCard },
  data: () => {
    return {
      orderInfo: {
        order: {
          orderId: "440000197703081614",
          customerId: "230000199310267813",
          payStatus: "P",
          processStatus: "N",
          billPro: "浙江省",
          billCity: "吕梁市",
          billDistrict: "洛隆县",
          billAddr: "东北",
          totalPrice: 60,
          billName: "孟勇",
          createDateTime: "1984-08-18 13:57:24",
          payDateTime: "2016-04-23 14:02:28",
          shippingCost: 65,
          payMethod: "支付宝",
          note: "请及时发货"
        },
        orderItemList: [
          {
            orderId: 60,
            quantity: 310964,
            status: "aliquip quis adipisicing voluptate ullamco",
            totalPrice: 33,
            item: {
              itemId: "210000197612064656",
              name: "iezcxridb",
              unitCost: 3618134.818448038,
              listPrice: 53099257.22776379,
              status: "veniam qui",
              categoryId: "210000198612156897",
              descn: "题验斯各人消带保土音观业收需精石。",
              imgUrl: "http://dummyimage.com/125x125"
            },
            taskId: 79
          },
          {
            orderId: 60,
            quantity: 765965,
            status: "nisi",
            totalPrice: 66,
            item: {
              itemId: "440000200410315047",
              name: "bbzjalm",
              unitCost: 9834862.502083719,
              listPrice: 16734342.036927562,
              status: "irure dolor",
              categoryId: "500000200509185154",
              descn: "记目近元王场要展出单式化向。",
              imgUrl: "http://dummyimage.com/120x90"
            },
            taskId: 57
          },
          {
            orderId: 60,
            quantity: 635008,
            status: "esse do proident cupidatat nisi",
            totalPrice: 33,
            item: {
              itemId: "36000020070311621X",
              name: "puhrbmlo",
              unitCost: 22612450.691776197,
              listPrice: 57018712.23601958,
              status: "tempor",
              categoryId: "210000197407245300",
              descn: "面党革王圆将南数保热候术增思存着观。",
              imgUrl: "http://dummyimage.com/88x31"
            },
            taskId: 81
          }
        ],
        mainsite: {
          mainsiteId: "17",
          province: "广西壮族自治区",
          city: "巴彦淖尔市",
          district: "西南"
        },
        subsite: {
          subsiteId: "6",
          mainsiteId: "9",
          district: "华南"
        },
        msg: "订单主站从西南发货, 配送站为华南站, 预计总配送时长3天"
      },
      dialogVisible: false,
      formData: {
        mainsiteId: "",
        shippingCost: null
      },
      orderId: "",
      rowFrame: null
    };
  },
  mounted() {
    this.orderId = this.$route.params.orderId;
    this.rowFrame = this.$router.currentRoute.query.rowFrame;
    console.log(this.$router.currentRoute.query);
    this.fetchData();
  },
  methods: {
    updateOnClick() {
      this.dialogVisible = false;
      console.log(this.formData);
      this.editOrderInfo(this.orderInfo.order.processStatus);
    },
    fetchData() {
      getExamineDetails(this.orderId).then(res => {
        this.orderInfo = res.data;
      });
    },
    onEdit() {
      this.dialogVisible = true;
      this.formData.mainsiteId = this.orderInfo.mainsite
        ? this.orderInfo.mainsite.mainsiteId
        : "";
      this.formData.shippingCost = this.orderInfo.order.shippingCost;
    },
    canceled() {
      this.dialogVisible = false;
      this.formData = {
        mainsiteId: "",
        shippingCost: null
      };
    },
    editOrderInfo(status) {
      var reqData = {};
      reqData.processStatus = status;
      reqData.mainsiteId = this.orderInfo.mainsite
        ? this.orderInfo.mainsite.mainsiteId
        : "";
      if (this.formData.shippingCost) {
        reqData.shippingCost = this.formData.shippingCost;
      }
      console.log(reqData);
      patchOrderInfo(this.orderId, reqData).then(() => {
        this.fetchData();
      });
    },
    onPass() {
      var alertMsg = "是否确认接收 订单" + this.orderId + " ?";
      this.$confirm(alertMsg, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      })
        .then(() => {
          console.log("确认接收 订单" + this.orderId);
          this.confirmCheck(this.rowFrame, "P"); //确认接收
          setTimeout(function() {router.push("/main/orderExamine")}, 1500);
        })
    },
    onCancel() {
      var alertMsg = "是否确认接收 订单" + this.orderId + " ?";
      this.$confirm(alertMsg, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "success"
      })
        .then(() => {
          console.log("确认接收 订单" + this.orderId);
          this.confirmCheck(this.rowFrame, "C"); //确认接收
          setTimeout(function() {router.push("/main/orderExamine")}, 1500);
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
      patchOrderStatus(rowFrame.orderId, status).then(resp => {
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
      });
    }
  }
};
</script>

<style scoped>
.display-box {
  margin: 50px 50px 100px;
}
.text-label {
  float: left;
  width: 100px;
  min-height: 30px;
  text-align: right;
}
.text-value {
  float: left;
  min-height: 30px;
  max-width: 60%;
  text-align: left;
}
</style>