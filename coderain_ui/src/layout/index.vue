<!--主页框架-->
<template>
    <div :class="classObj" class="app-wrapper" :style="{'--current-color': theme}">
        <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside"/>
        <!-- 左边菜单 -->
        <sidebar class="sidebar-container"/>
        <!-- 右边全体主题内容 -->
        <div :class="{hasTagsView:needTagsView}" class="main-container">
            <div :class="{'fixed-header':fixedHeader}">
                <!-- 第一栏的菜单（可能为一级菜单，在某些配置下） -->
                <navbar />
                <!-- 小菜单上的右键菜单（即关闭本路由……） -->
                <tags-view v-if="needTagsView" />
            </div>
            <!-- 中间主题路由（即配置的菜单内容） -->
            <app-main />
            <!-- 右边收缩设置框 -->
            <right-panel>
                <settings />
            </right-panel>
        </div>
    </div>
</template>

<script>
    import RightPanel from '@/components/RightPanel'
    import { AppMain, Navbar, Settings, Sidebar, TagsView } from './components'
    /*import ResizeMixin from './mixin/ResizeHandler'*/
    import { mapState } from 'vuex'
    import variables from '@/assets/styles/variables.scss'

    export default {
        name: 'Layout',
        components: {
            AppMain,
            Navbar,
            RightPanel,
            Settings,
            Sidebar,
            TagsView
        },
        /*mixins: [ResizeMixin],*/
        computed: {
            ...mapState({
                theme: state => state.settings.theme,
                sideTheme: state => state.settings.sideTheme,
                sidebar: state => state.app.sidebar,
                device: state => state.app.device,
                needTagsView: state => state.settings.tagsView,
                fixedHeader: state => state.settings.fixedHeader
            }),
            classObj() {
                return {
                    hideSidebar: !this.sidebar.opened,
                    openSidebar: this.sidebar.opened,
                    withoutAnimation: this.sidebar.withoutAnimation,
                    mobile: this.device === 'mobile'
                }
            },
            variables() {
                return variables;
            }
        },
        methods: {
            handleClickOutside() {
                this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
            }
        }
    }
</script>
+-+
<style lang="scss" scoped>
    @import "~@/assets/styles/mixin.scss";
    @import "~@/assets/styles/variables.scss";

    .app-wrapper {
        @include clearfix;
        position: relative;
        height: 100%;
        width: 100%;

        &.mobile.openSidebar {
            position: fixed;
            top: 0;
        }
    }

    .drawer-bg {
        background: #000;
        opacity: 0.3;
        width: 100%;
        top: 0;
        height: 100%;
        position: absolute;
        z-index: 999;
    }

    .fixed-header {
        position: fixed;
        top: 0;
        right: 0;
        z-index: 9;
        width: calc(100% - #{$base-sidebar-width});
        transition: width 0.28s;
    }

    .hideSidebar .fixed-header {
        width: calc(100% - 54px)
    }

    .mobile .fixed-header {
        width: 100%;
    }
</style>
