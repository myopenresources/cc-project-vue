import { individuationConfig } from "/@/shared/configs/individuation";
import { commonConst } from "../consts/common-const";
import CookieUtils from "./cookie-utils";
import { message } from "ant-design-vue";
import { replaceStyleVariables } from 'vite-plugin-theme/es/client';
import { generate } from '@ant-design/colors';



export default class IndividuationUtil {

    /**
     * 设置css var
     * @param obj 
     */
    static setCssVariable(obj: Object) {
        Object.keys(obj).forEach((key: string) => {
            document.body.style.setProperty(key, obj[key]);
        });
    }

    /**
     * 更换主题
     * @param key 
     * @param loading 
     * @param succ 
     * @param err 
     */
    static themeChange(key: string, loading: boolean, succ?: Function, err?: Function) {
        const hide = loading ? message.loading('正在更换皮肤...', 0) : null;

        const colors = generate(individuationConfig.antdTheme[key][0], {
            theme: 'default',
        });
        replaceStyleVariables({
            colorVariables: [...colors],
        }).then(() => {
            IndividuationUtil.setCssVariable(individuationConfig.appTheme[key]);
            succ && succ(key);
            hide && setTimeout(hide, 66);
        }).catch((error: any) => {
            hide && setTimeout(hide, 66);
            err && err(error);
        });;
    }

    /**
     * 获取 config
     * @returns 
     */
    static getIndividuationConfig() {
        return individuationConfig;
    }

    /**
    * 根据key获取layout 配置
    * @param key 
    * @returns 
    */
    static getLayoutConfigByKey(key: string) {
        return individuationConfig.layoutList.find((item: any) => item.key === key);
    }


    /**
     * 初始化应用主题
     */
    static initAppTheme() {
        const sessionTheme = CookieUtils.get(commonConst.sessionTheme);
        if (sessionTheme && sessionTheme !== IndividuationUtil.getIndividuationConfig().themeMenuList[0].key) {
            IndividuationUtil.themeChange(sessionTheme, true, () => {
                CookieUtils.set(commonConst.sessionTheme, sessionTheme, 365);
            });
        }
    }
}
