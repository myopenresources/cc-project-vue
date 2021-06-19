export default [
    {
        path: 'notFound',
        name: 'NotFound',
        component: () => import('/@/platform/views/exception/NotFound.vue'),
        meta: {
            title: '404'
        }
    }
]