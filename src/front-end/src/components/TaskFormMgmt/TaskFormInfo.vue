<template>
  <div>
    <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>配送管理</el-breadcrumb-item>
          <el-breadcrumb-item>任务单列表</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col> 
    </el-row>
    <el-row style="padding-top: 3%">
      <el-col :span="22" :offset="1">
        <BasicCard header="任务单列表">
          <el-row>
            <el-col :span="2">
              <router-link to="/main/taskFormStatusChart">
                <el-button type="warning" plain>任务单状态图</el-button>
              </router-link>
            </el-col>
            <el-col :span="4" :offset="16">
              <el-input
                style="width: 100%"
                placeholder="请输入关键字"
                prefix-icon="el-icon-search"
                v-model="keyWords_of_searchTaskForm"
              ></el-input>
            </el-col>
            <el-col :span="2">
              <el-button @click="clickToSearchTaskForm" type="primary" round>搜索</el-button>
            </el-col>
          </el-row>
          <br />
          <el-row>
            <el-table :data="content">
              <el-table-column label="任务单ID" prop="taskFormId"></el-table-column>
              <el-table-column label="配送站ID" prop="subSiteId"></el-table-column>
              <el-table-column label="状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.status=='O'">运输中</el-tag>
                  <el-tag v-else-if="scope.row.status=='Y'" type="success">已签收</el-tag>
                  <el-tag v-else-if="scope.row.status=='W'" type="info">缺货待调货</el-tag>
                  <el-tag v-else-if="scope.row.status=='U'" type="warning">未发出</el-tag>
                  <el-tag v-else-if="scope.row.status=='N'" type="danger">未配送</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="primary" @click="clickToTaskDetails(scope.row.taskFormId)">查看详情</el-button>
                  <br />
                </template>
              </el-table-column>
            </el-table>
          </el-row>
          <br />
          <el-row>
            <el-pagination
              :current-page.sync="pageNum"
              :page-count="totalPages"
              layout="prev, pager, next"
              @current-change="onPageChange"
            ></el-pagination>
          </el-row>
        </BasicCard>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import { getTaskFormList } from "../../api/taskform";

export default {
  name: "TaskFormInfo",
  components: { BasicCard },
  data: function() {
    return {
      keyWords_of_searchTaskForm: "",

      pageNum: 1, 
      content: [],
      totalPages: 0,
      totalSize: 0,
      pageSize: 10 //我不是很清楚这是俩干啥的
    };
  },
  methods: {
    clickToTaskDetails: function(taskFormId) {
      this.$router.push("/main/taskforms/" + taskFormId + "/details");
    },
    clickToSearchTaskForm: function() {
      //把 this.keyWords_of_searchTaskForm传过去 作为搜索关键词
      console.log(this.keyWords_of_searchTaskForm);
      this.fetchData();
    },
    onPageChange() {
      this.fetchData();
    },
    fetchData: function() {
      //使用导入的 函数连接后端
      getTaskFormList({
        q: this.keyWords_of_searchTaskForm,
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }).then(res => {
        this.totalPages = res.data.totalPages;
        this.content = res.data.content;
      });
    }
  },
  mounted() {
    this.fetchData();
  }
};
</script>

<style scoped>
</style>