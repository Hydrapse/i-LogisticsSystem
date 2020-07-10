<template>
    <a class="dropdown-item d-flex align-items-center" :href="item.url">
        <div class="mr-3">
            <div class="icon-circle text-white" :class='"bg-" + item.type'>
                <font-awesome-icon :icon="icon"></font-awesome-icon>
            </div>
        </div>
        <div class="text-left">
            <div class="small text-gray-500">{{item.date}}</div>
            <span class="font-weight-normal">{{item.text}}</span>
        </div>
    </a>
</template>

<script>
    export default {
        name: "DropdownItem",
        props: {
            item: {
                type: Object,
                default: ()=>({
                    type: 'primary',
                    date: 'December 12, 2019',
                    text: 'A new monthly report is ready to download!',
                    url: '#'
                })
            }
        },
        ready() {
           this.$watch('item', function (newItem) {
               console.log(newItem)
                this.item = newItem //接受父组件传来的item
           })
        },
        computed: {
            icon: function () {
                switch (this.item.type) {
                    case 'primary':
                        return 'file-alt'
                    case 'success':
                        return 'clipboard-check'
                    case 'info':
                        return 'file-invoice-dollar'
                    case 'warning':
                        return 'exclamation-triangle'
                    case 'danger':
                        return 'exclamation-circle'
                    default:
                        return 'file-alt'
                }
            }
        }
    }
</script>

<style scoped>

</style>