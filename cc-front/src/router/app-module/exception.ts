export default [
    {
        path: 'notFound',
        name: 'NotFound',
        component: () => import('/@/views/exception/NotFound.vue'),
        meta: {
            title: '404'
        }
    }
]