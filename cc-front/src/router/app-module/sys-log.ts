export default [
    {
        path: 'sysLog',
        name: 'SysLog',
        component: () => import('/@/views/sys-log/SysLog.vue'),
        meta: {
            title: '系统日志'
        }
    }
]
