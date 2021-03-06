import Vue from 'vue'
import Vuex from 'vuex'
import service from './rms-service'

Vue.use(Vuex);

export default new Vuex.Store(
  {
    state: {
      user: null,
    },
    mutations: {
      setUser(state, user) {
        state.user = user;
      },
    }
  }
);
