<template>
  <a-modal
    :width="'560px'"
    :visible="visible"
    :destroyOnClose="true"
    :maskClosable="false"
    :keyboard="false"
    title="外观设置"
  >
    <template #footer>
      <a-button key="cancel" @click="handleCancel">取消</a-button>
    </template>

    <div class="app-individuation-container">
      <a-alert
        message="主题设置完后将保存在本地浏览器中，下次使用时自动更换保存的设置！"
        type="info"
      />
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
    </div>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
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
    const currentTheme = ref(
      CookieUtils.get(commonConst.sessionTheme) || individuationConst.defaultVal.theme
    );

    const themeMenuList = ref(IndividuationUtil.getIndividuationConfig().themeMenuList || []);

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

    return {
      handleCancel,
      currentTheme,
      themeMenuList,
      themeToggle,
    };
  },
});
</script>

<style lang="less" scoped>
@import './IndividuationModal.less';
</style>
