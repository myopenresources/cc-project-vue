import { createApp } from 'vue';
import App from './App.vue';
import { useAll } from '/@/use/use';
import './assets/icon/icon.less';
// import '../node_modules/animate.css/animate.css';

const app = createApp(App);

useAll(app);
app.mount('#app', true);
