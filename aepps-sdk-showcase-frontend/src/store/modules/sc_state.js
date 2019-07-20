import axios from 'axios';

const state = {
    networks: [
        {
            id: 'AE_DEVNET',
            name: 'Local Node',
            baseURL: 'http://localhost/v2',
            compilerURL: ''
        },
        {
            id: 'AE_TESTNET',
            name: 'Testnet',
            baseURL: '',
            compilerURL: ''
        },
        {
            id: 'AE_MAINNET',
            name: 'Mainnet',
            baseURL: '',
            compilerURL: ''
        }],
    selectedNetwork: 'AE_DEVNET',
    availableServices: [
        {
            name: 'KeyPairService',
            url: 'http://localhost:4000/keypair',
            alive: false
        },
        {
            name: 'KeyPairService 2',
            url: 'http://localhost:4000/keypair',
            alive: false
        },
        {
            name: 'KeyPairService 3',
            url: 'http://localhost:4000/keypair',
            alive: false
        }
    ]
};

const getters = {
    getAvailableNetworks: state => state.networks,
    getAvailableServices: state => state.availableServices,
    getSelectedNetwork: state => state.selectedNetwork
};

const actions = {
    // check backend (SDK service) available
    checkAvailableServices({ commit, state }) {
        state.availableServices.forEach(service => {
            async function checkService(service) {
                const response = await axios.get(service.url);
                const available = (200 == response.status);
                commit('updateAvailableServices', { service, status: available });
            }
            checkService(service).catch(e => { console.log("Service not reachable " + service.url); commit('updateAvailableServices', { service, status: false }); });
        });
    }
};

const mutations = {
    updateAvailableServices: (state, response) => response.service.alive = response.status,
    updateSelectedNetwork: (state, selectedNetwork) => state.selectedNetwork = selectedNetwork
};

export default {
    state,
    getters,
    actions,
    mutations
}