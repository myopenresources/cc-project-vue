<template>
  <div class="app-line-title-container">
    <div class="app-left-line"></div>
    <div class="app-left-title">{{ title }}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'LineTitle',
  props: {
    title: {
      type: String,
      default: '',
    },
  },
});
</script>

<style lang="less" scoped>
@import './LineTitle.less';
</style>
