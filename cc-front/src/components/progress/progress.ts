import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

export function getProgress() {
    return NProgress
}
export function startProgress() {
    NProgress.start()
}

export function doneProgress() {
    NProgress.done()
}
