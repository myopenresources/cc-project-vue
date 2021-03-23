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
          <a-col :span="19">{{ data.roleCode }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">角色名：</a-col>
          <a-col :span="19">{{ data.roleName }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">超级管理员：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(isSuperOptionList, 'dicItemValue', data.isSuper) }}
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
import CommonUtil from '/@/common/util/common-util';
import HttpResultUtils from '/@/common/util/http-result-utils';
import RoleType from '/@/types/role-type';
import RoleApi from '/@/api/role-api';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'RoleView',
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
    const isSuperOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const data = shallowRef<RoleType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      RoleApi.queryRoleById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          isSuperOptionList.value = res.data.resultData.IS_SUPER_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          data.value = res.data.resultData.roleData;
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
      isSuperOptionList,
      statusOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './RoleView.less';
</style>
