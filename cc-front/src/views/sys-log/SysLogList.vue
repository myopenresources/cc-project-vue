<template>
  <app-def-main-layout :showTool="true">
    <div class="app-sys-log-container">
      <a-list item-layout="horizontal" :data-source="dataSource" :bordered="true">
        <template #renderItem="{ item }">
          <a-list-item>
            <template #actions>
              <app-business-row-btns :buttonList="buttonList" @btnClick="btnClick($event, item)" />
            </template>

            <a-list-item-meta>
              <template #title>
                <div class="app-flex app-flex-justify-content-between">
                  <div>{{ item.fileName }}</div>
                  <div class="app-sys-log-file-size">更新时间：{{ item.lastModifyDate }}</div>
                </div>
              </template>
              <template #avatar>
                <img width="45" height="45" src="../../assets/img/log/sys-log.svg" />
              </template>

              <template #description>
                <div class="app-flex app-flex-justify-content-between app-sys-log-file-desc">
                  <div>{{ item.filePath }}</div>
                  <div>文件大小：{{ item.fileSize }}</div>
                </div>
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </div>
  </app-def-main-layout>
</template>

<script lang="ts">
import { Modal } from 'ant-design-vue';
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import HttpResultUtils from '/@/common/util/http-result-utils';
import SysLogApi from '/@/api/sys-log-api';

export default defineComponent({
  name: 'SysLogList',
  setup() {
    const buttonList = shallowRef<any>([]);
    const dataSource = ref<any>([]);

    const initPageData = () => {
      SysLogApi.initSystemLogManageData().then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          dataSource.value = res.data.resultData.systemLogFileList;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    const btnClick = (name, item) => {
      ({
        downloadFile: () => {
          window.location.href = SysLogApi.downloadSystemLog(item.filePath);
        },
        deleteFile: () => {
          Modal.confirm({
            title: '确认',
            icon: createVNode(QuestionCircleOutlined),
            content: '您确认要删除吗？',
            onOk() {
              SysLogApi.deleteSystemLog({
                filePath: item.filePath,
              }).then((res) => {
                HttpResultUtils.successTipMsg(res, () => {
                  initPageData();
                }) && HttpResultUtils.failureTipMsg(res);
              });
            },
          });
        },
      }[name]());
    };

    onMounted(() => {
      initPageData();
    });

    return {
      buttonList,
      dataSource,
      btnClick,
    };
  },
});
</script>

<style lang="less" scoped>
@import './SysLogList.less';
</style>

function QuestionCircleOutlined(QuestionCircleOutlined: any): import("vue").VNodeTypes|undefined {
    throw new Error('Function not implemented.');
}

function QuestionCircleOutlined(QuestionCircleOutlined: any): import("vue").VNodeTypes|undefined {
    throw new Error('Function not implemented.');
}
