import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {login} from '../services/authApi';
import {
  Container,
  TextField,
  Button,
  Typography,
  Box,
  Alert,
  Paper,
  InputAdornment,
  IconButton, CircularProgress,
} from '@mui/material';
import {
  Person as PersonIcon,
  Lock as LockIcon,
  Visibility as VisibilityIcon,
  VisibilityOff as VisibilityOffIcon,
} from '@mui/icons-material';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [serverError, setServerError] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    setError('');
    setServerError(false);
    setIsLoading(true);

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
      if (err.response &&
          (err.response.status === 401 ||
              err.response.status === 403 ||
              err.response.status === 404)) {
        setError("Invalid username or password");
      } else {
        setServerError(true);
      }
    })
    .finally(() => {
      setIsLoading(false);
    });
  };

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  return (
      <Container
          maxWidth={false}
          sx={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            minHeight: '100vh',
          }}
      >
        <Paper
            elevation={10}
            sx={{
              p: 5,
              width: '100%',
              maxWidth: '400px',
              borderRadius: '15px',
              backgroundColor: 'rgb(234,235,238)',
            }}
        >
          <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            gap: 3,
          }}>
            <Typography
                variant="h4"
                component="h1"
                sx={{
                  fontWeight: 700,
                  color: '#333',
                  textAlign: 'center',
                  mb: 3,
                }}
            >
              QIMA Assessment
            </Typography>

            <Box
                component="form"
                onSubmit={handleLogin}
                sx={{width: '100%'}}
            >
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
                  InputProps={{
                    startAdornment: (
                        <InputAdornment position="start">
                          <PersonIcon sx={{color: '#666'}}/>
                        </InputAdornment>
                    ),
                  }}
                  sx={{
                    '& .MuiOutlinedInput-root': {
                      borderRadius: '10px',
                    },
                  }}
              />

              <TextField
                  margin="normal"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  autoComplete="current-password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  InputProps={{
                    startAdornment: (
                        <InputAdornment position="start">
                          <LockIcon sx={{color: '#666'}}/>
                        </InputAdornment>
                    ),
                    endAdornment: (
                        <InputAdornment position="end">
                          <IconButton
                              aria-label="toggle password visibility"
                              onClick={handleTogglePassword}
                              edge="end"
                          >
                            {showPassword ? <VisibilityOffIcon/> :
                                <VisibilityIcon/>}
                          </IconButton>
                        </InputAdornment>
                    ),
                  }}
                  sx={{
                    '& .MuiOutlinedInput-root': {
                      borderRadius: '10px',
                    },
                  }}
              />

              {error && <Alert severity="error">{error}</Alert>}
              {serverError && <Alert severity="error">We are very sorry, our
                servers are down</Alert>}

              <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  disabled={isLoading}
                  sx={{
                    mt: 3,
                    mb: 2,
                    backgroundColor: '#667eea',
                    padding: '12px',
                    fontSize: '1.1rem',
                    fontWeight: 600,
                    textTransform: 'none',
                    position: 'relative',
                    '&:hover': {
                      backgroundColor: '#4ba25f',
                      transform: 'translateY(-2px)',
                      boxShadow: '0 4px 12px rgba(0,0,0,0.15)',
                    },
                    transition: 'all 0.3s ease',
                    borderRadius: '12px',
                    minHeight: '48px',
                  }}
              >
                {isLoading ? (
                    <Box
                        sx={{
                          display: 'flex',
                          alignItems: 'center',
                          justifyContent: 'center',
                          gap: 1
                        }}
                    >
                      <CircularProgress
                          size={20}
                          sx={{
                            color: 'white'
                          }}
                      />
                      <span>Logging in...</span>
                    </Box>
                ) : (
                    'Login'
                )}
              </Button>
            </Box>
          </Box>
        </Paper>
      </Container>
  );
};

export default LoginPage;
