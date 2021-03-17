import HttpUtil from '/@/common/util/http-util';
import http from "/@/common/util/axios-util";


export default class UserApi {
    public static REQUEST_PREFIX = 'user/';

    /**
     * 用户头像
     */
    static previewUserAvatar() {
        return HttpUtil.stringUrl(`${this.REQUEST_PREFIX}previewUserAvatar`);
    }

    /**
     * 修改当前用户密码
     */
    static updateCurrentUserPassword(params: any) {
        return http.post(`${this.REQUEST_PREFIX}updateCurrentUserPassword`, params);
    }

    /**
     *  根据id修改密码
     */
    static updateUserPasswordById(params: any) {
        return http.post(`${this.REQUEST_PREFIX}updateUserPasswordById`, params);
    }

    /**
     * 上传头像
     * @param params 
     */
    static uploadUserAvatarImg(params: any) {
        return http.post(`${this.REQUEST_PREFIX}uploadUserAvatarImg`, {
            data: params,
            convert: false
        });
    }
}
