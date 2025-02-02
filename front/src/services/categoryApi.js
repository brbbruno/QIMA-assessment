import { createApi } from './apiConfig';

const { api, getAuthHeaders } = createApi('v1/api/categories');

export const getCategories = async () => {
  const response = await api.get('', {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const createCategory = async (category) => {
  const response = await api.post('', category, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const updateCategory = async (id, category) => {
  const response = await api.put(`/${id}`, category, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const deleteCategory = async (id) => {
  await api.delete(`/${id}`, {
    headers: getAuthHeaders(),
  });
};