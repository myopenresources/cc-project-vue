<template>
  <div class="app-exception">
    <div class="app-img-block">
      <div class="app-img-ele" :class="config[type].class"></div>
    </div>
    <div class="app-content">
      <h2>{{ config[type].title }}</h2>
      <div class="app-desc">{{ config[type].desc }}</div>
      <a-button type="primary" @click="handleToHome">
        <app-dynamic-icon iconName="DoubleLeftOutlined" /> 返回首页
      </a-button>
    </div>
  </div>
</template>

<script lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { defineComponent } from 'vue';
import Environments from '/@/platform/commons/utils/env-util';
import { useStore } from 'vuex';

const exceptionConfig = {
  404: {
    class: 'app-img-ele-not-found',
    title: '404',
    desc: '抱歉，你访问的页面不存在...',
  },
};

export default defineComponent({
  name: 'Exception',
  props: {
    type: {
      type: String,
      default: '404',
    },
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const route = useRoute();
    const config = exceptionConfig;

    const handleToHome = () => {
      store.dispatch('tagsViewState/delVisitedView', route).then(() => {
        router.push(Environments.getEvnProp('VITE_HOME_ROUTER'));
      });
    }
    
    return {
      config,
      handleToHome,
    };
  },
});
</script>

<style lang="less" scoped>
@import './Exception.less';
</style>
