

import http from "/@/common/util/axios-util";


export default class RoleApi {
    public static REQUEST_PREFIX = 'role/';

    /**
   * 初始角色管理数据
   * @param params 
   */
    static initRoleManageData(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}initRoleManageData`, params);
    }

    /**
     * 根据条件分页查询角色
     */
    static queryRoleByConditionPaging(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}queryRoleByConditionPaging`, params);
    }


    /**
     * 初始化角色添加数据
     * @param params 
     */
    static initRoleAddData(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}initRoleAddData`, params);
    }


    /**
     * 保存角色
     * @param params 
     */
    static saveRole(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}saveRole`, params);
    }

    /**
    * 删除角色
    * @param params 
    */
    static deleteRoleByIds(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}deleteRoleByIds`, params);
    }


    /**
     * 查询角色信息
     */
    static queryRoleById(roleId: string) {
        return http.get(`${RoleApi.REQUEST_PREFIX}queryRoleById`, {
            params: {
                roleId: roleId
            }
        });
    }

    /**
     * 查询角色用户
     */
    static queryRoleUsers(roleId: string) {
        return http.get(`${RoleApi.REQUEST_PREFIX}queryRoleUsers`, {
            params: {
                roleId: roleId
            }
        });
    }

    /**
     * 查询角色权限分配
     */
    static queryRolePermissionDistribute(roleId: string) {
        return http.get(`${RoleApi.REQUEST_PREFIX}queryRolePermissionDistribute`, {
            params: {
                roleId: roleId
            }
        });
    }

    /**
     * 保存角色权限分配
     * @param params 
     */
    static saveRolePermissionDistribute(params: any) {
        return http.post(`${RoleApi.REQUEST_PREFIX}saveRolePermissionDistribute`, params);
    }

}
