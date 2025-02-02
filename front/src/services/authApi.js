import {createApi} from './apiConfig';

const {api} = createApi('v1/auth');

export const validateToken = async (token) => {
  const response = await api.post('/validate/session', {token});
  return response.data;
};

export const login = (username, password) => {
  return api.post('/validate', {username, password})
  .then((response) => response.data)
  .catch((err) => {
    throw err;
  });
};