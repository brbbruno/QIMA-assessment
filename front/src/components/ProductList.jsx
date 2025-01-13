import React, { useEffect, useState } from 'react';
import { getProducts, deleteProduct } from '../services/api';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TableSortLabel,
  Button,
  Paper,
  Box,
  Typography,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions
} from '@mui/material';
import ProductForm from './ProductForm';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [orderBy, setOrderBy] = useState('name');
  const [orderDirection, setOrderDirection] = useState('asc');
  const [openEdit, setOpenEdit] = useState(false);
  const [openConfirm, setOpenConfirm] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [productToDelete, setProductToDelete] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      const data = await getProducts();
      setProducts(data);
    };
    fetchProducts();
  }, []);

  const handleDelete = async () => {
    await deleteProduct(productToDelete.id);
    setProducts((prev) => prev.filter((product) => product.id !== productToDelete.id));
    setOpenConfirm(false);
    setProductToDelete(null);
  };

  const handleSort = (column) => {
    const isAsc = orderBy === column && orderDirection === 'asc';
    setOrderDirection(isAsc ? 'desc' : 'asc');
    setOrderBy(column);
  };

  const sortedProducts = [...products].sort((a, b) => {
    if (a[orderBy] < b[orderBy]) {
      return orderDirection === 'asc' ? -1 : 1;
    }
    if (a[orderBy] > b[orderBy]) {
      return orderDirection === 'asc' ? 1 : -1;
    }
    return 0;
  });

  const handleEdit = (product) => {
    setSelectedProduct(product);
    setOpenEdit(true);
  };

  const handleCloseEdit = () => {
    setOpenEdit(false);
    setSelectedProduct(null);
  };

  const handleSave = (updatedProduct) => {
    setProducts((prev) => prev.map((product) => (product.id === updatedProduct.id ? updatedProduct : product)));
    setOpenEdit(false);
    setSelectedProduct(null);
  };

  const handleOpenConfirm = (product) => {
    setProductToDelete(product);
    setOpenConfirm(true);
  };

  const handleCloseConfirm = () => {
    setOpenConfirm(false);
    setProductToDelete(null);
  };

  return (
      <Box sx={{ padding: 3 }}>
        <Typography variant="h4" component="div" sx={{ textAlign: 'center', marginBottom: 3 }}>Product List</Typography>
        <TableContainer component={Paper} sx={{ boxShadow: 3, borderRadius: 2 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>
                  <TableSortLabel
                      active={orderBy === 'name'}
                      direction={orderBy === 'name' ? orderDirection : 'asc'}
                      onClick={() => handleSort('name')}
                  >
                    Name
                  </TableSortLabel>
                </TableCell>
                <TableCell>
                  <TableSortLabel
                      active={orderBy === 'description'}
                      direction={orderBy === 'description' ? orderDirection : 'asc'}
                      onClick={() => handleSort('description')}
                  >
                    Description
                  </TableSortLabel>
                </TableCell>
                <TableCell>
                  <TableSortLabel
                      active={orderBy === 'price'}
                      direction={orderBy === 'price' ? orderDirection : 'asc'}
                      onClick={() => handleSort('price')}
                  >
                    Price
                  </TableSortLabel>
                </TableCell>
                <TableCell>
                  <TableSortLabel
                      active={orderBy === 'categoryName'}
                      direction={orderBy === 'categoryName' ? orderDirection : 'asc'}
                      onClick={() => handleSort('categoryName')}
                  >
                    Category
                  </TableSortLabel>
                </TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {sortedProducts.map((product) => (
                  <TableRow key={product.id}>
                    <TableCell>{product.name}</TableCell>
                    <TableCell>{product.description}</TableCell>
                    <TableCell>${product.price}</TableCell>
                    <TableCell>{product.categoryName}</TableCell>
                    <TableCell>
                      <Button variant="contained" color="primary" sx={{ marginRight: 1 }} onClick={() => handleEdit(product)}>Edit</Button>
                      <Button variant="outlined" color="error" onClick={() => handleOpenConfirm(product)}>Delete</Button>
                    </TableCell>
                  </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        <Dialog open={openEdit} onClose={handleCloseEdit} maxWidth="sm" fullWidth>
          <DialogContent>
            <ProductForm product={selectedProduct} onSave={handleSave} onCancel={handleCloseEdit} />
          </DialogContent>
        </Dialog>

        <Dialog open={openConfirm} onClose={handleCloseConfirm}>
          <DialogTitle>Confirm Deletion</DialogTitle>
          <DialogContent>
            <Typography>Are you sure you want to delete this product?</Typography>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleDelete} color="primary">Yes</Button>
            <Button onClick={handleCloseConfirm} color="secondary">No</Button>
          </DialogActions>
        </Dialog>
      </Box>
  );
};

export default ProductList;