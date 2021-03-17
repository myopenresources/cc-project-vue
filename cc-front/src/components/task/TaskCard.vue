<template>
  <a-card :bordered="false" class="app-task-card" :bodyStyle="{ padding: 0 }">
    <template #extra>
      <a-button size="small" type="primary" ghost @click="toTaskList">
        更多
        <span class="iconfont icon-doubleright"></span>
      </a-button>
    </template>
    <template #title>
      <app-line-title title="待办信息" />
    </template>

    <a-alert
      :message="'您共有 ' + taskObj.count + ' 条待办信息未处理，请及时处理！'"
      type="info"
      show-icon
      v-show="taskObj.tasks && taskObj.tasks.length"
    />

    <a-table
      :row-key="'taskId'"
      :data-source="taskObj.tasks"
      :columns="columns"
      :pagination="false"
      :showHeader="false"
      :bordered="false"
      :size="'middle'"
      v-if="taskObj.tasks && taskObj.tasks.length"
      class="app-task-table"
    >
      <template #indexRender="{ index }">
        {{ index + 1 }}
      </template>
      <template #createUserRender="{ record }">
        <a-tag color="blue">{{ record.createUser }}</a-tag>
      </template>

      <template #createDateRender="{ record }">
        <div class="app-task-date">{{ record.createDate }}</div>
      </template>
    </a-table>

    <div class="app-task-empty-data" v-show="!(taskObj.tasks && taskObj.tasks.length)">
      <app-custom-empty />
    </div>
  </a-card>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import TaskObjType from '/@/types/task-obj';

export default defineComponent({
  name: 'TaskCard',
  props: {
    taskObj: {
      type: Object as PropType<TaskObjType>,
      default: () => {
        return {
          count: 0,
          tasks: [],
        };
      },
    },
  },
  setup() {
    const columns = [
      { key: 'index', dataIndex: 'index', width: '50px', slots: { customRender: 'indexRender' } },
      {
        key: 'createUser',
        dataIndex: 'createUser',
        width: '100px',
        slots: { customRender: 'createUserRender' },
      },
      { key: 'title', dataIndex: 'title', ellipsis: true },
      {
        key: 'createDate',
        dataIndex: 'createDate',
        align: 'right',
        width: '150px',
        slots: { customRender: 'createDateRender' },
      },
    ];

    const toTaskList = () => {};

    return {
      columns,
      toTaskList,
    };
  },
});
</script>

<style lang="less" scoped>
@import './TaskCard.less';
</style>
