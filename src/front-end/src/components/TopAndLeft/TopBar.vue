<template>
  <el-row type="flex" align="middle">
    <el-col :span="2" :offset="1">
      <a class="navbar-brand">物流管理系统</a>
    </el-col>
    <el-col :span="3" :offset="2">
      <el-autocomplete
        v-model="modulesKey"
        :fetch-suggestions="queryModules"
        placeholder="Search"
      >
        <i class="el-icon-search el-input__icon" slot="suffix"></i>
      </el-autocomplete>
    </el-col>

    <el-col :span="1" :offset="12">
          <button class="refreshBtn" @click="refresh"><i  class="el-icon-refresh" style="font-size: 20px"></i></button>
    </el-col>
    <el-col :span="1" >
      <el-dropdown>
        <span class="el-dropdown-link">
          <i class="el-icon-message-solid" style="font-size: 20px"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <!-- TODO: 目前还没有决定好这里面会有何条目 -->
          <el-dropdown-item>通知</el-dropdown-item>
          <el-dropdown-item>消息</el-dropdown-item>
          <el-dropdown-item disabled>快件信息</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>

    <el-col :span="1" class="mg-tp">
      <el-dropdown>
        <div class="demo-basic--circle">
          <div class="block">
            <el-avatar
              :size="35"
              src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
            ></el-avatar>
          </div>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item>注销</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name:'TopBar',
  data: function() {
    return {
      modules: [
        { value: "买家管理" },
        { value: "供应商管理" },
        { value: "调货策略" },
        { value: "补货策略" },
        { value: "分配策略" },
        { value: "发货策略" },
        { value: "订单查询" },
        { value: "新增订单" },
        { value: "订单审核" },
      ],
      modulesKey: ""
    };
  },
  methods: {
    queryModules(queryString, cb) {
      var modules = this.modules;
      var results = queryString
        ? modules.filter(module => {
            return (
              module.value.toLowerCase().indexOf(queryString.toLowerCase()) > -1
            );
          })
        : modules;
      cb(results);
    },
    refresh:function () {
      window.location.reload();
    }
  }
};
</script>

<style scoped>
.el-row{
  /*margin: 15px 0 0px;*/
  width: 100%;
}
.mg-tp{
  margin-top: 4px;
}
.navbar-brand {
  display: inline-block;
  padding-top: 0.3125rem;
  padding-bottom: 0.3125rem;
  margin-right: 1rem;
  font-size: 1.25rem;
  line-height: inherit;
  white-space: nowrap;
  font-weight: bold;
  color: #323f52;
}
.el-dropdown-link {
  cursor: pointer;
  font-weight: bold;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.refreshBtn{
  background: transparent;
  border-radius: 20px;
  /*width: 100%;*/
  /*height: 50%;*/
  transition: 0.25s;
  padding: 10px;
}
.refreshBtn:hover{
  transform: scale(1.2);
  background: #eff3f9;
}
</style>