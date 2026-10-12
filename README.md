# Proyecto3_lab1_backend
# Product and Category Management REST API

This project is a REST API developed in **Java** using the **Spring Boot** framework to manage products and their corresponding categories. It implements authentication and authorization mechanisms using JWT tokens and Role-Based Access Control (RBAC).

---

## Technologies and Architecture

- **Language / Framework:** Java - Spring Boot (Spring MVC, Spring Data JPA, Spring Security)
- **Security:** JWT (JSON Web Tokens)
- **CORS Configuration:** Enabled via a global configuration object (`CorsConfigurationSource`)
- **Database:** PostgreSQL (Local instance, non-cloud)

---

## Data Model

### 1. Category Entity (`Categoria`)
- `id`: Unique identifier (Long, Auto-incremental)
- `nombre`: Category name
- `descripcion`: Detailed category description

### 2. Product Entity (`Producto`)
- `id`: Unique identifier (Long, Auto-incremental)
- `nombre`: Product name
- `descripcion`: Product description
- `precio`: Unit price
- `cantidadEnStock`: Available stock
- `categoria`: `@ManyToOne` relationship pointing to `Categoria`. 
  - *Note:* A product belongs to a single category, whereas a category can be associated with one or more products.

---

## Security, Users, and Roles

The application utilizes a centralized security scheme with encrypted passwords stored in the database (via `BCryptPasswordEncoder`).

### System Roles (Registered in the Database):
1. **`SUPER-ADMIN-ROLE`**: Full control over the system (Read, Create, Update, and Delete).
2. **`USER`**: Read-only permissions on the catalog.

> **Important:** There is no method or endpoint for public or dynamic user registration. 

### Initial Data (Data Seeding)
When the application starts for the first time, the following are automatically inserted into the local database:
- The roles `SUPER-ADMIN-ROLE` and `USER`.
- A default user assigned the `SUPER-ADMIN-ROLE` role (with an encrypted password).
- A default user assigned the `USER` role (with an encrypted password).

---

## Permissions Matrix and Endpoints

| HTTP Method | Endpoint | Description | Required Permissions |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authentication and JWT token retrieval | Public |
| `GET` | `/api/productos` | List all products | Authenticated (`USER` or `SUPER-ADMIN-ROLE`) |
| `GET` | `/api/categorias` | List all categories | Authenticated (`USER` or `SUPER-ADMIN-ROLE`) |
| `POST` | `/api/productos` | Register a new product | `SUPER-ADMIN-ROLE` |
| `POST` | `/api/categorias` | Register a new category | `SUPER-ADMIN-ROLE` |
| `PUT` | `/api/productos/{id}` | Update an existing product | `SUPER-ADMIN-ROLE` |
| `PUT` | `/api/categorias/{id}` | Update an existing category | `SUPER-ADMIN-ROLE` |
| `DELETE` | `/api/productos/{id}` | Delete a product | `SUPER-ADMIN-ROLE` |
| `DELETE` | `/api/categorias/{id}` | Delete a category | `SUPER-ADMIN-ROLE` |

---

## Testing with Postman / Insomnia

A JSON collection file (`coleccion_postman.json`) is available in the root of the repository for direct import into Postman or Insomnia. 

The collection includes pre-configured requests for:
1. **Authentication:**
   - Login as `SUPER-ADMIN-ROLE`
   - Login as `USER`
2. **Category Operations:**
   - List Categories
   - Register Category
   - Update Category
   - Delete Category
3. **Product Operations:**
   - List Products
   - Register Product
   - Update Product
   - Delete Product

---

## Local Execution Requirements

1. Make sure you have an active instance of **PostgreSQL** running locally.
2. Configure your database credentials in `src/main/resources/application.yml`:

   ```yaml
   spring:
     application:
       name: app_name
       group: group_name
       version: x.x.x
       admin:
         enable: true

     datasource:
       url: jdbc:postgresql://localhost:5432/postgres
       username: tu_usuario_local
       password: tu_contraseña_local

     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
