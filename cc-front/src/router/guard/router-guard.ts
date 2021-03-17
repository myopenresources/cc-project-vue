import { Router } from 'vue-router'
import Environments from '../../common/util/env-util'
import { startProgress, doneProgress } from '/@/components/progress/progress'

const viteSysTitle = Environments.getEvnProp('VITE_SYS_TITLE')

/**
 * 创建进度条
 * @param router 路由对象
 */
export function createProgressGuard(router: Router) {
    router.beforeEach(async (to) => {
        if (to.meta.loaded) return true
        startProgress()
        return true
    })

    router.afterEach(async (to) => {
        if (to.meta.loaded) return true
        doneProgress()
        return true
    })
}

/**
 * 创建浏览器标题
 * @param router 路由对象
 */
export function createBrowserTitleGuard(router: Router) {
    router.beforeEach(async (to) => {
        if (to.meta.loaded) return true
        let sysTitle = viteSysTitle || '管理系统'
        sysTitle += (to.meta && to.meta.title) ? '-' + to.meta.title : ''
        document.title = sysTitle
        return true
    })
}

/**
 * 创建404跳转
 * @param router 
 */
export function createNotFoundGuard(router: Router) {
    router.beforeEach(async (to) => {
        if (to.matched.length === 0) {
            router.push('/app/notFound')
            return true;
        }
    });
}
