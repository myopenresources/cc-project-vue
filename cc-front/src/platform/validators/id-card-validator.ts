import { RuleObject } from "ant-design-vue/lib/form/interface";

const idCardValidator = async (rule: RuleObject, value: string) => {
    if (!(/^([0-9]{15}|[0-9]{18}|[0-9]{17}(X|x))$/.test(value))) {
        return Promise.reject('身份证格式不正确！');
    } else {
        return Promise.resolve()
    }
};

export default idCardValidator;