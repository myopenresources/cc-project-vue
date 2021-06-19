export default {
    state: () => ({
        visitedViews: [],
        cachedViews: []
    }),
    getters: {
        getVisitedViews(state: any) {
            return state.visitedViews;
        },
        getCachedViews(state: any) {
            return state.cachedViews;
        }
    },
    mutations: {
        ADD_VISITED_VIEW: (state: any, view: any) => {
            if (state.visitedViews.some((v: any) => v.path === view.path)) {
                return;
            }

            state.visitedViews.push(Object.assign({}, view, {
                title: view.meta.title || 'no-name'
            }))

            if (!view.meta.noCache) {
                state.cachedViews.push(view.name)
            }
        },
        DEL_VISITED_VIEW: (state: any, view: any) => {
            for (const [i, v] of state.visitedViews.entries()) {
                if (v.path === view.path) {
                    state.visitedViews.splice(i, 1)
                    break
                }
            }
            for (const i of state.cachedViews) {
                if (i === view.name) {
                    const index = state.cachedViews.indexOf(i)
                    state.cachedViews.splice(index, 1)
                    break
                }
            }
        },
        DEL_OTHERS_VIEWS: (state: any, view: any) => {
            for (const [i, v] of state.visitedViews.entries()) {
                if (v.path === view.path) {
                    state.visitedViews = state.visitedViews.slice(i, i + 1)
                    break
                }
            }
            for (const i of state.cachedViews) {
                if (i === view.name) {
                    const index = state.cachedViews.indexOf(i)
                    state.cachedViews = state.cachedViews.slice(index, i + 1)
                    break
                }
            }
        },
        DEL_ALL_VIEWS: (state: any) => {
            state.visitedViews = []
            state.cachedViews = []
        }
    },
    actions: {
        addVisitedView({ commit }, view: any) {
            commit('ADD_VISITED_VIEW', view)
        },
        delVisitedView({ commit, state }, view: any) {
            return new Promise((resolve) => {
                commit('DEL_VISITED_VIEW', view)
                resolve([...state.visitedViews])
            })
        },
        delOthersViews({ commit, state }, view: any) {
            return new Promise((resolve) => {
                commit('DEL_OTHERS_VIEWS', view)
                resolve([...state.visitedViews])
            })
        },
        delAllViews({ commit, state }) {
            return new Promise((resolve) => {
                commit('DEL_ALL_VIEWS')
                resolve([...state.visitedViews])
            })
        }
    }
}
