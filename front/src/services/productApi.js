import axios from 'axios';

const baseUrl = 'http://localhost:8888';
const path = 'v1/api/products';
const api = axios.create({
  baseURL: baseUrl,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Adicione esta linha para enviar cookies e credenciais
});

const getAuthHeaders = () => {
  const token = sessionStorage.getItem('authToken');
  return {
    Authorization: `Bearer ${token}`,
  };
};

export const getProducts = async () => {
  const response = await api.get(path, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const createProduct = async (product) => {
  const response = await api.post(path, product, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const updateProduct = async (id, product) => {
  const response = await api.put(`${path}/${id}`, product, {
    headers: getAuthHeaders(),
  });
  return response.data;
};

export const deleteProduct = async (id) => {
  await api.delete(`${path}/${id}`, {
    headers: getAuthHeaders(),
  });
};

export const login = (username, password) => {
  return api.post('/auth/validate', {username, password})
  .then((response) => response.data)
  .catch((err) => {
    throw err;
  });
};
