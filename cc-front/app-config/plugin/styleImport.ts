import styleImport from 'vite-plugin-style-import'

export function styleImportPlugin() {
    const plugin = styleImport({
        libs: [
            {
                libraryName: 'ant-design-vue',
                esModule: true,
                resolveStyle: (name: string) => {
                    return `ant-design-vue/es/${name}/style/index`
                }
            }
        ]
    })
    return plugin
}
