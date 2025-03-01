import api from "../../Config/api";
import {
    ASSIGNED_ISSUE_TO_USER_FAILURE,
    ASSIGNED_ISSUE_TO_USER_REQUEST,
    ASSIGNED_ISSUE_TO_USER_SUCCESS,
    CREATE_ISSUE_FAILURE,
    CREATE_ISSUE_REQUEST,
    CREATE_ISSUE_SUCCESS,
    DELETE_ISSUE_FAILURE,
    DELETE_ISSUE_REQUEST,
    DELETE_ISSUE_SUCCESS,
    FETCH_ISSUES_BY_ID_FAILURE,
    FETCH_ISSUES_BY_ID_REQUEST,
  FETCH_ISSUES_BY_ID_SUCCESS,
  FETCH_ISSUES_FAILURE,
  FETCH_ISSUES_REQUEST,
  FETCH_ISSUES_SUCCESS,
  UPDATE_ISSUE_STATUS_FAILURE,
  UPDATE_ISSUE_STATUS_REQUEST,
  UPDATE_ISSUE_STATUS_SUCCESS,
} from "./ActionType";

export const fetchIssues = (id) => {
  return async (dispatch) => {
    dispatch({ type: FETCH_ISSUES_REQUEST });

    try {
      const response = await api.get(`/api/issue/project/${id}`);
      console.log("fetchIssues", response.data);
      dispatch({ type: FETCH_ISSUES_SUCCESS, issues: response.data });
    } catch (error) {
      dispatch({ type: FETCH_ISSUES_FAILURE, error: error.message });
    }
  };
};

export const fetchIssueById = (id) => {
    return async (dispatch) => {
      dispatch({ type: FETCH_ISSUES_BY_ID_REQUEST });
  
      try {
        const response = await api.get(`/api/issue/${id}`);
        console.log("fetchIssueById", response.data);
        dispatch({ type: FETCH_ISSUES_BY_ID_SUCCESS, issues: response.data });
      } catch (error) {
        dispatch({ type: FETCH_ISSUES_BY_ID_FAILURE, error: response.message });
      }
    };
  };
  

  export const updateIssueStatus = ({id,status}) => {
    return async (dispatch) => {
      dispatch({ type: UPDATE_ISSUE_STATUS_REQUEST });
  
      try {
        const response = await api.put(`/api/issue/${id}/status/${status}`);
        console.log("updateIssueStatus", response.data);
        dispatch({ type: UPDATE_ISSUE_STATUS_SUCCESS, issues: response.data });
      } catch (error) {
        dispatch({ type: UPDATE_ISSUE_STATUS_FAILURE, error: response.message });
      }
    };
  };


  export const assignedUserToIssue = ({issueId,userId}) => {
    return async (dispatch) => {
      dispatch({ type: ASSIGNED_ISSUE_TO_USER_REQUEST });
  
      try {
        const response = await api.put(`/api/issue/${issueId}/assigned/${userId}`);
        console.log("assignedUserToIssue", response.data);
        dispatch({ type: ASSIGNED_ISSUE_TO_USER_SUCCESS, issue: response.data });
      } catch (error) {
        dispatch({ type: ASSIGNED_ISSUE_TO_USER_FAILURE, error: response.message });
      }
    };
  };

  export const createIssue = (issueData) => {
    return async (dispatch) => {
      dispatch({ type: CREATE_ISSUE_REQUEST });
  
      try {
        const response = await api.post(`/api/issue`,issueData);
        console.log("createIssue", response.data);
        dispatch({ type: CREATE_ISSUE_SUCCESS, issue:response.data });
      } catch (error) {
        dispatch({ type: CREATE_ISSUE_FAILURE, error: response.message });
      }
    };
  };

  export const deleteIssue = (issueId) => {
    return async (dispatch) => {
      dispatch({ type: DELETE_ISSUE_REQUEST });
  
      try {
        const response = await api.delete(`/api/issue/${issueId}`);
        console.log("deleteIssue", response.data);
        dispatch({ type: DELETE_ISSUE_SUCCESS, issueId });
      } catch (error) {
        dispatch({ type: DELETE_ISSUE_FAILURE, error: response.message });
      }
    };
  };
