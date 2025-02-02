import axios from 'axios';

const baseUrl = 'http://localhost:8888';

export const createApi = (path) => {
  const api = axios.create({
    baseURL: `${baseUrl}/${path}`,
    headers: {
      "Content-Type": "application/json",
    },
    withCredentials: true,
  });

  const getAuthHeaders = () => {
    const token = sessionStorage.getItem('authToken');
    return {
      Authorization: `Bearer ${token}`,
    };
  };

  return {api, getAuthHeaders};
};