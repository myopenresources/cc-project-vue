<template>
  <div :class="fixedNavMenu ? 'app-admin-right-main-container' : 'app-desk-right-main-container'">
    <app-page-tabs />
    <div class="app-right-main-content-container">
      <router-view v-slot="{ Component }">
        <keep-alive :include="cachedViews">
          <component :is="Component" />
        </keep-alive>
      </router-view>
    </div>
  </div>
</template>

<script lang="ts">
import { useStore } from 'vuex';
import { computed, defineComponent } from 'vue';
import PageTabs from '../page-tabs/PageTabs.vue';

export default defineComponent({
  name: 'RightMain',
  components: {
    AppPageTabs: PageTabs,
  },
  props: {
    fixedNavMenu: {
      type: Boolean,
      default: false,
    },
  },
  setup() {
    const store = useStore();

    const cachedViews = computed(() => {
      return store.getters['tagsViewState/getCachedViews'];
    });

    return {
      cachedViews,
    };
  },
});
</script>

<style lang="less" scoped>
@import './RightMain.less';
</style>
