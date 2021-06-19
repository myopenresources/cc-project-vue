import { RuleObject } from "ant-design-vue/lib/form/interface";

const chValidator = async (rule: RuleObject, value: string) => {
    if (!(/[\u4E00-\u9FA5\uF900-\uFA2D]/.test(value))) {
        return Promise.reject('请输入中文！');
    } else {
        return Promise.resolve()
    }
};

export default chValidator;