<template>
    <div class="card mb-4">
        <div class="card-header text-left font-weight-bold">
            {{header}}
        </div>
        <div class="card-body">
            {{body}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "DefaultCard",
        props: {
            header: {
                type: String,
                default: 'Plain Text Card Header'
            },
            body: {
                type: String,
                default: 'Body'
            }
        }
    }
</script>

<style type="text/css" src="../../assets/css/sb-admin-2.css" scoped>

</style>