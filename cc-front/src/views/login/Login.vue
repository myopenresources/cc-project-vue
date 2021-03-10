<template>
  <div class="app-login-container">
    <div class="app-login-inner-container">
      <div class="app-login-box-card">
        <div class="app-login-flex">
          <div class="app-login-img"></div>
          <div class="app-login-form-box">
            <h3 class="app-login-title">{{ sysTitle }}</h3>
            <a-form class="app-login-form">
              <a-form-item v-bind="validateInfos.userName">
                <a-input v-model:value="modelRef.userName" placeholder="请输入用户名！">
                  <template #prefix><span class="iconfont icon-user"></span></template>
                </a-input>
              </a-form-item>
              <a-form-item v-bind="validateInfos.password">
                <a-input
                  v-model:value="modelRef.password"
                  type="password"
                  placeholder="请输入密码！"
                >
                  <template #prefix><span class="iconfont icon-lock"></span></template>
                </a-input>
              </a-form-item>
              <a-button type="primary" block @click="login"> 登录 </a-button>
            </a-form>
          </div>
        </div>
        <div class="app-login-copyright">
          <div class="app-text-center"> Copyright 2021 cjh. All Rights Reserved. </div>
        </div>
      </div>
      <div class="app-login-desc">
        系统仅支持Edge、Google Chrome、Mozilla
        Firefox、Safari以及其它主流的浏览器，如您的浏览器无法使用本系统，请更换其它浏览器！
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, UnwrapRef, toRaw } from 'vue';
import { useRouter } from 'vue-router';
import { Button, Form, Input, notification } from 'ant-design-vue';
import { useForm } from '@ant-design-vue/use';
import { ValidateErrorEntity } from 'ant-design-vue/es/form/interface';
import UserLoginType from '../../types/user-login-type';
import Environments from '/@/common/util/env-util';
import SysApi from '/@/api/sys-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import SysStorageUtils from '/@/common/util/sys-storage-utils';

export default defineComponent({
  name: 'Login',
  components: {
    AButton: Button,
    AForm: Form,
    AInput: Input,
    AFormItem: Form.Item,
  },
  setup() {
    const sysTitle = Environments.getEvnProp('VITE_SYS_TITLE') || '管理系统';

    const router = useRouter();

    const modelRef: UnwrapRef<UserLoginType> = reactive({
      userName: Environments.getEvnProp('VITE_ADMIN_USER_NAME'),
      password: Environments.getEvnProp('VITE_ADMIN_PASSWORD'),
    });

    const { validate, validateInfos } = useForm(
      modelRef,
      reactive({
        userName: [
          {
            required: true,
            min: 4,
            max: 16,
            message: '用户名格式不正确(4-16位)!',
          },
        ],
        password: [
          {
            required: true,
            min: 4,
            max: 16,
            message: '密码格式不正确(4-16位)!',
          },
        ],
      })
    );

    const login = () => {
      validate()
        .then((values) => {
          SysApi.login(values).then((res) => {
            if (HttpResultUtils.isSuccess(res)) {
              SysStorageUtils.setSecurityKey(res.data.resultData.key);

              SysStorageUtils.setSessionUser({
                userId: res.data.resultData.userId,
                userName: res.data.resultData.userName,
                realName: res.data.resultData.realName,
                userCode: res.data.resultData.userCode,
              });

              router.push(Environments.getEvnProp('VITE_HOME_ROUTER'));
              setTimeout(() => {
                notification.success({
                  message: '欢迎',
                  description: res.data.resultMsg,
                });
              }, 1000);
            } else {
              HttpResultUtils.failureTipMsg(res);
            }
          });
        })
        .catch((error: ValidateErrorEntity<UserLoginType>) => {
          console.log('error', error);
        });
    };

    return {
      validateInfos,
      modelRef,
      login,
      sysTitle,
    };
  },
});
</script>

<style lang="less" scoped>
@import './Login.less';
</style>
