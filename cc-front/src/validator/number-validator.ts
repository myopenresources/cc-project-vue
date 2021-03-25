import { RuleObject } from "ant-design-vue/lib/form/interface";

const numberValidator = async (rule: RuleObject, value: string) => {
    if (!(/^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value))) {
        return Promise.reject('请输入数字！');
    } else {
        return Promise.resolve()
    }
};

export default numberValidator;