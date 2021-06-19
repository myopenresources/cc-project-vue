
import { notification } from "ant-design-vue";
import SysStorageUtils from "/@/platform/commons/utils/sys-storage-utils";
import { errorType, sysErrorResult } from '/@/platform/commons/consts/error-const';
import SecurityUtils from "/@/platform/commons/utils/security-utils";
import Environments from "/@/platform/commons/utils/env-util";
import SingleModal from "/@/platform/components/single-modal/single-modal";
import { Router } from "vue-router";
import { Store } from "vuex";
import http from "/@/platform/commons/utils/axios-util";





const registerHttpInterceptor = ( router: Router, store: Store<any>) => {

    const getSecurityKey = () => {
        const key = SysStorageUtils.getSecurityKey();
        if (!key) {
            throw errorType.securityKeyError;
        }
        return SecurityUtils.decrypt(key, Environments.getEvnProp("VITE_APP_S_KEY"));
    }

    http.interceptors.request.use(config => {


        config.url = config.url + Environments.getEvnProp("VITE_APP_WEB_SUFFIX");

        if ((config.data && config.data.convert === undefined) || (config.data && config.data.convert === true)) {
            const params = {
                data: SecurityUtils.encrypt(JSON.stringify(config.data), getSecurityKey()),
                convert: true
            };
            config.data = params;
        }

        const useSpinning = config.data && config.data.useSpinning !== undefined && config.data.useSpinning === false ? false : true;

        if (useSpinning) {
            store.dispatch("spinningState/showSpinning");
        }


        if (Environments.getEvnProp("VITE_SHOW_CONSOLE") === 'true') {
            console.info(config)
        }

        return config;
    }, error => {
        store.dispatch("spinningState/hideSpinning");

        notification.error({
            message: "提示",
            description: error
        });

        Promise.reject(error);
    });

    http.interceptors.response.use(
        (response: any) => {
            store.dispatch("spinningState/hideSpinning");

            if (response.data && response.data.sysErrorResult) {
                let errorMsg = "未知系统错误！";
                let skip = false;

                if (response.data.sysErrorResult === sysErrorResult.sessionTimeOut) {
                    errorMsg = "未登录系统或登录超时，请重新登录！";
                    skip = true;

                    SingleModal.open('提示', errorMsg, () => {
                        router.push('/login');
                        SingleModal.close();
                        return Promise.reject(errorType.sessionTimeOutError);
                    });

                } else if (response.data.sysErrorResult === sysErrorResult.noAuthority) {
                    errorMsg = "权限验证未通过！";
                } else if (response.data.sysErrorResult === sysErrorResult.undistributedRole) {
                    errorMsg = "未分配角色！";
                }

                if (!skip) {
                    notification.error({
                        message: "提示",
                        description: errorMsg
                    })
                }

                console.error(errorMsg)

                return response;

            } else if (response.data) {
                let realData = "";
                if (response.data.data && response.data.convert === false) {
                    realData = SecurityUtils.decrypt(response.data.data, Environments.getEvnProp("VITE_APP_S_KEY"));
                } else {
                    realData = SecurityUtils.decrypt(response.data.data, getSecurityKey());
                }

                response.data = JSON.parse(realData);

                if (Environments.getEvnProp("VITE_SHOW_CONSOLE") === 'true') {
                    console.info(response.data)
                }

                return response;
            }
        },
        (error: any) => {

            store.dispatch("spinningState/hideSpinning");
            const errorObj = JSON.parse(JSON.stringify(error));
            if (errorObj.response) {
                notification.error({
                    message: "提示",
                    description: "系统错误，状态码：" + errorObj.response.status
                });
            } else if (errorObj.type) {
                if (errorObj.type === errorType.securityKeyError.type) {
                    notification.error({
                        message: "提示",
                        description: errorType.securityKeyError.errorMsg
                    });
                }
            } else {
                notification.error({
                    message: "提示",
                    description: "网络异常，请重试！"
                });
            }
            return Promise.reject(error);
        }
    );
}

export default registerHttpInterceptor;
