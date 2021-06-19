<template>
  <div class="app-spinning" v-show="show">
    <div class="app-spinning-backdrop"></div>
    <div class="app-spinning-container">
      <a-spin tip="加载中" size="large" />
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue';
import { Spin } from 'ant-design-vue';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'Spinning',
  components: {
    ASpin: Spin,
  },
  setup() {
    const store = useStore();
    const show = computed(() => store.getters['spinningState/getSpinningState'] || false);
    return {
      show,
    };
  },
});
</script>

<style lang="less" scoped>
@import './Spinning.less';
</style>
