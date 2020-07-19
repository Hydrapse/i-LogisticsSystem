<template>
  <div>
    <el-row style="padding-top: 20px">
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配置策略</el-breadcrumb-item>
          <el-breadcrumb-item>订单策略</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1" style="padding-top: 1.5%">
        <BasicCard header="订单自动审核策略" style="text-align: left" class="display-box">
          <el-form label-width="100px">
            <el-form-item label="启用策略" style="font-weight: bold; zoom: 1.3">
              <el-switch active-color="#13ce66" v-model="formData.enable"></el-switch>
            </el-form-item>
            <el-divider content-position="left" style="font-weight: bold">
              <b>订单总额自动审核规则</b>
            </el-divider>
            <el-col>
              <el-form-item label="启用规则">
                <el-switch :disabled="!formData.enable" v-model="formData.totalPriceLimit"></el-switch>
              </el-form-item>
            </el-col>
            <el-form-item label="总额上限">
              <el-input-number
                v-model="formData.totalPriceAmount"
                :disabled="!formData.enable || !formData.totalPriceLimit"
                :min="0"
              ></el-input-number>
            </el-form-item>
            <small style="padding-left: 35px;color: #858796">总额低于上限的订单将会被自动审核</small>
            <br />
            <br />
            <br />
            <el-divider content-position="left" style="font-weight: bold">
              <b>商品大类自动审核规则</b>
            </el-divider>
            <el-row>
              <el-col :span="12">
                <el-form-item label="启用规则">
                  <el-switch :disabled="!formData.enable" v-model="formData.categoryIdLimit"></el-switch>
                </el-form-item>
                <el-form-item label="大类白名单">
                  <el-select
                    multiple
                    v-model="formData.categoryIdWhiteList"
                    filterable
                    collapse-tags
                    :disabled="!formData.enable || !formData.categoryIdLimit"
                  >
                    <el-option
                      v-for="category in categoryList"
                      :key="category.categoryId"
                      :label="category.categoryName"
                      :value="category.categoryId"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-col>
                  <small style="padding-left: 40px;color: #858796">处于白名单中的大类订单将会被自动审核</small>
                </el-col>
              </el-col>
              <el-col :span="8">
                <el-table :data="formData.categoryIdWhiteList" height="180">
                  <el-table-column label="白名单大类">
                    <template slot-scope="scope">{{scope.row}}</template>
                  </el-table-column>
                  <el-table-column>
                    <template slot-scope="scope">
                      <el-button type="danger" @click="delCateFromWhiteList(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
            <br />

            <el-divider content-position="left" style="font-weight: bold">
              <b>下单用户自动审核规则</b>
            </el-divider>
            <el-row>
              <el-col :span="12">
                <el-form-item label="启用规则">
                  <el-switch :disabled="!formData.enable" v-model="formData.customerIdLimit"></el-switch>
                </el-form-item>
                <el-form-item label="用户白名单">
                  <el-select
                    multiple
                    filterable
                    v-model="formData.customerIdWhiteList"
                    collapse-tags
                    :disabled="!formData.enable || !formData.customerIdLimit"
                  >
                    <el-option
                      v-for="customerId in customerIdList"
                      :key="customerId"
                      :label="customerId"
                      :value="customerId"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-table :data="formData.customerIdWhiteList" height="180">
                  <el-table-column label="白名单用户">
                    <template slot-scope="scope">{{scope.row}}</template>
                  </el-table-column>
                  <el-table-column>
                    <template slot-scope="scope">
                      <el-button type="danger" @click="delUserFromWhiteList(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </el-form>
          <el-divider></el-divider>
          <el-col :span="4" :offset="20">
            <el-button @click="fetchOrderSettings">重置</el-button>
            <el-button type="primary" @click="submitOrderSettings">提交</el-button>
          </el-col>
        </BasicCard>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getCustomers } from "../../api/clientele";
import { getCatergies } from "../../api/storage";
import { getOrderSettings, setOrderSettings } from "../../api/settings";

export default {
  components: { BasicCard },
  data: function() {
    return {
      formData: {
        enable: false,
        totalPriceLimit: false,
        totalPriceAmount: 0,
        categoryIdLimit: false,
        categoryIdWhiteList: [],
        customerIdLimit: false,
        customerIdWhiteList: []
      },
      categoryList: [],
      customerIdList: []
    };
  },
  // watch:{
  //   enable(val){
  //     console.log(val);
  //     if(val == false){
  //       this.formData.totalPriceLimit = false;
  //       this.formData.categoryIdLimit = false;
  //       this.formData.customerIdLimit = false;
  //     }
  //   }
  // },
  methods: {
    fetchCategories() {
      getCatergies().then(res => {
        this.categoryList = res.data;
      });
    },
    fetchCustomerId() {
      getCustomers({
        pageNum: 1,
        pageSize: 1000
      }).then(res => {
        this.customerIdList = res.data.content.map(customer => {
          return customer.customerId;
        });
      });
    },
    fetchOrderSettings() {
      getOrderSettings().then(res => {
        this.formData = res.data;
      });
    },
    submitOrderSettings() {
      setOrderSettings(this.formData).then(res => {
        if (res.status == 200) {
          this.$message({
            message: "修改成功",
            type: "success"
          });
        }
      });
    },
    delUserFromWhiteList(userId) {
      this.formData.customerIdWhiteList = this.formData.customerIdWhiteList.filter(
        id => {
          return id != userId;
        }
      );
    },
    delCateFromWhiteList(categoryId) {
      this.formData.categoryIdWhiteList = this.formData.categoryIdWhiteList.filter(
        id => {
          return id != categoryId;
        }
      );
    }
  },
  mounted() {
    this.fetchCategories();
    this.fetchCustomerId();
    this.fetchOrderSettings();
  }
};
</script>

<style scoped>
.display-box {
  margin: 0.5% 1.5%;
  width: 97.5%;
}
</style>