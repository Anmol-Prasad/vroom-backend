Backend system for a ride-booking platform built with Java 17 and Spring Boot, designed with clean architecture, secure authentication, and scalable backend principles.

Highlights

Built using Spring Boot, Spring Security, Spring Data JPA, PostgreSQL
Implemented JWT-based stateless authentication (no server-side sessions)
Designed complete ride lifecycle workflow: request → accept → start → complete
Implemented role-based authorization (RIDER / DRIVER)
Developed clean layered architecture (Controller → Service → Repository)
Modeled domain entities (User, Ride, Driver, Rating) with proper JPA relationships
Isolated business logic in service layer following separation of concerns
Designed RESTful APIs with production-ready structure
Structured for horizontal scalability and future integration with Redis/Kafka
Built with maintainability and microservice-readiness in mind
