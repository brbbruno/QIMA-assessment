import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
  const token = sessionStorage.getItem('authToken');

  if (!token) {
    // Redireciona para a página de login se o token não existir
    return <Navigate to="/login" replace />;
  }

  // Renderiza o componente filho se o token for válido
  return children;
};

export default ProtectedRoute;
