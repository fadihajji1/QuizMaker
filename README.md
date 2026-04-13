# QuizPlatformf

## Project coordinates

- **GroupId:** `com.example`
- **ArtifactId:** `QuizPlatformf`
- **Version:** `0.0.1-SNAPSHOT`
- **Spring Boot parent version:** `3.5.6`

## Description

Simple Spring Boot quiz platform application.

## Prerequisites

- Java 17 JDK installed and `JAVA_HOME` set.
- Git (optional).
- MySQL server (or change datasource to another DB). By default the app connects to a local MySQL database.

## Configuration

Edit the application properties in `src/main/resources/application.properties` to configure the datasource:

- `spring.datasource.url` (default in this project: `jdbc:mysql://localhost/location?...`)
- `spring.datasource.username`
- `spring.datasource.password`

If you do not set a `server.port`, the app runs on port `8080` by default.

## Build

This project contains the Maven wrapper. From the repository root run:

On Unix/macOS:

```bash
./mvnw clean package
```

On Windows (PowerShell or CMD):

```powershell
mvnw.cmd clean package
```

Or use your installed Maven:

```bash
mvn clean package
```

## Run

Run the application using the wrapper:

Unix/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
mvnw.cmd spring-boot:run
```

Alternatively run the packaged jar:

```bash
java -jar target/QuizPlatformf-0.0.1-SNAPSHOT.jar
```

## Tests

Run unit tests with:

Unix/macOS:

```bash
./mvnw test
```

Windows:

```powershell
mvnw.cmd test
```

## Notes

- The project uses `spring.jpa.hibernate.ddl-auto=create` in `application.properties`, which recreates the schema on each start. Change for production use.
- Update the MySQL connection string and credentials before running.

## Where to look next

- Main application class: `src/main/java/com/example/quizplatformf/QuizPlatformfApplication.java`
- Controllers: `src/main/java/com/example/quizplatformf/controller/`
