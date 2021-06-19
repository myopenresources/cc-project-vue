import type { Directive } from 'vue'
import * as lodash from 'lodash'
import OverlayScrollbars from 'overlayscrollbars'
import 'overlayscrollbars/css/OverlayScrollbars.css'

const scrollbar: Directive = {
    mounted(el, binding) {
        const defaultOptions = {
            overflowBehavior: {
                x: 'hidden'
            },
            scrollbars: {
                autoHide: 'leave',
                autoHideDelay:200
            }
        };

        const options = binding.value

        const ops = lodash.merge({}, defaultOptions, options)
        el.scrollbarsInstance = OverlayScrollbars(el, ops);
    },
    unmounted(el) {
        if (el.scrollbarsInstance) {
            el.scrollbarsInstance.destroy();
        }
    }
}

export default scrollbar
