<template>
  <div>
    <el-row>
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>订单管理</el-breadcrumb-item>
          <el-breadcrumb-item>退货换单</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col style="padding-left: 20px;">
        <el-card>
          <el-table :data="orderForms">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="退货单编号">
                    <span>{{props.row.orderId}}</span>
                  </el-form-item>
<!--                  <el-form-item label="订单编号">-->
<!--                    <span>{{props.row.paymentTime}}</span>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item label="类型">-->
                    <!-- 退货/换货 -->
<!--                    <span>{{props.row.paymentAmount}}</span>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item label="商品编号">-->
<!--                    <span>{{props.row.freight}}</span>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item label="退/换货数量">-->
<!--                    <span>{{props.row.freight}}</span>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item label="申请时间">-->
<!--                    <span>{{props.row.freight}}</span>-->
<!--                  </el-form-item>-->
                  <el-form-item label="退款金额">
                    <span>{{props.row.paymentAmount}}</span>
                  </el-form-item>
<!--                  <el-form-item label="处理状态">-->
<!--                    <el-tag v-if="props.row.status=='N'" type="danger">未处理</el-tag>-->
<!--                    <el-tag v-else-if="props.row.status=='P'" type="warning">处理中</el-tag>-->
<!--                    <el-tag v-else-if="props.row.status=='D'" type="success">交易成功</el-tag>-->
<!--                    <el-tag v-else-if="props.row.status=='C'" type="info">交易关闭</el-tag>-->
<!--                  </el-form-item>-->
                  <el-form-item label="申请时间">
                    <span>{{props.row.applyTime}}</span>
                  </el-form-item>
<!--                  <el-form-item label="处理时间">-->
<!--                    <span>{{props.row.receiverPhone}}</span>-->
<!--                  </el-form-item>-->
                  <el-form-item label="申请原因">
                    <span>{{props.row.reason}}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="退货单ID" prop="rfId">
              <template slot-scope="scope">
                {{scope.row.orderId}}
              </template>
            </el-table-column>
            <el-table-column label="退单原因" prop="receiver">
              <template slot-scope="scope">
                {{scope.row.reason}}
              </template>
            </el-table-column>
<!--            <el-table-column label="状态">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag v-if="scope.row.status=='N'" type="danger">未处理</el-tag>-->
<!--                <el-tag v-else-if="scope.row.status=='P'" type="warning">已处理</el-tag>-->
<!--                <el-tag v-else-if="scope.row.status=='I'" type="success">进行中</el-tag>-->
<!--                <el-tag v-else-if="scope.row.status=='Y'" type="info">成功</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="clickToPass(scope.row)">通过</el-button>
                <el-button type="danger" @click="clickToReject(scope.row)">拒绝</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods:{
    clickToPass:function (returnOrder) {
      this.$confirm('此操作将接受该退货单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        /*
        这里是  patch  修改订单状态
        */
        this.orderForms.splice(this.orderForms.indexOf(returnOrder),1);
        this.$message({
          type: 'success',
          message: '已成功接受该退货单!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消接受该退货单'
        });
      });
    },


    clickToReject:function (returnOrder) {
      this.$confirm('此操作将拒绝该退货单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        /*
        这里是  patch  修改订单状态
        */
        this.orderForms.splice(this.orderForms.indexOf(returnOrder),1);
        this.$message({
          type: 'success',
          message: '成功拒绝该退货单！'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消拒绝该退货单'
        });
      });
    }
  },
  data: function() {
    return {
      spanValue: 20,
      orderForms: [
        {
          orderId: 3582048,
          applyTime: "1996-01-28 11:20:17",
          paymentAmount: 56811112.815615684,
          reason:"👴🏿不想要🌶",
        },
        {
          orderId: -71028570,
          applyTime: "2013-09-08 05:18:16",
          paymentAmount: 70351359.7887876,
          reason:"🚬🐘带🦁",
        },
        {
          orderId: 68772420,
          applyTime: "2001-10-21 07:03:09",
          paymentAmount: -32197521.945785075,
          reason:"现在是巴黎凌晨四点半，我回不去了",
        },
        {
          orderId: 77047171,
          applyTime: "1992-01-20 09:17:49",
          paymentAmount: 2080963.1956221908,
          reason:"引清军入关，焚书坑儒",
        },
        {
          orderId: 49766969,
          applyTime: "1972-02-04 12:17:57",
          paymentAmount: 99059670.48581079,
          reason:"$真正的粉丝看到符号就懂了💜",
        }
      ]
    };
  }
};
</script>

<style scoped>
.el-row {
  padding-top: 20px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>