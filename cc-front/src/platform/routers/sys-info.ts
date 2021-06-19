export default [
    {
        path: 'sysInfo',
        name: 'SysInfo',
        component: () => import('/@/platform/views/sys-info/SysInfo.vue'),
        meta: {
            title: '系统信息'
        }
    }
]
