<template>
  <div class="app-page-tabs-container">
    <app-horizontal-scroll-pane class="app-page-tabs-wrapper" ref="scrollPaneRef">
      <router-link
        :ref="setTagViewsRef"
        class="app-page-tabs-item"
        :to="tag"
        v-for="tag in Array.from(visitedViews)"
        :key="tag.path"
      >
        <span class="iconfont icon-file"></span>
        <span>{{ tag.title }}</span>
        <span class="iconfont icon-close" @click.prevent.stop="closeSelectedTag(tag)"></span>
      </router-link>
    </app-horizontal-scroll-pane>

    <div class="app-page-tabs-btns">
      <div class="app-page-tabs-move-to" @click="moveToLeft">
        <span class="iconfont icon-left"></span>
      </div>

      <div class="app-page-tabs-move-to" @click="moveToRight">
        <span class="iconfont icon-right"></span>
      </div>

      <div class="app-page-tabs-more">
        <a-dropdown :placement="'bottomLeft'" :trigger="['click']">
          <template #overlay>
            <a-menu>
              <a-menu-item key="closeSelectedTag" @click="closeSelectedTag(selectedTag.value)">
                关闭当前
              </a-menu-item>
              <a-menu-item key="closeOthersTags" @click="closeOthersTags"> 关闭其他 </a-menu-item>
              <a-menu-item key="closeAllTags" @click="closeAllTags"> 关闭所有 </a-menu-item>
            </a-menu>
          </template>
          <div class="app-page-tabs-more-btn">
            更多
            <span class="iconfont icon-more-circle"></span>
          </div>
        </a-dropdown>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useStore } from 'vuex';
import { defineComponent, reactive, ref, computed, nextTick, onUnmounted } from 'vue';
import HorizontalScrollPane from '/@/components/scroll-pane/HorizontalScrollPane.vue';
import {
  useRoute,
  onBeforeRouteUpdate,
  RouteLocationNormalizedLoaded,
  useRouter,
  RouteLocationNormalized,
} from 'vue-router';
import Environments from '/@/common/util/env-util';

export default defineComponent({
  name: 'PageTabs',
  components: {
    AppHorizontalScrollPane: HorizontalScrollPane,
  },
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    const selectedTag = reactive<any>({});

    const tagViewsRef: any[] = [];
    const scrollPaneRef = ref<any>(null);

    /**
     * 列表
     */
    const visitedViews = computed(() => {
      return store.getters['tagsViewState/getVisitedViews'];
    });

    const setTagViewsRef = (el: any) => {
      tagViewsRef.push(el);
    };

    /**
     * 是否激活
     */
    const isActive = (view: RouteLocationNormalizedLoaded | RouteLocationNormalized) => {
      return view.path === selectedTag.value.path;
    };

    /**
     * 添加
     */
    const addViewTag = (currentRoute: RouteLocationNormalizedLoaded | RouteLocationNormalized) => {
      if (currentRoute && currentRoute.name) {
        selectedTag.value = currentRoute;
        store.dispatch('tagsViewState/addVisitedView', currentRoute);
        moveToCurrentTag();
      }
    };

    /**
     * 移动
     */
    const moveToCurrentTag = () => {
      const tags = tagViewsRef;
      nextTick(() => {
        for (const item of tags) {
          if (item && item.to.path === selectedTag.value.path) {
            scrollPaneRef.value.moveToTarget(item.$el);
            break;
          }
        }
      });
    };

    /**
     * 关闭选择
     */
    const closeSelectedTag = (view: RouteLocationNormalizedLoaded | RouteLocationNormalized) => {
      if (
        visitedViews.value.length == 1 &&
        view.path === Environments.getEvnProp('VITE_HOME_ROUTER')
      ) {
        return;
      }
      store.dispatch('tagsViewState/delVisitedView', view).then((views) => {
        if (isActive(view)) {
          const latestView = views.slice(-1)[0];
          if (latestView) {
            router.push(latestView);
          } else {
            router.push(Environments.getEvnProp('VITE_HOME_ROUTER'));
          }
        }
        scrollPaneRef.value.resize();
      });
    };

    /**
     * 关闭其它
     */
    const closeOthersTags = () => {
      router.push(selectedTag.value);
      store.dispatch('tagsViewState/delOthersViews', selectedTag.value).then(() => {
        moveToCurrentTag();
      });
    };

    /**
     * 关闭所有
     */
    const closeAllTags = () => {
      if (selectedTag.value.path === Environments.getEvnProp('VITE_HOME_ROUTER')) {
        return;
      }
      store.dispatch('tagsViewState/delAllViews');
      router.push(Environments.getEvnProp('VITE_HOME_ROUTER'));
      moveToCurrentTag();
    };

    /**
     * 左移
     */
    const moveToLeft = () => {
      scrollPaneRef.value.moveToLeft();
    };

    /**
     * 右移
     */
    const moveToRight = () => {
      scrollPaneRef.value.moveToRight();
    };

    /**
     * 初始化
     */
    (() => {
      addViewTag(route);
      onBeforeRouteUpdate((to: RouteLocationNormalized) => {
        nextTick(() => {
          addViewTag(to);
        });
      });
    })();

    onUnmounted(() => {
      store.dispatch('tagsViewState/delAllViews');
    });

    return {
      setTagViewsRef,
      scrollPaneRef,
      selectedTag,
      visitedViews,
      closeSelectedTag,
      closeOthersTags,
      closeAllTags,
      moveToLeft,
      moveToRight,
    };
  },
});
</script>

<style lang="less" scoped>
@import './PageTabs.less';
</style>

