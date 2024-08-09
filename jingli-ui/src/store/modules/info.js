import MyStorage from '@/utils/cache'

const state = {
  busiid: MyStorage.getItem('busiid') || '',
  businame: MyStorage.getItem('businame') || '',
  controlinfo: MyStorage.getItem('controlinfo') || ''
}

const mutations = {
  SET_BUSIID: (state, busiid) => {
    MyStorage.setItem('busiid', busiid)
    state.busiid = busiid
  },
  SET_BUSINAME: (state, businame) => {
    MyStorage.setItem('businame', businame)
    state.businame = businame
  },
  SET_CONTROLINFO: (state, controlinfo) => {
    MyStorage.setItem('controlinfo', controlinfo)
    state.controlinfo = controlinfo
  }
}

const actions = {
  saveBusiInfo ({ commit }, info) {
    return new Promise((resolve, reject) => {
      commit('SET_BUSIID', info.id)
      commit('SET_BUSINAME', info.name)
      resolve()
    }).catch(error => {
      reject(error)
    })
  },
  saveControlInfo ({ commit }, info) {
    return new Promise((resolve, reject) => {
      commit('SET_CONTROLINFO', info)
      resolve()
    }).catch(error => {
      reject(error)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
