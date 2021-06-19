import { createApp } from 'vue';
import App from './App.vue';
import { useAll } from './shared/use/use';
import { antdGloabalConfig } from './shared/configs/antd-global-config';
import './main.less';

antdGloabalConfig();


const app = createApp(App);
useAll(app).mount('#app', true);
