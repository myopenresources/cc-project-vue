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

      <a-form
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 19 }"
        layout="horizontal"
      >
        <a-form-item v-bind="validateInfos.roleCode" label="编号">
          <a-input v-model:value="modelRef.roleCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.roleName" label="角色名">
          <a-input v-model:value="modelRef.roleName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.isSuper" label="超级管理员">
          <a-select
            v-model:value="modelRef.isSuper"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in isSuperOptionList"
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
        <a-form-item v-bind="validateInfos.roleDescribe" label="描述">
          <a-textarea
            v-model:value="modelRef.roleDescribe"
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
import RoleType from '/@/platform/types/role-type';
import RoleApi from '/@/platform/apis/role-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';

export default defineComponent({
  name: 'RoleAdd',
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
    const isSuperOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<RoleType> = reactive({
      roleCode: '',
      roleName: '',
      roleDescribe: '',
      isSuper: undefined,
      status: undefined,
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        roleCode: [
          {
            required: true,
            min: 3,
            max: 15,
            message: '编号格式不正确（格式：3-15位字符）！',
          },
        ],
        roleName: [
          {
            required: true,
            min: 1,
            max: 25,
            message: '用户名格式不正确（格式：1-25位字符）！',
          },
        ],
        roleDescribe: [
          {
            required: false,
            min: 0,
            max: 128,
            message: '描述格式不正确（格式：0-128个字符）!',
          },
        ],
        isSuper: [
          {
            required: true,
            message: '请选择是否超级管理员！',
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
      RoleApi.initRoleAddData({
        roleId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          isSuperOptionList.value = res.data.resultData.IS_SUPER_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.roleData, modelRef);
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
        params.roleId = props.id;
        RoleApi.saveRole(params).then((res) => {
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
      isSuperOptionList,
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
@import './RoleAdd.less';
</style>
