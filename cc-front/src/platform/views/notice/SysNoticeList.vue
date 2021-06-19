<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="标题">
          <a-input v-model:value="queryParams.noticeTitle" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="截止时间">
          <a-date-picker v-model:value="queryParams.cutoffDate" placeholder="请选择..." />
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
        :row-key="'noticeId'"
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
            @btnClick="btnClick($event, record.noticeId)"
          />
        </template>
      </a-table>
    </div>
  </app-def-main-layout>

  <app-sys-notice-view v-model:visible="viewVisible" :id="editAndViewId" />
  <app-sys-notice-add
    v-model:visible="addVisible"
    :id="editAndViewId"
    @reload="query(true)"
  />
</template>

<script lang="ts">
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import NoticeApi from '/@/platform/apis/notice-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import CommonUtil from '/@/platform/commons/utils/common-util';
import { dataDicTransformPipe } from '/@/platform/pipes/data-dic-pipe';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import SysNoticeView from './SysNoticeView.vue';
import SysNoticeAdd from './SysNoticeAdd.vue';

type Key = ColumnProps['key'];

export default defineComponent({
  name: 'SysNoticeList',
  components: {
    AppSysNoticeView: SysNoticeView,
    AppSysNoticeAdd: SysNoticeAdd,
  },
  setup() {
    const buttonList = shallowRef<any>([]);
    const addVisible = ref<boolean>(false);
    const viewVisible = ref<boolean>(false);
    const editAndViewId = ref<string>('');

    const statusOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);
    const selectedRowKeys = ref<Key[]>([]);

    const queryParams = CommonUtil.queryParamsInit({
      cutoffDate: null,
      noticeTitle: '',
      status: '-1',
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
        title: '标题',
        dataIndex: 'noticeTitle',
        key: 'noticeTitle',
      },
      {
        title: '截止时间',
        dataIndex: 'cutoffDate',
        key: 'cutoffDate',
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
      NoticeApi.initSysNoticeManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          statusOptionList.value = res.data.resultData.FORM_STATUS_TYPE;
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
        NoticeApi.querySysNoticeByConditionPaging,
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
    const btnClick = (name, itemId = '') => {
      ({
        sysNoticeAdd: () => {
          addVisible.value = true;
        },
        sysNoticeView: () => {
          viewVisible.value = true;
          editAndViewId.value = itemId;
        },
        sysNoticeEdit: () => {
          addVisible.value = true;
          editAndViewId.value = itemId;
        },
        deleteSysNotice: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                NoticeApi.deleteSysNoticeByIds({
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
@import './SysNoticeList.less';
</style>
