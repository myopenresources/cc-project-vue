
export default class CookieUtils {
    static set(name: string, value: any, days: number) {
        const date = new Date;
        date.setTime(date.getTime() + 24 * 60 * 60 * 1000 * days);
        window.document.cookie = name + "=" + value + ";path=/;expires=" + date.toUTCString();
        window.document.cookie = name + "55" + "=" + value + ";path=/;expires=" + date.toUTCString();
    }

    static get(name: string) {
        const val = window.document.cookie.match("(^|)" + name + "=([^;]*)(;|$)");
        return val ? val[2] : null;
    }

    static setObject(name: string, value: any, days: number) {
        CookieUtils.set(name, JSON.stringify(value), days);
    }

    static getObject(name: string) {
        return JSON.parse(CookieUtils.get(name) as string);
    }

    static remove(name: string) {
        CookieUtils.set(name, "", -1);
    }
}
