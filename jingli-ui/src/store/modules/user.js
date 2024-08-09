import { login, getInfo, getUserMenu } from "@/api/user";
import { getToken, setToken, setName, removeToken } from "@/utils/auth";
import { Message } from "element-ui";
import { resetRouter } from "@/router";
import MyStorage from "@/utils/cache";
// import { setItem } from '@/utils/cache'
import qs from "qs";
const state = {
  token: getToken(),
  name: MyStorage.getItem("name") || "",
  menus: MyStorage.getItem("menus") || [],
  avatar: MyStorage.getItem("avatar") || "",
  userId: MyStorage.getItem("userId") || "",
  admin: MyStorage.getItem("admin") || "",
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    MyStorage.setItem("name", name);
    state.name = name;
  },
  SET_MENUS: (state, menus) => {
    MyStorage.setItem("menus", menus);
    state.menus = menus;
  },
  SET_AVATAR: (state, avatar) => {
    MyStorage.setItem("avatar", avatar);
    state.avatar = avatar;
  },
  SET_USER_ID: (state, userId) => {
    MyStorage.setItem("userId", userId);
    state.userId = userId;
  },
  SET_ADMIN: (state, admin) => {
    MyStorage.setItem("admin", admin);
    state.admin = admin;
  },
};

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo;
    return new Promise((resolve, reject) => {
      login(
        qs.stringify({
          userName: username.trim(),
          password: password,
          platForm: 4,
        })
      )
        .then((result) => {
          if (result.message.code != 0) {
            Message({
              message: result.message.message,
              type: "error",
              duration: 3 * 1000,
            });
            reject(result.message.message);
          } else {
            commit("SET_TOKEN", result.data.token);
            commit("SET_NAME", result.data.userName);
            commit("SET_USER_ID", result.data.userInfo.id);
            commit("SET_ADMIN", result.data.userInfo.admin);
            setToken(result.data.token);
            setName(result.data);
            resolve();
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token)
        .then((response) => {
          const { data } = response;
          if (!data) {
            reject("Verification failed, please Login again.");
          }
          const { name, avatar } = data;
          commit("SET_NAME", name);
          commit("SET_AVATAR", avatar);
          resolve(data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  getUserMenu({ commit, state }) {
    return new Promise((resolve, reject) => {
      getUserMenu(state.token)
        .then((response) => {
          const { data } = response;
          if (!data) {
            reject("Verification failed, please Login again.");
          }

          const { menus } = data;
          commit("SET_MENUS", menus);
          resolve(data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  // user logout
  logout({ commit }) {
    return new Promise((resolve, reject) => {
      MyStorage.clear();
      commit("SET_TOKEN", "");
      commit("SET_NAME", "");
      commit("SET_AVATAR", "");
      removeToken();
      resetRouter();
      resolve();
    });
  },

  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      commit("SET_TOKEN", "");
      removeToken();
      resolve();
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
