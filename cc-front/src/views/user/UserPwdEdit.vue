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
        <a-form-item v-bind="validateInfos.password" label="新密码">
          <a-input v-model:value="modelRef.password" type="password" placeholder="请输入新密码！" />
        </a-form-item>
        <a-form-item v-bind="validateInfos.confPassword" label="确认密码">
          <a-input
            v-model:value="modelRef.confPassword"
            type="password"
            placeholder="请输入确认密码！"
          />
        </a-form-item>
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, reactive, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/common/util/common-util';
import UserApi from '/@/api/user-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import { RuleObject } from 'ant-design-vue/lib/form/interface';
import passwordValidator from '/@/validator/password-validator';
import equalValidator from '/@/validator/equal-validator';

export default defineComponent({
  name: 'UserPwdEdit',
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
    const modelRef: UnwrapRef<{ password: string; confPassword: string }> = reactive({
      confPassword: '',
      password: '',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        password: [
          {
            required: true,
            message: '新密码必填！',
          },
          { validator: passwordValidator, trigger: 'change' },
        ],
        confPassword: [
          {
            required: true,
            message: '确认密码必填！',
          },
          {
            validator: (rule: RuleObject, value: string) => {
              return equalValidator(rule, value, modelRef.password, '密码与确认密码不一致！');
            },
            trigger: 'change',
          },
        ],
      })
    );

    /**
     * 保存
     */
    const save = () => {
      validate().then((values) => {
        const params = values;
        params.userId = props.id;
        UserApi.updateUserPasswordById(params).then((res) => {
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
      CommonUtil.drawerAddOrViewInit(visible, () => {}, resetFields);
    };

    return {
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
@import './UserPwdEdit.less';
</style>
