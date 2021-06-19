export default [
    {
        path: 'home',
        name: 'Home',
        component: () => import('/@/platform/views/home/Home.vue'),
        meta: {
            title: '主页'
        }
    }
]
