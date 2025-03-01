import {
  GET_USER_SUBSCRIPTION_FAILURE,
  GET_USER_SUBSCRIPTION_REQUEST,
  GET_USER_SUBSCRIPTION_SUCCESS,
  UPGRADE_SUBSCRIPTION_FAILURE,
  UPGRADE_SUBSCRIPTION_REQUEST,
  UPGRADE_SUBSCRIPTION_SUCCESS,
} from "./ActionType";

const initialState = {
  usersSubscription: null,
  loading: false,
  error: null,
};

export const subscriptionReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_USER_SUBSCRIPTION_REQUEST:
    case UPGRADE_SUBSCRIPTION_REQUEST:
      return { ...state, loading: true, error: null };

    case GET_USER_SUBSCRIPTION_SUCCESS:
      return {
        ...state,
        loading: false,
        usersSubscription: action.payload,
        error: null,
      };

    case UPGRADE_SUBSCRIPTION_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        usersSubscription: action.payload,
      };

    case GET_USER_SUBSCRIPTION_FAILURE:
    case UPGRADE_SUBSCRIPTION_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default subscriptionReducer;
