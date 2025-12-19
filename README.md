# PremierZone

A Spring Boot application that provides a REST API to manage football players. This project serves as a demonstration of building a simple RESTful web service using the Spring Boot framework.

## Technologies Used

*   **Java**: Version 21
*   **Spring Boot**: Version 3.4.12
*   **Maven**: For project build and dependency management
*   **PostgreSQL**: As the relational database to store player data
*   **Spring Data JPA**: To interact with the database
*   **Spring Web**: For building the REST API
*   **Springdoc OpenAPI**: For generating API documentation (Swagger UI)

## Features

*   **GET Players**: Retrieve a list of all players or filter them by:
    *   Team and position
    *   Team
    *   Name
    *   Position
    *   Nation
*   **GET Players (Paginated)**: Retrieve players in pages.
*   **ADD Player**: Add a new player to the database.
*   **UPDATE Player**: Update an existing player's information.
*   **DELETE Player**: Remove a player from the database.

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/premiere-zone.git
    cd premiere-zone
    ```

2.  **Configure the database:**
    Open the `src/main/resources/application.properties` file and update the following properties to match your PostgreSQL database configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3.  **Run the application:**
    You can run the application using the Maven wrapper included in the project:
    ```bash
    ./mvnw spring-boot:run
    ```
    The application will start on the default port `8080`.

## API Endpoints

The base URL for the API is `/api/player`.

| Method | Endpoint                                   | Description                                       |
|--------|--------------------------------------------|---------------------------------------------------|
| `GET`    | `/`                                        | Get all players.                                  |
| `GET`    | `/page`                                    | Get players in pages (e.g., `/?page=0&size=5`).    |
| `GET`    | `/?name={name}`                            | Get players by name.                              |
| `GET`    | `/?team={team}`                            | Get players by team.                              |
| `GET`    | `/?position={position}`                    | Get players by position.                          |
| `GET`    | `/?nation={nation}`                        | Get players by nation.                            |
| `GET`    | `/?team={team}&position={position}`        | Get players by team and position.                 |
| `POST`   | `/`                                        | Add a new player.                                 |
| `PUT`    | `/`                                        | Update an existing player.                        |
| `DELETE` | `/{playerName}`                            | Delete a player by their name.                    |

## API Documentation

This project uses `springdoc-openapi` to automatically generate API documentation. Once the application is running, you can access the Swagger UI at the following URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

The Swagger UI provides a user-friendly interface to explore and test the API endpoints.