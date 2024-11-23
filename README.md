Project Setup Guide
Prerequisites

Before setting up the project, ensure the following are installed on your system:

    Java 17 or higher
    PostgreSQL database
    Maven (for building the project)
    An IDE or text editor (e.g., IntelliJ IDEA, Eclipse, or VS Code)

Getting Started

1. Clone the Repository

    `git clone https://github.com/sonerkochan/UserManager`<br>
    `cd project-folder`

2. Configure the Database

Create a PostgreSQL database named UserManager.<br>
Update the following properties in the application.properties file to match your local database settings:

    spring.datasource.url=jdbc:postgresql://localhost:5432/UserManager
    spring.datasource.username=<your-database-username>
    spring.datasource.password=<your-database-password>

3. Install Dependencies

Run the following command to install all project dependencies:

    mvn clean install

4. Run Database Migrations

The project uses Flyway for database versioning. Migrations will run automatically during application startup. Ensure:

Migration scripts are located in the `src/main/resources/db/migration` folder.
Flyway settings in `application.properties` are correctly configured:

    spring.flyway.enabled=true
    spring.flyway.locations=classpath:db/migration

5. Start the Application

Run the application using Maven:

    mvn spring-boot:run

6. Access the Application

Open your browser and navigate to:

    http://localhost:8080

How to Access Swagger Documentation

Swagger UI:
Open the following URL in your browser:

    http://localhost:8080/swagger-ui.html

OpenAPI Documentation:
Access the API documentation in the following formats:

    JSON: http://localhost:8080/v3/api-docs
    YAML: http://localhost:8080/v3/api-docs.yaml