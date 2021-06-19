import { RouteRecordRaw } from "vue-router";
import { ModuleTree } from "vuex";

export default class ImportMetaUtil {

    /**
     * 获取router模块
     * @param path  路径
     */
    static getRouterModules(appModules: (Record<string, { [key: string]: any }>)[]): RouteRecordRaw[] {
        let appModuleList: RouteRecordRaw[] = []
        appModules.forEach((item: Record<string, { [key: string]: any }>) => {
            Object.keys(item).forEach((key) => {
                appModuleList = appModuleList.concat(item[key].default);
            })
        });

        return appModuleList;
    }

    /**
     * 获取store模块
     * @param path 路径
     */
    static getStoreModules(storeModules: (Record<string, { [key: string]: any }>)[]): ModuleTree<any> {
        const storeModuleTree: ModuleTree<any> = {};

        storeModules.forEach((item: Record<string, { [key: string]: any }>) => {
            Object.keys(item).forEach((key) => {
                const keys = key.split('/');
                const keyStr = keys[keys.length - 1].replace(/\.\/|\.ts/g, '');
                const keyStrList = keyStr.split('-');
                let index = 0;
                let distStrKey = '';
                keyStrList.forEach((element: string) => {
                    if (index !== 0) {
                        distStrKey += element[0].toUpperCase() + element.substring(1, element.length);
                    } else {
                        distStrKey = element;
                    }
                    index++;
                });

                storeModuleTree[distStrKey] = item[key].default;
                storeModuleTree[distStrKey]['namespaced'] = true
            })
        });

        return storeModuleTree;
    }
}