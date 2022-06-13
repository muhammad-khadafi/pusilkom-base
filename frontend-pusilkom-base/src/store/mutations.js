// https://vuex.vuejs.org/en/mutations.html

import { apiServer } from '../setup-axios'

export default {
  SET_USER_DATA (state, userData) {
    state.user = userData
    localStorage.setItem('user', JSON.stringify(userData))
    apiServer.defaults.headers.common.Authorization = `Bearer ${
      userData.token
      }`
  },
  LOGOUT () {
    localStorage.removeItem('user')
    location.reload()
  },
  SET_KERJASAMA (state, payload) {
    state.kerjasama.id = payload.id
    state.kerjasama.idTipeKerjasama = payload.idTipeKerjasama
    state.kerjasama.idPihakPertama = payload.idPihakPertama
    state.kerjasama.idPihakKedua = payload.idPihakKedua
    state.kerjasama.judul = payload.judul
    state.kerjasama.nilai = payload.nilai
    state.kerjasama.deskripsi = payload.deskripsi
    state.kerjasama.tanggalPerjanjian = payload.tanggalPerjanjian
    state.kerjasama.tanggalMulai = payload.tanggalMulai
    state.kerjasama.tanggalSelesai = payload.tanggalSelesai
    state.kerjasama.message = payload.message
    state.kerjasama.snackbarColor = payload.snackbarColor
  },
  SET_ORGANISASI (state, payload) {
    state.organisasi.snackbarColor = payload.snackbarColor
    state.organisasi.snackbarText = payload.snackbarText
  },
  SET_BERITA (state, payload) {
    state.berita.snackbarColor = payload.snackbarColor
    state.berita.snackbarText = payload.snackbarText
  },
  SET_STATUS (state, payload) {
    state.status.snackbarColor = payload.snackbarColor
    state.status.snackbarText = payload.snackbarText
  },
}
