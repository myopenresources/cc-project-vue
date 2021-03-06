import { notification } from "ant-design-vue";

export default class HttpResultUtils {
    static isError(res) {
        return res.data.resultType === "error";
    }

    static isFailure(res) {
        return res.data.resultType === "failure";
    }

    static isSuccess(res) {
        return res.data.resultType === "success";
    }

    static errorTipMsg(res) {
        if (HttpResultUtils.isError(res)) {
            notification.error({
                message: "提示",
                description: res.data.resultMsg
            });
            return true;
        } else {
            return false;
        }
    }

    static failureTipMsg(res) {
        if (HttpResultUtils.isFailure(res)) {
            notification.warning({
                message: "提示",
                description: res.data.resultMsg
            });
            return true;
        } else {
            HttpResultUtils.errorTipMsg(res);
        }
    }

    static successTipMsg(res, cb?: Function) {
        if (HttpResultUtils.isSuccess(res)) {
            notification.success({
                message: "提示",
                description: res.data.resultMsg
            });
            if (cb) {
                cb();
            }
            return true;
        } else {
            return false;
        }
    }
}
