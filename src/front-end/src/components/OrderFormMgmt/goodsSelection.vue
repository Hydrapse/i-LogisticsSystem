<template>
  <div>
    <el-cascader
      style="left: -22%"
      v-model="value1"
      :options="options"
      :props="{ expandTrigger: 'hover' }"
      @change="handleChange"
    ></el-cascader>
  </div>
</template>

<script>
export default {
  name: "goodsSelection",
  props: ["options"],
  data: function() {
    return {
      value1: [],
      
    };
  },
  methods: {
    handleChange(value1) {
      this.$emit("emitGoodsItemId", value1[1]);
      this.$emit("emitCaAndItemId",value1)
    }
  },
//   created() {
//     for (var i = 0; i < this.Goods.length; i++) {
//       this.options[i].value = this.Goods[i].categoryId;
//       this.options[i].label = this.Goods[i].categoryName;
//       for (var j = 0; j < this.Goods[i].items.length; j++) {
//         this.options[i].children[j].value = this.Goods[i].items[j].itemId;
//         this.options[i].children[j].label = this.Goods[i].items[j].itemName;
//       }
//     }
//   },
  
  
};
</script>

<style scoped>
</style>