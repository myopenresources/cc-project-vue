export default [
    {
        path: 'loginLogList',
        name: 'LoginLogList',
        component: () => import('../../views/login-log/LoginLogList.vue'),
        meta: {
            title: '登录日志'
        }
    }
]
