export default [
    {
        path: 'userList',
        name: 'UserList',
        component: () => import('/@/platform/views/user/UserList.vue'),
        meta: {
            title: '用户管理'
        }
    }
]
