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
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 19 }"
        layout="horizontal"
      >
        <a-form-item v-bind="validateInfos.accessUrlName" label="地址名称">
          <a-input v-model:value="modelRef.accessUrlName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.accessUrl" label="地址">
          <a-input v-model:value="modelRef.accessUrl" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.busniessMark" label="业务标识">
          <a-select
            v-model:value="modelRef.busniessMark"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in busniessMarkOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
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
import AccessUrlType from '/@/types/access-url-type';
import AccessUrlApi from '/@/api/access-url-api';
import HttpResultUtils from '/@/common/util/http-result-utils';

export default defineComponent({
  name: 'AccessUrlAdd',
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
    const busniessMarkOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<AccessUrlType> = reactive({
      accessUrl: '',
      accessUrlName: '',
      busniessMark: undefined,
      status: undefined,
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        accessUrl: [
          {
            required: true,
            min: 1,
            max: 50,
            message: '地址名称格式不正确（格式：1-50个字符）！',
          },
        ],
        accessUrlName: [
          {
            required: true,
            min: 1,
            max: 300,
            message: '地址格式不正确（格式：1-300个字符）！',
          },
        ],
        busniessMark: [
          {
            required: true,
            message: '请选择业务标识！',
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
      AccessUrlApi.initAccessUrlAddData({
        accessUrlId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          busniessMarkOptionList.value = res.data.resultData.BUSNIESS_MARK_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.accessUrlData, modelRef);
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
        params.accessUrlId = props.id;
        AccessUrlApi.saveAccessUrl(params).then((res) => {
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
      busniessMarkOptionList,
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
@import './AccessUrlAdd.less';
</style>
