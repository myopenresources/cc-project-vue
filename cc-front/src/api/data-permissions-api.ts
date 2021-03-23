

import http from "/@/common/util/axios-util";


export default class DataPermissionsApi {
    public static REQUEST_PREFIX = 'dataPermissions/';

    /**
      * 初始数据权限管理数据
      * @param params 
      */
    static initDataPermissionsManageData(params: any) {
        return http.post(`${DataPermissionsApi.REQUEST_PREFIX}initDataPermissionsManageData`, params);
    }


    /**
    * 根据条件分页查询数据权限
    */
    static queryDataPermissionsByConditionPaging(params: any) {
        return http.post(`${DataPermissionsApi.REQUEST_PREFIX}queryDataPermissionsByConditionPaging`, params);
    }

    /**
    * 初始化数据权限添加数据
    * @param params 
    */
    static initDataPermissionsAddData(params: any) {
        return http.post(`${DataPermissionsApi.REQUEST_PREFIX}initDataPermissionsAddData`, params);
    }

    /**
    * 保存数据权限
    * @param params 
    */
    static saveDataPermissions(params: any) {
        return http.post(`${DataPermissionsApi.REQUEST_PREFIX}saveDataPermissions`, params);
    }

    /**
    * 根据ids删除数据权限
    * @param params 
    */
    static deleteDataPermissionsByIds(params: any) {
        return http.post(`${DataPermissionsApi.REQUEST_PREFIX}deleteDataPermissionsByIds`, params);
    }


    /**
     * 查询数据权限信息
     */
    static queryDataPermissionsById(dataPermissionsId: string) {
        return http.get(`${DataPermissionsApi.REQUEST_PREFIX}queryDataPermissionsById`, {
            params: {
                dataPermissionsId: dataPermissionsId
            }
        });
    }

}
