import React, {useEffect, useState} from 'react';
import {Navigate} from 'react-router-dom';
import {validateToken} from '../services/authApi';

const ProtectedRoute = ({children}) => {
  const [isValid, setIsValid] = useState(null);
  const token = sessionStorage.getItem('authToken');

  useEffect(() => {
    const checkToken = async () => {
      try {
        await validateToken(token);
        setIsValid(true);
      } catch (error) {
        setIsValid(false);
      }
    };

    if (token) {
      checkToken();
    } else {
      setIsValid(false);
    }
  }, [token]);

  if (isValid === null) {
    return <div>Loading...</div>;
  }

  if (!isValid) {
    return <Navigate to="/login" replace/>;
  }

  return children;
};

export default ProtectedRoute;
