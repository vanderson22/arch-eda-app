#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

echo "Building the Spring Boot application..."
mvn clean install

echo "Building Docker images and starting services with Docker Compose..."
docker-compose up --build -d

echo "Application and Kafka services are starting up. It may take a few moments."
echo "You can check the application logs with: docker-compose logs -f app"
echo "Once running, access the application at http://localhost:8080"
echo "Swagger UI: http://localhost:8080/swagger-ui.html"
echo "Healthcheck: http://localhost:8080/actuator/health"
