import { RuleObject } from "ant-design-vue/lib/form/interface";

const equalValidator = async (rule: RuleObject, value: string, quValue: string, errorMsg: string) => {
    if (value === quValue) {
        return Promise.resolve()
    } else {
        return Promise.reject(errorMsg);
    }
};

export default equalValidator;