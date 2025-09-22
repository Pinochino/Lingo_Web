import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  headers: { 'Content-Type': 'application/json' }
});

export const getListTests = (params) => {
  return api.get("/api/v1/tests", { params });
}

export const getAttemptUserShort = (userId) => {
  return api.get("api/v1/attempt", { params: { userId } })
};

export const getAttempt = (attemptId) => {
  return api.get(`api/v1/attempt/${attemptId}`)
};

export default api;