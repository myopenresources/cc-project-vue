

import http from "../commons/utils/axios-util";


export default class DataDicApi {
    public static REQUEST_PREFIX = 'dataDic/';

    /**
     * 初始数据字典管理数据
     * @param params 
     */
    static initDataDicManageData(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}initDataDicManageData`, params);
    }

    /**
     * 查询数据字典类别列表
     * @param params 
     */
    static queryDataDicTypeList(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}queryDataDicTypeList`, params);
    }


    /**
     * 初始化数据字典类别数据
     * @param params 
     */
    static initDataDicTypeAddData(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}initDataDicTypeAddData`, params);
    }

    /**
     * 保存数据字典类别
     * @param params 
     */
    static saveDataDicType(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}saveDataDicType`, params);
    }

    /**
     * 删除数据字典类别
     * @param params 
     */
    static deleteDataDicTypeByIds(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}deleteDataDicTypeByIds`, params);
    }

    /**
     * 查询数据字典明细
     * @param params 
     */
    static queryDataDicItemByIdPaging(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}queryDataDicItemByIdPaging`, params);
    }

    /**
     * 查询数据字典类别信息
     */
    static queryDataDicTypeById(dicTypeId: string) {
        return http.get(`${DataDicApi.REQUEST_PREFIX}queryDataDicTypeById`, {
            params: {
                dicTypeId: dicTypeId
            }
        });
    }

    /**
     * 重新载入数据字典
     */
    static reloadDataDic() {
        return http.post(`${DataDicApi.REQUEST_PREFIX}reloadDataDic`, {});
    }


    /**
    * 初始化数据字典明细数据
    * @param params 
    */
    static initDataDataDicItemAddData(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}initDataDataDicItemAddData`, params);
    }

    /**
     * 保存数据字典明细
     * @param params 
     */
    static saveDataDicItem(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}saveDataDicItem`, params);
    }

    /**
     * 删除数据字典明细
     * @param params 
     */
    static deleteDataDicItemByIds(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}deleteDataDicItemByIds`, params);
    }

    /**
     * 查询数据字典明细信息
     */
    static queryDataDicItemById(dicItemId: string) {
        return http.get(`${DataDicApi.REQUEST_PREFIX}queryDataDicItemById`, {
            params: {
                dicItemId: dicItemId
            }
        });
    }

    /**
     * 查询数据字典明细分配角色
     */
    static queryDataDicItemRoleDistribute(dicItemId: string) {
        return http.get(`${DataDicApi.REQUEST_PREFIX}queryDataDicItemRoleDistribute`, {
            params: {
                dicItemId: dicItemId
            }
        });
    }

    /**
     * 保存数据字典明细角色映射
     * @param params 
     */
    static saveDataDicItemRoleMapping(params: any) {
        return http.post(`${DataDicApi.REQUEST_PREFIX}saveDataDicItemRoleMapping`, params);
    }
}
