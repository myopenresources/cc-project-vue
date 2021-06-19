class SessionStorageUtils {
    static get(key: string) {
        return window.sessionStorage.getItem(key);
    }

    static set(key: string, val: any) {
        window.sessionStorage.setItem(key, val);
    }

    static setObject(key: string, val: any) {
        window.sessionStorage.setItem(key, JSON.stringify(val));
    }

    static getObject(key: string) {
        return JSON.parse(window.sessionStorage.getItem(key) as string);
    }

    static remove(key: string) {
        window.sessionStorage.removeItem(key);
    }
}


class LocalStorageUtils {
    static get(key: string) {
        return window.localStorage.getItem(key);
    }

    static set(key: string, val: any) {
        window.localStorage.setItem(key, val);
    }

    static setObject(key: string, val: any) {
        window.localStorage.setItem(key, JSON.stringify(val));
    }

    static getObject(key: string) {
        return JSON.parse(window.localStorage.getItem(key) as string);
    }

    static remove(key: string) {
        window.localStorage.removeItem(key);
    }
}



export {
    SessionStorageUtils,
    LocalStorageUtils
}