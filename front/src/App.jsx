import React, {useState} from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate
} from 'react-router-dom';
import LoginPage from './components/LoginPage';
import ProductList from './components/ProductList';
import ProductForm from './components/ProductForm';
import ProtectedRoute from './components/ProtectedRoute';
import {Container} from '@mui/material';

const App = () => {
  const [editingProduct, setEditingProduct] = useState(null);

  const handleEdit = (product) => {
    setEditingProduct(product);
  };

  const handleSave = () => {
    setEditingProduct(null);
  };

  const handleCancel = () => {
    setEditingProduct(null);
  };

  return (
      <Router>
        <Container>
          <Routes>
            <Route path="/login" element={<LoginPage/>}/>
            <Route
                path="/products"
                element={
                  <ProtectedRoute>
                    {editingProduct ? (
                        <ProductForm product={editingProduct}
                                     onSave={handleSave}
                                     onCancel={handleCancel}/>
                    ) : (
                        <ProductList onEdit={handleEdit}/>
                    )}
                  </ProtectedRoute>
                }
            />
            <Route path="*" element={<Navigate to="/login" replace/>}/>
          </Routes>
        </Container>
      </Router>
  );
};

export default App;
