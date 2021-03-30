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
          <a-col :span="19">{{ data.dicItemCode || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">名称：</a-col>
          <a-col :span="19">{{ data.dicItemName || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">值：</a-col>
          <a-col :span="19">{{ data.dicItemValue || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">备用值：</a-col>
          <a-col :span="19">{{ data.spareValue || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">默认值：</a-col>
          <a-col :span="19">{{ data.defaultValue || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">序号：</a-col>
          <a-col :span="19">{{ data.sortNum || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">上一级：</a-col>
          <a-col :span="19">{{ data.dicItemParentName || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">图标：</a-col>
          <a-col :span="19">
            <span :class="['iconfont', data?.iconCls]"></span>
            {{ data?.iconCls || '无' }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">描述：</a-col>
          <a-col :span="19">{{ data.dicItemDesc || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">状态：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(statusOptionList, 'dicItemValue', data.status) }}
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
import DataDicItemType from '/@/types/data-dic-item-type';
import DataDicApi from '/@/api/data-dic-api';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'DataDicItemView',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    dicTypeId: {
      type: String,
      default: '',
    },
    id: {
      type: String,
      default: '',
    },
  },
  emits: ['update:visible', 'update:id'],
  setup(props, context) {
    const statusOptionList = shallowRef<any[]>([]);
    const data = shallowRef<DataDicItemType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      DataDicApi.queryDataDicItemById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          data.value = res.data.resultData.dataDicItemData;
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
      statusOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataDicItemView.less';
</style>
