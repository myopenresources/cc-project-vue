import { RouteRecordRaw, createRouter, createWebHashHistory } from 'vue-router'
import ImportMetaUtil from '/@/platform/commons/utils/import-meta-util'
import AppLayout from '/@/platform/layouts/AppLayout';
import FullLayout from '/@/platform/layouts/FullLayout.vue';

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'root',
        component: FullLayout,
        redirect: '/app/home',
        meta: {
            title: 'root',
            noCache: true
        }
    },
    {
        path: '/app',
        name: 'app',
        component: AppLayout,
        redirect: '/app/home',
        meta: {
            title: 'app'
        },
        children: ImportMetaUtil.getRouterModules([import.meta.globEager('../../platform/routers/**/*.ts')])
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('/@/platform/views/login/Login.vue'),
        meta: {
            title: '登录',
            noCache: true
        }
    }
]


const router = createRouter({
    history: createWebHashHistory(),
    routes,
    strict: true
})

export default router
