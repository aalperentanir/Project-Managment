import api from "../../Config/api";
import {
  GET_USER_SUBSCRIPTION_FAILURE,
  GET_USER_SUBSCRIPTION_REQUEST,
  GET_USER_SUBSCRIPTION_SUCCESS,
  UPGRADE_SUBSCRIPTION_FAILURE,
  UPGRADE_SUBSCRIPTION_REQUEST,
  UPGRADE_SUBSCRIPTION_SUCCESS,
} from "./ActionType";

export const getUserSubscription = () => {
  return async (dispatch) => {
    dispatch({ type: GET_USER_SUBSCRIPTION_REQUEST });
    try {
      const response = await api.get("/api/subscriptions/user", {
        headers:{
          "Authorization":`Bearer ${localStorage.getItem("jwt")}`
        },
      });

      dispatch({ type: GET_USER_SUBSCRIPTION_SUCCESS, payload: response.data });
      console.log("getUserSubscription", response.data);
    } catch (error) {
      console.log("error", error);
      dispatch({ type: GET_USER_SUBSCRIPTION_FAILURE, payload: error.message });
    }
  };
};

export const upgradeSubscription = ({subType}) => {
    return async (dispatch) => {
      dispatch({ type: UPGRADE_SUBSCRIPTION_REQUEST });
      try {
        const response = await api.patch("/api/subscriptions/upgrade",null, {
          headers:{
            "Authorization":`Bearer ${localStorage.getItem("jwt")}`
          },
          params: {
            subType:subType,
          },
        });
  
        dispatch({ type: UPGRADE_SUBSCRIPTION_SUCCESS, payload: response.data });
        console.log("upgradeSubscription", response.data);
      } catch (error) {
        console.log("error", error);
        dispatch({ type: UPGRADE_SUBSCRIPTION_FAILURE, payload: error.message });
      }
    };
  };
