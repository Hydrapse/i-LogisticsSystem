<template>
  <div>
    <el-dialog v-if="editable" :visible.sync="dialogVisible">
      <el-form :inline="true">
        <el-form-item label="商品名称">
          <el-input v-model="itemForm.name"></el-input>
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
            :on-change="addingImg"
            :on-remove="addingImg"
            :before-upload="beforeImgUpload"
          >
            <el-button type="primary">点击上传</el-button>
            <br />
            <img v-show="fileList.length==0" :src="item.imgUrl" />
          </el-upload>
        </el-form-item>
        <br />
        <el-form-item label="描述">
          <el-input type="textarea" v-model="itemForm.descn"></el-input>
        </el-form-item>
        <br />
        <el-button @click="resetItemForm">重置</el-button>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </el-form>
    </el-dialog>
    <div class="card-box shadow mb-4">
      <el-row>
        <div class="image-container">
          <img draggable="false" :src="item.imgUrl" />
        </div>
      </el-row>
      <div class="content-box">
        <el-row>
          <el-col>
            <h5>
              <div class="text-label" style="width: 50px">编号:</div>
              <div class="text-value">{{item.itemId}}</div>
            </h5>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <h5>
              <div class="text-label" style="width: 50px">名称:</div>
              <div class="text-value">{{item.name}}</div>
            </h5>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="text-label">大类:</div>
            <div class="text-value">{{item.categoryId}}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="text-label" v-if="warehouseId">库房:</div>
            <div class="text-value">{{warehouseId}}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24" v-if="quantity">
            <div class="text-label">库存:</div>
            <div class="text-value">{{quantity}}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-button type="success" style="zoom: 0.8" @click="toDetails">详细信息</el-button>
          <el-button v-if="editable" type="primary" style="zoom: 0.8" @click="onShow">编辑信息</el-button>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import router from "../../plugins/router";
import { updateItem, getItemDetails } from "../../api/storage";

export default {
  props: ["item", "quantity", "warehouseId", "editable"],
  methods: {
    toDetails() {
      router.push("/main/goods/items/" + this.item.itemId);
    },
    exceeding() {
      this.$message({
        message: "仅允许上传一张图片，请删除已上传图片并重试",
        type: "warning"
      });
    },
    onShow() {
      this.dialogVisible = true;
    },
    addingImg(file, fileList) {
      this.fileList = fileList;
    },
    resetItemForm() {
      this.itemForm.name = this.item.name;
      this.itemForm.categoryId = this.item.categoryId;
      this.itemForm.descn = this.item.descn;
      this.itemForm.unitCost = this.item.unitCost;
      this.itemForm.listPrice = this.item.listPrice;
      this.$refs.upload.clearFiles();
      this.fileList = [];
    },
    submitItem(file) {
      console.log(file);
      updateItem(this.item.itemId, this.itemForm, file.file)
        .then(() => {
          this.$message({
            message: "修改成功",
            type: "success"
          });
          this.dialogVisible=false;
          this.fetchData();
          this.resetItemForm();
        })
        .catch(() => {
          this.$message({
            message: "修改失败",
            type: "error"
          });
        });
    },
    beforeImgUpload(file) {
      const isJPG = file.type === "image/jpeg" || file.type === "image/png";
      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG 或 PNG 格式!");
      }
      return isJPG;
    },
    onSubmit() {
      if (this.fileList.length > 0) {
        this.$refs.upload.submit();
      } else {
        updateItem(this.item.itemId, this.itemForm, null)
          .then(() => {
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.dialogVisible=false;
            this.fetchData();
            this.resetItemForm();
          })
          .catch(() => {
            this.$message({
              message: "修改失败",
              type: "error"
            });
          });
      }
    },
    fetchData() {
      getItemDetails(this.item.itemId).then(res => {
        this.item = res.data.item;
      });
    }
  },
  data: function() {
    return {
      dialogVisible: false,
      itemForm: {
        name: this.item.name,
        categoryId: this.item.categoryId,
        descn: this.item.descn,
        unitCost: this.item.unitCost,
        listPrice: this.item.listPrice
      },
      fileList: []
    };
  },
  mounted() {}
};
</script>

<style scoped>
.image-contaier {
  margin: 0;
}
img {
  width: 100%;
  height: 250px;
  width: 250px;
  border-radius: 0.35rem 0.35rem 0 0;
}
.card-box {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border-radius: 0.35rem;
  max-width: 250px;
}
.display-box {
  margin: 50px 50px 100px;
}
.text-label {
  float: left;
  width: 40px;
  min-height: 30px;
  text-align: right;
  zoom: 0.8;
}
.text-value {
  float: left;
  min-height: 30px;
  max-width: 80%;
  text-align: left;
  zoom: 0.8;
}
.content-box {
  margin: 20px;
}
</style>
<style type="text/css" src="../../assets/css/sb-admin-2.css" scoped>