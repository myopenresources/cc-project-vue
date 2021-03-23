<template>
  <a-drawer
    :title="null"
    :placement="'right'"
    :width="'600px'"
    :closable="false"
    :maskClosable="false"
    :keyboard="false"
    :destroyOnClose="true"
    :visible="visible"
    :after-visible-change="afterVisibleChange"
  >
    <app-def-drawer-layout :title="'添加/编辑'" @close="handleCancel">
      <template #headerBtnsTpl>
        <a-button type="primary" @click="save">
          <span class="iconfont icon-save"></span>保存
        </a-button>
      </template>

      <a-form
        class="app-update-pwd-form"
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 19 }"
        layout="horizontal"
      >
        <a-form-item v-bind="validateInfos.noticeTitle" label="标题">
          <a-input v-model:value="modelRef.noticeTitle" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.noticeContent" label="内容">
          <a-textarea
            v-model:value="modelRef.noticeContent"
            placeholder="请输入..."
            :auto-size="{ minRows: 5, maxRows: 5 }"
          />
        </a-form-item>
        <a-form-item v-bind="validateInfos.cutoffDate" label="截止时间">
          <a-date-picker v-model:value="modelRef.cutoffDate" placeholder="请选择..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.status" label="状态">
          <a-select
            v-model:value="modelRef.status"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in statusOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, reactive, shallowRef, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/common/util/common-util';
import NoticeApi from '/@/api/notice-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import NoticeType from '/@/types/notice-type';
import moment from 'moment';

export default defineComponent({
  name: 'SysNoticeAdd',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    id: {
      type: String,
      default: '',
    },
  },
  emits: ['update:visible', 'update:id', 'reload'],
  setup(props, context) {
    const statusOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<NoticeType> = reactive({
      noticeTitle: '',
      noticeContent: '',
      cutoffDate: undefined,
      status: undefined,
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        noticeTitle: [
          {
            required: true,
            min: 1,
            max: 50,
            message: '标题格式不正确（格式：1-50个字符）！',
          },
        ],
        noticeContent: [
          {
            required: true,
            min: 1,
            max: 512,
            message: '内容格式不正确（格式：1-512个字符）！',
          },
        ],
        cutoffDate: [
          {
            required: true,
            message: '请选择截止时间！',
            type: 'object',
          },
        ],
        status: [
          {
            required: true,
            message: '请选择状态！',
          },
        ],
      })
    );

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      console.info(props.id);
      NoticeApi.initSysNoticeAddData({
        noticeId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          statusOptionList.value = res.data.resultData.FORM_STATUS_TYPE;
          if (res.data.resultData.noticeData && res.data.resultData.noticeData.cutoffDate) {
            res.data.resultData.noticeData.cutoffDate = moment(
              res.data.resultData.noticeData.cutoffDate,
              'YYYY-MM-DD'
            );
          }
          CommonUtil.loadFormData(res.data.resultData.noticeData, modelRef);
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 保存
     */
    const save = () => {
      validate().then((values) => {
        const params = values;
        params.noticeId = props.id;
        params.cutoffDate = params.cutoffDate.format('YYYY-MM-DD');
        NoticeApi.saveSysNotice(params).then((res) => {
          HttpResultUtils.successTipMsg(res, () => {
            CommonUtil.drawerClose(context, true);
          }) && HttpResultUtils.failureTipMsg(res);
        });
      });
    };

    /**
     * 取消
     */
    const handleCancel = () => {
      CommonUtil.drawerClose(context);
    };

    /**
     * 打开
     */
    const afterVisibleChange = (visible) => {
      CommonUtil.drawerAddOrViewInit(visible, initPageData, resetFields);
    };

    return {
      statusOptionList,
      validateInfos,
      modelRef,
      handleCancel,
      afterVisibleChange,
      save,
    };
  },
});
</script>

<style lang="less" scoped>
@import './SysNoticeAdd.less';
</style>
