import CryptoJS from "crypto-js";


export default class SecurityUtils {
    static securityKey: "_SECURITY_KEY";

    static encrypt(data: any, key: string) {
        const parseKey = CryptoJS.enc.Utf8.parse(key);
        const srcs = CryptoJS.enc.Utf8.parse(data);
        const encrypted = CryptoJS.AES.encrypt(srcs, parseKey, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
        return encrypted.toString();
    }

    static decrypt(data: any, key: string) {
        const parseKey = CryptoJS.enc.Utf8.parse(key);
        const decrypt = CryptoJS.AES.decrypt(data, parseKey, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }
}
