import api from "../../Config/api";
import {
  CREATE_COMMENT_FAILURE,
  CREATE_COMMENT_REQUEST,
  CREATE_COMMENT_SUCCESS,
  DELETE_COMMENT_FAILURE,
  DELETE_COMMENT_REQUEST,
  DELETE_COMMENT_SUCCESS,
  FETCH_COMMENTS_FAILURE,
  FETCH_COMMENTS_REQUEST,
  FETCH_COMMENTS_SUCCESS,
} from "./ActionType";

export const createComment = (commentData) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_COMMENT_REQUEST });

    try {
      const response = await api.post(`/api/comments`, commentData);
      console.log("createComment", response.data);
      dispatch({ type: CREATE_COMMENT_SUCCESS, comment: response.data });
    } catch (error) {
      console.log("error", error);
      dispatch({ type: CREATE_COMMENT_FAILURE, error: error.message });
    }
  };
};

export const deleteComment = ({commentId}) => {
    return async (dispatch) => {
      dispatch({ type: DELETE_COMMENT_REQUEST });

      console.log("Silinecek Yorum ID:", commentId);
  
      try {
        const response = await api.delete(`/api/comments/${commentId}`);
        console.log("deleteComment", response.data);
        dispatch({ type: DELETE_COMMENT_SUCCESS, commentId });
      } catch (error) {
        console.log("error", error);
        dispatch({ type: DELETE_COMMENT_FAILURE, error: error.message });
      }
    };
  };

  /*export const deleteComment = (comment) => {
    return async (dispatch) => {
      const commentId = typeof comment === "object" ? comment.id : comment;
  
      console.log("Silinecek Yorum ID:", commentId); // DEBUG
  
      dispatch({ type: DELETE_COMMENT_REQUEST });
  
      try {
        const response = await api.delete(`/api/comments/${commentId}`);
        console.log("Silme Başarılı:", response.data);
  
        dispatch({ type: DELETE_COMMENT_SUCCESS, commentId });
      } catch (error) {
        console.log("Silme Hatası:", error);
        dispatch({ type: DELETE_COMMENT_FAILURE, error: error.message });
      }
    };
  };*/
  
  

  export const fetchComment = (issueId) => {
    return async (dispatch) => {
      dispatch({ type: FETCH_COMMENTS_REQUEST });
  
      try {
        const response = await api.get(`/api/comments/${issueId}`);
        console.log("fetchComment", response.data);
        dispatch({ type: FETCH_COMMENTS_SUCCESS, comments:response.data });
      } catch (error) {
        console.log("error", error);
        dispatch({ type: FETCH_COMMENTS_FAILURE, error: error.message });
      }
    };
  };
