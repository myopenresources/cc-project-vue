import { RuleObject } from "ant-design-vue/lib/form/interface";

const userNameValidator = async (rule: RuleObject, value: string) => {
    const USER_NAME = new RegExp('^([a-zA-Z0-9]|[_@#]){5,20}$');
    if (!USER_NAME.test(value)) {
        return Promise.reject('用户名格式不正确（格式：5到15位，大小写字母、数字、特殊字符：_@#）！');
    } else {
        return Promise.resolve()
    }
};

export default userNameValidator;