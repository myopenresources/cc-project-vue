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
    <app-def-drawer-layout :title="'查看'" @close="handleCancel">
      <div class="app-data-view-container">
        <a-row>
          <a-col :span="5">编号：</a-col>
          <a-col :span="19">{{ data.userCode }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">用户名：</a-col>
          <a-col :span="19">{{ data.userName }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">姓名：</a-col>
          <a-col :span="19">{{ data.realName || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">身份证：</a-col>
          <a-col :span="19">{{ data.idCard || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">性别：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(sexOptionList, 'dicItemValue', data.sex) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">邮箱：</a-col>
          <a-col :span="19">{{ data.email || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">电话：</a-col>
          <a-col :span="19">{{ data.telephone || '无' }}</a-col>
        </a-row>
        <a-row>
          <a-col :span="5">状态：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(statusOptionList, 'dicItemValue', data.status) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">短信开关：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(smsSwitchOptionList, 'dicItemValue', data.smsSwitch) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">邮箱开关：</a-col>
          <a-col :span="19">
            {{ dataDicPipe(emailSwitchOptionList, 'dicItemValue', data.emailSwitch) }}
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="5">描述：</a-col>
          <a-col :span="19">
            {{ data.userDescribe || '无' }}
          </a-col>
        </a-row>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, shallowRef } from 'vue';
import CommonUtil from '/@/common/util/common-util';
import HttpResultUtils from '/@/common/util/http-result-utils';
import UserType from '/@/types/user-type';
import UserApi from '/@/api/user-api';
import { dataDicTransformPipe } from '/@/pipes/data-dic-pipe';

export default defineComponent({
  name: 'UserView',
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
  emits: ['update:visible', 'update:id'],
  setup(props, context) {
    const sexOptionList = shallowRef<any[]>([]);
    const emailSwitchOptionList = shallowRef<any[]>([]);
    const smsSwitchOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const data = shallowRef<UserType>({});

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      UserApi.queryUserById(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          sexOptionList.value = res.data.resultData.SEX_TYPE;
          smsSwitchOptionList.value = res.data.resultData.SMS_SWITCH_TYPE;
          emailSwitchOptionList.value = res.data.resultData.EMAIL_SWITCH_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          data.value = res.data.resultData.userData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     * 数据转换管道
     */
    const dataDicPipe = (list, key: string, value: any) => {
      const data = dataDicTransformPipe(list, key, value);
      return data ? data.dicItemName : '无';
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
      data.value = {};
      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    return {
      data,
      sexOptionList,
      statusOptionList,
      smsSwitchOptionList,
      emailSwitchOptionList,
      handleCancel,
      afterVisibleChange,
      dataDicPipe,
    };
  },
});
</script>

<style lang="less" scoped>
@import './UserView.less';
</style>
