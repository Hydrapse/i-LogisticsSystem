<template>
  <div>
    <el-row style="padding-top: 20px; padding-bottom: 20px">
      <el-col :offset="1">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>仓储管理</el-breadcrumb-item>
          <el-breadcrumb-item>商品整体信息</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="dialogVisible">
      <el-form :inline="true">
        <el-form-item label="商品名称">
          <el-input v-model="itemForm.name"></el-input>
        </el-form-item>
        <br />
        <el-form-item label="商品大类">
          <el-select v-model="itemForm.categoryId">
            <el-option
              v-for="category in categoryOptions"
              :key="category.categoryId"
              :label="category.categoryName"
              :value="category.categoryId"
            ></el-option>
          </el-select>
        </el-form-item>
        <br />
        <el-form-item label="单价">
          <el-input-number v-model="itemForm.unitCost"></el-input-number>
        </el-form-item>
        <br />
        <el-form-item label="售价">
          <el-input-number v-model="itemForm.listPrice"></el-input-number>
        </el-form-item>
        <br />
        <el-form-item label="商品图片">
          <el-upload
            class="avatar-uploader"
            action="null"
            :file-list="fileList"
            list-type="picture"
            :auto-upload="false"
            :http-request="submitItem"
            ref="upload"
            :limit="1"
            :on-exceed="exceeding"
            :before-upload="beforeImgUpload"
          >
            <el-button type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="itemForm.descn"></el-input>
        </el-form-item>
        <br />
        <el-button @click="resetItemForm">重置</el-button>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form>
    </el-dialog>
    <div class="display-box">
      <br>
      <el-row>
        <el-col :span="18" :offset="2">
        <el-form :inline="true">
          <el-form-item label="大类ID">
            <el-select v-model="selectedCategory" multiple placeholder="请选择" @change="getItems">
              <el-option
                v-for="category in categoryOptions"
                :key="category.categoryId"
                :label="category.categoryName"
                :value="category.categoryId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="关键词">
            <el-input v-model="keyword" @blur="getItems"></el-input>
          </el-form-item>
        </el-form>
        </el-col>
        <el-col :span="1">
          <el-button type="primary" @click="dialogVisible=true">新增商品</el-button>
        </el-col>
      </el-row>
      <el-row >
        <el-col v-for="item in items" :key="item.itemId" :span="6">
          <ItemCard :item="item" class="item-box" :editable="true"></ItemCard>
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
    </div>
  </div>
</template>

<script>
import ItemCard from "../DataCard/ItemCard";
import { getCatergies, getItemList, addItem } from "../../api/storage";

export default {
  components: { ItemCard },
  methods: {
    getItems() {
      getItemList({
        pageNum: this.curPage,
        pageSize: this.pageSize,
        categoryIdList: this.selectedCategory,
        keyword: this.keyword
      }).then(res => {
        this.items = res.data.content;
        this.totalPages = res.data.totalPages;
      });
    },
    getCatorgyOptions() {
      getCatergies().then(res => {
        this.categoryOptions = res.data;
      });
    },
    onPageChange() {
      this.getItems();
    },
    resetItemForm() {
      this.itemForm.name = "";
      this.itemForm.categoryId = "";
      this.itemForm.descn = "";
      this.itemForm.unitCost = 0;
      this.itemForm.listPrice = 0;
      this.$refs.upload.clearFiles()
    },
    submitItem(file) {
      console.log(file);
      addItem(this.itemForm, file.file).then(() => {
        this.$message({
          message: "添加成功",
          type: "success"
        })
      }).catch(()=> {
        this.$message({
          message: "添加失败",
          type: "error"
        })
      })
    },
    onSubmit() {
      this.$refs.upload.submit();
    },
    exceeding() {
      this.$message({
        message: "仅允许上传一张图片，请删除已上传图片并重试",
        type: "warning"
      })
    },
    beforeImgUpload(file) {
      const isJPG = file.type === "image/jpeg" || file.type === "image/png";
      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG 或 PNG 格式!");
      }
      return isJPG;
    },
  },
  data: function() {
    return {
      curPage: 1,
      pageSize: 4,
      totalPages: 0,
      selectedCategory: [],
      keyword: "",
      categoryOptions: [
        {
          categoryId: 4,
          categoryName: "世片切效适或界",
          description: "dolore"
        },
        {
          categoryId: 78,
          categoryName: "气律年我",
          description: "reprehenderit enim dolore"
        }
      ],
      items: [],
      fileList: [],
      dialogVisible: false,
      itemForm: {
        name: "",
        categoryId: "",
        descn: "",
        unitCost: 0,
        listPrice: 0
      }
    };
  },
  mounted() {
    this.getItems();
    this.getCatorgyOptions();
  }
};
</script>

<style scoped>
.item-box {
  float: left;
  margin: 35px;
}
.display-box {
  margin: 0px 50px 50px;
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
</style>