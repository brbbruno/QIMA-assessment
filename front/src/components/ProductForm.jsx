import React, {useState, useEffect} from 'react';
import {createProduct, updateProduct} from '../services/api';
import {
  TextField,
  Button,
  Box,
  FormControlLabel,
  Switch,
  Typography
} from '@mui/material';

const ProductForm = ({product, onSave, onCancel}) => {
  const [formData, setFormData] = useState({
    id: product?.id || null,
    name: product?.name || '',
    description: product?.description || '',
    price: product?.price || '',
    categoryName: product?.categoryName || '',
    categoryId: product?.categoryId || null,
    available: product?.available || false,
  });

  useEffect(() => {
    setFormData({
      id: product?.id || null,
      name: product?.name || '',
      description: product?.description || '',
      price: product?.price || '',
      categoryName: product?.categoryName || '',
      categoryId: product?.categoryId || null,
      available: product?.available || false,
    });
  }, [product]);

  const handleChange = (e) => {
    const {name, value} = e.target;
    setFormData((prev) => ({...prev, [name]: value}));
  };

  const handleSwitchChange = (e) => {
    setFormData((prev) => ({...prev, available: e.target.checked}));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.id) {
      await updateProduct(formData.id, formData);
    } else {
      await createProduct(formData);
    }

    onSave(formData);
  };

  return (
      <Box component="form" onSubmit={handleSubmit} sx={{
        display: 'flex',
        flexDirection: 'column',
        gap: 3,
        maxWidth: 500,
        margin: '0 auto',
        padding: 3,
        boxShadow: 3,
        borderRadius: 2
      }}>
        <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>Product
          Form</Typography>
        <TextField label="Name" name="name" value={formData.name}
                   onChange={handleChange} required fullWidth/>
        <TextField label="Description" name="description"
                   value={formData.description} onChange={handleChange}
                   fullWidth/>
        <TextField label="Price" name="price" type="number"
                   value={formData.price} onChange={handleChange} required
                   fullWidth/>
        <TextField label="Category Name" name="categoryName"
                   value={formData.categoryName} onChange={handleChange}
                   required fullWidth/>
        <FormControlLabel control={<Switch checked={formData.available}
                                           onChange={handleSwitchChange}/>}
                          label="Available"/>
        <Box sx={{display: 'flex', justifyContent: 'space-between'}}>
          <Button type="submit" variant="contained"
                  color="primary">Save</Button>
          <Button onClick={onCancel} variant="outlined"
                  color="secondary">Cancel</Button>
        </Box>
      </Box>
  );
};

export default ProductForm;