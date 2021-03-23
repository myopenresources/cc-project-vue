export default [
    {
        path: 'myNoticeList',
        name: 'MyNoticeList',
        component: () => import('/@/views/workbench/MyNoticeList.vue'),
        meta: {
            title: '我的公告'
        }
    },
    {
        path: 'myTaskList',
        name: 'MyTaskList',
        component: () => import('/@/views/workbench/MyTaskList.vue'),
        meta: {
            title: '我的待办'
        }
    }
]


