<template>
    <el-cascader
            style="left: -22%"
            v-model="value"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"
    ></el-cascader>
</template>

<script>
    export default {
        name: "AddrSelector",
        methods:{
            handleChange(value) {
                this.$emit("emitSupplierAddr", value);
            }
        },
        data:function () {
            return{
                value: [],

                options: [
                    {
                        value: "河北",
                        label: "河北",
                        children: [
                            {
                                value: "石家庄",
                                label: "石家庄",
                                children: [
                                    {
                                        value: "正定县",
                                        label: "正定县"
                                    },
                                    {
                                        value: "灵寿县",
                                        label: "灵寿县"
                                    },
                                    {
                                        value: "高邑县",
                                        label: "高邑县"
                                    },
                                    {
                                        value: "无极县",
                                        label: "无极县"
                                    }
                                ]
                            },
                            {
                                value: "唐山",
                                label: "唐山",
                                children: [
                                    {
                                        value: "乐亭县",
                                        label: "乐亭县"
                                    },
                                    {
                                        value: "玉田县",
                                        label: "玉田县"
                                    },
                                    {
                                        value: "迁西县",
                                        label: "迁西县"
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        value: "山西省",
                        label: "山西省",
                        children: [
                            {
                                value: "太原市",
                                label: "太原市",
                                children: [
                                    {
                                        value: "清徐县",
                                        label: "清徐县"
                                    },
                                    {
                                        value: "阳曲县",
                                        label: "阳曲县"
                                    },
                                    {
                                        value: "娄烦县",
                                        label: "娄烦县"
                                    }
                                ]
                            },
                            {
                                value: "大同市",
                                label: "大同市",
                                children: [
                                    {
                                        value: "阳高县",
                                        label: "阳高县"
                                    },
                                    {
                                        value: "天镇县",
                                        label: "天镇县"
                                    },
                                    {
                                        value: "广灵县",
                                        label: "广灵县"
                                    },
                                    {
                                        value: "灵丘县",
                                        label: "灵丘县"
                                    },
                                    {
                                        value: "浑源县",
                                        label: "浑源县"
                                    },
                                    {
                                        value: "左云县",
                                        label: "左云县"
                                    }
                                ]
                            },
                            {
                                value: "吕梁市",
                                label: "吕梁市",
                                children: [
                                    {
                                        value: "文水县",
                                        label: "文水县"
                                    },
                                    {
                                        value: "交城县",
                                        label: "交城县"
                                    },
                                    {
                                        value: "兴县",
                                        label: "兴县"
                                    },
                                    {
                                        value: "临县",
                                        label: "临县"
                                    },
                                    {
                                        value: "柳林县",
                                        label: "柳林县"
                                    },
                                    {
                                        value: "石楼县",
                                        label: "石楼县"
                                    }
                                ]
                            },
                            {
                                value: "阳泉市",
                                label: "阳泉市",
                                children: [
                                    {
                                        value: "平定县",
                                        label: "平定县"
                                    },
                                    {
                                        value: "盂县",
                                        label: "盂县"
                                    }
                                ]
                            },
                            {
                                value: "晋城市",
                                label: "晋城市",
                                children: [
                                    {
                                        value: "沁水县",
                                        label: "沁水县"
                                    },
                                    {
                                        value: "阳城县",
                                        label: "阳城县"
                                    },
                                    {
                                        value: "陵川县",
                                        label: "陵川县"
                                    },
                                    {
                                        value: "泽州县",
                                        label: "泽州县"
                                    }
                                ]
                            },
                            {
                                value: "朔州市",
                                label: "朔州市",
                                children: [
                                    {
                                        value: "山阴县",
                                        label: "山阴县"
                                    },
                                    {
                                        value: "应县",
                                        label: "应县"
                                    },
                                    {
                                        value: "右玉县",
                                        label: "右玉县"
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        value: "北京",
                        label: "北京",
                        children: [
                            {
                                value: "海淀区",
                                label: "海淀区"
                            },
                            {
                                value: "朝阳区",
                                label: "朝阳区"
                            },
                            {
                                value: "石景山区",
                                label: "石景山区"
                            },
                            {
                                value: "西城区",
                                label: "西城区"
                            },
                            {
                                value: "东城区",
                                label: "东城区"
                            },
                            {
                                value: "宣武区",
                                label: "宣武区"
                            },
                            {
                                value: "崇文区",
                                label: "崇文区"
                            },
                            {
                                value: "丰台区",
                                label: "丰台区"
                            }
                        ]
                    },
                    {
                        value: "广东省",
                        label: "广东省",
                        children: [
                            {
                                value: "广州市",
                                label: "广州市",
                                children: [
                                    {
                                        value: "天河区",
                                        label: "天河区"
                                    },
                                    {
                                        value: "黄埔区",
                                        label: "黄埔区"
                                    },
                                    {
                                        value: "越秀区",
                                        label: "越秀区"
                                    },
                                    {
                                        value: "白云区",
                                        label: "白云区"
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>