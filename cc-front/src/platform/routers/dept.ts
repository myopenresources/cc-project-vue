export default [
    {
        path: 'deptList',
        name: 'DeptList',
        component: () => import('/@/platform/views/dept/DeptList.vue'),
        meta: {
            title: '部门管理'
        }
    }
]
