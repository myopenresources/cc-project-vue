export default [
    {
        path: 'cardList',
        name: 'CardList',
        component: () => import('/@/views/demo/CardList.vue'),
        meta: {
            title: '卡片列表'
        }
    }, {
        path: 'tabList',
        name: 'TabList',
        component: () => import('/@/views/demo/TabList.vue'),
        meta: {
            title: '页签列表'
        }
    }, {
        path: 'chartList',
        name: 'ChartList',
        component: () => import('/@/views/demo/ChartList.vue'),
        meta: {
            title: '图表列表'
        }
    }



]
