export default {
    state: () => ({
        navMenuState: false
    }),
    getters: {
        getNavMenuState(state: any) {
            return state.navMenuState;
        }
    },
    mutations: {
        CHANGE_NAV_MENU_STATE(state: any, val: boolean) {
            state.navMenuState = val;
        }
    },
    actions: {
        showNavMenu(context) {
            context.commit('CHANGE_NAV_MENU_STATE', true);
        },
        hideNavMenu(context) {
            context.commit('CHANGE_NAV_MENU_STATE', false);
        }
    }
}
