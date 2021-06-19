import { RuleObject } from "ant-design-vue/lib/form/interface";

const passwordValidator = async (rule: RuleObject, value: string) => {
    const PASSWORD = new RegExp('^([a-zA-Z0-9]|[_.*$#]){6,15}$');
    if (!PASSWORD.test(value)) {
        return Promise.reject('密码格式不正确（格式：6到15位，大小写字母、数字、特殊字符：_.*$#）！');
    } else {
        return Promise.resolve()
    }
};

export default passwordValidator;