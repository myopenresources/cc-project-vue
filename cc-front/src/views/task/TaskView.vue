<template>
  <a-drawer
    :title="null"
    :placement="'right'"
    :width="'600px'"
    :closable="false"
    :maskClosable="false"
    :keyboard="false"
    :destroyOnClose="true"
    :visible="visible"
    :after-visible-change="afterVisibleChange"
  >
    <app-def-drawer-layout :title="'查看'" @close="handleCancel">
      <div>content</div>
    </app-def-drawer-layout>
  </a-drawer>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'TaskView',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    taskId: {
      type: String,
      default: '',
    },
  },
  emits: ['update:visible'],
  setup(props, context) {
    /**
     * 取消
     */
    const handleCancel = () => {
      context.emit('update:visible', false);
    };

    /**
     * 打开
     */
    const afterVisibleChange = (visible) => {
      if (visible) {
        console.info(props.taskId);
      }
    };

    return {
      handleCancel,
      afterVisibleChange,
    };
  },
});
</script>

<style lang="less" scoped>
@import './TaskView.less';
</style>
