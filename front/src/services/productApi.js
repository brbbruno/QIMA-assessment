import {createApi} from './apiConfig';

const {api, getAuthHeaders} = createApi('v1/api/products');

export const getProducts = async () => {
  const response = await api.get('', {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const createProduct = async (product) => {
  const response = await api.post('', product, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const updateProduct = async (id, product) => {
  const response = await api.put(`/${id}`, product, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const deleteProduct = async (id) => {
  await api.delete(`/${id}`, {
    headers: getAuthHeaders(),
  });
};