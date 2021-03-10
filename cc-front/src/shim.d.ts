/*declare module '*.vue' {
    import Vue from 'vue'
    export default Vue
}*/
/* eslint-disable */
declare module '*.vue' {
    const component: Component;
    export default component;
}
