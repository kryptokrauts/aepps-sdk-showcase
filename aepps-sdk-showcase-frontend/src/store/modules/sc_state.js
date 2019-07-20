import axios from 'axios';

const state = {
    networks: [
        {
            name: 'AE_DEVNET',
            baseURL: 'http://localhost/v2',
            compilerURL: ''
        },
        {
            name: 'AE_TESTNET',
            baseURL: '',
            compilerURL: ''
        },
        {
            name: 'AE_MAINNET',
            baseURL: '',
            compilerURL: ''
        }],
    availableServices: [
        {
            name: 'KeyPairService',
            url: 'http://localhost:4000/keypair',
            alive: false
        }
    ]
};

const getters = {
    getAvailableNetworks: state => state.networks,
    getAvailableServices: state => state.availableServices
};

const actions = {
    // check backend (SDK service) available
    checkAvailableServices({ commit, state }) {
        state.availableServices.forEach(service => {
            async function checkService(service) {
                const response = await axios.get(service.url);
                const available = (200 == response.status);
                service.alive = available;
                //commit('updateAvailableServices', { service, status: available });
            }
            checkService(service).catch(e => { console.log("Service not reachable " + service.url); commit('updateAvailableServices', { service, status: false }); });
        });
    }
};

const mutations = { updateAvailableServices: (state, response) => response.service.alive = response.status };

export default {
    state,
    getters,
    actions,
    mutations
}