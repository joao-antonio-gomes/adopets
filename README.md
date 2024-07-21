# Spring Boot - REST API - Pets Adoption

### Who Am I?

I'm a project that aims to provide a REST API to manage pets adoption.

### Which technologies were used?

- Java 17
- Gradle
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger
- Lombok
- MapStruct
- JUnit
- Mockito
- Spring Docker Compose

### How to run?

You need to run the `PetsApplication` class. The application will start on port 8080 and will run the Docker Compose
to start the PostgreSQL database on port 5432.

### API Rest Documentation

First you need to run the application. Than you can access the API Rest documentation in the following
link: [API Rest Documentation](http://localhost:8080/swagger-ui/index.html)

### Which architecture was used?

The project was developed using the Hexagonal Architecture. The project is divided into three main layers: `core`,
`infrastructure` and `application`.

### How to test?

The application has integration tests who are testing the main functionalities of the application. The integration tests
use the H2 database to run the tests. You can run the tests with gradle.

