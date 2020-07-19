<template>
<div>
  <el-row style="padding-top: 20px">
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配送管理</el-breadcrumb-item>
          <el-breadcrumb-item>任务单状态</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  <el-row style="padding-top: 3%">
    <el-col :span="12" :offset="6">
      <div class="small">
        <canvas id="myChart2" width="400px" height="400px"></canvas>
      </div>
    </el-col>
    <el-col :offset="7">
      <el-button @click="clickToBack" type="primary" plain round>返回任务单列表</el-button>
    </el-col>
  </el-row>
</div>
</template>
<script>
import Chart from "chart.js";
import { getTaskFormsStatus } from "../../api/taskform";

export default {
  components: {},
  data() {
    return {
      taskStatusData: [
        {
          status: "Y",
          number: "40"
        },
        {
          status: "U",
          number: "20"
        },
        {
          status: "O",
          number: "60"
        },
        {
          status: "N",
          number: "10"
        },
        {
          status: "W",
          number: "5"
        }
      ],
      dataUsed: []
    };
  },
  mounted() {
    this.fetchData();
    //然后把数据如下处理 （你有更好的方法也行

    // for (var i = 0; i < 5; i++) {
    //   if (this.taskFromData[i].status == "已签收") {
    //     this.dataUsed[i] = parseInt(this.taskFromData[i].number);
    //   } else if (this.taskFromData[i].status == "待发货") {
    //     this.dataUsed[i] = parseInt(this.taskFromData[i].number);
    //   } else if (this.taskFromData[i].status == "运输中") {
    //     this.dataUsed[i] = parseInt(this.taskFromData[i].number);
    //   } else if (this.taskFromData[i].status == "N") {
    //     this.dataUsed[i] = parseInt(this.taskFromData[i].number);
    //   } else {
    //     this.dataUsed[i] = parseInt(this.taskFromData[i].number);
    //   }
    // }
    // 然后把 this.dataUsed 赋给 dataset的data
  },
  methods: {
    fetchData() {
      getTaskFormsStatus().then(res => {
        this.taskStatusData = res.data;
        const orderedList = ["已签收", "待发货", "运输中", "配送中", "缺货"];
        // 将得到的结果进行排序
        this.taskStatusData.sort((prev, next) => {
          const p = orderedList.indexOf(prev.status);
          const n = orderedList.indexOf(next.status);
          return p - n;
        });
        console.log(this.taskStatusData)
        var data = this.taskStatusData.map(item => {
          return item.number;
        });
        this.paintChart(data);
      });
    },
    paintChart(data) {
      var ctx2 = document.getElementById("myChart2");

      new Chart(ctx2, {
        type: "doughnut",
        data: {
          labels: ["已签收", "未发出", "运输中", "未配送", "缺货待调货"],
          datasets: [
            {
              backgroundColor: [
                "#41B883",
                "#E46651",
                "#00D8FF",
                "#DD1B16",
                "#C0C0C0"
              ],
              data
            }
          ]
        },
        responsive: true,
        maintainAspectRatio: false,
        options: {}
      });
    },
    clickToBack: function() {
      this.$router.push("taskForm")
    }
  }
};
</script>
<style>
.small {
  width:520px;
  height: 520px;
}
</style>