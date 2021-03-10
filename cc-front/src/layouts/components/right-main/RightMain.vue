<template>
  <div class="app-right-main-container">
    <app-tags-view />
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
import TagsView from '../tags-view/TagsView.vue';

export default defineComponent({
  name: 'RightMain',
  components: {
    AppTagsView: TagsView,
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
