<template>
  <app-def-main-layout :usePadding="false">
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>

    <div class="app-menu-container">
      <a-row class="app-menu-row">
        <a-col :span="7" class="app-menu-col app-menu-left-col">
          <div class="app-menu-tree-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="菜单结构" />
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
          <div class="app-menu-tree-container">
            <div class="app-menu-tree-search">
              <a-input-search v-model:value="searchValue" placeholder="输入菜单名称..." />
            </div>
            <div class="app-menu-tree" v-app-scrollbar="scrollOptions">
              <a-tree
                :tree-data="treeData"
                :blockNode="true"
                :showIcon="false"
                :checkStrictly="true"
                :checkable="true"
                v-model:checkedKeys="checkedKeys"
              >
                <template #title="{ title, icon, key }">
                  <span
                    class="app-menu-tree-node"
                    v-if="title.indexOf(searchValue) > -1"
                    @click="menuView(key)"
                  >
                    <app-dynamic-icon :iconName="icon" />
                    {{ title.substr(0, title.indexOf(searchValue)) }}
                    <span class="app-menu-tree-search-node">
                      {{ searchValue }}
                    </span>
                    {{ title.substr(title.indexOf(searchValue) + searchValue.length) }}
                  </span>
                  <span class="app-menu-tree-node" v-else @click="menuView($event)">
                    <app-dynamic-icon :iconName="icon" /> {{ title }}</span
                  >
                </template>
              </a-tree>
            </div>
          </div>
        </a-col>
        <a-col :span="17" class="app-menu-col app-menu-right-col">
          <div class="app-menu-info-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="菜单信息" />
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
          <div class="app-menu-info" v-app-scrollbar="scrollOptions">
            <div v-show="menuData" class="app-data-view-container">
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">编号：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ menuData?.menuCode || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">名称：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ menuData?.menuName || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">排序：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ menuData?.sortNum || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">图标：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  <app-dynamic-icon :iconName="menuData?.iconCls" />
                  {{ menuData?.iconCls || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">级别：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ menuData?.menuLevel || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">访问地址：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ menuData?.accessUrl || '无' }}
                </a-col>
              </a-row>
            </div>
            <div v-show="!menuData" class="app-menu-info-empty">
              <app-custom-empty :description="'请点击查看菜单信息！'" />
            </div>
          </div>
        </a-col>
      </a-row>
    </div>
  </app-def-main-layout>

  <app-menu-add
    v-model:visible="addVisible"
    :id="addAndEditId"
    :add="isAdd"
    @reload="initPageData()"
  />
</template>

<script lang="ts">
import { Modal, notification } from 'ant-design-vue';
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import MenuApi from '/@/platform/apis/menu-api';
import { layoutMarks } from '/@/platform/commons/consts/business-tool-btn-cont';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import MenuAdd from './MenuAdd.vue';

export default defineComponent({
  name: 'MenuList',
  components: {
    AppMenuAdd: MenuAdd,
  },
  setup() {
    const addVisible = ref<boolean>(false);
    const addAndEditId = ref<string>('');
    const isAdd = ref<boolean>(false);

    const buttonList = shallowRef<any>([]);
    const layoutMark = layoutMarks;

    const searchValue = ref<string>('');

    const treeData = shallowRef<any>([]);
    const checkedKeys = ref<any>([]);
    const menuData = shallowRef<any>(undefined);

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

    const btnClick = (name) => {
      ({
        menuAdd: () => {
          addVisible.value = true;
          isAdd.value = true;
        },
        menuEdit: () => {
          if (addAndEditId.value && addAndEditId.value.length) {
            addVisible.value = true;
            isAdd.value = false;
          } else {
            notification.warning({
              message: '提示',
              description: '请选择菜单！',
            });
          }
        },
        deleteMenu: () => {
          const ids = checkedKeys.value.checked;
          if (ids) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                MenuApi.deleteMenuByIds({
                  ids: ids,
                }).then((res) => {
                  HttpResultUtils.successTipMsg(res, () => {
                    initPageData();
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
      }[name]());
    };

    const menuView = (id: any) => {
      addAndEditId.value = id;
      MenuApi.queryMenuByMenuId(id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          menuData.value = res.data.resultData.menuBean;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    const initPageData = () => {
      MenuApi.initMenuManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          if (res.data.resultData.menuList && res.data.resultData.menuList.length) {
            res.data.resultData.menuList[0].expanded = true;
            treeData.value = res.data.resultData.menuList;
          } else {
            treeData.value = [];
          }
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    onMounted(() => {
      initPageData();
    });

    return {
      isAdd,
      addAndEditId,
      addVisible,
      scrollOptions,
      treeData,
      checkedKeys,
      menuData,
      searchValue,
      layoutMark,
      buttonList,
      btnClick,
      menuView,
      initPageData,
    };
  },
});
</script>

<style lang="less" scoped>
@import './MenuList.less';
</style>

