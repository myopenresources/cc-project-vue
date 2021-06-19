
import { App } from "vue";
import DefMainLayout from "/@/platform/components/main-layout/DefMainLayout.vue";
import CustomEmpty from '/@/platform/components/custom-empty/CustomEmpty.vue';
import LineTitle from '/@/platform/components/line-title/LineTitle.vue';
import DefDrawerLayout from '/@/platform/components/drawer-layout/DefDrawerLayout.vue';
import BusinessToolBtns from '/@/platform/components/business-btn/BusinessToolBtns.vue';
import CustomButton from '/@/platform/components/custom-button/CustomButton.vue';
import BusinessRowBtns from '/@/platform/components/business-btn/BusinessRowBtns.vue';
import BusinessDropdownBtns from '/@/platform/components/business-btn/BusinessDropdownBtns.vue';
import DynamicIcon from "/@/platform/components/dynamic-icon/DynamicIcon";


import scrollbar from "../../platform/directives/scrollbar/scrollbar";
import errorSrc from "../../platform/directives/error-src/error-src";

import router from '/@/shared/router';
import { createRouterGuard } from '/@/shared/guard';

import store from '../store/index';

import {
    Button, Form, Input, Card, Dropdown,
    InputNumber, Row, Col, Select, Modal,
    Menu, Table, Alert, Tag, Drawer, Progress,
    Descriptions, Divider, List, DatePicker,
    Tabs, Tree, Checkbox, Transfer, Tooltip,
    TreeSelect, Collapse
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
        Descriptions, Divider, List, DatePicker,
        Tabs, Tree, Checkbox, Transfer, Tooltip,
        TreeSelect, Collapse
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
    app.component('AppCustomButton', CustomButton);
    app.component('AppBusinessToolBtns', BusinessToolBtns);
    app.component('AppBusinessRowBtns', BusinessRowBtns);
    app.component('AppBusinessDropdownBtns', BusinessDropdownBtns);
    app.component('AppDynamicIcon', DynamicIcon);
    
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


