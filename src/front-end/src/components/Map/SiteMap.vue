<template>
  <div>
    <el-row>
      <el-col :offset="1" style="padding-top: 20px; padding-bottom: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item>主站管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <BasicCard header="站点地图" style="left: 1.5%;top: 1.5%;width: 98.5%">
      <baidu-map
        class="map"
        :center="{lng: 103.854, lat: 31.455}"
        :zoom="6"
        :scroll-wheel-zoom="false"
      >
        <bml-curve-line :points="points" :editing="true" @lineupdate="update"></bml-curve-line>

        <bm-context-menu>
          <bm-context-menu-item :callback="JiaXing" text="嘉兴主站"></bm-context-menu-item>
          <bm-context-menu-item :callback="GuangZhou" text="广州主站"></bm-context-menu-item>
          <bm-context-menu-item :callback="SuZhou" text="苏州主站"></bm-context-menu-item>
          <bm-context-menu-item :callback="ChangSha" text="长沙主站"></bm-context-menu-item>
          <bm-context-menu-item :callback="XiAn" text="西安主站"></bm-context-menu-item>
        </bm-context-menu>

        <bm-control>
          <el-select v-model="selectedCity" placeholder="请选择主站">
            <el-option
              v-for="item in siteOption"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <el-button type="primary" @click="clickToMainSite">进入</el-button>
        </bm-control>

        <bm-overlay class="sample" :style="{visibility:adjustVision}">
          <el-table :data="AdjustForms" style="width: 100%;" height="400">
            <el-table-column prop="adjustId" width="100" label="调货单ID" ></el-table-column>
            <el-table-column prop="itemId" width="100" label="被调商品ID"></el-table-column>
            <el-table-column prop="itemNum" width="100" label="调货数量" ></el-table-column>
            <el-table-column prop="from" width="100" label="货源地" ></el-table-column>
            <el-table-column prop="to" width="100" label="缺货地"></el-table-column>
            <el-table-column prop="status" width="100" label="调货状态" ></el-table-column>
            <el-table-column fixed="right" width="100" label="操作" >
              <template slot-scope="scope">
                <el-button @click="clickToAdjustRoute(scope.row)" type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </bm-overlay>

        <bm-driving
          :start="pointToDrawRouter.fromPoint"
          :end="pointToDrawRouter.toPoint"
          :auto-viewport="true"
          policy="BMAP_DRIVING_POLICY_LEAST_TIME"
          :panel="false"
        ></bm-driving>

        <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      </baidu-map>
    </BasicCard>
  </div>
</template>

<script>
import { BmlCurveLine } from "vue-baidu-map";
import { getTrasferInfo } from "../../api/taskform";
import BasicCard from "../PanelCard/BasicCard";
export default {
  name: "SiteMap",
  components: {
    BasicCard,
    BmlCurveLine
  },
  methods: {
    clickToMainSite() {
      if (this.selectedCity == "MAIN-001") {
        this.JiaXing();
      } else if (this.selectedCity == "MAIN-002") {
        this.GuangZhou();
      } else if (this.selectedCity == "MAIN-003") {
        this.SuZhou();
      } else if (this.selectedCity == "MAIN-004") {
        this.ChangSha();
      } else if (this.selectedCity == "MAIN-005") {
        this.XiAn();
      }
    },

    JiaXing() {
      this.$router.push("mainsites/MAIN-001/inventory/warehouses");
      console.log("001");
    },
    GuangZhou() {
      this.$router.push("mainsites/MAIN-002/inventory/warehouses");
    },
    SuZhou() {
      this.$router.push("mainsites/MAIN-003/inventory/warehouses");
    },
    ChangSha() {
      this.$router.push("mainsites/MAIN-004/inventory/warehouses");
    },
    XiAn() {
      this.$router.push("mainsites/MAIN-005/inventory/warehouses");
    },

    update(e) {
      this.points = e.target.cornerPoints;
    },

    getAdjustFormByMainSiteId(MainSiteId) {
      console.log(MainSiteId);
    },
    clickToAdjustRoute(adjustItem) {
      console.log(adjustItem);
      this.pointToDrawRouter = adjustItem;
    }
  },
  data() {
    return {
      adjustVision: "hidden",

      selectedCity: "",

      points: [
        { lng: 120.565, lat: 30.63 },
        { lng: 113.273, lat: 23.158 },
        { lng: 121.109, lat: 31.45 },
        { lng: 112.82, lat: 28.347 },
        { lng: 108.961, lat: 34.266 }
      ],
      siteCity: ["嘉兴", "广州", "苏州", "长沙", "西安"],
      siteOption: [
        {
          value: "MAIN-001",
          label: "嘉兴主站"
        },
        {
          value: "MAIN-002",
          label: "广州主站"
        },
        {
          value: "MAIN-003",
          label: "苏州主站"
        },
        {
          value: "MAIN-004",
          label: "长沙主站"
        },
        {
          value: "MAIN-005",
          label: "西安主站"
        },
        {
          value: "",
          label: "查看地图"
        }
      ],

      AdjustForms: [
        {
          adjustid: 52,
          itemid: "78677",
          itemnum: 9,
          from: "tempor aliqua consectetur commodo in",
          to: "Lorem",
          status: "adipisicing officia cupidatat magna laboris",
          toPoint: {
            lng: 113.273,
            lat: 23.158
          },
          fromPoint: {
            lng: 112.82,
            lat: 28.347
          }
        },
        {
          adjustid: 59,
          itemid: "68868",
          itemnum: 34,
          from: "consequat ullamco",
          to: "officia laboris culpa sed",
          status: "occaecat enim",
          toPoint: {
            lng: 113.273,
            lat: 23.158
          },
          fromPoint: {
            lng: 108.961,
            lat: 34.266
          }
        }
      ],
      pointToDrawRouter: {}
    };
  },
  watch: {
    selectedCity(val) {
      console.log(val);
      if (val != "") {
        //先发请求 通过 val（就是主站的ID） 来获取配送单信息，填充到 this.AdjustForms
        getTrasferInfo(this.selectedCity).then(res => {
          this.AdjustForms = res.data;
        });
        this.getAdjustFormByMainSiteId(val);
        this.adjustVision = "visible";
      } else {
        this.adjustVision = "hidden";
      }
    }
  }
};
</script>

<style scoped>
.map {
  width: 100%;
  height: 610px;
}
.sample {
  width: 500px;
  height: 400px;
  line-height: 40px;
  background: rgba(0, 0, 0, 0.3);
  overflow: hidden;
  box-shadow: 0 0 5px #000;
  color: #fff;
  text-align: center;
  padding: 10px;
  position: absolute;
  left: 20px;
  top: 150px;
}
</style>