import React, { useEffect, useState } from 'react';
import { getProducts, deleteProduct } from '../services/productApi';
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
  DialogActions,
  Grid,
} from '@mui/material';
import { Add as AddIcon, Edit as EditIcon, Delete as DeleteIcon } from '@mui/icons-material';
import ProductForm from './ProductForm';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [orderBy, setOrderBy] = useState('name');
  const [orderDirection, setOrderDirection] = useState('asc');
  const [openEdit, setOpenEdit] = useState(false);
  const [openCreate, setOpenCreate] = useState(false);
  const [openConfirm, setOpenConfirm] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [productToDelete, setProductToDelete] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      const data = await getProducts();
      setProducts(data);
    };
    fetchProducts().then(r => r);
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
    setProducts((prev) => {
      const existingProductIndex = prev.findIndex((product) => product.id === updatedProduct.id);
      if (existingProductIndex !== -1) {
        const updatedProducts = [...prev];
        updatedProducts[existingProductIndex] = updatedProduct;
        return updatedProducts;
      } else {
        return [...prev, updatedProduct];
      }
    });
    setOpenEdit(false);
    setOpenCreate(false);
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

  const handleOpenCreate = () => {
    setSelectedProduct(null);
    setOpenCreate(true);
  };

  const handleCloseCreate = () => {
    setOpenCreate(false);
  };

  return (
    <Box sx={{ padding: 4, backgroundColor: '#f5f5f5', minHeight: '100vh' }}>
      <Grid container spacing={2} alignItems="center" justifyContent="space-between" sx={{ marginBottom: 3 }}>
        <Grid item>
          <Typography variant="h4" component="div" sx={{ color: '#333' }}>
            List of Products
          </Typography>
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            startIcon={<AddIcon />}
            onClick={handleOpenCreate}
          >
            Add a new product
          </Button>
        </Grid>
      </Grid>
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
              <TableCell align="center">Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {sortedProducts.map((product, index) => (
              <TableRow
                key={product.id}
                sx={{
                  bgcolor: index % 2 === 0 ? 'background.paper' : '#f9f9f9',
                  '&:hover': { backgroundColor: '#e3f2fd' },
                }}
              >
                <TableCell>{product.name}</TableCell>
                <TableCell>{product.description}</TableCell>
                <TableCell>${product.price.toFixed(2)}</TableCell>
                <TableCell>{product.categoryName}</TableCell>
                <TableCell align="center">
                  <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    startIcon={<EditIcon />}
                    sx={{ marginRight: 1 }}
                    onClick={() => handleEdit(product)}
                  >
                    Edit
                  </Button>
                  <Button
                    variant="outlined"
                    color="error"
                    size="small"
                    startIcon={<DeleteIcon />}
                    onClick={() => handleOpenConfirm(product)}
                  >
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Dialog de Edição */}
      <Dialog open={openEdit} onClose={handleCloseEdit} maxWidth="sm" fullWidth>
        <DialogTitle sx={{ backgroundColor: '#1976d2', color: '#fff' }}>
          {selectedProduct ? 'Edit Product' : 'Add Product'}
        </DialogTitle>
        <DialogContent>
          <ProductForm product={selectedProduct} onSave={handleSave} onCancel={handleCloseEdit} />
        </DialogContent>
      </Dialog>

      {/* Dialog de Criação */}
      <Dialog open={openCreate} onClose={handleCloseCreate} maxWidth="sm" fullWidth>
        <DialogTitle sx={{ backgroundColor: '#1976d2', color: '#fff' }}>Add new Product</DialogTitle>
        <DialogContent>
          <ProductForm product={null} onSave={handleSave} onCancel={handleCloseCreate} />
        </DialogContent>
      </Dialog>

      {/* Dialog de Confirmação de Deleção */}
      <Dialog open={openConfirm} onClose={handleCloseConfirm}>
        <DialogTitle>Confirm item deletion?</DialogTitle>
        <DialogContent>
          <Typography>Are you sure you want to delete this product?</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDelete} color="error" variant="contained">
            Yes
          </Button>
          <Button onClick={handleCloseConfirm} color="primary" variant="outlined">
            No
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default ProductList;
