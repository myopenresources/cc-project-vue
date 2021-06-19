import { commonConst } from "../consts/common-const";
import CookieUtils from "./cookie-utils";
import SecurityUtils from "./security-utils";
import { SessionStorageUtils } from './storage-utils';
import Environments from "./env-util";

export default class SysStorageUtils {
    static setSecurityKey(value: any) {
        const timeout = Number.parseFloat(Environments.getEvnProp('VITE_SESSION_TIMEOUT'));
        CookieUtils.set(SecurityUtils.securityKey, value, timeout);
    }

    static getSecurityKey() {
        return CookieUtils.get(SecurityUtils.securityKey);
    }

    static removeSecurityKey() {
        return CookieUtils.remove(SecurityUtils.securityKey);
    }

    static setSessionUser(value: any) {
        const timeout = Number.parseFloat(Environments.getEvnProp('VITE_SESSION_TIMEOUT'));
        CookieUtils.setObject(commonConst.sessionUser, value, timeout);
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