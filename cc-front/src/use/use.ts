
import { App } from "vue";
import DefMainLayout from "/@/components/main-layout/DefMainLayout.vue";
import CustomEmpty from '/@/components/custom-empty/CustomEmpty.vue';
import LineTitle from '/@/components/line-title/LineTitle.vue';
import DefDrawerLayout from '/@/components/drawer-layout/DefDrawerLayout.vue';


import scrollbar from "/@/directives/scrollbar/scrollbar";
import errorSrc from "/@/directives/error-src/error-src";

import router from '/@/router/index';
import { createRouterGuard } from '/@/router/guard';

import store from '/@/store/index';

import {
    Button, Form, Input, Card, Dropdown,
    InputNumber, Row, Col, Select, Modal,
    Menu, Table, Alert, Tag, Drawer, Progress,
    Descriptions
} from "ant-design-vue";

/**
 * 使用ant 组件
 * @param app 
 */
function useAntdComponnet(app: App) {
    const comps = [
        Button, Form, Input, Card, Dropdown,
        InputNumber, Row, Col, Select, Modal,
        Menu, Table, Alert, Tag, Drawer, Progress,
        Descriptions
    ];
    comps.forEach((componnet) => {
        app.use(componnet);
    })
}

/**
 *  使用自定义指令
 * @param app  应用根
 */
function useCustomDirectives(app: App) {
    app.directive('app-scrollbar', scrollbar);
    app.directive('app-error-src', errorSrc);
}

/**
 *  使用自定义组件
 * @param app  应用根
 */
function useCustomComponent(app: App) {
    app.component('AppDefMainLayout', DefMainLayout);
    app.component('AppCustomEmpty', CustomEmpty);
    app.component('AppLineTitle', LineTitle);
    app.component('AppDefDrawerLayout', DefDrawerLayout);
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
    useAntdComponnet(app);
    useCustomComponent(app);
    useCustomDirectives(app);
    useRouter(app);
    useStore(app);
    return app;
}


