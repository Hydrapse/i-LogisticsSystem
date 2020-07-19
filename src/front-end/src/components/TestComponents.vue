<template>

  <div class="display-box">
    <el-row :gutter="20">
      <el-col :sm="12" :md="6"><primary-card title="I LOVE U" content="123231"></primary-card></el-col>
      <el-col :sm="12" :md="6"><success-card></success-card></el-col>
      <el-col :sm="12" :md="6"><progress-card title="Progress" percent="70%"></progress-card></el-col>
      <el-col :sm="12" :md="6"><warning-card></warning-card></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="12" :md="6"><primary-card title="I LOVE U" content="123231"></primary-card></el-col>
      <el-col :sm="12" :md="6"><success-card></success-card></el-col>
      <el-col :sm="12" :md="6"><progress-card></progress-card></el-col>
      <el-col :sm="12" :md="6"><warning-card></warning-card></el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
    </el-row>
    <el-row>
      <el-col :sm="12" :md="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :sm="12" :md="6"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :sm="12" :md="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :sm="12" :md="6"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>

    <!--面板卡片-->
    <el-row :gutter="20">

      <!--混合布局-->
      <el-col :span="8">
        <!--纯文本卡片只能设置header和body文字-->
        <el-row>
          <plain-text-card body="这个是纯文本卡片body"/>
        </el-row>

        <el-row>
          <el-col :span="20">
            <!--下拉列表框 里面包含下拉项列表-->
            <message-window :item-list="itemList" :url="msgCenterUrl"></message-window>
          </el-col>
        </el-row>

      </el-col>

      <!--基本卡片可以设置标题, 以及在内部填充任意元素, 此处填充element-ui表格-->
      <el-col :span="16"><basic-card header="这个是基础卡片">

          <el-button @click="resetDateFilter">清除日期过滤器</el-button>
          <el-button @click="clearFilter">清除所有过滤器</el-button>
          <el-table
                  ref="filterTable"
                  :data="tableData"
                  style="width: 100%">
            <el-table-column
                    prop="date"
                    label="日期"
                    sortable
                    width="180"
                    column-key="date"
                    :filters="[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]"
                    :filter-method="filterHandler"
            >
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址"
                    :formatter="formatter">
            </el-table-column>
            <el-table-column
                    prop="tag"
                    label="标签"
                    width="100"
                    :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
              <template slot-scope="scope">
                <el-tag
                        :type="scope.row.tag === '家' ? 'primary' : 'success'"
                        disable-transitions>{{scope.row.tag}}</el-tag>
              </template>
            </el-table-column>
          </el-table>

        </basic-card></el-col>
    </el-row>

    <el-row>

      <el-col :span="24">
        <!--下拉项-->
        <dropdown-item
                v-for="item in itemList"
                v-bind:item="item"
                v-bind:key="item.text">
        </dropdown-item>
      </el-col>

    </el-row>

  </div>

</template>

<script>

//DataCard
import PrimaryCard from "./DataCard/PrimaryCard";
import ProgressCard from '@/components/DataCard/ProgressCard'
import WarningCard from "./DataCard/WarningCard";
import SuccessCard from "./DataCard/SuccessCard";
import MessageWindow from "./MessageWindow";

//PanelCard
import PlainTextCard from "./PanelCard/PlainTextCard";
import BasicCard from "./PanelCard/BasicCard";

//DropdownItem
import DropdownItem from "./DropdownItem"

export default {
  name: 'TestComponents',
  props: {
    msg: String
  },
  components : {
    PrimaryCard,
    SuccessCard,
    ProgressCard,
    WarningCard,
    PlainTextCard,
    BasicCard,
    MessageWindow,
    DropdownItem
  },
  data() {
    return {
      msgCenterUrl: '#1',
      itemList: [
        {
          type: 'danger',
          date: 'December 12, 2019',
          text: 'A new monthly report is ready to download!',
          url: '#2'
        },
        {
          type: 'success',
          date: 'December 12, 2019',
          text: '$290.29 has been deposited into your account!',
          url: '#3'
        },
        {
          type: 'info',
          date: 'December 12, 2019',
          text: 'Spending Alert: We\'ve noticed unusually high spending for your account.',
          url: '#4'
        },
      ],
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄',
        tag: '家'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄',
        tag: '公司'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄',
        tag: '家'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄',
        tag: '公司'
      }]
    }
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date');
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    formatter(row) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    }
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.display-box {
  margin: 50px 50px 100px;
}

/*自定义element-ui layout间距*/
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
  margin-bottom: 10px;
}


.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
