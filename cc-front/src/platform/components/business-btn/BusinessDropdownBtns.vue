<template>
  <a-dropdown>
    <a class="ant-dropdown-link" @click.prevent>
      <app-dynamic-icon iconName="SvgIconMore" />更多
    </a>
    <template #overlay>
      <a-menu>
        <a-menu-item
          v-for="btn in btnList"
          :key="btn.buttonId"
          @click="btnHandler(btn.functionName)"
          class="app-more-btn-item"
        >
          <a href="javascript:;">
            <app-dynamic-icon :iconName="btn.iconCls" />
            {{ btn.buttonName }}
          </a>
        </a-menu-item>
      </a-menu>
    </template>
  </a-dropdown>
</template>

<script lang="ts">
import { computed, defineComponent, PropType } from 'vue';
import BusinessBtnType from '/@/platform/types/business-btn-type';
import { layoutMarks } from '/@/platform/commons/consts/business-tool-btn-cont';
import CommonUtil from '/@/platform/commons/utils/common-util';

export default defineComponent({
  name: 'BusinessDropdownBtns',
  props: {
    layoutMark: {
      type: String,
      default: layoutMarks.rowBtn,
    },
    buttonList: {
      type: Array as PropType<BusinessBtnType[]>,
      default: () => {
        return [];
      },
    },
    data: {
      type: Object,
      default: undefined,
    },
    showBtnFunction: {
      type: Function,
      default: undefined,
    },
  },
  emits: ['btnClick'],
  setup(props, context) {
    const btnHandler = (functionName: string | undefined) => {
      context.emit('btnClick', functionName);
    };

    const btnList = computed(() => {
      return CommonUtil.computedBtns(props);
    });

    return {
      btnList,
      btnHandler,
    };
  },
});
</script>

<style lang="less" scoped>
@import './BusinessDropdownBtns.less';
</style>
