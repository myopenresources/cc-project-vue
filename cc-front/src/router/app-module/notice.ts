export default [
    {
        path: 'sysNoticeList',
        name: 'SysNoticeList',
        component: () => import('/@/views/notice/SysNoticeList.vue'),
        meta: {
            title: '系统公告管理'
        }
    }
]
