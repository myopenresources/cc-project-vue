<template>
  <div class="app-desk-layout-container">
    <app-left-toolbar :userData="mainData.userData" :menuData="mainData.menuData" />
    <app-right-main />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive } from 'vue';
import RightMain from '../components/right-main/RightMain.vue';
import LeftToolbar from '../components/left-toolbar/LeftToolbar.vue';
import SysApi from '/@/api/sys-api';
import HttpResultUtils from '/@/common/util/http-result-utils';
import MainDataType from '/@/types/main-data-type';
import SysStorageUtils from '/@/common/util/sys-storage-utils';

export default defineComponent({
  name: 'AppDeskLayout',
  components: {
    appLeftToolbar: LeftToolbar,
    appRightMain: RightMain,
  },
  setup() {
    const mainData = reactive<MainDataType>({
      userData: {},
      menuData: [],
    });

    const initMain = () => {
      SysApi.initMainData().then((result) => {
        if (HttpResultUtils.isSuccess(result)) {
          mainData.userData = result.data.resultData.userData;
          if (
            result.data.resultData.menuData &&
            result.data.resultData.menuData[0] &&
            result.data.resultData.menuData[0].children
          ) {
            mainData.menuData = result.data.resultData.menuData[0].children;
          }

          SysStorageUtils.setSessionUserInfo(result.data.resultData.userData);
        } else {
          HttpResultUtils.failureTipMsg(result);
        }
      });
    };

    onMounted(() => {
      initMain();
    });

    return {
      mainData,
    };
  },
});
</script>

<style lang="less" scoped>
@import './AppDeskLayout.less';
</style>
