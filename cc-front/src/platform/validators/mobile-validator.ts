import { RuleObject } from "ant-design-vue/lib/form/interface";

const mobileValidator = async (rule: RuleObject, value: string) => {
    const MOBILE = new RegExp('^[1][3|4|5|8|7][0-9]{9}$');

    if (!MOBILE.test(value)) {
        return Promise.reject('手机号码格式不正确！');
    } else {
        return Promise.resolve()
    }
};

export default mobileValidator;