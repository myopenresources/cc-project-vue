<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="角色名">
          <a-input v-model:value="queryParams.roleName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="超级管理员">
          <a-select
            v-model:value="queryParams.isSuper"
            :allowClear="true"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="mark.dicItemValue"
              :key="index"
              :title="mark.dicItemName"
              v-for="(mark, index) in queryIsSuperOptionList"
            >
              {{ mark.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="query(true)">
            <app-dynamic-icon iconName="SearchOutlined" /> 查询
          </a-button>
        </a-form-item>
      </a-form>
      <a-table
        :size="'middle'"
        :dataSource="dataSource"
        :columns="columns"
        :pagination="pagination"
        :bordered="true"
        :row-key="'roleId'"
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

        <template #isSuperRender="{ record }">
          {{ dataDicPipe(isSuperOptionList, 'dicItemValue', record.isSuper) }}
        </template>

        <template #statusRender="{ record }">
          {{ dataDicPipe(statusOptionList, 'dicItemValue', record.status) }}
        </template>

        <template #actionRender="{ record }">
          <app-business-row-btns
            :buttonList="buttonList"
            @btnClick="btnClick($event, record.roleId)"
          />
        </template>
      </a-table>
    </div>
  </app-def-main-layout>
  <app-role-add v-model:visible="addVisible" v-model:id="editAndViewId" @reload="query(true)" />
  <app-role-view v-model:visible="viewVisible" v-model:id="editAndViewId" />
  <app-role-users v-model:visible="usersVisible" v-model:id="editAndViewId" />
  <app-role-permission-distribute
    v-model:visible="permissionDistributeVisible"
    v-model:id="editAndViewId"
  />
</template>

<script lang="ts">
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import RoleApi from '/@/platform/apis/role-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import CommonUtil from '/@/platform/commons/utils/common-util';
import { dataDicTransformPipe } from '/@/platform/pipes/data-dic-pipe';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import RoleAdd from './RoleAdd.vue';
import RoleView from './RoleView.vue';
import RoleUsers from './RoleUsers.vue';
import RolePermissionDistribute from './RolePermissionDistribute.vue';

type Key = ColumnProps['key'];

export default defineComponent({
  name: 'RoleList',
  components: {
    AppRoleAdd: RoleAdd,
    AppRoleView: RoleView,
    AppRoleUsers: RoleUsers,
    AppRolePermissionDistribute: RolePermissionDistribute,
  },
  setup() {
    const buttonList = shallowRef<any>([]);
    const addVisible = ref<boolean>(false);
    const viewVisible = ref<boolean>(false);
    const usersVisible = ref<boolean>(false);
    const permissionDistributeVisible = ref<boolean>(false);

    const editAndViewId = ref<string>('');

    const isSuperOptionList = shallowRef<any[]>([]);
    const queryIsSuperOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);
    const selectedRowKeys = ref<Key[]>([]);

    const queryParams = CommonUtil.queryParamsInit({
      roleName: '',
      isSuper: '-1',
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
        title: '角色名',
        dataIndex: 'roleName',
        key: 'roleName',
      },
      {
        title: '超级管理员',
        dataIndex: 'isSuper',
        key: 'isSuper',
        slots: { customRender: 'isSuperRender' },
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
      RoleApi.initRoleManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          queryIsSuperOptionList.value = res.data.resultData.QUERY_IS_SUPER_TYPE;
          isSuperOptionList.value = res.data.resultData.IS_SUPER_TYPE;
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
        RoleApi.queryRoleByConditionPaging,
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
    const btnClick = (name, roleId = '') => {
      ({
        roleAdd: () => {
          addVisible.value = true;
        },
        roleEdit: () => {
          addVisible.value = true;
          editAndViewId.value = roleId;
        },
        roleView: () => {
          viewVisible.value = true;
          editAndViewId.value = roleId;
        },
        roleUsers: () => {
          usersVisible.value = true;
          editAndViewId.value = roleId;
        },
        rolePermissionDistribute: () => {
          permissionDistributeVisible.value = true;
          editAndViewId.value = roleId;
        },
        deleteRole: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                RoleApi.deleteRoleByIds({
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
      permissionDistributeVisible,
      usersVisible,
      editAndViewId,
      viewVisible,
      statusOptionList,
      queryIsSuperOptionList,
      isSuperOptionList,
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
@import './RoleList.less';
</style>
