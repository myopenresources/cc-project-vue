export default [
    {
        path: 'home',
        name: 'Home',
        component: () => import('/@/views/home/Home.vue'),
        meta: {
            title: '主页'
        }
    }
]
