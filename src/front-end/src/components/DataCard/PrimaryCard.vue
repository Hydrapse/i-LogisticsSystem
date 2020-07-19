<template>
    <div class="card border-left-primary shadow h-100 py-2">
        <div class="card-body">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2 text-left">
                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">{{title}}</div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800">{{content}}</div>
                </div>
                <div class="col-auto">
                    <span class="text-gray-300">
                        <font-awesome-icon icon="calendar" size="2x"></font-awesome-icon>
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PrimaryCard",
        props: {
            title: {
                type: String,
                default: 'Earnings (Monthly)'
            },
            content: {
                type: String,
                default: '$40,000'
            }
        }
    }
</script>

<style type="text/css" src="../../assets/css/sb-admin-2.css" scoped>

</style>