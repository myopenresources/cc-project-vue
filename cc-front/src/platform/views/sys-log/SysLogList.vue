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
                <component :is="sysLogImg" />
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
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import SysLogApi from '/@/platform/apis/sys-log-api';
import sysLog from '/@/assets/platform/img/log/sys-log.svg';

export default defineComponent({
  name: 'SysLogList',
  setup() {
    const sysLogImg = sysLog;

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
      sysLogImg,
    };
  },
});
</script>

<style lang="less" scoped>
@import './SysLogList.less';
</style>
