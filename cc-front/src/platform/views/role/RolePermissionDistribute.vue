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
    <app-def-drawer-layout :title="'权限配置'" @close="handleCancel">
      <template #headerBtnsTpl>
        <a-button type="primary" @click="save">
          <app-dynamic-icon iconName="SaveOutlined" />保存
        </a-button>
      </template>

      <div class="app-role-permission-container">
        <a-tabs>
          <a-tab-pane key="1" tab="菜单">
            <a-tree
              :tree-data="menuTreeData"
              :blockNode="true"
              :showIcon="false"
              :checkStrictly="true"
              :checkable="true"
              v-model:checkedKeys="roleMenuData"
            >
              <template #title="node">
                <app-dynamic-icon :iconName="node.icon" />
                {{ node.title }}
              </template>
            </a-tree>
          </a-tab-pane>
          <a-tab-pane key="2" tab="按钮与访问地址">
            <a-menu :mode="'inline'" :inlineIndent="2">
              <a-sub-menu v-for="(category, i) in accessUrlAndButtonData" :key="i">
                <template #title>
                  <span>
                    <app-dynamic-icon iconName="TagsOutlined" />
                    <span>{{ category.label }}</span>
                  </span>
                </template>
                <template v-if="category.children && category.children.length">
                  <a-menu-item v-for="(item, j) in category.children" :key="j">
                    <div class="app-flex">
                      <a-checkbox v-model:checked="item.checked" />
                      <span class="app-btn-accessurl-item-name">
                        <app-dynamic-icon :iconName="item.attr1 === 'TYPE_ACCESS_URL' ? 'ApiOutlined' : 'BorderOutlined'" />
                        {{ item.label }}
                      </span>
                    </div>
                  </a-menu-item>
                </template>

                <template v-if="!(category.children && category.children.length)">
                  <app-custom-empty />
                </template>
              </a-sub-menu>
            </a-menu>
          </a-tab-pane>
          <a-tab-pane key="3" tab="数据权限">
            <a-menu :mode="'inline'" :inlineIndent="2" class="app-data-permissions-ul">
              <a-sub-menu v-for="(dataPpermissionsCategory, i) in dataPermissionsData" :key="i">
                <template #title>
                  <span>
                    <app-dynamic-icon iconName="TagsOutlined" />
                    <span>{{ dataPpermissionsCategory.label }}</span>
                  </span>
                </template>
                <template
                  v-if="
                    dataPpermissionsCategory.children && dataPpermissionsCategory.children.length
                  "
                >
                  <a-menu-item v-for="(item, j) in dataPpermissionsCategory.children" :key="j">
                    <div class="app-flex app-data-permissions-title">
                      <a-checkbox v-model:checked="item.checked" />
                      <span class="app-btn-accessurl-item-name">
                        <app-dynamic-icon iconName="SvgIconDataPermissions" />
                        {{ item.label }}
                      </span>
                    </div>
                    <div
                      class="app-flex app-flex-justify-content-between app-data-permissions-processor"
                    >
                      <div>基本处理器：{{ item.attr2 || '无' }}</div>
                      <div>辅助处理器：{{ item.attr3 || '无' }}</div>
                    </div>
                  </a-menu-item>
                </template>
                <template
                  v-if="
                    !(dataPpermissionsCategory.children && dataPpermissionsCategory.children.length)
                  "
                >
                  <app-custom-empty />
                </template>
              </a-sub-menu>
            </a-menu>
          </a-tab-pane>
        </a-tabs>
      </div>
    </app-def-drawer-layout>
  </a-drawer>
</template>
<script lang="ts">
import { defineComponent, ref } from 'vue';
import CommonUtil from '/@/platform/commons/utils/common-util';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import RoleApi from '/@/platform/apis/role-api';

export default defineComponent({
  name: 'RolePermissionDistribute',
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
    const menuTreeData = ref<any[]>([]);
    const roleMenuData = ref<any[]>([]);
    const accessUrlAndButtonData = ref<any[]>([]);
    const dataPermissionsData = ref<any[]>([]);

    /**
     * 初始化页面数据
     */
    const initPageData = () => {
      RoleApi.queryRolePermissionDistribute(props.id).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          menuTreeData.value = res.data.resultData.menuTreeData;
          roleMenuData.value = res.data.resultData.roleMenuData;
          accessUrlAndButtonData.value = res.data.resultData.accessUrlAndButtonData;
          dataPermissionsData.value = res.data.resultData.dataPermissionsData;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
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
      menuTreeData.value = [];
      roleMenuData.value = [];
      accessUrlAndButtonData.value = [];
      dataPermissionsData.value = [];
      CommonUtil.drawerAddOrViewInit(visible, initPageData);
    };

    /**
     * 保存
     */
    const save = () => {
      const checkeds: any = roleMenuData.value;
      const menuIds: string[] = checkeds.checked;

      const btnAndAccessUrlIds: { id: string; type: string }[] = [];
      accessUrlAndButtonData.value.forEach((category: any) => {
        if (category.children && category.children.length) {
          category.children.forEach((item: any) => {
            if (item.checked) {
              btnAndAccessUrlIds.push({
                id: item.id,
                type: item.attr1,
              });
            }
          });
        }
      });

      const dataPermissionsIds: { id: string }[] = [];
      dataPermissionsData.value.forEach((category: any) => {
        if (category.children && category.children.length) {
          category.children.forEach((item: any) => {
            if (item.checked) {
              dataPermissionsIds.push({
                id: item.id,
              });
            }
          });
        }
      });


      RoleApi.saveRolePermissionDistribute({
        roleId: props.id,
        menuIds: menuIds,
        btnAndAccessUrlIds: btnAndAccessUrlIds,
        dataPermissionsIds: dataPermissionsIds,
      }).then((res) => {
        HttpResultUtils.successTipMsg(res, () => {
          CommonUtil.drawerClose(context);
        }) && HttpResultUtils.failureTipMsg(res);
      });
    };

    return {
      menuTreeData,
      roleMenuData,
      accessUrlAndButtonData,
      dataPermissionsData,
      handleCancel,
      afterVisibleChange,
      save,
    };
  },
});
</script>

<style lang="less" scoped>
@import './RolePermissionDistribute.less';
</style>
