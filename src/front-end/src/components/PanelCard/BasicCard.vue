<template>
    <div class="card shadow mb-4">
        <div class="card-header py-3 text-left font-weight-bold">
            <h6 class="m-0 font-weight-bold text-primary">{{header}}</h6>
        </div>
        <div class="card-body">
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BasicCard",
        props: {
            header: {
                type: String,
                default: 'Basic Card Header'
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/css/sb-admin-2.css";

</style>