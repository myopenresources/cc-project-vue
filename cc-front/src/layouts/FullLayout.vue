<template>
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'FullLayout',
});
</script>

<style lang="less" scoped>
@import './FullLayout.less';
</style>
