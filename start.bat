@echo off

REM Exit immediately if a command exits with a non-zero status.
SETLOCAL EnableDelayedExpansion

echo Building the Spring Boot application...
call mvn clean install
IF %ERRORLEVEL% NEQ 0 (
    echo Maven build failed!
    exit /b %ERRORLEVEL%
)

echo Building Docker images and starting services with Docker Compose...
call docker-compose up --build -d
IF %ERRORLEVEL% NEQ 0 (
    echo Docker Compose failed to start services!
    exit /b %ERRORLEVEL%
)

echo Application and Kafka services are starting up. It may take a few moments.
echo You can check the application logs with: docker-compose logs -f app
echo Once running, access the application at http://localhost:8080
echo Swagger UI: http://localhost:8080/swagger-ui.html
echo Healthcheck: http://localhost:8080/actuator/health

ENDLOCAL
