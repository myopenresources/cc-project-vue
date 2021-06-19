export default [
    {
        path: 'loginLogList',
        name: 'LoginLogList',
        component: () => import('/@/platform/views/login-log/LoginLogList.vue'),
        meta: {
            title: '登录日志'
        }
    }
]
