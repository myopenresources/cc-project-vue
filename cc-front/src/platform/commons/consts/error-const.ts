export const sysErrorResult = {
    sessionTimeOut: "SESSION_TIME_OUT",
    noAuthority: "NO_AUTHORITY",
    undistributedRole: "UNDISTRIBUTED_ROLE"
};

export const errorType = {
    securityKeyError: {
        type: "SECURITY_KEY_ERROR",
        errorMsg: "无法获取解码密钥！"
    },
    sessionTimeOutError:{
        type: "SESSION_TIME_OUT_ERROR",
        errorMsg: "未登录系统或登录超时！"
    }
};