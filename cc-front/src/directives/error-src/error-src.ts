import type { Directive } from 'vue'

const errorSrc: Directive = {
    mounted(el, binding) {

        const errSrc = binding.value;
        console.info(errSrc)
        el.addEventListener('error', () => {
            if (errSrc !== '') {
                el.src = errSrc;
            }
        });
    },
    unmounted(el) {
        if (el.scrollbarsInstance) {
            el.scrollbarsInstance.destroy();
        }
    }
}

export default errorSrc
