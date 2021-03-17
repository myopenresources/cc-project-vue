<template>
  <div class="app-custom-empty">
    <div class="app-custom-empty-image" :style="imgStyle"> </div>
    <p class="app-custom-empty-description">
      <span>{{ description }}</span>
    </p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'CustomEmpty',
  props: {
    imgStyle: {
      type: Object,
      default: () => {},
    },
    description: {
      type: String,
      default: '暂无数据！',
    },
  },
});
</script>

<style lang="less" scoped>
@import './CustomEmpty.less';
</style>
