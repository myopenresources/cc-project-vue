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
    <app-def-drawer-layout :title="'部门分配'" @close="handleCancel">
      <template #headerBtnsTpl>
        <a-button type="primary" @click="save">
          <span class="iconfont icon-save"></span>保存
        </a-button>
      </template>

      <div class="app-user-dept-distribute-container">
        <div class="app-dept-tree-container" v-app-scrollbar="scrollOptions">
          <a-tree
            :tree-data="deptTreeData"
            :blockNode="true"
            :showIcon="false"
            :checkStrictly="true"
            :checkable="true"
            v-model:checkedKeys="targetKeys"
            @select="selectNode"
          >
            <template #title="node">
              <span :class="['iconfont', node.icon]"></span>
              {{ node.title }}
            </template>
          </a-tree>
        </div>

        <div class="app-dept-selected-container" v-app-scrollbar="scrollOptions">
          <a-list :data-source="selectNodeList">
            <template #renderItem="{ item }">
              <a-list-item>
                <div class="app-dept-name">
                  {{ item.deptName }}
                </div>

                <template #actions>
                  <a @click="deleteNode(item)" class="app-dept-selected-delete">
                    <span class="iconfont icon-delete"></span>
                  </a>
                </template>
              </a-list-item>
            </template>
          </a-list>
        </div>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, ref, shallowRef } from 'vue';
import CommonUtil from '/@/common/util/common-util';
import UserApi from '/@/api/user-api';
import HttpResultUtils from '/@/common/util/http-result-utils';

export default defineComponent({
  name: 'UserDeptDistribute',
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
    const scrollOptions = {
      overflowBehavior: {
        y: 'scroll',
        x: 'scroll',
      },
      scrollbars: {
        autoHide: 'leave',
        autoHideDelay: 200,
      },
    };

    const selectNodeList = ref<any[]>([]);
    const targetKeys = ref<string[]>([]);
    const deptTreeData = shallowRef<any[]>([]);

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      UserApi.initUserDeptDistributeData(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          deptTreeData.value = res.data.resultData.deptTreeData;
          selectNodeList.value = res.data.resultData.userDeptData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 保存
     */
    const save = () => {
      const deptIds = CommonUtil.ArrayToSimpleArray(selectNodeList.value, 'deptId');

      const params = {
        deptIds: deptIds,
        userId: props.id,
      };

      UserApi.saveUserDeptMapping(params).then((res) => {
        HttpResultUtils.successTipMsg(res, () => {
          CommonUtil.drawerClose(context, true);
        }) && HttpResultUtils.failureTipMsg(res);
      });
    };

    const deleteNode = (item: any) => {
      const index = selectNodeList.value.findIndex((node: any) => {
        return node.deptId === item.deptId;
      });
      if (index !== -1) {
        selectNodeList.value.splice(index, 1);
      }
    };

    const selectNode = (selectedKeys, e) => {
      if (e.selectedNodes && e.selectedNodes.length) {
        const node = e.selectedNodes[0];

        let isExist = false;
        for (const selectNode of selectNodeList.value) {
          if (selectNode.deptId === node.key) {
            isExist = true;
            break;
          }
        }

        if (!isExist) {
          selectNodeList.value = selectNodeList.value.concat([
            {
              deptName: node.props.text,
              deptId: node.key,
            },
          ]);
        }
      }
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
      deptTreeData.value = [];
      selectNodeList.value = [];

      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    return {
      deptTreeData,
      selectNodeList,
      handleCancel,
      targetKeys,
      afterVisibleChange,
      save,
      deleteNode,
      selectNode,
      scrollOptions,
    };
  },
});
</script>

<style lang="less" scoped>
@import './UserDeptDistribute.less';
</style>
