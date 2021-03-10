<template>
  <div class="app-left-toolbar-container">
    <div class="app-left-toolbar-top">
      <div class="app-left-toolbar-top-logo" ref="toolbarLogoRef" v-show="showLogo">
        <img src="../../../assets/img/logo.png" />
      </div>

      <div class="app-left-toolbar-top-list" ref="toolbarTopListRef" v-scrollbar>
        <div class="app-left-toolbar-top-item" @click="changeMenuVisible(true)">
          <span class="iconfont icon-menu"></span>
          <span>菜单</span>
        </div>
        <div class="app-left-toolbar-top-item" @click="toHome">
          <span class="iconfont icon-home"></span>
          <span>首页</span>
        </div>
      </div>
    </div>
    <div class="app-left-toolbar-bottom" ref="toolbarBottomListRef">
      <div class="app-left-toolbar-bottom-item" @click="fullscreenToggle">
        <span
          class="iconfont"
          :class="['iconfont', fullscreenState ? 'icon-compress' : 'icon-expend']"
        ></span>
        <span>{{ fullscreenState ? '取消' : '全屏' }}</span>
      </div>
      <div class="app-left-toolbar-bottom-avatar">
        <img :src="originalUserAvatar" v-error-src="'./src/assets/img/default-avatar.png'" />
      </div>
    </div>
  </div>

  <div
    class="app-left-toolbar-menu-backdrop"
    v-show="menuVisible"
    @click="changeMenuVisible(false)"
  >
    <div class="app-left-toolbar-menu-popover" @click="stopPropagation($event)">
      <app-nav-menu :treeData="menuData" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, onUnmounted, ref, PropType, computed } from 'vue';
import Environments from '/@/common/util/env-util';
import CommonUtil from '/@/common/util/common-util';
import { useRouter } from 'vue-router';
import NavMenu from '../nav-menu/NavMenu.vue';
import UserType from '/@/types/user-type';
import MenuType from '/@/types/menu-type';
import { useStore } from 'vuex';
import UserApi from '/@/api/user-api';
import screenfull from 'screenfull';
import { notification } from 'ant-design-vue';

export default defineComponent({
  name: 'LeftToolbar',
  components: {
    AppNavMenu: NavMenu,
  },
  props: {
    userData: {
      type: Object as PropType<UserType>,
      default: () => null,
    },
    menuData: {
      type: Object as PropType<MenuType>,
      default: () => null,
    },
  },
  setup() {
    const router = useRouter();
    const store = useStore();

    const showLogo = CommonUtil.toBoolean(Environments.getEvnProp('VITE_ADMIN_SHOW_LOGO'));

    const toolbarTopListRef = ref();
    const toolbarBottomListRef = ref();
    const toolbarLogoRef = ref();

    const originalUserAvatar = ref('./src/assets/img/default-avatar.png');

    const fullscreenState = ref(false);

    /**
     * 跳转首页
     */
    const toHome = () => {
      router.push(Environments.getEvnProp('VITE_HOME_ROUTER'));
    };

    /**
     * 菜单显示
     */
    const menuVisible = computed(() => store.getters['navMenuState/getNavMenuState'] || false);

    /**
     * 改变菜单显示
     */
    const changeMenuVisible = (visible: boolean) => {
      if (visible) {
        store.dispatch('navMenuState/showNavMenu');
      } else {
        store.dispatch('navMenuState/hideNavMenu');
      }
    };

    /**
     * 全屏切换
     */
    const fullscreenToggle = () => {
      if (!screenfull.isEnabled) {
        notification.error({
          message: '提示',
          description: '您的浏览器不支持全屏！',
        });
        return false;
      }
      fullscreenState.value = !fullscreenState.value;

      screenfull.toggle();
    };

    /**
     * 计算
     */
    const calcToolbarTopListWidth = () => {
      const { height } = CommonUtil.getViewport();

      let toolbarTopListHeight = height - toolbarBottomListRef.value.offsetHeight - 15;
      if (showLogo) {
        toolbarTopListHeight -= toolbarLogoRef.value.offsetHeight;
      }

      toolbarTopListRef.value.style.height = toolbarTopListHeight + 'px';
    };

    const stopPropagation = (event: Event) => {
      event.stopPropagation();
      event.preventDefault();
    };

    const resize = () => {
      calcToolbarTopListWidth();
    };

    const init = () => {
      originalUserAvatar.value = UserApi.previewUserAvatar();
    };

    onMounted(() => {
      init();
      resize();
      window.addEventListener('resize', resize);
    });

    onUnmounted(() => {
      window.removeEventListener('resize', resize);
    });

    return {
      menuVisible,
      fullscreenState,
      toolbarTopListRef,
      toolbarBottomListRef,
      toolbarLogoRef,
      originalUserAvatar,
      showLogo,
      toHome,
      stopPropagation,
      changeMenuVisible,
      fullscreenToggle,
    };
  },
});
</script>

<style lang="less" scoped>
@import './LeftToolbar.less';
</style>
