import { createApp } from 'vue';
import App from './App.vue';
import { useAll } from '/@/use/use';
import { antdGloabalConfig } from '/@/config/antd-global-config';
import './assets/icon/icon.less';
// import '../node_modules/animate.css/animate.css';

antdGloabalConfig();


const app = createApp(App);
useAll(app).mount('#app', true);
