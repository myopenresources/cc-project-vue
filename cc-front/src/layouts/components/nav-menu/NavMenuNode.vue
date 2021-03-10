<template>
  <ul
    class="app-nav-menu-node-container animated fadeIn"
    v-show="getObjProp(data, 'menuState')"
    v-if="!isLeaf(data)"
  >
    <li v-for="item in getObjProp(data, 'menuChildren')" :key="item.id">
      <a @click="itemClicked(item)" :style="{ 'padding-left': level * 15 + 'px' }">
        <div class="app-node">
          <span
            class="iconfont icon-down"
            v-if="!isLeaf(item) && getObjProp(item, 'menuState')"
          ></span>
          <span
            class="iconfont icon-right"
            v-if="!isLeaf(item) && !getObjProp(item, 'menuState')"
          ></span>
        </div>

        <div>
          <span :class="['iconfont', 'app-node-icon', getObjProp(item, 'menuIcon')]"></span>
          <span>{{ getObjProp(item, 'menuLabel') }}</span>
        </div>
      </a>
      <nav-menu-node :nodeData="item" :nodeConfig="nodeConfig" :level="level + 1" />
    </li>
  </ul>
</template>

<script lang="ts">
import { useRouter } from 'vue-router';
import { computed, defineComponent, PropType } from 'vue';
import NavMenuNodeType from '../../../types/nav-menu-node-type';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'NavMenuNode',
  props: {
    nodeData: {
      type: Object as PropType<NavMenuNodeType>,
      default: [],
    },
    nodeConfig: {
      type: Object as PropType<NavMenuNodeType>,
      default: () => {
        return {
          menuId: 'id',
          menuParentId: 'parentId',
          menuLabel: 'text',
          menuIcon: 'icon',
          menuChildren: 'children',
          menuUrl: 'attr1',
          menuAttr: 'attributes',
          menuState: 'state',
        };
      },
    },
    level: {
      type: Number,
      default: 1,
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();

    const data = computed(() => {
      return props.nodeData;
    });

    const isLeaf = (item: any) => {
      return (
        !item[props.nodeConfig.menuChildren as string] ||
        !item[props.nodeConfig.menuChildren as string].length
      );
    };

    const itemClicked = (item: any) => {
      if (!isLeaf(item)) {
        item[props.nodeConfig.menuState as string] = !item[props.nodeConfig.menuState as string];
      } else {
        router.push({ path: item[props.nodeConfig.menuUrl as string] });
        store.dispatch("navMenuState/hideNavMenu");
      }
    };

    const getObjProp = (obj: any, prop: string) => {
      return obj[props.nodeConfig[prop]];
    };

    return {
      data,
      getObjProp,
      isLeaf,
      itemClicked,
    };
  },
});
</script>

<style lang="less" scoped>
@import './NavMenuNode.less';
</style>
