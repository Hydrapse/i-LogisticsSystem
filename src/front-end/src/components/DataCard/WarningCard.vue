<template>
    <div class="card border-left-warning shadow h-100 py-2">
        <div class="card-body">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2 text-left">
                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">{{title}}</div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800">{{content}}</div>
                </div>
                <div class="col-auto">
                    <span class="text-gray-300">
                        <font-awesome-icon icon="comments" size="2x"></font-awesome-icon>
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "WarningCard",
        props: {
            title: {
                type: String,
                default: 'Pending Requests'
            },
            content: {
                type: String,
                default: '18'
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/css/sb-admin-2.css";

</style>