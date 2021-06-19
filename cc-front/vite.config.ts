
import type { UserConfig, ConfigEnv } from 'vite';
import { loadEnv } from 'vite';
import { resolve } from 'path';

import vue from '@vitejs/plugin-vue';
import vueJsx from '@vitejs/plugin-vue-jsx';
import legacy from '@vitejs/plugin-legacy';
import svgLoader from 'vite-svg-loader'
import { styleImportPlugin } from './vite-config/plugins/style-import-plugin';
import { themeReplacerPlugin } from './vite-config/plugins/theme-replacer-plugin';



import { OUTPUT_DIR } from './vite-config/configs/build-config';
import { wrapperEnv } from './vite-config/utils/build-util';

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
        plugins: [
            vue(),
            vueJsx(),
            svgLoader(),
            legacy({
                targets: ['defaults', 'not IE 11']
            }),
            styleImportPlugin(),
            themeReplacerPlugin()
        ],
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
                    modifyVars: {
                        hack: `true; @import "${resolve(
                            './src/assets/shared/antd/modify-variable.less'
                        )}";`,
                    }
                    , javascriptEnabled: true
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
