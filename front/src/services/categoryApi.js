import axios from 'axios';

const baseUrl = 'http://localhost:8888';
const path = 'v1/api/categories';
const api = axios.create({
  baseURL: baseUrl,
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

export const getCategories = async () => {
  const response = await api.get(path, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const createCategory = async (category) => {
  const response = await api.post(path, category, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const updateCategory = async (id, category) => {
  const response = await api.put(`${path}/${id}`, category, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const deleteCategory = async (id) => {
  await api.delete(`${path}/${id}`, {
    headers: getAuthHeaders(),
  });
};
