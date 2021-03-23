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
        :row-key="'noticeId'"
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
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, shallowRef } from 'vue';
import NoticeApi from '/@/api/notice-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import CommonUtil from '/@/common/util/common-util';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';
import SysNoticeView from '/@/views/notice/SysNoticeView.vue';

export default defineComponent({
  name: 'MyNoticeList',
  components: {
    AppSysNoticeView: SysNoticeView,
  },
  setup() {
    const buttonList = shallowRef<any>([]);
    const viewVisible = ref<boolean>(false);
    const editAndViewId = ref<string>('');

    const statusOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);

    const queryParams = CommonUtil.queryParamsInit({
      noticeTitle: '',
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
      NoticeApi.initUserSysNoticeManageData({}).then((res) => {
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
        userNoticeView: () => {
          viewVisible.value = true;
          editAndViewId.value = itemId;
        },
      }[name]());
    };

    onMounted(() => {
      initPageData();
      query();
    });

    return {
      buttonList,
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
    };
  },
});
</script>

<style lang="less" scoped>
@import './MyNoticeList.less';
</style>
