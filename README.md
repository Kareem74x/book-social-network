# Book Social Network

This repository contains the backend API for a social network application designed to enable members to share, borrow, and exchange books. It is built with Spring Boot and provides a comprehensive set of features for user and book management.

## ✨ Features

- **User Authentication**: Secure user registration with email verification, login, and token-based authentication using JWT.
- **Book Management**:
    - Add new books to the platform.
    - Upload and manage book cover images.
    - View all available books with pagination.
    - List books owned by the authenticated user.
- **Book Sharing & Transaction System**:
    - Users can list their books as shareable.
    - Borrow books from other users.
    - Return borrowed books.
    - Owners can approve the return of a book.
    - View history of borrowed and returned books.
- **Feedback & Ratings**:
    - Leave ratings (1-5) and comments on books.
    - View all feedback for a specific book.
    - Calculate and display the average rating for each book.
- **Security**: Role-based access control and secured endpoints.
- **API Documentation**: Integrated Swagger UI for interactive API documentation and testing.
- **Email Service**: Asynchronous email sending for account activation.

#### Class diagram
![Class diagram](screenshots/class-diagram.png)

#### Spring security diagram
![Security diagram](screenshots/security.png)

## 🛠️ Technologies Used

- **Backend**:
    - Java 21
    - Spring Boot 3
    - Spring Security (JWT)
    - Spring Data JPA (Hibernate)
- **Database**:
    - PostgreSQL
- **Build & Dependency Management**:
    - Apache Maven
- **Development Tools**:
    - Docker & Docker Compose
    - Lombok
- **API Documentation**:
    - SpringDoc OpenAPI (Swagger UI)
- **Email**:
    - Spring Boot Starter Mail
    - Thymeleaf (for HTML email templates)
    - MailDev (for local email testing)

## 🚀 Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

- JDK 21
- Docker and Docker Compose
- Apache Maven

### Installation & Running

1. **Clone the repository:**
   ```sh
   git clone https://github.com/kareem74x/book-social-network.git
   cd book-social-network
   ```

2. **Start required services with Docker:**
   The `docker-compose.yml` file will set up a PostgreSQL database and a MailDev server for local email testing.
   ```sh
   docker-compose up -d
   ```
    - The PostgreSQL database will be available on port `5432`.
    - The MailDev web UI will be accessible at `http://localhost:1080` to view sent emails.

3. **Run the Spring Boot application:**
   Navigate to the `book-network` directory and use the Maven wrapper to start the application.
   ```sh
   cd book-network
   ./mvnw spring-boot:run
   ```

4. **Access the application:**
    - The API will be available at `http://localhost:8080/api/v1`.
    - You can access the interactive API documentation (Swagger UI) at `http://localhost:8080/api/v1/swagger-ui.html`.

## ⚙️ Configuration

Application settings are located in `src/main/resources/`.

- `application.yml`: Main configuration file, sets the active profile to `dev` and defines the API context path.
- `application-dev.yml`: Development-specific configurations, including:
    - **Datasource**: Connection details for the PostgreSQL database.
    - **Mail**: SMTP settings for the local MailDev server.
    - **JWT**: Secret key and expiration time for JSON Web Tokens.
    - **File Uploads**: The local directory path for storing uploaded book covers.

## 📖 API Endpoints

The application provides a RESTful API for all its features. For a detailed and interactive list of all available endpoints, please run the application and navigate to the Swagger UI at:

**[http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html)**

### Base URL
```
http://localhost:8080/api/v1
```

### Authentication Endpoints
- **POST** `/auth/register` - Register new user
- **GET** `/auth/activate-account?token=<string>` - Activate account
- **POST** `/auth/authenticate` - Login

### Book Endpoints

#### Core Operations
- **POST** `/books/save` - Create/update book
- **GET** `/books` - List all books
- **GET** `/books/{id}` - Get book details
- **POST** `/books/cover` - Upload cover image
- **PATCH** `/books/shareable/{bookId}` - Update sharing status
- **PATCH** `/books/archived/{bookId}` - Update archive status

#### Borrowing System
- **POST** `/books/borrow` - Request to borrow
- **PATCH** `/books/borrow/{bookId}` - Process borrow request
- **PATCH** `/books/borrow/approve/{bookId}` - Approve borrow
- **PATCH** `/books/return/{bookId}` - Return book
- **PATCH** `/books/return/approve/{bookId}` - Approve return

#### Book Lists
- **GET** `/books/borrowed` - View borrowed books
- **GET** `/books/returned` - View returned books
- **GET** `/books/owner` - View books by owner

### Feedback Endpoints
- **POST** `/feedbacks` - Submit feedback
- **GET** `/feedbacks/book/{bookId}` - View book feedback

## 🗂️ Project Structure

```
.
└── book-network/
    └── src/main/java/com/kareem/book_network/
        ├── auth/           # Authentication logic and DTOs
        ├── book/           # Book entity, repository, service, and controller
        ├── common/         # Common classes like BaseEntity and PageResponse
        ├── config/         # Spring configuration beans
        ├── email/          # Email service and templates
        ├── exception/      # Custom exceptions
        ├── feedback/       # Feedback entity and related logic
        ├── file/           # File storage service for uploads
        ├── handler/        # Global exception handler
        ├── history/        # Book transaction history tracking
        ├── role/           # User role entity and repository
        ├── security/       # JWT filter, service, and security configuration
        └── user/           # User entity, repository, and related classes
```

### Project Structure Explanation

- **auth/**: Manages authentication and user registration.
- **book/**: Handles book-related functionalities like adding, updating, and retrieving books.
- **common/**: Contains common utility classes and base entities.
- **config/**: Stores application configuration settings.
- **email/**: Manages email notifications and templates.
- **exception/**: Defines custom exceptions for error handling.
- **feedback/**: Handles book feedback and reviews.
- **file/**: Provides file upload and storage functionality.
- **handler/**: Contains global exception handling mechanisms.
- **history/**: Maintains a record of book borrowing transactions.
- **role/**: Manages user roles and permissions.
- **security/**: Configures security settings including JWT authentication.
- **user/**: Manages user data and authentication tokens.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.