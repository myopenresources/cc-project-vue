import http from "/@/common/util/axios-util";


export default class LoginLogApi {
    public static REQUEST_PREFIX = 'loginLog/';


    /**
   * 初始登录日志管理数据
   * @param params 
   */
    static initLoginLogManageData() {
        return http.post(`${LoginLogApi.REQUEST_PREFIX}initLoginLogManageData`, {});
    }

    /**
   * 根据条件分页查询登录日志
   */
    static queryLoginLogByConditionPaging(params: any) {
        return http.post(`${LoginLogApi.REQUEST_PREFIX}queryLoginLogByConditionPaging`, params);
    }
}
