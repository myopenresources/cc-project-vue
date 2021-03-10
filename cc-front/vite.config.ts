
import type { UserConfig, ConfigEnv } from 'vite';
import { loadEnv } from 'vite';
import { resolve } from 'path';

import vue from '@vitejs/plugin-vue'
import { styleImportPlugin } from './config/plugin/styleImport';

import { themeVars } from './config/theme/index';
import { OUTPUT_DIR } from './config/build/buildConfig';
import { wrapperEnv } from './config/build/buildUtils';

function pathResolve(dir: string) {
    return resolve(__dirname, '.', dir);
}


// https://vitejs.dev/config/
export default ({ mode }: ConfigEnv): UserConfig => {
    const root = process.cwd()

    const env = loadEnv(mode, root)

    const viteEnv = wrapperEnv(env)

    const { VITE_DROP_CONSOLE, VITE_LEGACY } = viteEnv

    return {
        plugins: [vue(), styleImportPlugin()],
        resolve: {
            alias: [
                {
                    find: /^\/@\//,
                    replacement: pathResolve('src') + '/'
                }
            ]
        },
        css: {
            preprocessorOptions: {
                less: {
                    modifyVars: themeVars,
                    javascriptEnabled: true
                }
            }
        },
        build: {
            outDir: OUTPUT_DIR,
            polyfillDynamicImport: VITE_LEGACY,
            terserOptions: {
                compress: {
                    keep_infinity: true,
                    drop_console: VITE_DROP_CONSOLE
                }
            },
            brotliSize: false,
            chunkSizeWarningLimit: 1200
        }
    }
}
