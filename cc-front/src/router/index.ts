import { RouteRecordRaw, createRouter, createWebHashHistory } from 'vue-router'
import ImportMetaUtil from '/@/common/util/import-meta-util'
import { AppFullLayout, AppDeskLayout } from '/@/layouts'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'root',
        component: AppFullLayout as any,
        redirect: '/app/home',
        meta: {
            title: 'root',
            noCache: true
        }
    },
    {
        path: '/app',
        name: 'app',
        component: AppDeskLayout as any,
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
