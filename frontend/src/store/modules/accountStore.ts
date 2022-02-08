import { Module } from "vuex";
import { RootState } from "../index";
import axios from "axios";
import router from "../../router";

export interface accountState {
  accessToken: string;
  user_id: string;
  accounts: Array<any>;
  userinfo: any;
  //
  count: number;
}

export const accountStore: Module<accountState, RootState> = {
  namespaced: true,
  state: () => ({
    accessToken: "",
    user_id: "",
    accounts: [],
    userinfo: {},
    count: 0,
  }),
  getters: {
    isLogin: (state) => {
      return !!state.accessToken;
    },
    getUser: (state) => {
      // return JSON.parse(atob(state.accessToken.split('.')[1])).username
      return JSON.parse(atob(state.accessToken.split(".")[1]));
    },
    getUserinfo: (state) => {
      return state.userinfo;
    },
    //
    doubleCount: (state) => {
      return state.count * 2;
    },
  },
  mutations: {
    setToken(state, newAccessToken) {
      state.accessToken = newAccessToken;
    },
    // 로그아웃 시 vuex-persistance 데이터도 삭제
    logout(state) {
      state.accessToken = "";
      state.userinfo = {};
      window.localStorage.clear();
      router.push({ name: "Login" });
    },
    setAccounts(state, newAccounts) {
      state.accounts = newAccounts;
      // console.log(state.accounts)
    },
    setUser(state, userinfo) {
      state.userinfo = userinfo;
      router.push({ name: "About" });
      // console.log(state.accounts)
    },
    //
    increment(state) {
      state.count++;
      console.log(state.count);
    },
  },
  actions: {
    getToken({ commit }, { user_id, password }) {
      axios
        .post("http://localhost:9999/api/v1/auth/login", { user_id, password })
        .then((response) => {
          localStorage.setItem("accessToken", response.data.accessToken);
          commit("setToken", response.data.accessToken);
          // commit("setUserid", user_id);
          axios
            .get("http://localhost:9999/api/v1/users/userInfo/", {
              headers: {
                Authorization: `Bearer ${response.data.accessToken}`,
              },
            })
            .then((response) => {
              commit("setUser", response.data);
            });
        })
        .catch((err) => {
          console.log("에러", err.response);
        });
    },
    getAccounts({ commit }) {
      axios.get("http://127.0.0.1:8000/accounts/").then((response) => {
        commit("setAccounts", response.data);
      });
    },
    getUserinfo({ commit }, accessToken) {
      axios
        .get(`http://localhost:9999/api/v1/users/userInfo/`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        })
        .then((response) => {
          console.log(response);
          commit("setUser", response.data);
        })
        .catch((err) => {
          console.log("에러", err.response);
        });
    },
    //
    incrementNumber({ commit }) {
      commit("increment");
    },
  },
};
