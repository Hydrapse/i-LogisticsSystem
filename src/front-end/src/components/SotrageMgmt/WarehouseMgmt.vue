<template>
  <div>
    <el-row>
      <el-col :offset="1" style="padding-top: 20px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item to="/main/siteMap">主站管理</el-breadcrumb-item>
          <el-breadcrumb-item>主站{{mainsiteId}}</el-breadcrumb-item>
          <el-breadcrumb-item>库内管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-dialog
      :title="'将'+targetItemId+'从库房'+srcWarehouse+'转移至库房'+targetWarehouse"
      :visible.sync="dialogVisible"
    >
      <el-row>
        <el-col :span="12">
          <div class="text-label">移动数量：</div>
          <el-input-number v-model="transferNum" :min="0" :max="maxNum" class="text-value"></el-input-number>
        </el-col>
      </el-row>
      <br />
      <el-row>
        <el-button type="primary" @click="comfirmTransfer">确认</el-button>
        <el-button @click="cancelTransfer">取消</el-button>
      </el-row>
    </el-dialog>
    <el-row>
      <el-col :span="15" class="display-box">
        <!-- <BasicCard header="库房商品" class="display-box"> -->
        <el-row>
          <el-form :inline="true">
            <el-form-item label="大类ID">
              <el-select v-model="selectedCategory" multiple placeholder="请选择" @change="fetchItems">
                <el-option
                  v-for="category in categoryOptions"
                  :key="category.categoryId"
                  :label="category.categoryName"
                  :value="category.categoryId"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="关键词">
              <el-input v-model="keyword" @blur="fetchItems"></el-input>
            </el-form-item>
          </el-form>
        </el-row>
        <el-row>
          <el-col :span="7" :offset="1" v-for="item in curInventory" :key="item.item.itemId">
            <ItemCard
              draggable="true"
              :item="item.item"
              :quantity="item.inventory"
              :warehouseId="item.warehouseId"
              class="item-box"
              @dragstart.native="drag($event, item)"
            ></ItemCard>
          </el-col>
        </el-row>
        <el-row>
          <el-pagination
            :current-page.sync="curPage"
            :page-count="totalPages"
            layout="prev, pager, next"
            @current-change="onPageChange"
          ></el-pagination>
        </el-row>
        <!-- </BasicCard> -->
      </el-col>
      <el-col :span="6" style="margin: 50px 10px 100px;">
        <el-checkbox-group v-model="selectedWarehouse" @change="fetchItems">
          <el-row
            v-for="warehouse in warehouseList"
            :key="warehouse.warehouseId"
            style="padding-bottom: 30px"
          >
            <el-checkbox
              :label="warehouse.warehouseId"
              border
              class="warehouse-container"
              @drop.native="drop($event, warehouse.warehouseId)"
              @dragover.native="allowDrop($event)"
            >库房{{warehouse.warehouseId}}</el-checkbox>
          </el-row>
        </el-checkbox-group>
        <!-- <BasicCard
          v-for="warehouse in warehouseList"
          :key="warehouse.warehouseId"
          :header="'库房'+warehouse.warehouseId"
          @click.native="changeWarehouse(warehouse.warehouseId)"
          @drop.native="drop($event, warehouse.warehouseId)"
          @dragover.native="allowDrop($event)"
        >
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">类别：</div>
              <div class="text-value">{{warehouse.category.name}}</div>
            </el-col>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">描述：</div>
              <div class="text-value">{{warehouse.category.descn}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">库存种类：</div>
              <div class="text-value">{{warehouse.kindNumOfItem + "种"}}</div>
            </el-col>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">售价：</div>
              <div class="text-value">{{warehouse.totalSize}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" style="text-align: left">
              <div class="text-label">最大库存：</div>
              <div class="text-value">{{warehouse.maxSize}}</div>
            </el-col>
          </el-row>
        </BasicCard>-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import BasicCard from "../PanelCard/BasicCard";
import ItemCard from "../DataCard/ItemCard";
// import myaxios from "../../plugins/mockaxios";
import {
  getCatergies,
  getWarehousesList,
  getItems,
  transferItem
} from "../../api/storage";

export default {
  components: { ItemCard },
  methods: {
    fetchWarehouses() {
      console.log(this.mainsiteId);
      getWarehousesList(this.mainsiteId).then(res => {
        console.log(res);
        this.warehouseList = res.data;
        this.fetchItems();
      });
    },
    getCatorgyOptions() {
      getCatergies().then(res => {
        this.categoryOptions = res.data;
      });
    },
    fetchItems() {
      getItems(this.mainsiteId, {
        warehouseIdList: this.selectedWarehouse,
        categoryIdList: this.selectedCategory,
        keyword: this.keyword,
        pageNum: this.curPage,
        pageSize: 3
      }).then(res => {
        this.curInventory = res.data.content;
        this.totalPages = res.data.totalPages;
      });
    },
    allowDrop(ev) {
      ev.preventDefault();
    },
    drag(ev, item) {
      console.log(item);
      ev.dataTransfer.setData("itemId", item.item.itemId);
      this.maxNum = item.inventory;
      console.log(this.maxNum);
      this.srcWarehouse = item.warehouseId;
    },
    drop(ev, targetWarehouse) {
      ev.preventDefault();
      if (this.srcWarehouse == targetWarehouse) return;
      this.targetItemId = ev.dataTransfer.getData("itemId");
      this.targetWarehouse = targetWarehouse;
      this.dialogVisible = true;
    },
    // changeWarehouse(warehouseId) {
    //   console.log(warehouseId);
    //   this.srcWarehouse = warehouseId;
    //   this.fetchWarehousDetail();
    // },
    // TODO: 把选择库房的触发事件改为直接拉取items
    comfirmTransfer() {
      if (this.transferNum == 0) return;
      console.log(
        "Transfering " +
          this.transferNum +
          " " +
          this.targetItemId +
          " from " +
          this.srcWarehouse +
          " to " +
          this.targetWarehouse
      );
      // 此处需要进行转移数据的传输
      transferItem(this.mainsiteId, this.targetItemId, {
        sourceWarehouseId: this.srcWarehouse,
        destWarehouseId: this.targetWarehouse,
        itemNum: this.transferNum
      })
        .then(res => {
          console.log(res.status);
          this.dialogVisible = false;
          this.transferNum = 0;
          this.fetchItems();
        })
        .catch(() => {
          alert("目标库房无法存储该商品");
          this.dialogVisible = false;
          this.transferNum = 0;
        });
    },
    cancelTransfer() {
      this.dialogVisible = false;
      this.transferNum = 0;
    },
    onPageChange() {
      this.fetchItems();
    }
  },
  data: () => {
    return {
      curInventory: [
        {
          warehouseId: "94",
          item: {
            itemId: "",
            categoryId: "",
            name: "",
            descn: "",
            unitCost: 0,
            listPrice: 0,
            imgUrl: "",
            status: ""
          },
          inventory: 0
        },
        {
          warehouseId: "",
          item: {
            itemId: "",
            categoryId: "",
            name: "",
            descn: "",
            unitCost: 0,
            listPrice: 0,
            imgUrl: "",
            status: ""
          },
          inventory: 0
        },
        {
          warehouseId: "",
          item: {
            itemId: "",
            categoryId: "",
            name: "",
            descn: "",
            unitCost: 0,
            listPrice: 0,
            imgUrl: "",
            status: ""
          },
          inventory: 0
        }
      ],
      warehouseList: [],
      mainsiteId: "",
      srcWarehouse: "",
      targetWarehouse: "",
      targetItemId: "",
      categoryId: "",
      transferNum: 0,
      selectedWarehouse: [],
      selectedCategory: [],
      dialogVisible: false,
      keyword: "",
      maxNum: 0,
      pageSize: 10,
      totalPages: 5,
      curPage: 1
    };
  },
  mounted() {
    this.mainsiteId = this.$route.params.mainsiteId;
    this.fetchWarehouses();
    this.getCatorgyOptions();
  }
};
</script>

<style scoped>
.item-box {
  float: left;
  margin: 35px 2%;
}
.display-box {
  margin: 50px 50px 100px;
}
.text-label {
  float: left;
  width: 100px;
  min-height: 30px;
  text-align: right;
}
.text-value {
  float: left;
  min-height: 30px;
  max-width: 80%;
  text-align: left;
}
.warehouse-container {
  zoom: 1.7;
  background-color: #ffffff;
}
</style>