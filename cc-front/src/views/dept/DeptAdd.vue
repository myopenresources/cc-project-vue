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
        <a-form-item v-bind="validateInfos.deptCode" label="编号">
          <a-input v-model:value="modelRef.deptCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.deptName" label="名称">
          <a-input v-model:value="modelRef.deptName" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptSortName" label="简称">
          <a-input v-model:value="modelRef.deptSortName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.parentDeptId" label="上一级">
          <a-tree-select
            v-model:value="modelRef.parentDeptId"
            :showSearch="true"
            :dropdown-style="{ maxHeight: '300px', overflow: 'auto' }"
            :tree-data="deptOptionList"
            placeholder="请选择.."
            :replaceFields="{
              title: 'title',
              key: 'key',
              value: 'key',
            }"
            @change="selectParentDept"
          >
            <template #title="{ title }">
              {{ title }}
            </template>
          </a-tree-select>
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptLevel" label="级别">
          <a-input v-model:value="modelRef.deptLevel" placeholder="请输入..." :disabled="true" />
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptLeaderId" label="负责人">
          <a-select
            v-model:value="modelRef.deptLeaderId"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.userId"
              :key="index"
              :title="item.realName"
              v-for="(item, index) in deptLeaderOptionList"
            >
              {{ item.realName }}
            </a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptTel" label="联系电话">
          <a-input v-model:value="modelRef.deptTel" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.iconCls" label="图标">
          <a-input v-model:value="modelRef.iconCls" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptAddr" label="地址">
          <a-input v-model:value="modelRef.deptAddr" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.deptDescript" label="描述">
          <a-textarea
            v-model:value="modelRef.deptDescript"
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
import DeptType from '/@/types/dept-type';
import DeptApi from '/@/api/dept-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import telMobileValidator from '/@/validator/tel-mobile-validator';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: 'DeptAdd',
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
    const deptOptionList = shallowRef<any[]>([]);
    const deptLeaderOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<DeptType> = reactive({
      deptCode: '',
      deptName: '',
      deptSortName: '',
      deptLevel: '1',
      deptDescript: '',
      parentDeptId: undefined,
      deptLeaderId: '',
      deptTel: '',
      deptAddr: '',
      iconCls: '',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        deptCode: [
          {
            required: true,
            min: 3,
            max: 15,
            message: '编号格式不正确（格式：3-15位字符）！',
          },
        ],
        deptName: [
          {
            required: true,
            min: 1,
            max: 50,
            message: '名称格式不正确（格式：1-50位字符）！',
          },
        ],
        deptSortName: [
          {
            required: true,
            min: 1,
            max: 25,
            message: '简称格式不正确（格式：1-25位字符）!',
          },
        ],
        deptLevel: [
          {
            required: true,
            message: '请先选择上一级！',
          },
        ],
        deptDescript: [
          {
            required: false,
            min: 0,
            max: 250,
            message: '描述格式不正确（格式：0-250位字符）!',
          },
        ],
        parentDeptId: [
          {
            required: true,
            message: '请选择上一级！',
          },
        ],
        deptLeaderId: [
          {
            required: true,
            message: '请选择负责人！',
          },
        ],
        deptTel: [
          {
            required: true,
            message: '电话必填！',
          },
          { validator: telMobileValidator, trigger: 'change' },
        ],
        deptAddr: [
          {
            required: false,
            min: 0,
            max: 100,
            message: '地址格式不正确（格式：0-100位字符）！',
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
      DeptApi.initDeptAddData({
        deptId: props.add ? undefined : props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          deptLeaderOptionList.value = res.data.resultData.userList;
          deptOptionList.value = res.data.resultData.deptList;
          CommonUtil.loadFormData(res.data.resultData.deptData, modelRef);
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
        params.deptId = props.add ? undefined : props.id;
        DeptApi.saveDept(params).then((res) => {
          HttpResultUtils.successTipMsg(res, () => {
            CommonUtil.drawerClose(context, true);
          }) && HttpResultUtils.failureTipMsg(res);
        });
      });
    };

    const selectParentDept = (value, label, extra) => {
      let deptLevel: number = Number.parseInt(extra.triggerNode.dataRef.attr3);
      if (props.add) {
        modelRef.deptLevel = deptLevel + 1 + '';
      } else {
        if (value === props.id) {
          notification.warning({
            message: '提示',
            description: '上一级不能是自己！',
          });
          modelRef.deptLevel = '';
        } else {
          modelRef.deptLevel = deptLevel + 1 + '';
        }
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
      deptLeaderOptionList,
      deptOptionList,
      validateInfos,
      modelRef,
      handleCancel,
      afterVisibleChange,
      save,
      selectParentDept,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DeptAdd.less';
</style>
