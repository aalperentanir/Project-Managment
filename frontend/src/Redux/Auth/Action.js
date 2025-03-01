import axios from "axios";
import { API_BASE_URL } from "../../Config/api";
import {
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  LOGOUT_REQUEST,
} from "./ActionType";

export const register = (userData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST });

  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/signup`, userData);

    if (data.jwt) {
      localStorage.setItem("jwt", data.jwt);
      dispatch({ type: REGISTER_SUCCESS, payload: data });
    }

    console.log("Register success", data);
  } catch (error) {
    console.log(error);
  }
};

export const login = (userData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST });

  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/signin`, userData);

    if (data.jwt) {
      localStorage.setItem("jwt", data.jwt);
      dispatch({ type: LOGIN_SUCCESS, payload: data });
    }
    console.log("Login success", data);
  } catch (error) {
    console.log(error);
  }
};

export const getUserProfile = () => async (dispatch) => {
  dispatch({ type: GET_USER_REQUEST });

  const token = localStorage.getItem("jwt")
  //console.log("Stored JWT:", token);

  try {
    const { data } = await axios.get(`${API_BASE_URL}/api/users/profile`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    dispatch({ type: GET_USER_SUCCESS, payload: data });

    console.log("Get user success", data);
  } catch (error) {
    console.log("error",error);
  }
};

export const logout = () => async (dispatch) => {
  dispatch({ type: LOGOUT_REQUEST });
  localStorage.clear();
};
