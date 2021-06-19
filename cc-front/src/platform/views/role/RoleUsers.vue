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
    <app-def-drawer-layout :title="'角色用户'" @close="handleCancel">
      <div class="app-role-user-container">
        <a-list :data-source="dataSource">
          <template #renderItem="{ item }">
            <a-list-item>
              <div class="app-flex app-user-content-container">
                <div class="app-user-content-item"> 编号：{{ item.userCode }} </div>
                <div class="app-user-content-item"> 姓名：{{ item.realName }} </div>
              </div>
            </a-list-item>
          </template>
        </a-list>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, shallowRef } from 'vue';
import CommonUtil from '/@/platform/commons/utils/common-util';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import RoleType from '/@/platform/types/role-type';
import RoleApi from '/@/platform/apis/role-api';

export default defineComponent({
  name: 'RoleUsers',
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
    const dataSource = shallowRef<RoleType[]>([]);

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      RoleApi.queryRoleUsers(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dataSource.value = res.data.resultData.roleUser;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
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
      dataSource.value = [];
      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    return {
      dataSource,
      handleCancel,
      afterVisibleChange,
    };
  },
});
</script>

<style lang="less" scoped>
@import './RoleUsers.less';
</style>
