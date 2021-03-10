import { createStore } from 'vuex'
import ImportMetaUtil from '/@/common/util/import-meta-util';

export default createStore({
	modules: ImportMetaUtil.getStoreModules(import.meta.globEager('./module/**/*.ts'))
})
