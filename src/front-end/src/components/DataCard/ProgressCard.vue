<template>
    <div class="card border-left-info shadow h-100 py-2">
        <div class="card-body">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2 text-left">
                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">{{title}}</div>
                    <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">{{percent}}</div>
                        </div>
                        <div class="col">
                            <div class="progress progress-sm mr-2">
                                <div class="progress-bar bg-info" role="progressbar" :style="{width: percent}" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-auto">
                    <span class="text-gray-300">
                        <font-awesome-icon icon="clipboard-list" size="2x"></font-awesome-icon>
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ProgressCard",
        props: {
            title: {
                type: String,
                default: 'Tasks'
            },
            percent: {
                type: String,
                default: '50%'
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/css/sb-admin-2.css";

</style>