# Product Management Project

This project is a web application for product management, using a technology stack that includes
Spring Boot for the backend and React for the frontend.

## Technologies Used

### Backend

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **Maven**

### Frontend

- **JavaScript**
- **React**
- **Axios**
- **Material-UI**

## Project Structure

### Backend

The backend is built with Spring Boot and provides a REST API for CRUD operations on products and
categories. It also includes authentication and authorization using Spring Security.

### Frontend

The frontend is built with React and uses Axios to make API calls. The user interface is styled with
Material-UI.

## Setup and Execution

### Backend

1. Ensure you have Java and Maven installed.
2. Navigate to the backend directory.
3. Run the command `mvn spring-boot:run` to start the server.

### Frontend

1. Ensure you have Node.js and npm installed.
2. Navigate to the `front` directory.
3. Run `npm install` to install the dependencies.
4. Run `npm start` to start the development server.

### Database(PostgreSQL)
1. Ensure you have docker installed.
2. Open src/main/resources/init folder.
3. Run the command `docker-compose up` to start the database.
4. Run the app to create the database and tables with hibernate.
5. Run the script_database.sql to create the database.


## API Endpoints

### Products

- `GET /productApi/products` - Lists all products.
- `POST /productApi/products` - Creates a new product.
- `PUT /productApi/products/{id}` - Updates an existing product.
- `DELETE /productApi/products/{id}` - Deletes a product.

### Categories

- `GET /productApi/categories` - Lists all categories.
- `POST /productApi/categories` - Creates a new category.
- `PUT /productApi/categories/{id}` - Updates an existing category.

### Authentication

- `POST /auth/validate` - Validates user credentials.
- `POST /auth/create` - Creates a new user.

## Main Components

### Frontend

- **LoginPage**: Login page.
- **ProductList**: Product list.
- **ProductForm**: Form to create/edit products.
- **ProtectedRoute**: Protected route that requires authentication.

### Backend

- **ProductController**: REST controller for product operations.
- **AuthController**: REST controller for authentication.
- **ProductService**: Service for product business logic.
- **UserService**: Service for user business logic.
- **CategoryService**: Service for category business logic.

## Page Structure

### Frontend

- **/login**: Login page.
- **/products**: Product listing page (protected).
