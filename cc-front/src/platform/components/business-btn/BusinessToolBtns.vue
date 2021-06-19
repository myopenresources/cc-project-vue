<template>
  <div class="app-tool-btns-container">
    <template v-for="btn in btnList" :key="btn.buttonId">
      <app-custom-button :type="btn.btnCls" :size="btnSize" @click="btnHandler(btn.functionName)">
        <app-dynamic-icon :iconName="btn.iconCls" />
        {{ btn.buttonName }}
      </app-custom-button>
    </template>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, PropType } from 'vue';
import BusinessBtnType from '/@/platform/types/business-btn-type';
import { layoutMarks } from '/@/platform/commons/consts/business-tool-btn-cont';
import CommonUtil from '/@/platform/commons/utils/common-util';

export default defineComponent({
  name: 'BusinessToolBtns',
  props: {
    btnSize: {
      type: String,
      default: 'default',
    },
    layoutMark: {
      type: String,
      default: layoutMarks.toolBtn,
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
@import './BusinessToolBtns.less';
</style>
