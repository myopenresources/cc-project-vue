import { CommonConst } from "../const/common-const";
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
        CookieUtils.setObject(CommonConst.sessionUser, value, 0.125);
    }

    static getSessionUser() {
        return CookieUtils.getObject(CommonConst.sessionUser);
    }

    static removeSessionUser() {
        return CookieUtils.remove(CommonConst.sessionUser);
    }

    static getSessionUserInfo() {
        return SessionStorageUtils.getObject(CommonConst.sessionUserInfo);
    }

    static setSessionUserInfo(data: any) {
        SessionStorageUtils.setObject(CommonConst.sessionUserInfo, data);
    }

    static removeSessionUserInfo() {
        return SessionStorageUtils.remove(CommonConst.sessionUserInfo);
    }
}