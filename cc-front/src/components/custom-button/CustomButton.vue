<template>
  <a-button
    :class="['ant-btn-' + type, btnClass]"
    :disabled="disabled"
    :htmlType="htmlType"
    :ghost="ghost"
    :size="size"
    :loading="loading"
    :shape="shape"
    :block="block"
  >
    <slot></slot>
  </a-button>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';

type BtnLoadingType = Boolean | { delay: number };

export default defineComponent({
  name: 'CustomButton',
  props: {
    btnClass: {
      type: String,
      default: '',
    },
    size: {
      type: String,
      default: 'default',
    },
    type: {
      type: String,
      default: 'default',
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    ghost: {
      type: Boolean,
      default: false,
    },
    htmlType: {
      type: String,
      default: 'button',
    },
    loading: {
      type: [Boolean,Object] as PropType<BtnLoadingType>,
      default: false,
    },
    shape: {
      type: String,
      default: null,
    },
    block: {
      type: Boolean,
      default: false,
    },
  },
});
</script>

<style lang="less" scoped>
@import './CustomButton.less';
</style>
