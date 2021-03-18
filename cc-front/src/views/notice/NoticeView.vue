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
      <template #headerBtnsTpl>
        <a-button type="danger" class="app-def-drawer-layout-close">
          <span class="iconfont icon-close"></span>关闭2
        </a-button>
      </template>

      <div>content</div>
    </app-def-drawer-layout>
  </a-drawer>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'NoticeView',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    noticeId: {
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
        console.info(props.noticeId);
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
@import './NoticeView.less';
</style>
