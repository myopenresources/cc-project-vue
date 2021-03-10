export default [
    {
        path: 'userList',
        name: 'UserList',
        component: () => import('/@/views/user/UserList.vue'),
        meta: {
            title: '用户管理'
        }
    }
]
