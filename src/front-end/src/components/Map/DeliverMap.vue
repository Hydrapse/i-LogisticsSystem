<template>
<div>
  <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配送管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/taskForm">任务单列表</el-breadcrumb-item>
          <el-breadcrumb-item :to="'/main/taskforms/'+taskId+'/details'">任务单{{taskId}}</el-breadcrumb-item>
          <el-breadcrumb-item>配送信息</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col> 
    </el-row>
  <el-row :gutter="20" class="display-box" style="padding-left: 2%">
    <el-col :span="11">
      <BasicCard header="配送信息" style="left: 0.75%;height: 600px">
        <el-steps direction="vertical" :active="deliverStauts" :process-status="processStatus">
          <el-step :title="startStep" :description="startDescrip"></el-step>
          <el-step :title="nowStep" :description="nowDescrip"></el-step>
          <el-step :title="endStep" :description="endDescrip"></el-step>
        </el-steps>
      </BasicCard>
    </el-col>
    <el-col :span="13">
      <BasicCard header="配送地图" style="left: 1.3%;width: 98.5%">
        <baidu-map
          class="map"
          :center="{lng: 116.432045, lat: 39.910683}"
          :zoom="18"
          :scroll-wheel-zoom="false"
        >
          <bm-driving
            :start="startPosition"
            :end="endPosition"
            :auto-viewport="true"
            policy="BMAP_DRIVING_POLICY_LEAST_TIME"
            :panel="false"
            location="北京"
            :waypoints="nowPosition"
          ></bm-driving>
          <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
        </baidu-map>
      </BasicCard>
    </el-col>
  </el-row>
</div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
// import myaxios from "../../plugins/myaxios";
import {getDeliverPoints} from "../../api/map"

export default {
  name: "DeliverMap",
  components: {
    BasicCard
  },
  data() {
    return {
      deliverStauts: 1,

      startPosition: {
        lng: 116.432045,
        lat: 39.910683
      },
      endPosition: { lng: 112.55, lat: 37.87 },
      nowPosition: [],

      startStep: "",
      nowStep: "",
      endStep: "",
      startDescrip: "",
      nowDescrip: "",
      endDescrip: "",

      processStatus: "",
      restDeliverDay: 1,

      first: {
        distance: 27,
        duration: 92,
        steps: [
          {
            leg_index: 3,
            start_location: {
              lng: 108.47,
              lat: 33.83
            },
            end_location: {
              lng: 31,
              lat: 96
            }
          },
          {
            leg_index: 69,
            start_location: {
              lng: 96,
              lat: 60
            },
            end_location: {
              lng: 114.31,
              lat: 30.52
            }
          }
        ]
      },
      second: {
        distance: 31,
        duration: 52,
        steps: [
          {
            leg_index: 11,
            start_location: {
              lng: 56,
              lat: 62
            },
            end_location: {
              lng: 18,
              lat: 46
            }
          },
          {
            leg_index: 26,
            start_location: {
              lng: 99,
              lat: 37
            },
            end_location: {
              lng: 1,
              lat: 56
            }
          },
          {
            leg_index: 97,
            start_location: {
              lng: 60,
              lat: 38
            },
            end_location: {
              lng: 78,
              lat: 49
            }
          },
          {
            leg_index: 83,
            start_location: {
              lng: 27,
              lat: 22
            },
            end_location: {
              lng: 65,
              lat: 100
            }
          },
          {
            leg_index: 21,
            start_location: {
              lng: 49,
              lat: 33
            },
            end_location: {
              lng: 120.129721,
              lat: 30.314429
            }
          }
        ]
      },
      status: "Y",
      mainSiteName: "MAIN-004",
      subSiteName: "SUB-005",

      taskId: null
    };
  },
  created() {
    
  },
  methods: {
    // getDeliverPoints: function() {
    //   myaxios.get("/goods/catalog").then(res => {
    //     console.log(res);
    //     // this.startPosition = res.data[0];
    //     // this.endPosition = res.data[1];
    //     // this.nowPosition = res.data[2];
    //   });
    // }
    updateStatus() { 
    if(this.status == "W") {//缺货
      this.deliverStauts = 0; 
      this.processStatus = "process"
      this.startStep = "未发货";
      this.startDescrip = this.mainSiteName+"商品缺货,调货中...";
      this.nowStep = "运输";
      this.nowDescrip = "";
      this.endStep = "配送";
      this.endDescrip = "";
    } else if(this.status == "U") { //未发出
      this.deliverStauts = 0; 
      this.processStatus = "process";
      this.startStep = "未发货";
      this.startDescrip = "等待从"+ this.mainSiteName + "出库...";
      this.nowStep = "运输";
      this.nowDescrip = "";
      this.endStep = "配送";
      this.endDescrip = "";
    } else if(this.status == "O") {//在途中
      this.deliverStauts = 1; 
      this.processStatus = "process";
      this.startStep = "已发货";
      this.startDescrip = this.mainSiteName + "已发出";
      this.nowStep = "运输";
      this.nowDescrip = "正在前往" + this.subSiteName + "...";
      this.endStep = "配送";
      this.endDescrip = "";
    } else if(this.status == "N"){//未配送
      this.deliverStauts = 2;
      this.processStatus = "process";
      this.startStep = "已发货";
      this.startDescrip = this.mainSiteName + "已发出";
      this.nowStep = "运输";
      this.nowDescrip = this.subSiteName + "已到货";
      this.endStep = "配送";
      this.endDescrip = "等待配送...";
    } else { //已签收
      this.deliverStauts = 2;
      this.processStatus = "success";
      this.startStep = "已发货";
      this.startDescrip = this.mainSiteName + "已发出";
      this.nowStep = "运输";
      this.nowDescrip = this.subSiteName + "已到货";
      this.endStep = "配送";
      this.endDescrip = "已签收";
    }

    this.startPosition = this.first.steps[0].start_location;
    this.endPosition = this.second.steps[
      this.second.steps.length - 1
    ].end_location;
    this.nowPosition.push(
      this.first.steps[this.first.steps.length - 1].end_location
    );

    this.nowPosition = [];
    }
  },
  mounted() {
      this.taskId = this.$route.params.taskId;
    //先 getDeliverPoints() get到数据 赋到 this.first 和 this.second 中
    getDeliverPoints(this.taskId).then(res => {
        this.first = res.data.first;
        this.second = res.data.second;
        this.status = res.data.status;
        this.mainSiteName = res.data.mainSiteName;
        this.subSiteName = res.data.subSiteName;
        this.updateStatus();
    })
  }
};
</script>

<style scoped>
.map {
  width: 100%;
  height: 610px;
}
.display-box {
  margin: 1.5% 1.5%;
  width: 97.5%;
}
</style>