import { commonConst } from "../const/common-const";
import CookieUtils from "./cookie-utils";
import SecurityUtils from "./security-utils";
import { SessionStorageUtils } from './storage-utils';

export default class SysStorageUtils {
    static setSecurityKey(value: any) {
        CookieUtils.set(SecurityUtils.securityKey, value, 0.125);
    }

    static getSecurityKey() {
        return CookieUtils.get(SecurityUtils.securityKey);
    }

    static removeSecurityKey() {
        return CookieUtils.remove(SecurityUtils.securityKey);
    }

    static setSessionUser(value: any) {
        CookieUtils.setObject(commonConst.sessionUser, value, 0.125);
    }

    static getSessionUser() {
        return CookieUtils.getObject(commonConst.sessionUser);
    }

    static removeSessionUser() {
        return CookieUtils.remove(commonConst.sessionUser);
    }

    static getSessionUserInfo() {
        return SessionStorageUtils.getObject(commonConst.sessionUserInfo);
    }

    static setSessionUserInfo(data: any) {
        SessionStorageUtils.setObject(commonConst.sessionUserInfo, data);
    }

    static removeSessionUserInfo() {
        return SessionStorageUtils.remove(commonConst.sessionUserInfo);
    }
}