<template>
  <a-config-provider :locale="locale">
    <template #renderEmpty>
      <app-custom-empty />
    </template>

    <router-view />
    <app-spinning />
  </a-config-provider>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Spinning from '/@/components/spinning/Spinning.vue';
import { ConfigProvider } from 'ant-design-vue';
import zhCN from 'ant-design-vue/es/locale/zh_CN';

import 'moment/dist/locale/zh-cn';
import moment from 'moment';


export default defineComponent({
  name: 'App',
  components: {
    AConfigProvider: ConfigProvider,
    AppSpinning: Spinning
  },
  setup() {
    moment.locale('zhCN');
    const locale = zhCN;
    return {
      locale,
    };
  },
});
</script>

<style lang="less" scoped>
</style>
