import { RouteRecordRaw } from "vue-router";
import { ModuleTree } from "vuex";

export default class ImportMetaUtil {
    
    /**
     * 获取router模块
     * @param path  路径
     */
    static getRouterModules(appModule: Record<string, { [key: string]: any }>): RouteRecordRaw[] {
        let appModuleList: RouteRecordRaw[] = []
        Object.keys(appModule).forEach((key) => {
            appModuleList = appModuleList.concat(appModule[key].default);
        })
        return appModuleList;
    }

    /**
     * 获取store模块
     * @param path 路径
     */
    static getStoreModules(storeModule: Record<string, { [key: string]: any }>): ModuleTree<any> {
        const storeModuleTree: ModuleTree<any> = {};

        Object.keys(storeModule).forEach((key) => {
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
            console.info(distStrKey)

            storeModuleTree[distStrKey] = storeModule[key].default;
            storeModuleTree[distStrKey]['namespaced'] = true
        })

        return storeModuleTree;
    }
}