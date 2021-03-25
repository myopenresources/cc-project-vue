<template>
  <a-drawer
    :title="null"
    :placement="'right'"
    :width="'670px'"
    :closable="false"
    :maskClosable="false"
    :keyboard="false"
    :destroyOnClose="true"
    :visible="visible"
    :after-visible-change="afterVisibleChange"
  >
    <app-def-drawer-layout :title="'角色分配'" @close="handleCancel">
      <template #headerBtnsTpl>
        <a-button type="primary" @click="save">
          <span class="iconfont icon-save"></span>保存
        </a-button>
      </template>

      <a-transfer
        :data-source="dataSource"
        :show-search="true"
        :target-keys="targetKeys"
        :list-style="{
          width: '305px',
          height: '100%',
        }"
        :titles="['未分配角色', '已分配角色']"
        :render="(item) => `${item.title}`"
        @change="(keys) => (targetKeys = keys)"
      />
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, ref, shallowRef } from 'vue';
import CommonUtil from '/@/common/util/common-util';
import UserApi from '/@/api/user-api';
import HttpResultUtils from '/@/common/util/http-result-utils';

export default defineComponent({
  name: 'UserRoleDistribute',
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
  emits: ['update:visible', 'update:id', 'reload'],
  setup(props, context) {
    const dataSource = shallowRef<any[]>([]);
    const targetKeys = ref<string[]>([]);

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      UserApi.initUserRoleDistributeData(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          const selectKeys: string[] = [];
          for (const role of res.data.resultData.roleData) {
            role.key = role.roleId;
            role.direction = 'left';
            role.title = role.roleName;
            for (let i = 0; i < res.data.resultData.userRoleData.length; i++) {
              const extendRole = res.data.resultData.userRoleData[i];
              if (role.roleId === extendRole.roleId) {
                selectKeys.push(extendRole.roleId);
                break;
              }
            }
          }
          targetKeys.value = selectKeys;
          dataSource.value = res.data.resultData.roleData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 保存
     */
    const save = () => {
      const roleIds: string[] = targetKeys.value;
      const params = {
        roleIds: roleIds,
        userId: props.id,
      };

      UserApi.saveUserRoleMapping(params).then((res) => {
        HttpResultUtils.successTipMsg(res, () => {
          CommonUtil.drawerClose(context, true);
        }) && HttpResultUtils.failureTipMsg(res);
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
      targetKeys.value = [];
      targetKeys.value = [];
      dataSource.value = [];
      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    return {
      dataSource,
      handleCancel,
      targetKeys,
      afterVisibleChange,
      save,
    };
  },
});
</script>

<style lang="less" scoped>
@import './UserRoleDistribute.less';
</style>
