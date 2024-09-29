# Patient Management API

## Overview
This is a Spring Boot application to manage patient records.

## API Endpoints
- **Register a new patient**: `POST /api/patient`
- **Get patient details**: `GET /api/patient/{id}`
- **Get all patients**: `GET /api/patient/all`
- **Count patients by gender**: `GET /api/patient/count`
- **Create a new patient based on parent IDs**: `POST /api/patient/create`

## Swagger UI
Access the Swagger UI at: `http://localhost:8080/swagger-ui/`

## Database Setup
Make sure to create the `projet_prog301` schema in PostgreSQL for data persistence.

## Running the Application
To run the application, use:
```bash
./mvnw spring-boot:run
