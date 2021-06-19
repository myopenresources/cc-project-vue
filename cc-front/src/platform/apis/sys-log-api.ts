import HttpUtil from "../commons/utils/http-util";
import http from "../commons/utils/axios-util";


export default class SysLogApi {
    public static REQUEST_PREFIX = 'systemLog/';


    /**
     * 初始日志管理数据
     * @param params 
     */
    static initSystemLogManageData() {
        return http.post(`${SysLogApi.REQUEST_PREFIX}initSystemLogManageData`, {});
    }

    /**
     * 下载日志
     * @param params 
     */
    static downloadSystemLog(filePath: string): string {
        return HttpUtil.stringUrl(`${SysLogApi.REQUEST_PREFIX}downloadSystemLog`) + '?filePath=' + encodeURI(encodeURI(filePath));
    }


    /**
     * 删除日志
     * @param params 
     */
    static deleteSystemLog(params: any) {
        return http.post(`${SysLogApi.REQUEST_PREFIX}deleteSystemLog`, params);
    }
}
