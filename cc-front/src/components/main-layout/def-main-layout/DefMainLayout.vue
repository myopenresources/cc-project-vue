<template>
  <div :class="containerClass">
    <div class="app-def-main-layout-header" v-show="showTool">
      <div class="app-def-main-layout-header-title">首页</div>
      <div>
        <slot name="headerBtnsTpl"></slot>
      </div>
    </div>
    <div
      :class="{
        'app-def-main-layout-content': showTool,
        'app-def-main-layout-content-full': !showTool,
      }"
    >
      <div class="app-def-main-layout-inner-content" v-scrollbar="scrollOptions">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'DefMainLayout',
  props: {
    containerClass: {
      type: String,
      default: 'app-main-default-container',
    },
    showTool: {
      type: Boolean,
      default: true,
    },
  },
  setup() {
    const scrollOptions = {
      overflowBehavior: {
        y: 'scroll',
        x: 'scroll',
      },
      scrollbars: {
        autoHide: 'leave',
        autoHideDelay: 200,
      },
    };

    return {
      scrollOptions,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DefMainLayout.less';
</style>
