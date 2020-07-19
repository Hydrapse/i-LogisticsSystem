<template>
  <div>
    <el-row style="padding-top: 20px; padding-bottom: 1%">
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配置策略</el-breadcrumb-item>
          <el-breadcrumb-item>仓储策略</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="13">
        <BasicCard header="仓储自动审核策略" style="text-align: left" class="display-box">
          <el-form label-width="100px">
            <el-form-item label="启用策略" style="font-weight: bold; zoom: 1.3">
              <el-switch active-color="#13ce66" v-model="formData.enable"></el-switch>
            </el-form-item>
            <el-divider content-position="left" style="font-weight: bold">
              <b>出入库单总额自动审核规则</b>
            </el-divider>
            <el-form-item label="启用规则">
              <el-switch :disabled="!formData.enable" v-model="formData.totalPriceLimit"></el-switch>
            </el-form-item>
            <el-form-item label="总额上限">
              <el-input-number
                v-model="formData.totalPriceAmount"
                :disabled="!formData.enable || !formData.totalPriceLimit"
                :min="0"
              ></el-input-number>
            </el-form-item>
            <small style="padding-left: 35px;color: #858796">总额低于上限的出入库单将会被自动审核</small>
            <br />
            <br />
            <el-divider content-position="left" style="font-weight: bold">
              <b>出入库数量自动审核规则</b>
            </el-divider>
            <el-form-item label="启用规则">
              <el-switch :disabled="!formData.enable" v-model="formData.totalNumLimit"></el-switch>
            </el-form-item>
            <el-form-item label="数量上限">
              <el-input-number
                v-model="formData.totalNum"
                :disabled="!formData.enable || !formData.totalNumLimit"
                :min="0"
              ></el-input-number>
            </el-form-item>
            <small style="padding-left: 35px;color: #858796">出入库数量低于上限的出入库单将会被自动审核</small>
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
                <small style="padding-left: 35px;color: #858796">处于白名单中的大类出入库单将会被自动审核</small>
                <br />
                <br />
              </el-col>
              <el-col :span="10">
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

            <el-divider content-position="left" style="font-weight: bold">
              <b>入库状态自动审核规则</b>
            </el-divider>
            <el-row>
              <el-col :span="12">
                <el-form-item label="启用规则">
                  <el-switch :disabled="!formData.enable" v-model="formData.siteInTypeLimit"></el-switch>
                </el-form-item>
                <el-form-item label="状态白名单">
                  <el-select
                    multiple
                    v-model="formData.siteInTypeWhiteList"
                    :disabled="!formData.enable || !formData.siteInTypeLimit"
                  >
                    <el-option
                      v-for="siteInType in siteInTypeList"
                      :key="siteInType.value"
                      :label="siteInType.label"
                      :value="siteInType.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <br />
            </el-row>
            <el-divider content-position="left" style="font-weight: bold">
              <b>出库状态自动审核规则</b>
            </el-divider>
            <el-row>
              <el-col :span="12">
                <el-form-item label="启用规则">
                  <el-switch :disabled="!formData.enable" v-model="formData.siteOutTypeLimit"></el-switch>
                </el-form-item>
                <el-form-item label="状态白名单">
                  <el-select
                    multiple
                    v-model="formData.siteOutTypeWhiteList"
                    :disabled="!formData.enable || !formData.siteOutTypeLimit"
                  >
                    <el-option
                      v-for="siteOutType in siteOutTypeList"
                      :key="siteOutType.value"
                      :label="siteOutType.label"
                      :value="siteOutType.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-divider></el-divider>
          <el-col :span="10" :offset="18">
            <el-button @click="fetchSiteIOSettings">重置</el-button>
            <el-button type="primary" @click="submitSiteIOSettings">提交</el-button>
          </el-col>
        </BasicCard>
      </el-col>
      <el-col :span="10">
        <BasicCard header="出库分拣策略" style="text-align: left" class="display-box">
          <el-form label-width="100px">
            <el-form-item label="分拣策略">
              <el-select v-model="selectedSortOpt">
                <el-tooltip
                  class="tooltip"
                  v-for="sortOption in sortOptions"
                  :content="sortOption.desc"
                  placement="right"
                  :key="sortOption.value"
                >
                  <el-option :value="sortOption.value" :label="sortOption.label"></el-option>
                </el-tooltip>
              </el-select>
              <br />
              <small>{{selectedSortOpt ? sortOptions[selectedSortOpt-1].desc : ""}}</small>
            </el-form-item>
            <el-form-item v-show="selectedSortOpt==3" label="阈值">
              <el-input-number :min="0" v-model="threshold"></el-input-number>
            </el-form-item>
          </el-form>
          <el-divider></el-divider>
          <el-col :span="10" :offset="15">
            <el-button @click="fetchSiteIOSettings">重置</el-button>
            <el-button type="primary" @click="fetchSiteoutSettings">提交</el-button>
          </el-col>
        </BasicCard>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getCatergies } from "../../api/storage";
import {
  getSiteIOSettings,
  getSiteoutSettings,
  setSiteIOSettings,
  setSiteoutSettings
} from "../../api/settings";

export default {
  components: { BasicCard },
  data: function() {
    return {
      formData: {
        enable: false,
        totalPriceLimit: false,
        totalPriceAmount: 0,
        totalNumLimit: false,
        totalNum: 0,
        categoryIdLimit: false,
        categoryIdWhiteList: [],
        siteInTypeLimit: false,
        siteInTypeWhiteList: [],
        siteOutTypeLimit: false,
        siteOutTypeWhiteList: []
      },
      selectedSortOpt: null,
      threshold: 0,
      categoryList: [],
      siteInTypeList: [
        { value: 1, label: "补货" },
        { value: 2, label: "调货" },
        { value: 3, label: "退货" },
        { value: 4, label: "换货" }
      ],
      siteOutTypeList: [
        { value: 1, label: "退货" },
        { value: 2, label: "调货" },
        { value: 3, label: "发货" }
      ],
      sortOptions: [
        {
          value: 1,
          label: "选项一",
          desc: "默认从单个库房出库，单个库房存货不足时剩余量重新分拣出库"
        },
        {
          value: 2,
          label: "选项二",
          desc: "默认从单个库房出库，单个库房存货不足时按比例从多个库房分批出库"
        },
        {
          value: 3,
          label: "选项三",
          desc: "默认从单个库房出库，到达一定值时按比例从多个库房分批出库"
        }
      ]
    };
  },
  methods: {
    fetchSiteIOSettings() {
      getSiteIOSettings().then(res => {
        this.formData = res.data;
      });
    },
    fetchSiteoutSettings() {
      getSiteoutSettings().then(res => {
        this.selectedSortOpt = res.data.option;
        this.threshold = res.data.threshold;
      });
    },
    fetchCategories() {
      getCatergies().then(res => {
        this.categoryList = res.data;
      });
    },
    submitSiteIOSettings() {
      setSiteIOSettings(this.formData)
        .then(res => {
          if (res.status == 200) {
            this.$message({
              message: "修改出入库策略审核成功",
              type: "success"
            });
          }
        })
        .catch(() => {
          this.$message({
            message: "修改失败",
            type: "error"
          });
        });
    },
    submitSiteoutSettings() {
      setSiteoutSettings({
        option: this.selectedSortOpt,
        threshold: this.threshold
      })
        .then(() => {
          this.$message({
            message: "修改出库分拣策略成功",
            type: "success"
          });
        })
        .catch(() => {
          this.$message({
            message: "修改失败",
            type: "error"
          });
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
    this.fetchSiteIOSettings();
    this.fetchSiteoutSettings();
  }
};
</script>

<style scoped>
.display-box {
  margin: 0.5% 1.5%;
  width: 97.5%;
}
.tooltip {
  margin: 4px;
}
</style>