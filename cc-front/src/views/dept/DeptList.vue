<template>
  <app-def-main-layout :usePadding="false">
    <template #headerBtnsTpl>
      <app-business-tool-btns :buttonList="buttonList" @btnClick="btnClick" />
    </template>

    <div class="app-dept-container">
      <a-row class="app-dept-row">
        <a-col :span="7" class="app-dept-col app-dept-left-col">
          <div class="app-dept-tree-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="部门结构" />
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
          <div class="app-dept-tree-container">
            <div class="app-dept-tree-search">
              <a-input-search v-model:value="searchValue" placeholder="输入部门名称..." />
            </div>
            <div class="app-dept-tree" v-app-scrollbar="scrollOptions">
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
                    class="app-dept-tree-node"
                    v-if="title.indexOf(searchValue) > -1"
                    @click="deptView(key)"
                  >
                    <span :class="['iconfont', icon]"></span>
                    {{ title.substr(0, title.indexOf(searchValue)) }}
                    <span class="app-dept-tree-search-node">
                      {{ searchValue }}
                    </span>
                    {{ title.substr(title.indexOf(searchValue) + searchValue.length) }}
                  </span>
                  <span class="app-dept-tree-node" v-else @click="deptView($event)">
                    <span :class="['iconfont', icon]"></span> {{ title }}</span
                  >
                </template>
              </a-tree>
            </div>
          </div>
        </a-col>
        <a-col :span="17" class="app-dept-col app-dept-right-col">
          <div class="app-dept-info-title">
            <a-row>
              <a-col :xs="12" :sm="6" :md="7" :lg="8" :xl="8">
                <app-line-title title="部门信息" />
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
          <div class="app-dept-info" v-app-scrollbar="scrollOptions">
            <div v-show="deptData" class="app-data-view-container">
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">编号：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptCode || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">名称：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptName || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">简称：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptSortName || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">级别：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptLevel || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">负责人：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptLeader || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">图标：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  <span :class="['iconfont', deptData?.iconCls]"></span>
                  {{ deptData?.iconCls || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">地址：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptAddr || '无' }}
                </a-col>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">联系电话：</a-col>
                <a-col :xs="24" :sm="24" :md="7" :lg="9" :xl="10">
                  {{ deptData?.deptTel || '无' }}
                </a-col>
              </a-row>
              <a-row>
                <a-col :xs="24" :sm="24" :md="5" :lg="3" :xl="2">简称：</a-col>
                <a-col :xs="24" :sm="24" :md="18" :lg="21" :xl="22">
                  {{ deptData?.deptDescript || '无' }}
                </a-col>
              </a-row>
            </div>
            <div v-show="!deptData" class="app-dept-info-empty">
              <app-custom-empty :description="'请点击查看部门信息！'" />
            </div>
          </div>
        </a-col>
      </a-row>
    </div>
  </app-def-main-layout>
  <app-dept-add
    v-model:visible="addVisible"
    :id="addAndEditId"
    :add="isAdd"
    @reload="initPageData()"
  />

  <app-dept-users v-model:visible="usersVisible" :id="addAndEditId" />
</template>

<script lang="ts">
import { Modal, notification } from 'ant-design-vue';
import { createVNode, defineComponent, onMounted, ref, shallowRef } from 'vue';
import DeptApi from '/@/api/dept-api';
import { layoutMarks } from '/@/common/const/business-tool-btn-cont';
import HttpResultUtils from '/@/common/util/http-result-utils';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import DeptAdd from './DeptAdd.vue';
import DeptUsers from './DeptUsers.vue';

export default defineComponent({
  name: 'DeptList',
  components: {
    AppDeptAdd: DeptAdd,
    AppDeptUsers: DeptUsers,
  },
  setup() {
    const addVisible = ref<boolean>(false);
    const addAndEditId = ref<string>('');
    const isAdd = ref<boolean>(false);
    const usersVisible = ref<boolean>(false);

    const buttonList = shallowRef<any>([]);
    const layoutMark = layoutMarks;

    const searchValue = ref<string>('');

    const treeData = shallowRef<any>([]);
    const checkedKeys = ref<any>([]);
    const deptData = shallowRef<any>(undefined);

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
        deptAdd: () => {
          addVisible.value = true;
          isAdd.value = true;
        },
        deptEdit: () => {
          if (addAndEditId.value && addAndEditId.value.length) {
            addVisible.value = true;
            isAdd.value = false;
          } else {
            notification.warning({
              message: '提示',
              description: '请选择部门！',
            });
          }
        },
        deptUsers: () => {
          if (addAndEditId.value && addAndEditId.value.length) {
            usersVisible.value = true;
          } else {
            notification.warning({
              message: '提示',
              description: '请选择部门！',
            });
          }
        },
        deleteDept: () => {
          const ids = checkedKeys.value.checked;
          if (ids) {
            Modal.confirm({
              title: '确认',
              icon: createVNode(QuestionCircleOutlined),
              content: '您确认要删除吗？',
              onOk() {
                DeptApi.deleteDeptByIds({
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

    const deptView = (id: any) => {
      addAndEditId.value = id;
      DeptApi.queryDeptByDeptId(id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          deptData.value = res.data.resultData.deptBean;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
    };

    const initPageData = () => {
      DeptApi.initDeptManageData({}).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          buttonList.value = res.data.resultData.buttonList;
          if (res.data.resultData.deptList && res.data.resultData.deptList.length) {
            res.data.resultData.deptList[0].expanded = true;
            treeData.value = res.data.resultData.deptList;
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
      usersVisible,
      isAdd,
      addAndEditId,
      addVisible,
      scrollOptions,
      treeData,
      checkedKeys,
      deptData,
      searchValue,
      layoutMark,
      buttonList,
      btnClick,
      deptView,
      initPageData,
    };
  },
});
</script>

<style lang="less" scoped>
@import './DeptList.less';
</style>
