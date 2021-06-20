import { commonConst } from "../commons/consts/common-const";
import CookieUtils from "../commons/utils/cookie-utils";
import { individuationConst } from '../commons/consts/individuation-const';
const sessionLayout = CookieUtils.get(commonConst.sessionLayout) as string;

export default {
    state: () => ({
        layoutState: sessionLayout ? sessionLayout : individuationConst.layout.adminLayout
    }),
    getters: {
        getLayoutState(state: any) {
            return state.layoutState;
        }
    },
    mutations: {
        CHANGE_LAYOUT_STATE(state: any, val: string) {
            state.layoutState = val;
        }
    },
    actions: {
        toggleLayout({ commit }, layoutVal: any) {
            commit('CHANGE_LAYOUT_STATE', layoutVal)
        }
    }
}
