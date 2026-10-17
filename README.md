# Medical Laboratory Solutions

<img width="1920" height="1080" alt="White and Blue Illustrative Medical Center Services Presentation" src="https://github.com/user-attachments/assets/3c5c452b-f5a6-4732-992e-bd0cc0f24ace" />

MediLabo Solutions is a microservices-based application designed to help doctors detect the risk of type 2 diabetes in patients. It allows medical staff to manage patient records, add doctor notes, and automatically calculate the diabetes risk level based on patient age, gender, and medical history.

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

### Run the application

```bash
docker-compose up --build
```

Open your browser at: **http://localhost:8082**

### Run locally (without Docker)

Start each service individually from IntelliJ or with:

```bash
mvn spring-boot:run
```

---

## Risk Levels

| Level | Description |
|---|---|
| 🟢 None | No trigger terms found |
| 🟡 Borderline | 2-5 triggers, patient over 30 |
| 🟠 In Danger | Depends on age and gender |
| 🔴 Early Onset | High risk, early appearance |

---

## ♻️ Green Code

### Implemented
- ✅ Lightweight Docker images with Alpine Linux (~5MB vs ~200MB)
- ✅ Right database chosen for each use case (MySQL + MongoDB)
- ✅ No database in risk-service, no unnecessary storage
- ✅ Microservices architecture, scale only what is needed

---

## 👤 Author

### Fuat Kara
