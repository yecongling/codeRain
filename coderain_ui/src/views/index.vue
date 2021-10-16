<template>
    <div class="app-container home">
        这是首页
    </div>
</template>

<script>
    export default {
        name: "Index",
        data() {
            return {
                // 版本号
                version: "3.7.0",
            };
        },
        methods: {
            goTarget(href) {
                window.open(href, "_blank");
            },
        },
    }
</script>

<style scoped>

</style>