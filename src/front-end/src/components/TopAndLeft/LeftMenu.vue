<template>


<!--    <div class="bodyl">-->
<!--        <input type="checkbox" id="check">-->
<!--        <label for="check">-->
<!--            <i class="el-icon-menu" id="btn"></i>-->
<!--            <i class="el-icon-menu" id="cancel"></i>-->
<!--        </label>-->
    <el-row>
        <el-col>

            <!--侧边栏控制按钮-->
<!--            <input type="checkbox" id="check">-->
<!--            <label for="check">-->
<!--                <i class="el-icon-menu" id="btn"></i>-->
<!--                <i class="el-icon-menu" id="cancel"></i>-->
<!--            </label>-->

            <div class="menu-heading">
                管理菜单
            </div>

            <el-menu :router="true" :unique-opened="true">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-user-solid"></i>
                        <span> 客户管理</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title" >买家/供应商信息入口</template>
                        <el-menu-item index="/main/customers"><span class="cursorHover"><a>买家管理</a></span></el-menu-item>
                        <el-menu-item index="/main/suppliers"><span class="cursorHover"><a>供应商管理</a></span></el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-submenu index="2" class="center">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>配置策略</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title" >策略配置设置入口</template>
                        <el-menu-item index="/main/settings/ordersettings" ><span class="cursorHover"><a>订单审核策略</a></span></el-menu-item>
                        <el-menu-item index="/main/settings/storagesettings"><span class="cursorHover"><a>出入库策略</a></span></el-menu-item>
                        <el-menu-item index="/main/deliverStrategy"><span class="cursorHover"><a>配送策略</a></span></el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-submenu index="3" class="center">
                    <template slot="title">
                        <i class="el-icon-s-order"></i>
                        <span>订单管理</span>
                    </template>
                    <el-menu-item-group>
<!--                        <template slot="title" ><div class="item-title">123</div></template>-->
                        <template slot="title" >订单审核/查询入口</template>
                        <el-menu-item index="/main/orderForm"><span class="cursorHover"><a>订单查询</a></span></el-menu-item>
                        <el-menu-item index="/main/orderExamine"><span class="cursorHover"><a>订单审核</a></span></el-menu-item>
                        <el-menu-item index="/main/returnForm"><span class="cursorHover"><a>退货换单</a></span></el-menu-item>
                        <el-menu-item index="/main/charts"><span class="cursorHover" ><a>统计报表</a></span></el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-submenu index="4" class="center">
                    <template slot="title">
                        <i class="el-icon-s-home"></i>
                        <span>仓储管理</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title" >仓储审核/调整入口</template>
                        <el-menu-item index="/main/siteMap"><span class="cursorHover"><a>站点地图</a></span></el-menu-item>
                        <el-submenu index="4-3">
                            <span slot="title" class="cursorHover"><a>主站管理</a></span>
                            <el-menu-item index="/main/mainsites/MAIN-001/inventory/warehouses"><span class="cursorHover"><a>库内管理</a></span></el-menu-item>
                            <el-menu-item index="/main/mainsites/MAIN-001/inventory/siteout"><span class="cursorHover">出库审核</span></el-menu-item>
                            <el-menu-item index="/main/mainsites/MAIN-001/inventory/sitein"><span class="cursorHover">入库审核</span></el-menu-item>
                        </el-submenu>
                        <el-menu-item index="/main/catalogInfo"><span class="cursorHover"><a>整体商品信息</a></span></el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-submenu index="5" class="center">
                    <template slot="title">
                        <i class="el-icon-s-promotion"></i>
                        <span>配送管理</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title" >调货配送查询入口</template>
                        <el-menu-item index="/main/taskForm" style="padding-left: 52px"><span class="cursorHover"><a>任务单管理</a></span></el-menu-item>
                        <el-menu-item index="/main/taskFormStatusChart" style="padding-left: 52px"><span class="cursorHover"><a>任务单状态</a></span></el-menu-item>
                    </el-menu-item-group>
<!--                    <el-menu-item index="5-3" style="padding-left: 52px"><span class="cursorHover"><a>配送站管理</a></span></el-menu-item>-->
                </el-submenu>

            </el-menu>
        </el-col>
    </el-row>

<!--        <div id="page">-->
<!--            <router-view ></router-view>-->
<!--        </div>-->
<!--    </div>-->

</template>

<script>
    export default {
        name: "LeftMenu",
        data:function(){
          return{
              isDeepColor:false,
          }
        },
        // watch: {
            // $route: {
                // handler: function(route) {
                //     var routerArr = route.path.split("/");
                //     var details = routerArr[routerArr.length-1];
                //     if(details=="details"){
                //         document.getElementById("page").style.height = "1500px";
                //     }
                //     else{
                //         document.getElementById("page").style.height = "738px";
                //     }
                //
                //     if(route.path == "/main/charts"){
                //         document.getElementById("page").style.background = "#706caa";
                //         document.getElementById("page").style.transition = "1.5s";
                //     }
                //     else{
                //         document.getElementById("page").style.background = "#eff3f9";
                //         document.getElementById("page").style.transition = "1s";
                //     }
                // },

            // }
        // },

    }
</script>

<style type="text/css" src="../../assets/css/left.css" scoped>

</style>