export default [
    {
        path: 'myNoticeList',
        name: 'MyNoticeList',
        component: () => import('/@/platform/views/workbench/MyNoticeList.vue'),
        meta: {
            title: '我的公告'
        }
    },
    {
        path: 'myTaskList',
        name: 'MyTaskList',
        component: () => import('/@/platform/views/workbench/MyTaskList.vue'),
        meta: {
            title: '我的待办'
        }
    }
]


