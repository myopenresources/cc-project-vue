<template>
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'AppFullLayout',
});
</script>

<style lang="less" scoped>
@import './AppFullLayout.less';
</style>
