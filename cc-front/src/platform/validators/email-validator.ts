import { RuleObject } from "ant-design-vue/lib/form/interface";

const emailValidator = async (rule: RuleObject, value: string) => {
    if (!(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value))) {
        return Promise.reject('邮箱格式不正确！');
    } else {
        return Promise.resolve()
    }
};

export default emailValidator;