<template>
  <app-def-main-layout>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="用户">
          <a-select
            v-model:value="queryParams.queryUserId"
            :allowClear="true"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.userId"
              :key="index"
              :title="item.realName"
              v-for="(item, index) in userOptionList"
            >
              {{ item.realName }}
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
        :row-key="'loginId'"
        @change="handleTableChange"
        class="app-data-table-container"
      >
        <template #indexRender="{ index }">
          {{ index + 1 }}
        </template>
      </a-table>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, shallowRef } from 'vue';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import CommonUtil from '/@/platform/commons/utils/common-util';
import LoginLogApi from '/@/platform/apis/login-log-api';

export default defineComponent({
  name: 'LoginLogList',
  setup() {
    const userOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);

    const queryParams = CommonUtil.queryParamsInit({
      queryUserId: null,
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
        title: '用户',
        dataIndex: 'createUser',
        key: 'createUser',
      },
      {
        title: '登录IP',
        dataIndex: 'loginIP',
        key: 'loginIP',
      },
      {
        title: '登录时间',
        dataIndex: 'createDate',
        key: 'createDate',
      },
    ];

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      LoginLogApi.initLoginLogManageData().then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          userOptionList.value = res.data.resultData.userList;
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
        LoginLogApi.queryLoginLogByConditionPaging,
        dataSource
      );
    };

    /**
     *分页计算
     */
    const pagination = CommonUtil.paginationComputed(queryParams);

    //分页参数改变处理
    const handleTableChange = (pag: any) => CommonUtil.paginationChange(queryParams, pag, query);

    onMounted(() => {
      initPageData();
      query();
    });

    return {
      userOptionList,
      queryParams,
      columns,
      pagination,
      dataSource,
      handleTableChange,
      query,
    };
  },
});
</script>

<style lang="less" scoped>
@import './LoginLogList.less';
</style>
