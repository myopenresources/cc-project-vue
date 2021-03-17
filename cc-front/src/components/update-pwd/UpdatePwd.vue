<template>
  <a-modal
    :width="'460px'"
    :visible="visible"
    :destroyOnClose="true"
    :maskClosable="false"
    :keyboard="false"
    title="修改密码"
    @ok="handleOk"
    @cancel="handleCancel"
  >
    <a-form
      class="app-update-pwd-form"
      :label-col="{ span: 5 }"
      :wrapper-col="{ span: 19 }"
      layout="horizontal"
    >
      <a-form-item v-bind="validateInfos.password" label="新密码">
        <a-input v-model:value="modelRef.password" type="password" placeholder="请输入新密码！">
          <template #prefix><span class="iconfont icon-lock"></span></template>
        </a-input>
      </a-form-item>
      <a-form-item v-bind="validateInfos.confPassword" label="确认密码">
        <a-input
          v-model:value="modelRef.confPassword"
          type="password"
          placeholder="请输入确认密码！"
        >
          <template #prefix><span class="iconfont icon-lock"></span></template>
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, reactive, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import { notification } from 'ant-design-vue';
import Environments from '/@/common/util/env-util';
import passwordValidator from '/@/validator/password-validator';
import equalValidator from '/@/validator/equal-validator';
import { RuleObject } from 'ant-design-vue/lib/form/interface';
import HttpResultUtils from '/@/common/util/http-result-utils';
import UserApi from '/@/api/user-api';

export default defineComponent({
  name: 'UpdatePwd',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['update:visible'],
  setup(props, context) {
    const modelRef: UnwrapRef<{ password: string; confPassword: string }> = reactive({
      confPassword: Environments.getEvnProp('VITE_ADMIN_USER_NAME'),
      password: Environments.getEvnProp('VITE_ADMIN_PASSWORD'),
    });

    const { validate, validateInfos } = useForm(
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
     * 取消
     */
    const handleCancel = () => {
      context.emit('update:visible', false);
    };

    /**
     * 确认
     */
    const handleOk = () => {
      validate().then((values) => {
        UserApi.updateCurrentUserPassword(values).then((res) => {
          if (HttpResultUtils.isSuccess(res)) {
            handleCancel();
            notification.success({
              message: '提示',
              description: res.data.resultMsg,
            });
          } else {
            HttpResultUtils.failureTipMsg(res);
          }
        });
      });
    };

    return {
      validateInfos,
      modelRef,
      handleOk,
      handleCancel,
    };
  },
});
</script>

<style lang="less" scoped>
@import './UpdatePwd.less';
</style>
