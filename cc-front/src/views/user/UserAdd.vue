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
        <a-form-item v-bind="validateInfos.userCode" label="编号">
          <a-input v-model:value="modelRef.userCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.userName" label="用户名">
          <a-input v-model:value="modelRef.userName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.realName" label="姓名">
          <a-input v-model:value="modelRef.realName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.sex" label="性别">
          <a-select
            v-model:value="modelRef.sex"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in sexOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.idCard" label="身份证">
          <a-input v-model:value="modelRef.idCard" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.email" label="邮箱">
          <a-input v-model:value="modelRef.email" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.telephone" label="电话">
          <a-input v-model:value="modelRef.telephone" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.emailSwitch" label="邮箱开关">
          <a-select
            v-model:value="modelRef.emailSwitch"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in emailSwitchOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.smsSwitch" label="短信开关">
          <a-select
            v-model:value="modelRef.smsSwitch"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in smsSwitchOptionList"
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
        <a-form-item v-bind="validateInfos.userDescribe" label="描述">
          <a-textarea
            v-model:value="modelRef.userDescribe"
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
import UserType from '/@/types/user-type';
import UserApi from '/@/api/user-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import userNameValidator from '/@/validator/user-name-validator';
import idCardValidator from '/@/validator/id-card-validator';
import emailValidator from '/@/validator/email-validator';
import mobileValidator from '/@/validator/mobile-validator';

export default defineComponent({
  name: 'UserAdd',
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
    const sexOptionList = shallowRef<any[]>([]);
    const emailSwitchOptionList = shallowRef<any[]>([]);
    const smsSwitchOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<UserType> = reactive({
      userCode: '',
      userName: '',
      realName: '',
      sex: undefined,
      idCard: '',
      email: '',
      telephone: '',
      emailSwitch: undefined,
      smsSwitch: undefined,
      status: undefined,
      userDescribe: '',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        userCode: [
          {
            required: true,
            min: 3,
            max: 15,
            message: '编号格式不正确（格式：3-15位字符）！',
          },
        ],
        userName: [
          {
            required: true,
            message: '用户名必填！',
          },
          { validator: userNameValidator, trigger: 'change' },
        ],
        realName: [
          {
            required: true,
            min: 2,
            max: 15,
            message: '姓名格式不正确（格式：2-15个字符）！',
          },
        ],
        sex: [
          {
            required: true,
            min: 1,
            max: 25,
            message: '请选择性别！',
          },
        ],
        idCard: [
          {
            required: true,
            message: '身份证必填！',
          },
          { validator: idCardValidator, trigger: 'change' },
        ],
        email: [
          {
            required: true,
            message: '邮箱必填！',
          },
          { validator: emailValidator, trigger: 'change' },
        ],
        telephone: [
          {
            required: true,
            message: '电话必填！',
          },
          { validator: mobileValidator, trigger: 'change' },
        ],
        emailSwitch: [
          {
            required: true,
            message: '请选择邮箱开关！',
          },
        ],
        smsSwitch: [
          {
            required: true,
            message: '请选择短信开关！',
          },
        ],
        userDescribe: [
          {
            required: false,
            min: 0,
            max: 128,
            message: '描述格式不正确（格式：0-128个字符）!',
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
      console.info(props.id)
      UserApi.initUserAddData({
        userId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          sexOptionList.value = res.data.resultData.SEX_TYPE;
          smsSwitchOptionList.value = res.data.resultData.SMS_SWITCH_TYPE;
          emailSwitchOptionList.value = res.data.resultData.EMAIL_SWITCH_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.userData, modelRef);
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
        params.userId = props.id;
        UserApi.saveUser(params).then((res) => {
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
      sexOptionList,
      statusOptionList,
      smsSwitchOptionList,
      emailSwitchOptionList,
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
@import './UserAdd.less';
</style>
