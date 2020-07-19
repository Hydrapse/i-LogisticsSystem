<template>
  <div>
    <el-row>
      <el-col :offset="1" :span="10">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>客户管理</el-breadcrumb-item>
          <el-breadcrumb-item>供应商管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
      <el-col :span="2" :offset="21">
        <AddSupplier @submitted="fetchData" />
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <BasicCard header="供应商管理">
          <el-table :data="suppliers">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="供应商ID：">{{props.row.supplierId}}</el-form-item>
                  <el-form-item label="供应商名称：">{{props.row.brandName}}</el-form-item>
                  <el-form-item label="经理姓名：">{{props.row.managerName}}</el-form-item>
                  <el-form-item label="供应商电话：">{{props.row.tel}}</el-form-item>
                  <el-form-item
                    label="供应商地址："
                  >{{props.row.province}} {{props.row.city}} {{props.row.district}}</el-form-item>
                  <el-form-item label="邮箱：">{{props.row.email}}</el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column :width="200" label="ID" prop="supplierId"></el-table-column>
            <el-table-column :width="200" label="名称" prop="brandName"></el-table-column>
            <el-table-column :width="220" label="省市">
              <template slot-scope="props">{{props.row.province}} {{props.row.city}}</template>
            </el-table-column>
            <el-table-column label="详细地址" prop="addr"></el-table-column>
            <el-table-column  label="管理操作">
              <template slot-scope="scope">
                <el-button type="danger" @click="clickToDelSupplier(scope.row)">删除</el-button>
                <el-button @click="clickToModifySupplier(scope.row)" type="warning">修改</el-button>
                <el-button type="primary" @click="showDetails(scope.row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>

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

    <el-dialog title="修改供应商" :visible.sync="dialogFormVisibleModify" width="35%" top="3%">
      <el-form label-width="100px">
        <el-form-item label="经理姓名">
          <el-input v-model="modifiedInfo.managerName" style="width:90%"></el-input>
        </el-form-item>
        <el-form-item label="供应商电话">
          <el-input v-model="modifiedInfo.tel" style="width:90%"></el-input>
        </el-form-item>
        <el-form-item label="供应商邮箱">
          <el-input v-model="modifiedInfo.email" style="width:90%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleModify = false">取 消</el-button>
        <el-button type="primary" @click="modifySupplierInfo()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import BasicCard from "../PanelCard/BasicCard";
import AddSupplier from "./AddSupplier";
import {
  getSuppliers,
  modifySupplier,
  deleteSupplier
} from "../../api/clientele";
import router from "../../plugins/router";

export default {
  components: {
    BasicCard,
    AddSupplier
  },
  methods: {
    fetchData() {
      getSuppliers({
        pageNum: this.pageNum,
        pageSize: 10
      }).then(res => {
        this.suppliers = res.data.content;
        this.totalPages = res.data.totalPages;
      });
    },
    onPageChange() {
      this.fetchData();
    },
    modifySupplierInfo() {
      //这个是点击确定按钮
      modifySupplier(this.modifiedInfo.supplierId, this.modifiedInfo)
        .then(() => {
          this.$message({
            message: "修改成功",
            type: "success"
          });
          this.fetchData()
          this.dialogFormVisibleModify = false;
        })
        .catch(() => {
          this.$message({
            message: "修改失败",
            type: "error"
          });
        });

      //用patch方法发送修改信息，发送的数为 this.modifiedInfo
    },
    clickToModifySupplier(supplier) {
      //这个是打开隐藏的对话框
      this.dialogFormVisibleModify = true;
      this.modifiedInfo.supplierId = supplier.supplierId;
      this.modifiedInfo.managerName = supplier.managerName;
      this.modifiedInfo.tel = supplier.tel;
      this.modifiedInfo.email = supplier.email;
    },
    clickToDelSupplier: function(supplied) {
      this.$confirm("此操作将删除该供应商, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log(supplied);
          //supplied为供应商对象 拿着suppliedId去请求删除   //删除成功则提示删除成功
          deleteSupplier(supplied.supplierId).then(() => {
            this.suppliers.splice(this.suppliers.indexOf(supplied), 1);
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          }).catch(() => {
            this.$message({
              type: "error",
              message: "删除失败!"
            })
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    showDetails(supplier) {
      router.push("suppliers/" + supplier.supplierId + "/supplyGoods");
    }
  },
  data: () => {
    return {
      pageNum: 5,
      pageSize: 59,
      totalSize: 13,
      totalPages: 18,

      dialogFormVisibleModify: false,

      modifiedInfo: {
        supplierId: "",
        managerName: "",
        tel: "",
        email: ""
      },

      suppliers: []
    };
  },
  mounted() {
    this.fetchData();
  }
};
</script>

<style scoped>
.el-row {
  padding: 20px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>