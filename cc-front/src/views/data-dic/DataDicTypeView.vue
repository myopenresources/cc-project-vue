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
          <a-col :span="5">编号：</a-col>
          <a-col :span="19">{{ data.dicTypeCode || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">名称：</a-col>
          <a-col :span="19">{{ data.dicTypeName || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">字典组：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(dicCategoryOptionList, 'dicItemValue', data.dicCategory) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">图标：</a-col>
          <a-col :span="19">
            <span :class="['iconfont', data?.iconCls]"></span>
            {{ data?.iconCls || '无' }}
          </a-col>
        </a-row>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, shallowRef } from 'vue';
import CommonUtil from '/@/common/util/common-util';
import HttpResultUtils from '/@/common/util/http-result-utils';
import DataDicTypeType from '/@/types/data-dic-type-type';
import DataDicApi from '/@/api/data-dic-api';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'DataDicTypeView',
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
    const dicCategoryOptionList = shallowRef<any[]>([]);
    const data = shallowRef<DataDicTypeType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      DataDicApi.queryDataDicTypeById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dicCategoryOptionList.value = res.data.resultData.DIC_CATEGORY_TYPE;
          data.value = res.data.resultData.dataDicTypeData;
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
      dicCategoryOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataDicTypeView.less';
</style>
