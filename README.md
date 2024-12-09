# Foro Hub

## Description
Foro Hub is a web application designed to manage discussion forums. It allows users to register, log in, create, and participate in discussion topics. The application is developed in Java using the Spring Boot framework and connects to a SQL Server database.

## Requirements
- Java 17 or higher
- Spring Boot 3.2.0
- Maven 3.6.3 or higher
- SQL Server

## Project Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/Wilmer200520t/foro_hub.git
    cd foro_hub
    ```

2. Configure the database properties in `src/main/resources/application.properties`:
    ```properties
    spring.application.name=Foro hub
    spring.datasource.url=${DB_URL};databaseName=${DB_NAME};encrypt=true;trustServerCertificate=true
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}
    api.security.secret=${JWT_SECRET}
    ```

   Make sure to define the environment variables `DB_URL`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`, and `JWT_SECRET` with the appropriate values for your SQL Server instance.

3. Build the project with Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Main Endpoints

### Authentication (Access free)
- **POST /login**: Allows users to authenticate.
- **POST /register**: Allows new users to register.

### Forums
- **GET /topic**: Retrieves the list of forums.
- **POST /topic**: Creates a new forum.
- **GET /topic/{topicId}**: Retrieves the list of topics in a forum.
- **PUT /topic/{topicId}**: Updates a forum.

### User
- **GET /user**: Retrieves the logged-in user.
- **PUT /user/update**: Updates the logged-in user.
- **DELETE /user/block**: Blocks the logged-in user.
- **DELETE /user/delete**: Deletes the logged-in user.

## Security
The application's security is handled using JWT (JSON Web Tokens). Each request to protected endpoints must include an authentication token in the `Authorization` header in the format `Bearer <token>`.

