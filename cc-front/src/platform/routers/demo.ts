export default [
    {
        path: 'cardList',
        name: 'CardList',
        component: () => import('/@/platform/views/demo/CardList.vue'),
        meta: {
            title: '卡片列表'
        }
    }, {
        path: 'tabList',
        name: 'TabList',
        component: () => import('/@/platform/views/demo/TabList.vue'),
        meta: {
            title: '页签列表'
        }
    }, {
        path: 'chartList',
        name: 'ChartList',
        component: () => import('/@/platform/views/demo/ChartList.vue'),
        meta: {
            title: '图表列表'
        }
    }



]
