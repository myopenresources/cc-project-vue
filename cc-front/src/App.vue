<template>
  <router-view />
  <app-spinning />
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Spinning from '/@/components/spinning/Spinning.vue';

export default defineComponent({
  name: 'App',
  components: {
    appSpinning: Spinning,
  },
  setup() {},
});
</script>

<style lang="less" scoped>
</style>
