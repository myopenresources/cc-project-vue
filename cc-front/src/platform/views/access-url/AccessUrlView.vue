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
          <a-col :span="5">地址名称：</a-col>
          <a-col :span="19">{{ data.accessUrlName }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">地址：</a-col>
          <a-col :span="19">{{ data.accessUrl }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">业务标识：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(busniessMarkOptionList, 'dicItemValue', data.busniessMark) }}
          </a-col>
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
import CommonUtil from '/@/platform/commons/utils/common-util';
import AccessUrlApi from '/@/platform/apis/access-url-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import AccessUrlType from '/@/platform/types/access-url-type';
import { dataDicTransformPipe } from '/@/platform/pipes/data-dic-pipe';

export default defineComponent({
  name: 'AccessUrlView',
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
    const statusOptionList = shallowRef<any[]>([]);
    const data = shallowRef<AccessUrlType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      AccessUrlApi.queryAccessUrlById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          busniessMarkOptionList.value = res.data.resultData.BUSNIESS_MARK_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          data.value = res.data.resultData.accessUrlData;
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
      busniessMarkOptionList,
      statusOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './AccessUrlView.less';
</style>
