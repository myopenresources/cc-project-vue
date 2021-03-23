<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="地址名称">
          <a-input v-model:value="queryParams.accessUrlName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item label="业务标识">
          <a-select
            v-model:value="queryParams.busniessMark"
            :allowClear="true"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="mark.dicItemValue"
              :key="index"
              :title="mark.dicItemName"
              v-for="(mark, index) in busniessMarkOptionList"
            >
              {{ mark.dicItemName }}
            </a-select-option>
          </a-select>
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
        :row-key="'accessUrlId'"
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

        <template #busniessMarkRender="{ record }">
          {{ dataDicPipe(busniessMarkOptionList, 'dicItemValue', record.busniessMark) }}
        </template>

        <template #statusRender="{ record }">
          {{ dataDicPipe(statusOptionList, 'dicItemValue', record.status) }}
        </template>

        <template #actionRender="{ record }">
          <app-business-row-btns
            :buttonList="buttonList"
            @btnClick="btnClick($event, record.accessUrlId)"
          />
        </template>
      </a-table>
    </div>
  </app-def-main-layout>
  <app-access-url-add
    v-model:visible="addVisible"
    v-model:id="editAndViewId"
    @reload="query(true)"
  />
  <app-access-url-view v-model:visible="viewVisible" v-model:id="editAndViewId" />
</template>

<script lang="ts">
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import AccessUrlApi from '/@/api/access-url-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import CommonUtil from '/@/common/util/common-util';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import AccessUrlAdd from './AccessUrlAdd.vue';
import AccessUrlView from './AccessUrlView.vue';

type Key = ColumnProps['key'];

export default defineComponent({
  name: 'AccessUrlList',
  components: {
    AppAccessUrlAdd: AccessUrlAdd,
    AppAccessUrlView: AccessUrlView,
  },
  setup() {
    const buttonList = shallowRef<any>([]);
    const addVisible = ref<boolean>(false);
    const viewVisible = ref<boolean>(false);

    const editAndViewId = ref<string>('');
    const busniessMarkOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);
    const selectedRowKeys = ref<Key[]>([]);

    const queryParams = CommonUtil.queryParamsInit({
      busniessMark: null,
      accessUrlName: '',
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
        title: '业务标识',
        dataIndex: 'busniessMark',
        key: 'busniessMark',
        slots: { customRender: 'busniessMarkRender' },
      },
      {
        title: '地址名称',
        dataIndex: 'accessUrlName',
        key: 'accessUrlName',
      },
      {
        title: '地址',
        dataIndex: 'accessUrl',
        key: 'accessUrl',
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
      AccessUrlApi.initAccessUrlManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          busniessMarkOptionList.value = res.data.resultData.BUSNIESS_MARK_TYPE;
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
        AccessUrlApi.queryAccessUrlByConditionPaging,
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
    const btnClick = (name, btnId = '') => {
      ({
        accessUrlAdd: () => {
          addVisible.value = true;
        },
        accessUrlEdit: () => {
          addVisible.value = true;
          editAndViewId.value = btnId;
        },
        accessUrlView: () => {
          viewVisible.value = true;
          editAndViewId.value = btnId;
        },
        deleteAccessUrl: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                AccessUrlApi.deleteAccessUrlByIds({
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
      busniessMarkOptionList,
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
@import './AccessUrlList.less';
</style>
