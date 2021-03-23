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
      <div class="app-data-view-container">
        <a-row>
          <a-col :span="5">按钮编号：</a-col>
          <a-col :span="19">{{ data.buttonCode }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">按钮名称：</a-col>
          <a-col :span="19">{{ data.functionName }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">序号：</a-col>
          <a-col :span="19">{{ data.sortNum }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">业务标识：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(busniessMarkOptionList, 'dicItemValue', data.busniessMark) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">布局标识：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(layoutMarkOptionList, 'dicItemValue', data.layoutMark) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">图标：</a-col>
          <a-col :span="19">{{ data.iconCls }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">按钮样式：</a-col>
          <a-col :span="19">
            <div :key="index" v-for="(item, index) in btnClsOptionList">
              <app-custom-button
                :type="item.dicItemValue"
                :size="'small'"
                v-if="data.btnCls === item.dicItemValue"
              >
                {{ item.dicItemName }}
              </app-custom-button>
            </div>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">表达式：</a-col>
          <a-col :span="19">{{ data.btnExp || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">状态：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(statusOptionList, 'dicItemValue', data.status) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">描述：</a-col>
          <a-col :span="19">{{ data.buttonDescribe || '无' }}</a-col>
        </a-row>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, shallowRef } from 'vue';
import CommonUtil from '/@/common/util/common-util';
import ButtonApi from '/@/api/button-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import ButtonType from '/@/types/button-type';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'ButtonView',
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
  emits: ['update:visible', 'update:id'],
  setup(props, context) {
    const busniessMarkOptionList = shallowRef<any[]>([]);
    const layoutMarkOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const btnClsOptionList = shallowRef<any[]>([]);
    const data = shallowRef<ButtonType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      ButtonApi.queryButtonById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          busniessMarkOptionList.value = res.data.resultData.BUSNIESS_MARK_TYPE;
          layoutMarkOptionList.value = res.data.resultData.BUTTON_LAYOUT_MARK_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          btnClsOptionList.value = res.data.resultData.BUTTON_CLS_TYPE;
          data.value = res.data.resultData.buttonData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 数据转换管道
     */
    const dataDicPipe = (list, key: string, value: any) => {
      const data = dataDicTransformPipe(list, key, value);
      return data ? data.dicItemName : '无';
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
      data.value = {};
      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    return {
      data,
      btnClsOptionList,
      busniessMarkOptionList,
      statusOptionList,
      layoutMarkOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './ButtonView.less';
</style>
