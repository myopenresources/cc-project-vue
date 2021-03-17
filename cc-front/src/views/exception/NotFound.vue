<template>
  <app-exception />
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Exception from '/@/components/exception/Exception.vue';

export default defineComponent({
  name: 'NotFound',
  components: {
    AppException: Exception,
  },
});
</script>

<style lang="less" scoped>
@import './NotFound.less';
</style>
