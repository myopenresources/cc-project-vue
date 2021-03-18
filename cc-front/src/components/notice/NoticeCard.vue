<template>
  <a-card :bordered="false" class="app-notice-card" :bodyStyle="{ padding: 0 }">
    <template #extra>
      <a-button size="small" type="primary" ghost @click="toNoticeList">
        更多
        <span class="iconfont icon-doubleright"></span>
      </a-button>
    </template>
    <template #title>
      <app-line-title title="公告信息" />
    </template>

    <a-alert
      :message="'共为您展示 ' + noticeList.length + ' 条公告信息，请及时查看！'"
      type="info"
      show-icon
      v-show="noticeList && noticeList.length"
    />

    <a-table
      :row-key="'noticeId'"
      :data-source="noticeList"
      :columns="columns"
      :pagination="false"
      :showHeader="false"
      :bordered="false"
      :size="'middle'"
      :custom-row="customRow"
      v-if="noticeList && noticeList.length"
      class="app-notice-table"
    >
      <template #indexRender="{ index }">
        {{ index + 1 }}
      </template>
      <template #createUserRender="{ record }">
        <a-tag color="blue">{{ record.createUser }}</a-tag>
      </template>

      <template #cutoffDateRender="{ record }">
        <div class="app-notice-date">{{ record.cutoffDate }}</div>
      </template>
    </a-table>

    <div class="app-notice-empty-data" v-show="!(noticeList && noticeList.length)">
      <app-custom-empty />
    </div>
  </a-card>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import NoticeType from '/@/types/notice-type';

export default defineComponent({
  name: 'NoticeCard',
  props: {
    noticeList: {
      type: Array as PropType<NoticeType[]>,
      default: () => {
        return [];
      },
    },
  },
  emits: ['toNoticeList', 'toNoticeView'],
  setup(props, context) {
    const columns = [
      { key: 'index', dataIndex: 'index', width: '50px', slots: { customRender: 'indexRender' } },
      {
        key: 'createUser',
        dataIndex: 'createUser',
        width: '100px',
        slots: { customRender: 'createUserRender' },
      },
      { key: 'noticeTitle', dataIndex: 'noticeTitle', ellipsis: true },
      {
        key: 'cutoffDate',
        dataIndex: 'cutoffDate',
        align: 'right',
        width: '150px',
        slots: { customRender: 'cutoffDateRender' },
      },
    ];

    const toNoticeList = () => {
      context.emit('toNoticeList');
    };

    const customRow = (record) => {
      return {
        onClick: () => {
          context.emit('toNoticeView', record);
        },
      };
    };

    return {
      columns,
      toNoticeList,
      customRow,
    };
  },
});
</script>

<style lang="less" scoped>
@import './NoticeCard.less';
</style>
