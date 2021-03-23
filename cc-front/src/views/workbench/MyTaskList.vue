<template>
  <app-def-main-layout>
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>
    <div>
      <a-form layout="inline" class="app-query-form">
        <a-form-item label="状态">
          <a-select
            v-model:value="queryParams.status"
            :allowClear="true"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="mark.dicItemValue"
              :key="index"
              :title="mark.dicItemName"
              v-for="(mark, index) in queryTaskStateOptionList"
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
        :row-key="'taskId'"
        @change="handleTableChange"
        class="app-data-table-container"
      >
        <template #indexRender="{ index }">
          {{ index + 1 }}
        </template>

        <template #statusRender="{ record }">
          {{ dataDicPipe(queryTaskStateOptionList, 'dicItemValue', record.status) }}
        </template>

        <template #actionRender="{ record }">
          <app-business-row-btns
            :buttonList="buttonList"
            :data="record"
            @btnClick="btnClick($event, record)"
          />
        </template>
      </a-table>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, shallowRef } from 'vue';
import TaskApi from '/@/api/task-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import CommonUtil from '/@/common/util/common-util';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'MyTaskList',
  setup() {
    const router = useRouter();

    const buttonList = shallowRef<any>([]);

    const queryTaskStateOptionList = shallowRef<any[]>([]);
    const dataSource = ref<any>([]);

    const queryParams = CommonUtil.queryParamsInit({
      status: '1',
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
        dataIndex: 'title',
        key: 'title',
      },
      {
        title: '提交人',
        dataIndex: 'createUser',
        key: 'createUser',
      },
      {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        slots: { customRender: 'statusRender' },
      },
      {
        title: '提交时间',
        dataIndex: 'createDate',
        key: 'createDate',
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
      TaskApi.initTaskManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          queryTaskStateOptionList.value = res.data.resultData.QUERY_TASK_STATUS_TYPE;
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
        TaskApi.queryTaskByConditionPaging,
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
    const btnClick = (name, record) => {
      ({
        taskView: () => {
          router.push({ path: record.taskUrl, query: JSON.parse(record.taskParameter) });
        },
        updateTaskStatus: () => {
          TaskApi.updateTaskById(record.taskId).then((res) => {
            HttpResultUtils.successTipMsg(res, () => {
              query(true);
            }) && HttpResultUtils.failureTipMsg(res);
          });
        },
      }[name]());
    };

    onMounted(() => {
      initPageData();
      query();
    });

    return {
      buttonList,
      queryParams,
      columns,
      pagination,
      dataSource,
      btnClick,
      handleTableChange,
      query,
      dataDicPipe,
      queryTaskStateOptionList,
    };
  },
});
</script>

<style lang="less" scoped>
@import './MyTaskList.less';
</style>
