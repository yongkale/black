//公共
export default {
    namespaced: true,
    state: {
        header_name: "",
        link: '',
        right: '',
        showCity: false,
        targetCity: '',
        currentCity: '西安',
        startCity: '',
        endCity: '',
        showDetial: false,
    },
    mutations: {
        changeStaticState(state, params) {
            state[params.name] = params.newVal;
        }
    },
    actions: {
        changeStaticState(state, params) {
            state[params.name] = params.newVal;
        }
    }
}
