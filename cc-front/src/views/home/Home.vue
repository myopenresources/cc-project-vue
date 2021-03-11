<template>
  <app-def-main-layout :showTool="true">
    <template #headerBtnsTpl>
      <a-button type="primary">
        <template #icon> <span class="iconfont icon-plus"></span></template>
        添加
      </a-button>
      <a-button type="danger">
        <template #icon> <span class="iconfont icon-delete"></span></template>
        删除
      </a-button>
    </template>
    <div>
      <a-transfer :data-source="[]" />
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { Button, Transfer } from 'ant-design-vue';
import { defineComponent, onMounted } from 'vue';

export default defineComponent({
  name: 'Home',
  components: {
    AButton: Button,
    ATransfer: Transfer,
  },
  setup() {
    onMounted(() => {
      console.info('init=============');
    });
    return {};
  },
});
</script>

<style lang="less" scoped>
@import './Home.less';
</style>
