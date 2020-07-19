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
          <el-breadcrumb-item>入库请求{{recordId}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-dialog title="调整库房编号" :visible.sync="dialogVisible">
      <el-form :model="formData" label-position="right" label-width="80px">
        <el-row>
          <el-col :span="14" :offset="4">
            <el-form-item label="库房ID">
              <el-select v-model="formData.warehouseId">
                <el-option
                  v-for="option in record.warehouseOptionalList"
                  :key="option"
                  :label="option"
                  :value="option"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-button @click="dialogVisible=false;formData.warehouseId = record.warehouseId;">取消</el-button>
        <el-button type="primary" @click="updateOnClick">确定</el-button>
      </el-form>
    </el-dialog>
    <div class="display-box">
      <el-row :gutter="80">
        <el-col :span="1" :offset="19">
          <el-button type="danger" @click="onRefuse">拒绝</el-button>
        </el-col>
        <el-col :span="1">
          <el-button
            type="primary"
            @click="dialogVisible=true;formData.warehouseId = record.warehouseId;"
          >编辑</el-button>
        </el-col>
        <el-col :span="1">
          <el-button type="success" @click="onPass">通过</el-button>
        </el-col>
      </el-row>
      <br />
      <el-row>
        <BasicCard header="入库请求详情">
          <el-row>
            <el-col :span="12" style="text-align: left">
              <h3>
                <div class="text-label">记录编号:</div>
                <div class="text-value">{{record.recordId}}</div>
              </h3>
            </el-col>
          </el-row>
          <el-divider></el-divider>
          <!-- 订单详情 -->
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">请求日期：</div>
              <div class="text-value">{{record.timeStamp}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">所属库房：</div>
              <div class="text-value">{{record.warehouseId}}</div>
            </el-col>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">操作类型：</div>
              <div class="text-value">{{record.typeDescn}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">关联ID：</div>
              <div class="text-value">{{record.formId}}</div>
            </el-col>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">货物来源：</div>
              <div class="text-value">{{record.itemSrc}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">批准状态：</div>
              <div class="text-value">
                <el-tag v-if="record.approvalStatus=='W'" type="warning">待审核</el-tag>
                <el-tag v-if="record.approvalStatus=='Y'" type="success">已审核</el-tag>
                <el-tag v-if="record.approvalStatus=='F'" type="info">已失效</el-tag>
              </div>
            </el-col>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">操作人：</div>
              <div class="text-value">{{record.approver}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">商品数量：</div>
              <div class="text-value">{{record.itemNum}}</div>
            </el-col>
          </el-row>
        </BasicCard>
        <BasicCard header="入库商品信息">
          <el-row style="text-align: left">
            <el-col :span="2">
              <el-image style="width: 100px; height: 100px" :src="record.item.imgUrl" fit="fit"></el-image>
            </el-col>
            <el-col :span="22">
              <el-row>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">商品ID：</div>
                  <div class="text-value">{{record.item.itemId}}</div>
                </el-col>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">大类ID：</div>
                  <div class="text-value">{{record.item.categoryId}}</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">商品名称：</div>
                  <div class="text-value">{{record.item.name}}</div>
                </el-col>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">文字描述：</div>
                  <div class="text-value">{{record.item.descn}}</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">原价：</div>
                  <div class="text-value">{{record.item.unitCost}}</div>
                </el-col>
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">售价：</div>
                  <div class="text-value">{{record.item.listPrice}}</div>
                </el-col>
              </el-row>
              <el-row v-if="record.item.status">
                <el-col :span="12" style="text-align: left">
                  <div class="text-label">状态：</div>
                  <div class="text-value">
                    <el-tag v-if="record.item.status=='F'" type="warning">下架</el-tag>
                    <el-tag v-if="record.item.status=='P'" type="success">上架</el-tag>
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </BasicCard>
      </el-row>
    </div>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
// import myaxious from "../../plugins/myaxios";
import { getMainsiteInDetails, patchMainsiteInRecord } from "../../api/storage";

export default {
  components: { BasicCard },
  methods: {
    fetchData() {
      getMainsiteInDetails(this.mainsiteId, this.recordId).then(res => {
        this.record = res.data;
      });
    },
    updateOnClick() {
      patchMainsiteInRecord(this.mainsiteId, this.recordId, {
        approvalStatus: this.record.approvalStatus,
        warehouseId: this.formData.warehouseId
      }).then(res => {
        this.fetchData();
        console.log(res.status);
      });
      this.dialogVisible = false;
    },
    onPass() {
      patchMainsiteInRecord(this.mainsiteId, this.recordId, {
        approvalStatus: "Y",
        warehouseId: ""
      }).then(res => {
        this.frame.ack();
        this.$message({
          type: "success",
          message: "请求审核成功"
        });
        this.fetchData();
        console.log(res.status);
      });
    },
    onRefuse() {
      patchMainsiteInRecord(this.mainsiteId, this.recordId, {
        approvalStatus: "F",
        warehouseId: ""
      }).then(() => {
        this.frame.ack();
        this.$message({
          type: "success",
          message: "请求拒绝成功"
        });
        this.fetchData();
      });
    }
  },
  data: () => {
    return {
      mainsiteId: "",
      recordId: "",
      record: {
        recordId: 162877391,
        timeStamp: "1995-04-16 16:48:38",
        warehouseId: "WH-001",
        warehouseOptionalList: [],
        item: {
          itemId: "EST-01",
          name: "牛奶",
          categoryId: "YL-01",
          categoryName: "饮料",
          unitPrice: 40,
          listPrice: 55,
          descn: "草莓味",
          imgUrl: "milk.jpg"
        },
        itemNum: 2,
        type: 3,
        typeDescn: "退货",
        formId: 8391864581,
        itemSrc: "czlll",
        approvalStatus: "W",
        approver: "Jack"
      },
      dialogVisible: false,
      formData: {
        warehouseId: ""
      },
      frame: null
    };
  },
  mounted() {
    this.mainsiteId = this.$route.params.mainsiteId;
    this.recordId = this.$route.params.recordId;
    this.frame = this.$router.currentRoute.query.record;
    console.log(this.mainsiteId);
    console.log(this.recordId);
    this.fetchData();
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