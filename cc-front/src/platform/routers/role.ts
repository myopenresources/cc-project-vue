export default [
    {
        path: 'roleList',
        name: 'RoleList',
        component: () => import('/@/platform/views/role/RoleList.vue'),
        meta: {
            title: '角色管理'
        }
    }
]
