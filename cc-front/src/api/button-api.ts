

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


}
