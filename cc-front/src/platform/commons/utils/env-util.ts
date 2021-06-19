export default class Environments {
    static getEvnProp(key: string): string {
        const prop = import.meta.env[key]
        if (import.meta.env['VITE_SHOW_CONSOLE' as string] === 'true') {
            console.log('环境变量:' + prop)
        }
        return prop as string;
    }
}
