

import http from "../commons/utils/axios-util";


export default class MenuApi {
    public static REQUEST_PREFIX = 'menu/';

    /**
      * 初始菜单管理数据
      * @param params 
      */
    static initMenuManageData(params: any) {
        return http.post(`${MenuApi.REQUEST_PREFIX}initMenuManageData`, params);
    }

    /**
    * 查询菜单信息
    */
    static queryMenuByMenuId(menuId: string) {
        return http.get(`${MenuApi.REQUEST_PREFIX}queryMenuByMenuId`, {
            params: {
                menuId: menuId
            }
        });
    }

    /**
    * 根据条件查询菜单
    * @param params 
    */
    static queryMenuByCondition(params: any) {
        return http.post(`${MenuApi.REQUEST_PREFIX}queryMenuByCondition`, params);
    }

    /**
    * 初始化菜单添加数据
    * @param params 
    */
    static initMenuAddData(params: any) {
        return http.post(`${MenuApi.REQUEST_PREFIX}initMenuAddData`, params);
    }

    /**
    * 保存菜单
    * @param params 
    */
    static saveMenu(params: any) {
        return http.post(`${MenuApi.REQUEST_PREFIX}saveMenu`, params);
    }

    /**
     * 根据ids删除菜单
     * @param params 
     */
    static deleteMenuByIds(params: any) {
        return http.post(`${MenuApi.REQUEST_PREFIX}deleteMenuByIds`, params);
    }

}
