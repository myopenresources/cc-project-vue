import { createStore } from 'vuex'
import ImportMetaUtil from '/@/platform/commons/utils/import-meta-util';

export default createStore({
	modules: ImportMetaUtil.getStoreModules([import.meta.globEager('../../platform/stores/**/*.ts')])
})
