<template>
  <a-config-provider>
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
import CustomEmpty from '/@/components/custom-empty/CustomEmpty.vue';

export default defineComponent({
  name: 'App',
  components: {
    AConfigProvider: ConfigProvider,
    AppSpinning: Spinning,
    AppCustomEmpty: CustomEmpty,
  },
  setup() {},
});
</script>

<style lang="less" scoped>

</style>
