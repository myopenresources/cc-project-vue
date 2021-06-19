<template>
  <div class="app-left-toolbar-container">
    <div class="app-left-toolbar-top">
      <div class="app-left-toolbar-top-logo" ref="toolbarLogoRef" v-show="showLogo">
        <img src="../../../../assets/platform/img/logo.png" />
      </div>

      <div class="app-left-toolbar-top-list" ref="toolbarTopListRef" v-app-scrollbar>
        <div class="app-left-toolbar-top-item" @click="changeMenuVisible(true)">
          <app-dynamic-icon iconName="MenuOutlined" :iconProps="{ class: 'icon' }" />
          <span>菜单</span>
        </div>
        <div class="app-left-toolbar-top-item" @click="toHome">
          <app-dynamic-icon iconName="HomeOutlined" :iconProps="{ class: 'icon' }" />
          <span>首页</span>
        </div>
      </div>
    </div>
    <div class="app-left-toolbar-bottom" ref="toolbarBottomListRef">
      <div class="app-left-toolbar-bottom-item" @click="individuationVisible = true">
        <app-dynamic-icon iconName="SvgIconTheme" :iconProps="{ class: 'icon' }" />
        <span>外观</span>
      </div>

      <div class="app-left-toolbar-bottom-item" @click="fullscreenToggle">
        <app-dynamic-icon
          :iconName="fullscreenState ? 'FullscreenExitOutlined' : 'FullscreenOutlined'"
          :iconProps="{ class: 'icon' }"
        />
        <span>{{ fullscreenState ? '取消' : '全屏' }}</span>
      </div>

      <div class="app-left-toolbar-bottom-avatar" @click="userDropdownVisible = true">
        <img :src="originalUserAvatar" v-app-error-src="defaultAvatar" />
      </div>
    </div>
  </div>

  <div
    class="app-left-toolbar-menu-backdrop"
    v-show="menuVisible"
    @click="changeMenuVisible(false)"
  >
    <div class="app-left-toolbar-menu-popover" @click="stopPropagation($event)">
      <div class="app-left-toolbar-menu-container">
        <div class="app-left-toolbar-menu-header">
          <div class="app-left-toolbar-menu-title">
            <app-dynamic-icon iconName="MenuOutlined" />
            <span>菜单</span>
          </div>
          <div class="app-left-toolbar-menu-close" @click="changeMenuVisible(false)">
            <app-dynamic-icon iconName="CloseOutlined" />
          </div>
        </div>
        <div class="app-left-toolbar-menu-body">
          <app-nav-menu :treeData="menuData" />
        </div>
      </div>
    </div>
  </div>

  <div
    class="app-left-toolbar-user-dropdown-backdrop"
    v-show="userDropdownVisible"
    @click="userDropdownVisible = false"
  >
    <div class="app-left-toolbar-user-dropdown-popover" @click="stopPropagation($event)">
      <a-menu :selectable="false">
        <a-menu-item
          @click="
            imgCropperVisible = true;
            userDropdownVisible = false;
          "
        >
          <a href="javascript:;">
            <app-dynamic-icon iconName="CloudUploadOutlined" />
            头像上传
          </a>
        </a-menu-item>
        <a-menu-item
          @click="
            updatePwdVisible = true;
            userDropdownVisible = false;
          "
        >
          <a href="javascript:;">
            <app-dynamic-icon iconName="LockOutlined" />
            修改密码
          </a>
        </a-menu-item>
        <a-menu-item @click="exitSys">
          <a href="javascript:;">
            <app-dynamic-icon iconName="LoginOutlined" />
            退出系统
          </a>
        </a-menu-item>
      </a-menu>
    </div>
  </div>

  <app-update-pwd v-model:visible="updatePwdVisible" />
  <app-img-cropper-modal
    v-model:visible="imgCropperVisible"
    :title="'头像上传'"
    :previewTitle="'头像预览'"
    :originalImgLabel="'原头像'"
    :originalImgSrc="originalUserAvatar"
    @imgUpload="imgUpload"
  />
  <app-individuation-modal v-if="individuationVisible" v-model:visible="individuationVisible" />
</template>

<script lang="ts">
import { defineComponent, onMounted, onUnmounted, ref, PropType, computed, createVNode } from 'vue';
import Environments from '/@/platform/commons/utils/env-util';
import CommonUtil from '/@/platform/commons/utils/common-util';
import { useRouter } from 'vue-router';
import NavMenu from '../nav-menu/NavMenu.vue';
import UserType from '/@/platform/types/user-type';
import MenuType from '/@/platform/types/menu-type';
import { useStore } from 'vuex';
import UserApi from '/@/platform/apis/user-api';
import screenfull from 'screenfull';
import { Modal, notification } from 'ant-design-vue';
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import defaultAvatarImg from '/@/assets/platform/img/default-avatar.png';
import UpdatePwd from '/@/platform/components/update-pwd/UpdatePwd.vue';
import SysStorageUtils from '/@/platform/commons/utils/sys-storage-utils';
import SysApi from '/@/platform/apis/sys-api';
import HttpResultUtils from '/@/platform/commons/utils/http-result-utils';
import ImgCropperModal from '/@/platform/components/img-cropper/ImgCropperModal.vue';
import IndividuationModal from '/@/platform/components/individuation/IndividuationModal.vue';

export default defineComponent({
  name: 'LeftToolbar',
  components: {
    AppNavMenu: NavMenu,
    AppUpdatePwd: UpdatePwd,
    AppImgCropperModal: ImgCropperModal,
    AppIndividuationModal: IndividuationModal,
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
    const logo = Environments.getEvnProp('VITE_ADMIN_LOGO');

    const toolbarTopListRef = ref();
    const toolbarBottomListRef = ref();
    const toolbarLogoRef = ref();

    const originalUserAvatar = ref(defaultAvatarImg);
    const defaultAvatar = ref(defaultAvatarImg);

    const fullscreenState = ref(false);

    const userDropdownVisible = ref(false);

    const updatePwdVisible = ref(false);

    const imgCropperVisible = ref(false);

    const individuationVisible = ref(false);

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

      screenfull.toggle().then(() => {
        fullscreenState.value = !fullscreenState.value;
      });
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

    /**
     * 停止事件冒泡
     */
    const stopPropagation = (event: Event) => {
      event.stopPropagation();
      event.preventDefault();
    };

    /**
     * resize
     */
    const resize = () => {
      calcToolbarTopListWidth();
    };

    /**
     * 初始化
     */
    const init = () => {
      originalUserAvatar.value = UserApi.previewUserAvatar();

      if (screenfull.isEnabled) {
        screenfull.on('change', () => {
          fullscreenState.value = screenfull['isFullscreen'];
        });
      }
    };

    /**
     * 退出系统
     */
    const exitSys = () => {
      userDropdownVisible.value = false;
      Modal.confirm({
        title: '确认',
        icon: createVNode(QuestionCircleOutlined),
        content: '您确认退出系统吗？',
        onOk() {
          SysApi.exitLogin().then((res) => {
            if (HttpResultUtils.isSuccess(res)) {
              SysStorageUtils.removeSessionUserInfo();
              SysStorageUtils.removeSecurityKey();
              SysStorageUtils.removeSessionUser();
              router.push('/login');
              setTimeout(() => {
                notification.success({
                  message: '提示',
                  description: res.data.resultMsg,
                });
              }, 500);
            } else {
              HttpResultUtils.failureTipMsg(res);
            }
          });
        },
      });
    };

    const imgUpload = (imgData) => {
      UserApi.uploadUserAvatarImg(imgData).then((res) => {
        if (HttpResultUtils.isSuccess(res)) {
          notification.success({
            message: '提示',
            description: '上传头像成功，刷新页面后生效！',
          });
          imgCropperVisible.value = false;
        } else {
          HttpResultUtils.failureTipMsg(res);
        }
      });
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
      userDropdownVisible,
      updatePwdVisible,
      fullscreenState,
      toolbarTopListRef,
      toolbarBottomListRef,
      toolbarLogoRef,
      originalUserAvatar,
      defaultAvatar,
      showLogo,
      toHome,
      stopPropagation,
      changeMenuVisible,
      fullscreenToggle,
      exitSys,
      imgCropperVisible,
      imgUpload,
      logo,
      individuationVisible,
    };
  },
});
</script>

<style lang="less" scoped>
@import './LeftToolbar.less';
</style>
