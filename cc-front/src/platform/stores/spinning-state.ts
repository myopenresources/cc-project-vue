export default {
    state: () => ({
        spinningState: false
    }),
    getters: {
        getSpinningState(state: any) {
            return state.spinningState;
        }
    },
    mutations: {
        CHANGE_SPINNING_STATE(state: any, val: boolean) {
            state.spinningState = val;
        }
    },
    actions: {
        showSpinning(context) {
            context.commit('CHANGE_SPINNING_STATE', true);
        },
        hideSpinning(context) {
            context.commit('CHANGE_SPINNING_STATE', false);
        }
    }
}
