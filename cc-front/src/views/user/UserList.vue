<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="用户名">
          <a-input v-model:value="queryParams.userName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="姓名">
          <a-input v-model:value="queryParams.realName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="query(true)">
            <span class="iconfont icon-search"></span> 查询
          </a-button>
        </a-form-item>
      </a-form>
      <a-table
        :size="'middle'"
        :dataSource="dataSource"
        :columns="columns"
        :pagination="pagination"
        :bordered="true"
        :row-key="'userId'"
        :row-selection="{
          selectedRowKeys: selectedRowKeys,
          onChange: (changableRowKeys) => (selectedRowKeys = changableRowKeys),
        }"
        @change="handleTableChange"
        class="app-data-table-container"
      >
        <template #indexRender="{ index }">
          {{ index + 1 }}
        </template>

        <template #statusRender="{ record }">
          {{ dataDicPipe(statusOptionList, 'dicItemValue', record.status) }}
        </template>

        <template #actionRender="{ record }">
          <app-business-row-btns
            :buttonList="buttonList"
            @btnClick="btnClick($event, record.userId)"
          />
        </template>
      </a-table>
    </div>
  </app-def-main-layout>
  <app-user-add v-model:visible="addVisible" v-model:id="editAndViewId" @reload="query(true)" />
  <app-user-view v-model:visible="viewVisible" v-model:id="editAndViewId" />
  <app-user-pwd-edit v-model:visible="pwdEditVisible" v-model:id="editAndViewId" />
  <app-user-role-distribute v-model:visible="userRoleDisVisible" v-model:id="editAndViewId" />
  <app-user-dept-distribute v-model:visible="userDeptDisVisible" v-model:id="editAndViewId" />
</template>

<script lang="ts">
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import UserApi from '/@/api/user-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import CommonUtil from '/@/common/util/common-util';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import UserAdd from './UserAdd.vue';
import UserView from './UserView.vue';
import UserPwdEdit from './UserPwdEdit.vue';
import UserRoleDistribute from './UserRoleDistribute.vue';
import UserDeptDistribute from './UserDeptDistribute.vue';

type Key = ColumnProps['key'];

export default defineComponent({
  name: 'UserList',
  components: {
    AppUserAdd: UserAdd,
    AppUserView: UserView,
    AppUserPwdEdit: UserPwdEdit,
    AppUserRoleDistribute: UserRoleDistribute,
    AppUserDeptDistribute: UserDeptDistribute,
  },
  setup() {
    const buttonList = shallowRef<any>([]);
    const addVisible = ref<boolean>(false);
    const viewVisible = ref<boolean>(false);
    const pwdEditVisible = ref<boolean>(false);
    const userRoleDisVisible = ref<boolean>(false);
    const userDeptDisVisible = ref<boolean>(false);

    const editAndViewId = ref<string>('');
    const statusOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);
    const selectedRowKeys = ref<Key[]>([]);

    const queryParams = CommonUtil.queryParamsInit({
      userName: '',
      realName: '',
    });

    const columns = [
      {
        title: '序号',
        key: 'index',
        dataIndex: 'index',
        width: '80px',
        slots: { customRender: 'indexRender' },
      },
      {
        title: '用户名',
        dataIndex: 'userName',
        key: 'userName',
      },
      {
        title: '姓名',
        dataIndex: 'realName',
        key: 'realName',
      },
      {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        slots: { customRender: 'statusRender' },
      },
      {
        title: '创建时间',
        dataIndex: 'createDate',
        key: 'createDate',
      },
      {
        title: '修改时间',
        dataIndex: 'updateDate',
        key: 'updateDate',
      },
      {
        title: '操作',
        dataIndex: 'action',
        key: 'action',
        slots: { customRender: 'actionRender' },
      },
    ];

    /**
     * 数据转换管道
     */
    const dataDicPipe = (list, key: string, value: any) => {
      const data = dataDicTransformPipe(list, key, value);
      return data ? data.dicItemName : '无';
    };

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      UserApi.initUserManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 查询数据
     */
    const query = (btnQuery = false) => {
      selectedRowKeys.value = [];
      CommonUtil.queryDataByConditionPaging(
        btnQuery,
        queryParams,
        UserApi.queryUserByConditionPaging,
        dataSource
      );
    };

    /**
     *分页计算
     */
    const pagination = CommonUtil.paginationComputed(queryParams);

    //分页参数改变处理
    const handleTableChange = (pag: any) => CommonUtil.paginationChange(queryParams, pag, query);

    //动态按钮处理
    const btnClick = (name, objId = '') => {
      ({
        userAdd: () => {
          addVisible.value = true;
        },
        userEdit: () => {
          addVisible.value = true;
          editAndViewId.value = objId;
        },
        userView: () => {
          viewVisible.value = true;
          editAndViewId.value = objId;
        },
        userPasswordEdit: () => {
          pwdEditVisible.value = true;
          editAndViewId.value = objId;
        },
        userPasswordReset: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要重置吗？',
              onOk() {
                UserApi.resetUserPwdByIds({
                  ids: selectedRowKeys.value,
                }).then((res) => {
                  HttpResultUtils.successTipMsg(res, () => {
                    selectedRowKeys.value = [];
                  }) && HttpResultUtils.failureTipMsg(res);
                });
              },
            });
          } else {
            notification.warning({
              message: '提示',
              description: '请选择要重置的内容！',
            });
          }
        },
        userRoleDistribute: () => {
          userRoleDisVisible.value = true;
          editAndViewId.value = objId;
        },
        userDeptDistribute: () => {
          userDeptDisVisible.value = true;
          editAndViewId.value = objId;
        },
        deleteUser: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                UserApi.deleteUserByIds({
                  ids: selectedRowKeys.value,
                }).then((res) => {
                  HttpResultUtils.successTipMsg(res, () => {
                    query(true);
                  }) && HttpResultUtils.failureTipMsg(res);
                });
              },
            });
          } else {
            notification.warning({
              message: '提示',
              description: '请选择要删除的内容！',
            });
          }
        },
      }[name]());
    };

    onMounted(() => {
      initPageData();
      query();
    });

    return {
      buttonList,
      addVisible,
      pwdEditVisible,
      userRoleDisVisible,
      userDeptDisVisible,
      editAndViewId,
      viewVisible,
      statusOptionList,
      queryParams,
      columns,
      pagination,
      dataSource,
      btnClick,
      handleTableChange,
      query,
      dataDicPipe,
      selectedRowKeys,
    };
  },
});
</script>

<style lang="less" scoped>
@import './UserList.less';
</style>
