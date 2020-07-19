<template>
    <div class="msg-box dropdown-list dropdown-menu-right shadow">
        <h6 class="dropdown-header box-header">
            Alerts Center
        </h6>
        <dropdown-item
                v-for="item in itemList"
                v-bind:item="item"
                v-bind:key="item.text">
        </dropdown-item>
        <a class="dropdown-item text-center small text-gray-500" :href="url">Show All Alerts</a>
    </div>
</template>

<script>
    import DropdownItem from "../DropdownItem"

    export default {
        name: "MessageWindow",
        props: ['itemList', 'url'],
        components: {
            DropdownItem
        }
    }
</script>

<style type="text/css" src="../../assets/css/sb-admin-2.css" scoped>
    .msg-box {
        /*position: absolute;*/
        /*top: 100%;*/
        /*left: 0;*/
        /*z-index: 1000;*/
        /*display: none;*/
        /*float: left;*/
        min-width: 10rem;
        /*padding: 0.5rem 0;*/
        margin: 0.125rem 0 0;
        font-size: 0.85rem;
        color: #858796;
        text-align: left;
        list-style: none;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #e3e6f0;
        border-radius: 0.5rem;
    }
    .box-header {
        border-radius: 0.35rem 0.35rem 0 0;
        text-transform: uppercase!important;
        font-weight: bold;
    }
</style>