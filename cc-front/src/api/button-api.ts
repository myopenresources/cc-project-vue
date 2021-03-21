

import http from "/@/common/util/axios-util";


export default class ButtonApi {
    public static REQUEST_PREFIX = 'button/';


    /**
     * 初始按钮管理数据
     */
    static initButtonManageData() {
        return http.post(`${ButtonApi.REQUEST_PREFIX}initButtonManageData`, {});
    }

    /**
     * 分页查询
     * @param params 
     * @returns 
     */
    static queryButtonByConditionPaging(params: any) {
        return http.post(`${ButtonApi.REQUEST_PREFIX}queryButtonByConditionPaging`, params);
    }

    /**
    * 保存按钮
    * @param params 
    */
    static saveButton(params: any) {
        return http.post(`${ButtonApi.REQUEST_PREFIX}saveButton`, params);
    }

    /**
     *  初始化按钮数据
     * @param params 
     */
    static initButtonAddData(params: any) {
        return http.post(`${ButtonApi.REQUEST_PREFIX}initButtonAddData`, params);
    }

    /**
    * 根据ids删除按钮
    * @param params 
    */
    static deleteButtonByIds(params: any) {
        return http.post(`${ButtonApi.REQUEST_PREFIX}deleteButtonByIds`, params);
    }


    /**
     *  查询按钮信息
     */
    static queryButtonById(buttonId: any) {
        return http.get(`${ButtonApi.REQUEST_PREFIX}queryButtonById`, {
            params: {
                buttonId: buttonId
            }
        });
    }

}
