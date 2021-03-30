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
        <a-form-item v-bind="validateInfos.buttonCode" label="按钮编号">
          <a-input v-model:value="modelRef.buttonCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.buttonName" label="按钮名称">
          <a-input v-model:value="modelRef.buttonName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.functionName" label="函数名">
          <a-input v-model:value="modelRef.functionName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.sortNum" label="序号">
          <a-input-number
            v-model:value="modelRef.sortNum"
            :min="1"
            :max="99"
            placeholder="请输入..."
          />
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
        <a-form-item v-bind="validateInfos.layoutMark" label="布局标识">
          <a-select
            v-model:value="modelRef.layoutMark"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in layoutMarkOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.iconCls" label="图标">
          <a-input v-model:value="modelRef.iconCls" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.btnCls" label="按钮样式">
          <a-select v-model:value="modelRef.btnCls" placeholder="请选择...">
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in btnClsOptionList"
            >
              <app-custom-button :type="item.dicItemValue" :size="'small'">
                {{ item.dicItemName }}
              </app-custom-button>
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.btnExp" label="表达式">
          <a-textarea
            v-model:value="modelRef.btnExp"
            placeholder="请输入..."
            :auto-size="{ minRows: 2, maxRows: 2 }"
          />
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
        <a-form-item v-bind="validateInfos.buttonDescribe" label="描述">
          <a-textarea
            v-model:value="modelRef.buttonDescribe"
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
import CommonUtil from '/@/common/util/common-util';
import ButtonType from '/@/types/button-type';
import ButtonApi from '/@/api/button-api';
import HttpResultUtils from '/@/common/util/http-result-utils';

export default defineComponent({
  name: 'ButtonAdd',
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
    const layoutMarkOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const btnClsOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<ButtonType> = reactive({
      buttonName: '',
      buttonCode: '',
      functionName: '',
      busniessMark: undefined,
      layoutMark: undefined,
      iconCls: '',
      btnCls: undefined,
      buttonDescribe: '',
      sortNum: 1,
      status: undefined,
      btnExp: '',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        buttonCode: [
          {
            required: true,
            min: 1,
            max: 30,
            message: '按钮编号格式不正确（格式：1-30个字符）！',
          },
        ],
        buttonName: [
          {
            required: true,
            min: 1,
            max: 25,
            message: '按钮名称格式不正确（格式：1-25个字符）！',
          },
        ],
        functionName: [
          {
            required: true,
            min: 1,
            max: 60,
            message: '函数名格式不正确（格式：1-60个字符）！',
          },
        ],
        busniessMark: [
          {
            required: true,
            message: '请选择业务标识！',
          },
        ],
        layoutMark: [
          {
            required: true,
            message: '请选择布局标识！',
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
        btnCls: [
          {
            required: true,
            message: '请选择按钮样式！',
          },
        ],
        sortNum: [
          {
            type: 'number',
            required: true,
            message: '序号格式不正确（格式：1-2个数字）！',
          },
        ],
        status: [
          {
            required: true,
            message: '请选择状态！',
          },
        ],
        buttonDescribe: [
          {
            required: false,
            min: 0,
            max: 100,
            message: '描述格式不正确（格式：0-100个字符）!',
          },
        ],
        btnExp: [
          {
            required: false,
            min: 0,
            max: 256,
            message: '表达式格式不正确（格式：0-256个字符）!',
          },
        ],
      })
    );

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      ButtonApi.initButtonAddData({
        buttonId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          busniessMarkOptionList.value = res.data.resultData.BUSNIESS_MARK_TYPE;
          layoutMarkOptionList.value = res.data.resultData.BUTTON_LAYOUT_MARK_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          btnClsOptionList.value = res.data.resultData.BUTTON_CLS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.buttonData, modelRef);
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
        params.buttonId = props.id;
        ButtonApi.saveButton(params).then((res) => {
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
      btnClsOptionList,
      busniessMarkOptionList,
      statusOptionList,
      layoutMarkOptionList,
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
@import './ButtonAdd.less';
</style>
