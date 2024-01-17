# Getting Started

## Requirements (Optional)
  - Postgres instance(docker mongo:latest)
  
  - Run Create sql(initial.sql)

  P.S(docker-compose.yml is under container folder and need to  verify)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com/fuat/exchange/ExchangeApplication.java` class from your IDE.

Alternatively you can use the gradle plugin like so:

```shell
mvn spring-boot:run
```

## Swagger ui 

http://localhost:8082/swagger-ui/index.html

## Notes
   Tests are need to check and verify again due to time restrictions
   Exceptions for HttpMethods need to add (401,404 etc)