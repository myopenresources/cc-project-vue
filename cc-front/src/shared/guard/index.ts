import { Router } from 'vue-router'
import { createProgressGuard, createBrowserTitleGuard, createNotFoundGuard } from '/@/platform/guards/router-guard'

export function createRouterGuard(router: Router) {
    createProgressGuard(router)
    createBrowserTitleGuard(router)
    createNotFoundGuard(router);
}
