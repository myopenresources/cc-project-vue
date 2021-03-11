<template>
  <a-empty :image="image">
    <template #description>
      <span>{{ description }}</span>
    </template>
  </a-empty>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { Empty } from 'ant-design-vue';

export default defineComponent({
  name: 'CustomEmpty',
  components: {
    AEmpty: Empty,
  },
  props: {
    image: {
      type: String,
      default: './src/assets/img/empty/default-empty.svg',
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
