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
        <a-form-item v-bind="validateInfos.dicTypeCode" label="编号">
          <a-input v-model:value="modelRef.dicTypeCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.dicTypeName" label="名称">
          <a-input v-model:value="modelRef.dicTypeName" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.dicCategory" label="字典组">
          <a-select
            v-model:value="modelRef.dicCategory"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in dicCategoryOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item v-bind="validateInfos.iconCls" label="图标">
          <a-input v-model:value="modelRef.iconCls" placeholder="请输入..." />
        </a-form-item>
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, reactive, shallowRef, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/common/util/common-util';
import DataDicTypeType from '/@/types/data-dic-type-type';
import DataDicApi from '/@/api/data-dic-api';
import HttpResultUtils from '/@/common/util/http-result-utils';

export default defineComponent({
  name: 'DataDicTypeAdd',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    add: {
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
    const dicCategoryOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<DataDicTypeType> = reactive({
      dicTypeCode: '',
      dicTypeName: '',
      dicCategory: undefined,
      iconCls: '',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        dicTypeCode: [
          {
            required: true,
            min: 1,
            max: 30,
            message: '编号格式不正确（格式：1-30个字符）',
          },
        ],
        dicTypeName: [
          {
            required: true,
            min: 1,
            max: 30,
            message: '名称格式不正确（格式：1-30个字符）！',
          },
        ],
        dicCategory: [
          {
            required: true,
            message: '请选择字典组!',
          },
        ],
        iconCls: [
          {
            required: true,
            min: 1,
            max: 32,
            message: '图标格式不正确（格式：1-32个字符）！',
          },
        ],
      })
    );

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      DataDicApi.initDataDicTypeAddData({
        dicTypeId: props.add ? undefined : props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dicCategoryOptionList.value = res.data.resultData.DIC_CATEGORY_TYPE;
          CommonUtil.loadFormData(res.data.resultData.dataDicTypeData, modelRef);
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
        params.dicTypeId = props.add ? undefined : props.id;
        DataDicApi.saveDataDicType(params).then((res) => {
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
      dicCategoryOptionList,
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
@import './DataDicTypeAdd.less';
</style>
