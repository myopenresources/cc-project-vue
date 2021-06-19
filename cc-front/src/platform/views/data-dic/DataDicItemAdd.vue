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
          <app-dynamic-icon iconName="SaveOutlined" />保存
        </a-button>
      </template>

      <a-form :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }" layout="horizontal">
        <a-form-item v-bind="validateInfos.dicItemCode" label="编号">
          <a-input v-model:value="modelRef.dicItemCode" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.dicItemName" label="名称">
          <a-input v-model:value="modelRef.dicItemName" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.dicItemValue" label="值">
          <a-input v-model:value="modelRef.dicItemValue" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.spareValue" label="备用值">
          <a-input v-model:value="modelRef.spareValue" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.defaultValue" label="默认值">
          <a-input v-model:value="modelRef.defaultValue" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.sortNum" label="序号">
          <a-input-number
            v-model:value="modelRef.sortNum"
            :min="1"
            :max="999"
            placeholder="请输入..."
          />
        </a-form-item>

        <a-form-item v-bind="validateInfos.dicItemParentId" label="上一级">
          <a-tree-select
            v-model:value="modelRef.dicItemParentId"
            :showSearch="true"
            :dropdown-style="{ maxHeight: '300px', overflow: 'auto' }"
            :tree-data="dicItemOptionList"
            placeholder="请选择.."
            :replaceFields="{
              title: 'title',
              key: 'key',
              value: 'key',
            }"
            @change="selectParentDicItem"
          >
            <template #title="{ title }">
              {{ title }}
            </template>
          </a-tree-select>
        </a-form-item>

        <a-form-item v-bind="validateInfos.iconCls" label="图标">
          <a-input v-model:value="modelRef.iconCls" placeholder="请输入..." />
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
        <a-form-item v-bind="validateInfos.dicItemDesc" label="描述">
          <a-textarea
            v-model:value="modelRef.dicItemDesc"
            placeholder="请输入..."
            :auto-size="{ minRows: 2, maxRows: 2 }"
          />
        </a-form-item>
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, reactive, shallowRef, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/platform/commons/utils/common-util';
import DataDicItemType from '/@/platform/types/data-dic-item-type';
import DataDicApi from '/@/platform/apis/data-dic-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: 'DataDicItemAdd',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    dicTypeId: {
      type: String,
      default: '',
    },
    id: {
      type: String,
      default: '',
    },
  },
  emits: ['update:visible', 'update:id', 'reload'],
  setup(props, context) {
    const statusOptionList = shallowRef<any[]>([]);
    const dicItemOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<DataDicItemType> = reactive({
      dicItemCode: '',
      dicItemName: '',
      dicItemParentId: undefined,
      dicItemValue: '',
      spareValue: '',
      defaultValue: '',
      dicItemDesc: '',
      iconCls: '',
      sortNum: '1',
      status: undefined,
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        dicItemCode: [
          {
            required: true,
            min: 1,
            max: 30,
            message: '编号格式不正确（格式：1-30个字符）！',
          },
        ],
        dicItemName: [
          {
            required: true,
            min: 1,
            max: 50,
            message: '名称格式不正确（格式：1-50位字符）！',
          },
        ],
        dicItemValue: [
          {
            min: 1,
            max: 256,
            required: true,
            message: '值格式不正确（格式：1-256个字符）！',
          },
        ],
        spareValue: [
          {
            min: 0,
            max: 256,
            required: false,
            message: '备用值格式不正确（格式：0-256个字符）！',
          },
        ],
        defaultValue: [
          {
            min: 0,
            max: 256,
            required: false,
            message: '默认值格式不正确（格式：0-256个字符）！',
          },
        ],
        dicItemParentId: [
          {
            required: false,
            message: '请先选择上一级！',
          },
        ],
        sortNum: [
          {
            required: true,
            message: '序号格式不正确（格式：1-3个数字）！',
          },
        ],
        iconCls: [
          {
            required: false,
            min: 0,
            max: 32,
            message: '图标格式不正确（格式：0-32个字符）！',
          },
        ],
        dicItemDesc: [
          {
            required: false,
            min: 0,
            max: 256,
            message: '描述格式不正确（格式：0-256个字符）！',
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
      DataDicApi.initDataDataDicItemAddData({
        dicItemId: props.id,
        dicTypeId: props.dicTypeId,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dicItemOptionList.value = res.data.resultData.dataDicItemList;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.dataDicItemData, modelRef);
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
        params.dicTypeId = props.dicTypeId;
        params.dicItemId = props.id;

        if (
          null === params.dicItemParentId ||
          undefined === params.dicItemParentId ||
          'null' === params.dicItemParentId
        ) {
          params.dicItemParentId = '';
        }

        if (params.dicItemParentId!='' && params.dicItemId === params.dicItemParentId) {
          notification.warning({
            message: '提示',
            description: '上一级不能是自己！',
          });
          return;
        }

        DataDicApi.saveDataDicItem(params).then((res) => {
          HttpResultUtils.successTipMsg(res, () => {
            CommonUtil.drawerClose(context, true);
          }) && HttpResultUtils.failureTipMsg(res);
        });
      });
    };

    const selectParentDicItem = (value) => {
      if (value === props.id) {
        notification.warning({
          message: '提示',
          description: '上一级不能是自己！',
        });

        modelRef.dicItemParentId = undefined;
      }
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
      dicItemOptionList,
      statusOptionList,
      validateInfos,
      modelRef,
      handleCancel,
      afterVisibleChange,
      save,
      selectParentDicItem,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataDicItemAdd.less';
</style>
