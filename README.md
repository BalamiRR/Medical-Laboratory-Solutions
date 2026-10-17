# Medical Laboratory Solutions

<img width="851" height="365" alt="image" src="https://github.com/user-attachments/assets/9ce22244-8080-41d4-83da-6aaa8f4f1508" />

A microservices application to help doctors detect the risk of type 2 diabetes in patients.

## Technologies
 
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud Gateway 2023.0.0**
- **Thymeleaf** — frontend templating
- **MySQL 8.0** — patient data
- **MongoDB 6.0** — medical notes
- **Docker** — containerization
- **Lombok** — boilerplate reduction

## Architecture

| Service | Technology | Port |
|---|---|---|
| patient-service | Spring Boot + MySQL | 8080 |
| notes-service | Spring Boot + MongoDB | 9000 |
| risk-service | Spring Boot | 8084 |
| frontend-service | Spring Boot + Thymeleaf | 8083 |
| gateway-service | Spring Cloud Gateway | 8082 |

## Getting Started

```bash
docker-compose up --build
```

Open: http://localhost:8082

## Green Code

**Implemented:**
- Lightweight Docker images (Alpine Linux)
- Right database for each use case
- No database in risk-service

**Suggestions:**
- Add pagination
- Add caching
- Enable HTTP compression
