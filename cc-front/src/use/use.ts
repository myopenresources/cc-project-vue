
import { App } from "vue";
import { DefMainLayout } from "/@/components/main-layout";

import scrollbar from "/@/directives/scrollbar/scrollbar";
import errorSrc from "/@/directives/error-src/error-src";

import router from '/@/router/index';
import { createRouterGuard } from '/@/router/guard';

import store from '/@/store/index';



/**
 *  使用自定义指令
 * @param app  应用根
 */
function useCustomDirectives(app: App) {
    app.directive('scrollbar', scrollbar);
    app.directive('error-src', errorSrc);
}

/**
 *  使用自定义组件
 * @param app  应用根
 */
function useCustomComponent(app: App) {
    app.component('AppDefMainLayout', DefMainLayout);
}

/**
 * 使用路由
 * @param app 
 */
function useRouter(app: App) {
    app.use(router);
    // 创建路由守卫
    createRouterGuard(router);
}

/**
 * 使用store
 * @param app 
 */
function useStore(app: App) {
    app.use(store);
}


/**
 * 使用所有
 * @param app  应用根
 */
export function useAll(app: App) {
    useCustomComponent(app);
    useCustomDirectives(app);
    useRouter(app);
    useStore(app);
}


