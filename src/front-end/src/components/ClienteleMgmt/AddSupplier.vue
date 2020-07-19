<template>
    <div>
    <el-button type="primary" @click="addNewSupplier()" round>新增供应商</el-button>

        <el-dialog title="新增供应商" :visible.sync="dialogFormVisible" width="35%" top="3%">
            <el-form label-width="90px">
                <el-form-item label="供应商ID">
                    <el-input v-model="supplier.supplierId" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="供应商名称">
                    <el-input v-model="supplier.brandName" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="经理姓名">
                    <el-input v-model="supplier.managerName" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="供应商电话">
                    <el-input v-model="supplier.tel" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="供应商邮箱">
                    <el-input v-model="supplier.email" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="供应商地址">
                    <AddrSelector @emitSupplierAddr="handlerSupplierAddr" />
                </el-form-item>

                <el-form-item label="详细地址">
                    <el-input v-model="supplier.addr" style="width:90%"></el-input>
                </el-form-item>
                <el-form-item label="供应商品">
                    <el-col :span="12" :offset="3">
                    <GoodsSelection v-bind:options="theOptions" @emitCaAndItemId="handleCaAndItemId" />
                    </el-col>
                    <el-col :span="8" :offset="1">
                    <el-button type="success" @click="clickAddItem">添加</el-button>
                    </el-col>
                </el-form-item>

                <el-form-item label="已选择商品: " label-width="20">
                    <el-tag
                            :key="tag"
                            v-for="tag in dynamicTags"
                            closable
                            :disable-transitions="false"
                            @close="handleClose(tag)"
                    >{{tag}}</el-tag>
                </el-form-item>
            </el-form>


            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="emitSupplier">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import AddrSelector from "./AddrSelector";
    import GoodsSelection from  "../OrderFormMgmt/goodsSelection"
    import myaxios from "../../plugins/myaxios";
    import { addSupplier } from "../../api/clientele"

    export default {
        name: "AddSupplier",
        components:{
          AddrSelector,
            GoodsSelection
        },
        methods:{
            fetchData(){

            },
            getGoods(){//应该是向后端请求 货物数据
                var result = [];
                myaxios.get("/goods/catalog").then(res => {
                    this.catalogs = res.data;
                    // console.log(this.catalogs.map);
                    this.theOptions = this.catalogs.map(category => {
                        return {
                            value: category.categoryId,
                            label: category.categoryName,
                            children: category.itemList.map(item => {
                                return {
                                    value: item.itemId,
                                    label: item.itemName
                                };
                            })
                        };
                    });

                    result = res.data;
                });
                console.log(result);
            },
            handleCaAndItemId(e){
                this.item.categoryId = e[0];
                this.item.itemId = e[1];
            },
            clickAddItem(){
                this.number_of_Items += 1;
                //inputValue为标签内容
                let inputValue = "";
                //获取全部货物 来匹配价格 和 名字
                var allGoods = this.catalogs;
                for (var i = 0; i < allGoods.length; i++) {
                    for (var j = 0; j < allGoods[i].itemList.length; j++) {
                        if (this.item.itemId == allGoods[i].itemList[j].itemId) {
                            inputValue =
                                inputValue +
                                this.number_of_Items +
                                "、" +
                                allGoods[i].itemList[j].itemName;
                            if (inputValue) {
                                //选择了商品才能往里面加
                                this.dynamicTags.push(inputValue);
                                var newItem = {};
                                newItem = this.item;
                                this.itemSupplyList.push(newItem.itemId);
                            }

                            break;
                        }
                    }
                }
                this.item = {
                    itemId: "",
                    categoryId: "",
                }
            },
            handleClose(tag) {
                var index = tag.split("、")[0];
                index = parseInt(index) - 1;
                this.itemSupplyList.splice(index, 1);
                this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
                this.number_of_Items -=1;
            },
            addNewSupplier(){
                this.dialogFormVisible = true;
            },
            handlerSupplierAddr:function(supplierAddr){
                this.supplier.province = supplierAddr[0];
                this.supplier.city = supplierAddr[1];
                this.supplier.district = supplierAddr[2];
            },
            emitSupplier:function () {
                this.dialogFormVisible = false;
                this.SupplierAddReq.supplier = this.supplier;
                this.SupplierAddReq.itemSupplyList = this.itemSupplyList;
                //post把SupplierAddReq 发出去
                addSupplier(this.SupplierAddReq).then(() => {
                    this.$message({
                        message: "添加成功",
                        type: "success"
                    })
                    this.$emit("submitted")
                })
                console.log(this.SupplierAddReq);
                setTimeout(this.clear,2000);
            },
            clear(){
                for(var k in this.suppliers){
                    this.suppliers[k] = "";
                }

                this.SupplierAddReq = {
                    supplier:null,
                    itemSupplyList:[],
                }
                this.dynamicTags = [];
                this.itemSupplyList= [];
                this.number_of_Items = 0;
            }
        },
        mounted() {
            this.fetchData();
            this.getGoods();
        },
        data:function () {
            return{
                dialogFormVisible: false,
                dynamicTags: [],

                SupplierAddReq : {
                    supplier:null,
                    itemSupplyList:[],
                },

                supplier: {
                    supplierId: "",
                    brandName: "",
                    managerName: "",
                    tel: "",
                    email: "",
                    province: "",
                    city: "",
                    district: "",
                    addr: ""
                },
                itemSupplyList: [],
                item:{
                    itemId: "",
                    categoryId: "",
                    // name: "",
                    // descn: "",
                    // unitCost: 0,
                    // listPrice: 0,
                },
                number_of_Items:0,
                catalogs: [],
                theOptions: []
            }
        },

    }
</script>

<style scoped>

</style>