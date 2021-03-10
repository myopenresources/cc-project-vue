import Environments from "../common/util/env-util";
import http from "/@/common/util/axios-util";
import SecurityUtils from "/@/common/util/security-utils";


export default class SysApi {
    public static REQUEST_PREFIX = 'system/';

    /**
     * 登录 
     * @param params 
     * @returns 
     */
    static login(params: any) {
        const loginParams = {
            data: SecurityUtils.encrypt(JSON.stringify(params), Environments.getEvnProp("VITE_APP_S_KEY")),
            convert: false
        };

        return http.post(`${this.REQUEST_PREFIX}login`, loginParams);
    }

    /**
     * 初始化主数据
     */
    static initMainData(){
        return http.get(`${this.REQUEST_PREFIX}initMainData`, {});  
    }
}
