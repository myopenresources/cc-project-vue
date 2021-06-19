<template>
  <app-def-main-layout :usePadding="false">
    <template #headerBtnsTpl>
      <a-button type="primary" ghost @click="reloadDataDic">
        <app-dynamic-icon iconName="ReloadOutlined" />
        重新载入数据字典
      </a-button>
    </template>
    <div class="app-data-dic-type-container">
      <a-row class="app-data-dic-row">
        <a-col :span="7" class="app-data-dic-col app-data-dic-left-col">
          <div class="app-data-dic-type-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="字典类别" />
              </a-col>
              <a-col :xs="12" :sm="18" :md="17" :lg="16" :xl="16">
                <app-business-tool-btns
                  :buttonList="buttonList"
                  :btnSize="'small'"
                  :layoutMark="layoutMark.leftToolBtn"
                  @btnClick="btnClick"
                />
              </a-col>
            </a-row>
          </div>
          <div class="app-data-dic-type-list" v-app-scrollbar="scrollOptions">
            <a-collapse @change="dicTypeCategoryChange" :bordered="false" accordion>
              <a-collapse-panel
                v-for="category in dicCategoryOptionList"
                :key="category.dicItemCode"
              >
                <template #header>
                  <span>
                    <span class="app-margin-right10">
                      <app-dynamic-icon iconName="SvgIconDataDic" />
                    </span>
                    <span>{{ category.dicItemName }}</span>
                  </span>
                </template>

                <template v-if="dataDicTypeList && dataDicTypeList.length">
                  <a-menu :selectable="false" @click="queryDataDicItemList">
                    <a-menu-item
                      v-for="dataDicType in dataDicTypeList"
                      :key="dataDicType.dicTypeId"
                    >
                      <div class="app-flex app-flex-justify-content-between">
                        <div>
                          <a-checkbox v-model:checked="dataDicType.checked" />
                          <span class="app-data-dic-type-item-name">
                            <app-dynamic-icon :iconName="dataDicType.iconCls" />
                            {{ dataDicType.dicTypeName }}
                          </span>
                        </div>
                        <app-business-dropdown-btns
                          :buttonList="buttonList"
                          :btnSize="'small'"
                          :layoutMark="layoutMark.leftRowBtn"
                          @btnClick="btnClick($event, dataDicType.dicTypeId)"
                        />
                      </div>
                    </a-menu-item>
                  </a-menu>
                </template>

                <template v-if="!(dataDicTypeList && dataDicTypeList.length)">
                  <app-custom-empty />
                </template>
              </a-collapse-panel>
            </a-collapse>
          </div>
        </a-col>
        <a-col :span="17" class="app-data-dic-col app-data-dic-right-col">
          <div class="app-data-dic-item-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="字典明细" />
              </a-col>
              <a-col :xs="12" :sm="18" :md="17" :lg="16" :xl="16">
                <app-business-tool-btns
                  :buttonList="buttonList"
                  :btnSize="'small'"
                  :layoutMark="layoutMark.rightToolBtn"
                  @btnClick="btnClick"
                />
              </a-col>
            </a-row>
          </div>
          <div class="app-data-dic-item-table" v-app-scrollbar="scrollOptions">
            <a-form layout="inline" class="app-query-form">
              <a-form-item label="编号">
                <a-input v-model:value="queryParams.dicItemCode" placeholder="请输入..." />
              </a-form-item>
              <a-form-item label="名称">
                <a-input v-model:value="queryParams.dicItemName" placeholder="请输入..." />
              </a-form-item>
              <a-form-item>
                <a-button type="primary" @click="query(true)">
                  <app-dynamic-icon iconName="SearchOutlined" /> 查询
                </a-button>
              </a-form-item>
            </a-form>
            <a-table
              :size="'middle'"
              :dataSource="dataSource"
              :columns="columns"
              :pagination="pagination"
              :bordered="true"
              :row-key="'dicItemId'"
              :row-selection="{
                selectedRowKeys: selectedRowKeys,
                onChange: (changableRowKeys) => (selectedRowKeys = changableRowKeys),
              }"
              @change="handleTableChange"
              class="app-data-table-container"
            >
              <template #indexRender="{ index }">
                {{ index + 1 }}
              </template>

              <template #statusRender="{ record }">
                {{ dataDicPipe(statusOptionList, 'dicItemValue', record.status) }}
              </template>

              <template #actionRender="{ record }">
                <app-business-row-btns
                  :buttonList="buttonList"
                  :layoutMark="layoutMark.rightRowBtn"
                  @btnClick="btnClick($event, record.dicItemId)"
                />
              </template>
            </a-table>
          </div>
        </a-col>
      </a-row>
    </div>
  </app-def-main-layout>
  <app-data-dic-type-add
    v-model:visible="typeAddVisible"
    v-model:id="typeEditAndViewId"
    @reload="queryDataDicTypeList"
  />

  <app-data-dic-type-view v-model:visible="typeViewVisible" v-model:id="typeEditAndViewId" />

  <app-data-dic-item-add
    v-model:visible="itemAddVisible"
    v-model:id="itemEditAndViewId"
    :dicTypeId="dicCategory"
    @reload="query(true)"
  />

  <app-data-dic-item-view
    v-model:visible="itemViewVisible"
    :dicTypeId="dicCategory"
    v-model:id="itemEditAndViewId"
  />

  <app-data-dic-item-role-distribute
    v-model:visible="itemRoleDisVisible"
    v-model:id="itemEditAndViewId"
  />
</template>

<script lang="ts">
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import { layoutMarks } from '/@/platform/commons/consts/business-tool-btn-cont';
import CommonUtil from '/@/platform/commons/utils/common-util';
import { ColumnProps } from 'ant-design-vue/es/table/interface';
import { dataDicTransformPipe } from '/@/platform/pipes/data-dic-pipe';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import DataDicApi from '/@/platform/apis/data-dic-api';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import DataDicTypeAdd from './DataDicTypeAdd.vue';
import DataDicTypeView from './DataDicTypeView.vue';
import DataDicItemAdd from './DataDicItemAdd.vue';
import DataDicItemView from './DataDicItemView.vue';
import DataDicItemRoleDistribute from './DataDicItemRoleDistribute.vue';

type Key = ColumnProps['key'];

export default defineComponent({
  name: 'DataDicList',
  components: {
    AppDataDicTypeAdd: DataDicTypeAdd,
    AppDataDicTypeView: DataDicTypeView,
    AppDataDicItemAdd: DataDicItemAdd,
    AppDataDicItemView: DataDicItemView,
    AppDataDicItemRoleDistribute: DataDicItemRoleDistribute,
  },
  setup() {
    const scrollOptions = {
      overflowBehavior: {
        y: 'scroll',
        x: 'scroll',
      },
      scrollbars: {
        autoHide: 'leave',
        autoHideDelay: 200,
      },
    };

    const typeAddVisible = ref<boolean>(false);
    const typeViewVisible = ref<boolean>(false);
    const typeEditAndViewId = ref<string>('');

    const itemAddVisible = ref<boolean>(false);
    const itemViewVisible = ref<boolean>(false);
    const itemEditAndViewId = ref<string>('');
    const itemRoleDisVisible = ref<boolean>(false);

    const buttonList = shallowRef<any>([]);
    const layoutMark = layoutMarks;

    const dicCategoryOptionList = shallowRef<any>([]);
    const statusOptionList = shallowRef<any[]>([]);

    const dataSource = ref<any>([]);
    const selectedRowKeys = ref<Key[]>([]);
    const dicCategory: any = ref<any>(null);
    const dataDicTypeList = shallowRef<any[]>([]);
    const dicTypeCategory: any = ref<any>(null);

    const columns = [
      {
        title: '序号',
        key: 'index',
        dataIndex: 'index',
        width: '80px',
        slots: { customRender: 'indexRender' },
      },
      {
        title: '编号',
        dataIndex: 'dicItemCode',
        key: 'dicItemCode',
      },
      {
        title: '名称',
        dataIndex: 'dicItemName',
        key: 'dicItemName',
      },
      {
        title: '值',
        dataIndex: 'dicItemValue',
        key: 'dicItemValue',
      },
      {
        title: '上一级',
        dataIndex: 'dicItemParentName',
        key: 'dicItemParentName',
        customRender: ({ record }) => {
          return record.dicItemParentName || '无';
        },
      },
      {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        slots: { customRender: 'statusRender' },
      },
      {
        title: '操作',
        dataIndex: 'action',
        key: 'action',
        slots: { customRender: 'actionRender' },
      },
    ];

    const queryParams = CommonUtil.queryParamsInit({
      dicItemCode: '',
      dicItemName: '',
      dicTypeId: '',
    });

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      DataDicApi.initDataDicManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          statusOptionList.value = res.data.resultData.STATUS_TYPE;
          dicCategoryOptionList.value = res.data.resultData.DIC_CATEGORY_TYPE;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    /**
     *分页计算
     */
    const pagination = CommonUtil.paginationComputed(queryParams);

    //分页参数改变处理
    const handleTableChange = (pag: any) => CommonUtil.paginationChange(queryParams, pag, query);

    /**
     * 数据转换管道
     */
    const dataDicPipe = (list, key: string, value: any) => {
      const data = dataDicTransformPipe(list, key, value);
      return data ? data.dicItemName : '无';
    };

    const btnClick = (name, id) => {
      ({
        dataDicTypeAdd: () => {
          typeAddVisible.value = true;
        },
        dataDicTypeEdit: () => {
          typeAddVisible.value = true;
          typeEditAndViewId.value = id;
        },
        dataDicTypeView: () => {
          typeViewVisible.value = true;
          typeEditAndViewId.value = id;
        },
        deleteDataDic: () => {
          const checkList: string[] = [];
          for (const dataDicType of dataDicTypeList.value) {
            if (dataDicType.checked) {
              checkList.push(dataDicType.dicTypeId);
            }
          }

          if (checkList.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                DataDicApi.deleteDataDicTypeByIds({
                  ids: checkList,
                }).then((res) => {
                  HttpResultUtils.successTipMsg(res, () => {
                    resetPage();
                  }) && HttpResultUtils.failureTipMsg(res);
                });
              },
            });
          } else {
            notification.warning({
              message: '提示',
              description: '请选择要删除的内容！',
            });
          }
        },
        dataDicItemAdd: () => {
          if (dicTypeCategory.value && dicTypeCategory.value.dicItemValue) {
            itemAddVisible.value = true;
          } else {
            notification.warning({
              message: '提示',
              description: '请先选择字典类！',
            });
          }
        },
        dataDicItemEdit: () => {
          itemAddVisible.value = true;
          itemEditAndViewId.value = id;
        },
        dataDicItemView: () => {
          itemViewVisible.value = true;
          itemEditAndViewId.value = id;
        },
        deleteDicItem: () => {
          if (selectedRowKeys.value.length) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                DataDicApi.deleteDataDicItemByIds({
                  ids: selectedRowKeys.value,
                }).then((res) => {
                  HttpResultUtils.successTipMsg(res, () => {
                    query(true);
                  }) && HttpResultUtils.failureTipMsg(res);
                });
              },
            });
          } else {
            notification.warning({
              message: '提示',
              description: '请选择要删除的内容！',
            });
          }
        },
        dicItemRoleDistribute: () => {
          itemRoleDisVisible.value = true;
          itemEditAndViewId.value = id;
        },
      }[name]());
    };

    const dicTypeCategoryChange = (key) => {
      const item = dicCategoryOptionList.value.find((element) => element.dicItemCode === key);
      if (item) {
        dicTypeCategory.value = item;
        queryDataDicTypeList();
      } else {
        dicTypeCategory.value = null;
      }
    };

    const queryDataDicTypeList = () => {
      if (dicTypeCategory.value) {
        dataDicTypeList.value = [];
        DataDicApi.queryDataDicTypeList({
          dicCategory: dicTypeCategory.value.dicItemValue,
        }).then((res) => {
          if (HttpResultUtils.isSuccess(res)) {
            if (res && res.data && res.data.resultData && res.data.resultData.length) {
              dataDicTypeList.value = res.data.resultData;
            }
          } else {
            HttpResultUtils.failureTipMsg(res);
          }
        });
      }
    };

    const queryDataDicItemList = ({ key }) => {
      dicCategory.value = key;
      query(true);
    };

    const query = (btnQuery = false) => {
      selectedRowKeys.value = [];
      queryParams.value.dicTypeId = dicCategory.value;
      CommonUtil.queryDataByConditionPaging(
        btnQuery,
        queryParams,
        DataDicApi.queryDataDicItemByIdPaging,
        dataSource
      );
    };

    const resetPage = () => {
      queryParams.value.dicItemCode = '';
      queryParams.value.dicItemName = '';
      queryParams.value.dicTypeId = '';
      dicCategory.value = null;
      pagination.value.current = 1;
      queryParams.value.page = 1;
      queryParams.value.total = 0;
      dataSource.value = [];
      initPageData();
      queryDataDicTypeList();
    };

    const reloadDataDic = () => {
      Modal.confirm({
        title: '确认',
        icon: createVNode(QuestionCircleOutlined),
        content: '您确认重新载入数据字典吗？',
        onOk() {
          DataDicApi.reloadDataDic().then((res) => {
            HttpResultUtils.successTipMsg(res, () => {
              resetPage();
            }) && HttpResultUtils.failureTipMsg(res);
          });
        },
      });
    };

    onMounted(() => {
      initPageData();
    });

    return {
      dicCategory,
      typeAddVisible,
      typeViewVisible,
      typeEditAndViewId,
      scrollOptions,
      buttonList,
      layoutMark,
      columns,
      dicCategoryOptionList,
      btnClick,
      dataDicTypeList,
      queryDataDicTypeList,
      dicTypeCategoryChange,
      queryParams,
      dataSource,
      pagination,
      handleTableChange,
      selectedRowKeys,
      dataDicPipe,
      statusOptionList,
      query,
      queryDataDicItemList,
      reloadDataDic,
      itemAddVisible,
      itemViewVisible,
      itemEditAndViewId,
      itemRoleDisVisible,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DataDicList.less';
</style>
