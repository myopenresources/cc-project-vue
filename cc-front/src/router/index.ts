import { RouteRecordRaw, createRouter, createWebHashHistory } from 'vue-router'
import ImportMetaUtil from '/@/common/util/import-meta-util'
import DeskLayout from '/@/layouts/DeskLayout.vue';
import FullLayout from '/@/layouts/FullLayout.vue';

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'root',
        component: FullLayout as any,
        redirect: '/app/home',
        meta: {
            title: 'root',
            noCache: true
        }
    },
    {
        path: '/app',
        name: 'app',
        component: DeskLayout as any,
        redirect: '/app/home',
        meta: {
            title: 'app'
        },
        children: ImportMetaUtil.getRouterModules(import.meta.globEager('./app-module/**/*.ts'))
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('/@/views/login/Login.vue'),
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
