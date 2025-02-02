import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {login} from '../services/authApi';
import {Container, TextField, Button, Typography, Box, Alert, Paper} from '@mui/material';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [serverError, setServerError] = useState(false);
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    setError('');
    setServerError(false);
    login(username, password)
    .then((token) => {
      if (token) {
        sessionStorage.setItem('authToken', token);
        navigate('/products');
      } else {
        setError('Token não recebido');
      }
    })
    .catch((err) => {
      if (err.response
          && (err.response.status === 401
              || err.response.status === 403
              || err.response.status === 404)) {
        setError("Invalid username or password");
      } else {
        setServerError(true);
      }
    });
  };

  return (
      <Container maxWidth="xs" sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
        <Paper elevation={3} sx={{p: 4}}>
          <Box sx={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <Typography variant="h4" component="h1" gutterBottom sx={{whiteSpace: 'nowrap'}}>
              Qima Assessment Login
            </Typography>
            <Box component="form" onSubmit={handleLogin} sx={{mt: 1}}>
              <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="username"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  autoFocus
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
              />
              <TextField
                  margin="normal"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="current-password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
              />
              {error && <Alert severity="error">{error}</Alert>}
              {serverError && <Alert severity="error">Sentimos muito, nossos servidores estão fora do ar</Alert>}
              <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{mt: 3, mb: 2}}
              >
                Login
              </Button>
            </Box>
          </Box>
        </Paper>
      </Container>
  );
};

export default LoginPage;
