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
import { onMounted, defineComponent } from 'vue';
import Spinning from '/@/platform/components/spinning/Spinning.vue';
import { ConfigProvider } from 'ant-design-vue';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import registerHttpInterceptor from '/@/shared/interceptors/http-interceptor';

import 'moment/dist/locale/zh-cn';
import moment from 'moment';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import IndividuationUtil from '/@/platform/commons/utils/individuation-util';

export default defineComponent({
  name: 'App',
  components: {
    AConfigProvider: ConfigProvider,
    AppSpinning: Spinning,
  },
  setup() {
    registerHttpInterceptor(useRouter(), useStore());

    moment.locale('zhCN');
    const locale = zhCN;

    onMounted(() => {
      IndividuationUtil.initAppTheme();
    });

    return {
      locale,
    };
  },
});
</script>

<style lang="less" scoped>
</style>
