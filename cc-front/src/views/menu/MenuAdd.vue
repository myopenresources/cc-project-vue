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
        <a-form-item v-bind="validateInfos.menuCode" label="编号">
          <a-input v-model:value="modelRef.menuCode" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.menuName" label="名称">
          <a-input v-model:value="modelRef.menuName" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.sortNum" label="序号">
          <a-input-number
            v-model:value="modelRef.sortNum"
            :min="1"
            :max="99"
            placeholder="请输入..."
          />
        </a-form-item>

        <a-form-item v-bind="validateInfos.iconCls" label="图标">
          <a-input v-model:value="modelRef.iconCls" placeholder="请输入..." />
        </a-form-item>

        <a-form-item v-bind="validateInfos.parentMenuId" label="上一级">
          <a-tree-select
            v-model:value="modelRef.parentMenuId"
            :showSearch="true"
            :dropdown-style="{ maxHeight: '300px', overflow: 'auto' }"
            :tree-data="menuOptionList"
            placeholder="请选择.."
            :replaceFields="{
              title: 'title',
              key: 'key',
              value: 'key',
            }"
            @change="selectParentMenu"
          >
            <template #title="{ title }">
              {{ title }}
            </template>
          </a-tree-select>
        </a-form-item>

        <a-form-item v-bind="validateInfos.menuLevel" label="级别">
          <a-input v-model:value="modelRef.menuLevel" placeholder="请输入..." :disabled="true" />
        </a-form-item>

        <a-form-item v-bind="validateInfos.accessUrl" label="访问地址">
          <a-input v-model:value="modelRef.accessUrl" placeholder="请输入..." />
        </a-form-item>
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, reactive, shallowRef, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/common/util/common-util';
import MenuType from '/@/types/menu-type';
import MenuApi from '/@/api/menu-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: 'MenuAdd',
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
    const menuOptionList = shallowRef<any[]>([]);

    const modelRef: UnwrapRef<MenuType> = reactive({
      menuCode: '',
      menuName: '',
      iconCls: '',
      accessUrl: '',
      parentMenuId: undefined,
      sortNum: '1',
      menuLevel: '1',
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        menuCode: [
          {
            required: true,
            min: 3,
            max: 15,
            message: '编号格式不正确（格式：3-15位字符）！',
          },
        ],
        menuName: [
          {
            required: true,
            min: 1,
            max: 50,
            message: '名称格式不正确（格式：1-50位字符）！',
          },
        ],
        menuLevel: [
          {
            required: true,
            message: '请先选择上一级！',
          },
        ],
        parentMenuId: [
          {
            required: true,
            message: '请选择上一级！',
          },
        ],
        sortNum: [
          {
            required: true,
            message: '序号格式不正确（格式：1-2个数字）！',
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
        accessUrl: [
          {
            required: false,
            min: 0,
            max: 150,
            message: '访问地址格式不正确（格式：0-150个字符）！',
          },
        ],
      })
    );

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      MenuApi.initMenuAddData({
        menuId: props.add ? undefined : props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          menuOptionList.value = res.data.resultData.menuList;
          CommonUtil.loadFormData(res.data.resultData.menuData, modelRef);
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
        params.menuId = props.add ? undefined : props.id;
        MenuApi.saveMenu(params).then((res) => {
          HttpResultUtils.successTipMsg(res, () => {
            CommonUtil.drawerClose(context, true);
          }) && HttpResultUtils.failureTipMsg(res);
        });
      });
    };

    const selectParentMenu = (value, label, extra) => {
      let menuLevel: number = Number.parseInt(extra.triggerNode.dataRef.attr3);
      if (props.add) {
        modelRef.menuLevel = menuLevel + 1 + '';
      } else {
        if (value === props.id) {
          notification.warning({
            message: '提示',
            description: '上一级不能是自己！',
          });
          modelRef.menuLevel = '';
        } else {
          modelRef.menuLevel = menuLevel + 1 + '';
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
      menuOptionList,
      validateInfos,
      modelRef,
      handleCancel,
      afterVisibleChange,
      save,
      selectParentMenu,
    };
  },
});
</script>

<style lang="less" scoped>
@import './MenuAdd.less';
</style>
