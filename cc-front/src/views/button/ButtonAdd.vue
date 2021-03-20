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
    <app-def-drawer-layout :title="'添加/编辑'" @close="handleCancel">
      <template #headerBtnsTpl>
        <a-button type="primary" @click="save">
          <span class="iconfont icon-save"></span>保存
        </a-button>
      </template>

      <div>content</div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import CommonUtil from '/@/common/util/common-util';

export default defineComponent({
  name: 'ButtonAdd',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    id: {
      type: String,
      default: '',
    },
  },
  emits: ['update:visible', 'reload'],
  setup(props, context) {
    /**
     * 初始化页面数据
     */
    const initPageData = () => {};

    /**
     * 保存
     */
    const save = () => {
      CommonUtil.drawerClose(context, true);
    };

    /**
     * 取消
     */
    const handleCancel = () => {
      CommonUtil.drawerClose(context);
    };

    /**
     * 打开
     */
    const afterVisibleChange = (visible) => {
      CommonUtil.drawerAddOrViewInit(visible, props, initPageData);
    };

    return {
      handleCancel,
      afterVisibleChange,
      save,
    };
  },
});
</script>

<style lang="less" scoped>
@import './ButtonAdd.less';
</style>
