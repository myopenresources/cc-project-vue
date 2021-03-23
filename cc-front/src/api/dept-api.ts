

import http from "/@/common/util/axios-util";


export default class DeptApi {
    public static REQUEST_PREFIX = 'dept/';

    /**
     * 初始部门管理数据
     * @param params 
     */
    static initDeptManageData(params: any) {
        return http.post(`${DeptApi.REQUEST_PREFIX}initDeptManageData`, params);
    }



    /**
    * 查询部门信息
    */
    static queryDeptByDeptId(deptId: string) {
        return http.get(`${DeptApi.REQUEST_PREFIX}queryDeptByDeptId`, {
            params: {
                deptId: deptId
            }
        });
    }

    /**
    * 根据条件查询部门
    * @param params 
    */
    static queryDeptByCondition(params: any) {
        return http.post(`${DeptApi.REQUEST_PREFIX}queryDeptByCondition`, params);
    }

    /**
    * 初始化部门添加数据
    * @param params 
    */
    static initDeptAddData(params: any) {
        return http.post(`${DeptApi.REQUEST_PREFIX}initDeptAddData`, params);
    }

    /**
    * 保存部门
    * @param params 
    */
    static saveDept(params: any) {
        return http.post(`${DeptApi.REQUEST_PREFIX}saveDept`, params);
    }

    /**
     * 根据ids删除部门
     * @param params 
     */
    static deleteDeptByIds(params: any) {
        return http.post(`${DeptApi.REQUEST_PREFIX}deleteDeptByIds`, params);
    }



    /**
    * 查询部门用户
    */
    static queryDeptUsers(deptId: string) {
        return http.get(`${DeptApi.REQUEST_PREFIX}queryDeptUsers`, {
            params: {
                deptId: deptId
            }
        });
    }

}
