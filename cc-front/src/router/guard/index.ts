import { Router } from 'vue-router'
import { createProgressGuard, createBrowserTitleGuard } from './router-guard'

export function createRouterGuard(router: Router) {
    createProgressGuard(router)
    createBrowserTitleGuard(router)
}
