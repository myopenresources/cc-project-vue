export default [
    {
        path: 'sysLogList',
        name: 'SysLogList',
        component: () => import('../../views/sys-log/SysLogList.vue'),
        meta: {
            title: '系统日志'
        }
    }
]
