<template>
  <div :class="containerClass">
    <div class="app-def-drawer-layout-header" v-show="showTool">
      <div class="app-def-drawer-layout-title">
        <span class="iconfont icon-file"></span>{{ title }}
      </div>
      <div class="app-def-drawer-layout-btns">
        <slot name="headerBtnsTpl"></slot>
        <app-custom-button
          :type="btnType"
          :size="btnSize"
          v-if="closable"
          @click="close"
          class="app-def-drawer-layout-close"
        >
          <span class="iconfont icon-close"></span>关闭
        </app-custom-button>
      </div>
    </div>
    <div
      :class="{
        'app-def-drawer-layout-content': showTool,
        'app-def-drawer-layout-content-full': !showTool,
      }"
      v-app-scrollbar="scrollOptions"
    >
      <div class="app-def-drawer-layout-content-inner">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'DefDrawerLayout',
  props: {
    containerClass: {
      type: String,
      default: 'app-def-drawer-layout',
    },
    showTool: {
      type: Boolean,
      default: true,
    },
    title: {
      type: String,
      default: '',
    },
    closable: {
      type: Boolean,
      default: true,
    },
    btnSize: {
      type: String,
      default: 'default',
    },
    btnType: {
      type: String,
      default: 'error',
    },
  },
  emits: ['close'],
  setup(props, context) {
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

    const close = () => {
      context.emit('close');
    };

    return {
      scrollOptions,
      close,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DefDrawerLayout.less';
</style>
