<template>
  <div class="app-nav-menu-container">
    <div class="app-nav-menu-search-container">
      <a-input-search placeholder="搜索菜单..." @search="onSearch" v-model="searchTxt" />
    </div>

    <div class="app-nav-menu-tree" v-scrollbar>
      <div class="app-nav-menu-tree-content">
        <div class="app-search-msg" v-show="!searchMsgHidden">
          <app-custom-empty description="搜索的菜单不存在！" />
        </div>
        <ul class="app-nav-menu-ul" v-show="searchMsgHidden">
          <li v-for="item in data" :key="item.id">
            <a @click="itemClicked(item)">
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
            <app-nav-menu-node :nodeData="item" :nodeConfig="nodeConfig" :level="level" />
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { useRouter } from 'vue-router';
import { computed, defineComponent, PropType, ref } from 'vue';
import NavMenuNode from './NavMenuNode.vue';
import NavMenuNodeType from '/@/types/nav-menu-node-type';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'NavMenu',
  components: {
    AppNavMenuNode: NavMenuNode
  },
  props: {
    treeData: {
      type: Array,
      default: () => {
        return [];
      },
    },
    showSearch: {
      type: Boolean,
      default: () => {
        return true;
      },
    },
    nodeConfig: {
      type: Object as PropType<NavMenuNodeType>,
      default: () => {
        return {
          menuId: 'id',
          menuParentId: 'parentId',
          menuLabel: 'title',
          menuIcon: 'icon',
          menuChildren: 'children',
          menuUrl: 'attr1',
          menuAttr: 'attributes',
          menuState: 'state',
        };
      },
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();

    let searchTxt = ref<string>('');
    let searchMsgHidden = ref(true);
    const level = 1;

    const allData: any = computed(() => {
      return props.treeData;
    });

    let data: any = computed(() => {
      return props.treeData;
    });

    const onSearch = (searchValue: string) => {
      const tempData: any = allData;
      searchTxt.value = searchValue.trim();
      searchMsgHidden.value = true;

      if ('' !== searchTxt.value) {
        const keyWord = new RegExp(searchTxt.value);
        const menuList = [];
        const menuIdList = [];

        for (const item of tempData.value) {
          searchItem(item, menuList, menuIdList, keyWord);
        }
        if (menuList.length > 0) {
          data = menuList;
        } else {
          searchMsgHidden.value = false;
        }
      } else {
        data = allData;
      }
    };

    const searchItem = (item: any, menuList: any, menuIdList: any, keyWord: any) => {
      item[props.nodeConfig.menuState as string] = false;
      //关键字匹配，并且不在列表中的
      if (
        item[props.nodeConfig.menuLabel as string].match(keyWord) &&
        !checkSearchMenuIdExists(item[props.nodeConfig.menuId as string], menuIdList)
      ) {
        menuList.push(item);
        pushSearchMenu(item, menuIdList);
      }

      //存在子菜单的，递归子菜单
      const itemChildren = item[props.nodeConfig.menuChildren as string];
      if (itemChildren && itemChildren.length > 0) {
        for (const subItem of itemChildren) {
          searchItem(subItem, menuList, menuIdList, keyWord);
        }
      }
    };

    const pushSearchMenu = (item: any, menuIdList: any) => {
      menuIdList.push(item[props.nodeConfig.menuId as string]);
      const itemChildren = item[props.nodeConfig.menuChildren as string];
      if (itemChildren && itemChildren.length > 0) {
        for (const subItem of itemChildren) {
          pushSearchMenu(subItem, menuIdList);
        }
      }
    };

    const checkSearchMenuIdExists = (id: string, menuIdList: any) => {
      for (const menuId of menuIdList) {
        if (menuId === id) {
          return true;
        }
      }
      return false;
    };

    const isLeaf = (item: any) => {
      return (
        !item[props.nodeConfig.menuChildren as string] ||
        !item[props.nodeConfig.menuChildren as string].length
      );
    };

    const itemClicked = (item) => {
      if (!isLeaf(item)) {
        item[props.nodeConfig.menuState as string] = !item[props.nodeConfig.menuState as string];
      } else {
        router.push({ path: item[props.nodeConfig.menuUrl as string] });
        store.dispatch('navMenuState/hideNavMenu');
      }
    };

    const getObjProp = (obj: any, prop: string) => {
      return obj[props.nodeConfig[prop]];
    };

    return {
      searchTxt,
      searchMsgHidden,
      level,
      data,
      onSearch,
      isLeaf,
      itemClicked,
      getObjProp,
    };
  },
});
</script>

<style lang="less" scoped>
@import './NavMenu.less';
</style>
