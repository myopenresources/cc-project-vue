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
          <a-col :span="5">标题：</a-col>
          <a-col :span="19">{{ data.noticeTitle }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">内容：</a-col>
          <a-col :span="19">{{ data.noticeContent || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">截止时间：</a-col>
          <a-col :span="19">{{ data.cutoffDate }}</a-col>
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
import NoticeApi from '/@/api/notice-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import NoticeType from '/@/types/notice-type';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'SysNoticeView',
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
    const statusOptionList = shallowRef<any[]>([]);
    const data = shallowRef<NoticeType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      NoticeApi.querySysNoticeById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          statusOptionList.value = res.data.resultData.FORM_STATUS_TYPE;
          data.value = res.data.resultData.noticeData;
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
@import './SysNoticeView.less';
</style>
