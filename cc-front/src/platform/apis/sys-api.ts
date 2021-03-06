import Environments from "../commons/utils/env-util";
import http from "../commons/utils/axios-util";
import SecurityUtils from "../commons/utils/security-utils";


export default class SysApi {
    public static REQUEST_PREFIX = 'system/';

    /**
     * 登录 
     * @param params 
     * @returns 
     */
    static login(params: any) {
        return http.post(`${SysApi.REQUEST_PREFIX}login`, {
            data: SecurityUtils.encrypt(JSON.stringify(params), Environments.getEvnProp("VITE_APP_S_KEY")),
            convert: false
        });
    }

    /**
   * 退回登录
   */
    static exitLogin() {
        return http.post(`${SysApi.REQUEST_PREFIX}exitLogin`);
    }

    /**
     * 初始化主数据
     */
    static initMainData() {
        return http.get(`${SysApi.REQUEST_PREFIX}initMainData`);
    }

    /**
     * 初始化主页数据
     */
    static initHomeData(){
        return http.get(`${SysApi.REQUEST_PREFIX}initHomeData`);
    }
}
