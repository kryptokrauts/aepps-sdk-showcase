import Vue from 'vue'
import Vuex from 'vuex'
import sc_state from './modules/sc_state'

// Load Vuex
Vue.use(Vuex)

// Create Store
export default new Vuex.Store({
  modules: {
    sc_state
  }
});
