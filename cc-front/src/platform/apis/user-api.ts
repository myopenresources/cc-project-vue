import HttpUtil from '../commons/utils/http-util';
import http from "../commons/utils/axios-util";


export default class UserApi {
    public static REQUEST_PREFIX = 'user/';

    /**
     * 用户头像
     */
    static previewUserAvatar() {
        return HttpUtil.stringUrl(`${UserApi.REQUEST_PREFIX}previewUserAvatar`);
    }

    /**
     * 修改当前用户密码
     */
    static updateCurrentUserPassword(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}updateCurrentUserPassword`, params);
    }

    /**
     *  根据id修改密码
     */
    static updateUserPasswordById(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}updateUserPasswordById`, params);
    }

    /**
     * 上传头像
     * @param params 
     */
    static uploadUserAvatarImg(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}uploadUserAvatarImg`, {
            data: params,
            convert: false
        });
    }


    /**
    * 个人资料查询
    */
    static queryCurrentUserInfo() {
        return http.get(`${UserApi.REQUEST_PREFIX}queryCurrentUserInfo`);
    }


    /**
    *  初始化用户管理数据
    */
    static initUserManageData(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}initUserManageData`, params);
    }

    /**
     * 查询用户列表数据
     * @param params 
     * @param urlParams 
     */
    static queryUserByConditionPaging(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}queryUserByConditionPaging`, params);
    }


    /**
     * 查询用户信息
     */
    static queryUserById(userId: string) {
        return http.get(`${UserApi.REQUEST_PREFIX}queryUserById`, {
            params: {
                userId: userId
            }
        });
    }

    /**
     * 初始用户添加数据
     * @param params 
     */
    static initUserAddData(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}initUserAddData`, params);
    }

    /**
    * 保存用户
    * @param params 
    */
    static saveUser(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}saveUser`, params);
    }

    /**
     * 删除用户
     * @param params 
     */
    static deleteUserByIds(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}deleteUserByIds`, params);
    }

    /**
      * 重置密码
      * @param params 
      */
    static resetUserPwdByIds(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}resetUserPwdByIds`, params);
    }

    /**
     * 初始化用户角色分配数据
     * @param userId 
     */
    static initUserRoleDistributeData(userId: string) {
        return http.get(`${UserApi.REQUEST_PREFIX}initUserRoleDistributeData`, {
            params: {
                userId: userId
            }
        });
    }

    /**
     * 保存用户角色映射
     * @param params 
     */
    static saveUserRoleMapping(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}saveUserRoleMapping`, params);
    }

    /**
     * 初始化用户部门分配数据
     * @param userId 
     */
    static initUserDeptDistributeData(userId: string) {
        return http.get(`${UserApi.REQUEST_PREFIX}initUserDeptDistributeData`, {
            params: {
                userId: userId
            }
        });
    }

    /**
     * 保存用户部门映射
     * @param params 
     */
    static saveUserDeptMapping(params: any) {
        return http.post(`${UserApi.REQUEST_PREFIX}saveUserDeptMapping`, params);
    }
}
