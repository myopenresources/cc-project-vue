import http from "/@/common/util/axios-util";


export default class SysInfoApi {
    public static REQUEST_PREFIX = 'sysInfo/';


    /**
   * 初始系统信息数据
   * @param params 
   */
    static initSysInfoData() {
        return http.post(`${SysInfoApi.REQUEST_PREFIX}initSysInfoData`);
    }
}
