

import http from "../commons/utils/axios-util";


export default class AccessUrlApi {
    public static REQUEST_PREFIX = 'accessUrl/';

    /**
      * 初始访问地址管理数据
      * @param params 
      */
    static initAccessUrlManageData(params: any) {
        return http.post(`${AccessUrlApi.REQUEST_PREFIX}initAccessUrlManageData`, params);
    }


    /**
    * 根据条件分页查询访问地址
    */
    static queryAccessUrlByConditionPaging(params: any) {
        return http.post(`${AccessUrlApi.REQUEST_PREFIX}queryAccessUrlByConditionPaging`, params);
    }

    /**
    * 初始化访问地址数据
    * @param params 
    */
    static initAccessUrlAddData(params: any) {
        return http.post(`${AccessUrlApi.REQUEST_PREFIX}initAccessUrlAddData`, params);
    }

    /**
    * 保存访问地址
    * @param params 
    */
    static saveAccessUrl(params: any) {
        return http.post(`${AccessUrlApi.REQUEST_PREFIX}saveAccessUrl`, params);
    }

    /**
    * 根据ids删除访问地址
    * @param params 
    */
    static deleteAccessUrlByIds(params: any) {
        return http.post(`${AccessUrlApi.REQUEST_PREFIX}deleteAccessUrlByIds`, params);
    }


    /**
     * 查询访问地址信息
     */
    static queryAccessUrlById(accessUrlId: string) {
        return http.get(`${AccessUrlApi.REQUEST_PREFIX}queryAccessUrlById`, {
            params: {
                accessUrlId: accessUrlId
            }
        });
    }

}
