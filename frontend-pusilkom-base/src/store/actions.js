// https://vuex.vuejs.org/en/actions.html

import { apiServer } from '../setup-axios'

export default {
  login ({ commit }, credentials) {
    return apiServer
      .post('/authenticate', credentials)
      .then(({ data }) => {
        commit('SET_USER_DATA', data)
      })
  },
  logout ({ commit }) {
    commit('LOGOUT')
  },
  setKerjasama (context, payload) {
     context.commit('SET_KERJASAMA', payload)
  },
  setOrganisasi (context, payload) {
    context.commit('SET_ORGANISASI', payload)
  },
  setBerita (context, payload) {
    context.commit('SET_BERITA', payload)
  },
  setStatus (context, payload) {
    context.commit('SET_STATUS', payload)
  },

}
