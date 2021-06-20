<template>
  <a-modal
    :width="'560px'"
    :visible="visible"
    :destroyOnClose="true"
    :maskClosable="false"
    :keyboard="false"
    @cancel="handleCancel"
    title="外观设置"
  >
    <template #footer>
      <a-button key="cancel" @click="handleCancel">取消</a-button>
    </template>

    <div class="app-individuation-container">
      <a-alert
        message="布局与主题设置完后将保存在本地浏览器中，下次使用时自动更换保存的设置！"
        type="info"
      />

      <app-line-title title="主题设置" class="app-margin-top15" />
      <div class="app-flex app-margin-top10">
        <div
          class="app-individuation-color-item"
          v-for="item in themeMenuList"
          @click="themeToggle(item.key)"
          :key="item.key"
        >
          <a-tag :color="item.color" class="app-individuation-color-tag">
            <app-dynamic-icon iconName="CheckOutlined" v-if="currentTheme === item.key" />
          </a-tag>
          <span>{{ item.label }}</span>
        </div>
      </div>

      <app-line-title title="布局设置" class="app-margin-top15" />

      <a-row :gutter="12" class="app-individuation-layout-row app-margin-top10">
        <a-col :span="12" :key="item.key" v-for="item in layoutList">
          <a-card
            class="app-individuation-layout-card"
            size="small"
            @click="layoutToggle(item.key)"
          >
            <div class="app-individuation-layout-checked" v-if="currentLayout === item.key">
              <app-dynamic-icon iconName="SvgIconFullCheck" />
            </div>
            <img class="app-individuation-layout-item" :src="item.img" :alt="item.label" />
          </a-card>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { useStore } from 'vuex';
import { commonConst } from '/@/platform/commons/consts/common-const';
import { individuationConst } from '/@/platform/commons/consts/individuation-const';
import CookieUtils from '/@/platform/commons/utils/cookie-utils';
import IndividuationUtil from '/@/platform/commons/utils/individuation-util';

export default defineComponent({
  name: 'IndividuationModal',
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['update:visible'],
  setup(props, context) {
    const store = useStore();
    const currentTheme = ref(
      CookieUtils.get(commonConst.sessionTheme) || individuationConst.defaultVal.theme
    );

    const currentLayout = ref(
      CookieUtils.get(commonConst.sessionLayout) || individuationConst.defaultVal.layout
    );

    const themeMenuList = ref(IndividuationUtil.getIndividuationConfig().themeMenuList || []);
    const layoutList = ref(IndividuationUtil.getIndividuationConfig().layoutList || []);

    /**
     * 取消
     */
    const handleCancel = () => {
      context.emit('update:visible', false);
    };

    const themeToggle = (key: string) => {
      IndividuationUtil.themeChange(key, true, () => {
        currentTheme.value = key;
        CookieUtils.set(commonConst.sessionTheme, key, 365);
      });
    };

    const layoutToggle = (key: string) => {
      const layout = IndividuationUtil.getLayoutConfigByKey(key);
      layout && IndividuationUtil.setCssVariable(layout.cssVariable);
      currentLayout.value = key;
      CookieUtils.set(commonConst.sessionLayout, key, 365);
      store.dispatch('appLayoutState/toggleLayout', key);
    };

    return {
      handleCancel,
      currentTheme,
      currentLayout,
      themeMenuList,
      layoutList,
      themeToggle,
      layoutToggle,
    };
  },
});
</script>

<style lang="less" scoped>
@import './IndividuationModal.less';
</style>
