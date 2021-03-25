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
        class="app-update-pwd-form"
        :label-col="{ span: 5 }"
        :wrapper-col="{ span: 19 }"
        layout="horizontal"
      >
        <a-form-item v-bind="validateInfos.dataPermissionsName" label="数据权限名称">
          <a-input v-model:value="modelRef.dataPermissionsName" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.dataPermissionsCategory" label="数据权限分组">
          <a-select
            v-model:value="modelRef.dataPermissionsCategory"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in dataPermitCategoryOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.primaryProcessor" label="基本处理器">
          <a-select
            v-model:value="modelRef.primaryProcessor"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in dataPermitProcessorOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.secondaryProcessor" label="辅助处理器">
          <a-select
            v-model:value="modelRef.secondaryProcessor"
            :showSearch="true"
            :optionFilterProp="'title'"
            placeholder="请选择..."
          >
            <a-select-option
              :value="item.dicItemValue"
              :key="index"
              :title="item.dicItemName"
              v-for="(item, index) in dataPermitProcessorOptionList"
            >
              {{ item.dicItemName }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="validateInfos.processorPriority" label="处理器优先级">
          <a-input-number
            v-model:value="modelRef.processorPriority"
            :min="1"
            :max="99"
            placeholder="请输入..."
          />
        </a-form-item>
        <a-form-item v-bind="validateInfos.matchColumn" label="匹配列名">
          <a-input v-model:value="modelRef.matchColumn" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.accessUrl" label="匹配地址">
          <a-input v-model:value="modelRef.accessUrl" placeholder="请输入...">
            <template #suffix>
              <a-tooltip title="从访问地址功能中导入已配置的地址">
                <span
                  class="iconfont icon-plus app-cursor-pointer"
                  @click="openAccessUrlModal"
                ></span>
              </a-tooltip>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item v-bind="validateInfos.sqlId" label="匹配SQL ID">
          <a-input v-model:value="modelRef.sqlId" placeholder="请输入..." />
        </a-form-item>
        <a-form-item v-bind="validateInfos.paramKey" label="参数名">
          <a-input v-model:value="modelRef.paramKey" placeholder="请输入..." />
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
      </a-form>
    </app-def-drawer-layout>
  </a-drawer>

  <app-access-url-modal v-model:visible="accessUrlModalVisble" @selectItem="selectAccessUrl" />
</template>
<script lang="ts">
import { defineComponent, reactive, ref, shallowRef, UnwrapRef } from 'vue';
import { useForm } from '@ant-design-vue/use';
import CommonUtil from '/@/common/util/common-util';
import DataPermissionsType from '/@/types/data-permissions-type';
import DataPermissionsApi from '/@/api/data-permissions-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import AccessUrlModal from '/@/components/access-url-modal/AccessUrlModal.vue';

export default defineComponent({
  name: 'DataPermissionsAdd',
  components: {
    AppAccessUrlModal: AccessUrlModal,
  },
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
    const dataPermitCategoryOptionList = shallowRef<any[]>([]);
    const dataPermitProcessorOptionList = shallowRef<any[]>([]);
    const statusOptionList = shallowRef<any[]>([]);
    const accessUrlModalVisble = ref<boolean>(false);

    const modelRef: UnwrapRef<DataPermissionsType> = reactive({
      dataPermissionsName: '',
      accessUrl: '',
      sqlId: '',
      processorPriority: 1,
      primaryProcessor: undefined,
      secondaryProcessor: undefined,
      matchColumn: '',
      dataPermissionsCategory: undefined,
      status: undefined,
    });

    const { validate, validateInfos, resetFields } = useForm(
      modelRef,
      reactive({
        dataPermissionsName: [
          {
            required: true,
            min: 1,
            max: 64,
            message: '数据权限名称格式不正确（格式：1-64个字符）！',
          },
        ],
        dataPermissionsCategory: [
          {
            required: true,
            message: '请选择数据权限分组！',
          },
        ],
        primaryProcessor: [
          {
            required: true,
            message: '请选择基本处理器！',
          },
        ],
        secondaryProcessor: [
          {
            required: false,
          },
        ],
        processorPriority: [
          {
            required: true,
            type: 'number',
            message: '处理器优先级格式不正确（格式：1-2个数字）！',
          },
        ],
        matchColumn: [
          {
            required: true,
            min: 1,
            max: 128,
            message: '匹配列名格式不正确（格式：1-128个字符）！',
          },
        ],
        accessUrl: [
          {
            required: true,
            min: 1,
            max: 300,
            message: '地址格式不正确（格式：1-300个字符）！',
          },
        ],
        sqlId: [
          {
            required: true,
            min: 1,
            max: 255,
            message: 'SQL语句ID格式不正确（格式：1-255个字符）！',
          },
        ],
        paramKey: [
          {
            required: true,
            min: 1,
            max: 128,
            message: '参数名格式不正确（格式：0-128个字符）！',
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
      DataPermissionsApi.initDataPermissionsAddData({
        dataPermissionsId: props.id,
      }).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          dataPermitCategoryOptionList.value = res.data.resultData.DATA_PERMIT_CATEGORY;
          dataPermitProcessorOptionList.value = res.data.resultData.DATA_PERMIT_PROCESSOR;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          CommonUtil.loadFormData(res.data.resultData.dataPermissionsData, modelRef);
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
        params.dataPermissionsId = props.id;
        DataPermissionsApi.saveDataPermissions(params).then((res) => {
          HttpResultUtils.successTipMsg(res, () => {
            CommonUtil.drawerClose(context, true);
          }) && HttpResultUtils.failureTipMsg(res);
        });
      });
    };

    const openAccessUrlModal = () => {
      accessUrlModalVisble.value = true;
    };

    const selectAccessUrl = (item: any) => {
      modelRef.accessUrl = item.accessUrl;
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
      selectAccessUrl,
      accessUrlModalVisble,
      dataPermitCategoryOptionList,
      dataPermitProcessorOptionList,
      statusOptionList,
      validateInfos,
      modelRef,
      handleCancel,
      afterVisibleChange,
      save,
      openAccessUrlModal,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataPermissionsAdd.less';
</style>
