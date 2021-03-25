import { RuleObject } from "ant-design-vue/lib/form/interface";

const telMobileValidator = async (rule: RuleObject, value: string) => {
    const TEL = new RegExp('^[0-9]{3,4}-[0-9]{7,8}$');
    const MOBILE = new RegExp('^[1][3|4|5|8|7][0-9]{9}$');
    if (!TEL.test(value) && !MOBILE.test(value)) {
        return Promise.reject('固定电话或手机号码格式不正确！');
    } else {
        return Promise.resolve()
    }
};

export default telMobileValidator;