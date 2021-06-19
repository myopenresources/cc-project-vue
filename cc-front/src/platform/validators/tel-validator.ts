import { RuleObject } from "ant-design-vue/lib/form/interface";

const telValidator = async (rule: RuleObject, value: string) => {
    const TEL = new RegExp('^[0-9]{3,4}-[0-9]{7,8}$');
    if (!TEL.test(value)) {
        return Promise.reject('固定电话格式不正确！');
    } else {
        return Promise.resolve()
    }
};

export default telValidator;