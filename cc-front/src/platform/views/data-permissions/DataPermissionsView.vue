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
          <a-col :span="5">数据权限名称：</a-col>
          <a-col :span="19">{{ data.dataPermissionsName || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">数据权限分组：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(dataPermitCategoryOptionList, 'dicItemValue', data.dataPermissionsCategory) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">基本处理器：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(dataPermitProcessorOptionList, 'dicItemValue', data.primaryProcessor) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">辅助处理器：</a-col>
          <a-col :span="19">
            {{
              dataDicPipe(dataPermitProcessorOptionList, 'dicItemValue', data.secondaryProcessor)
            }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">处理器优先级：</a-col>
          <a-col :span="19">
            {{ data.processorPriority || '无' }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">匹配列名：</a-col>
          <a-col :span="19">
            {{ data.matchColumn || '无' }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">匹配地址：</a-col>
          <a-col :span="19">{{ data.accessUrl || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">匹配SQL ID：</a-col>
          <a-col :span="19">
            {{ data.sqlId || '无' }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">参数名：</a-col>
          <a-col :span="19">
            {{ data.paramKey || '内置参数' }}
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
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import DataPermissionsType from '/@/platform/types/data-permissions-type';
import DataPermissionsApi from '/@/platform/apis/data-permissions-api';
import { dataDicTransformPipe } from '/@/platform/pipes/data-dic-pipe';

export default defineComponent({
  name: 'DataPermissionsView',
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
    const dataPermitCategoryOptionList = shallowRef<any[]>([]);
    const dataPermitProcessorOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const data = shallowRef<DataPermissionsType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      DataPermissionsApi.queryDataPermissionsById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dataPermitCategoryOptionList.value = res.data.resultData.DATA_PERMIT_CATEGORY;
          dataPermitProcessorOptionList.value = res.data.resultData.DATA_PERMIT_PROCESSOR;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          data.value = res.data.resultData.dataPermissionsData;
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
      dataPermitCategoryOptionList,
      dataPermitProcessorOptionList,
      statusOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataPermissionsView.less';
</style>
