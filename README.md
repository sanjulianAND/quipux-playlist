# Playlist API (Spring Boot)

API para autenticación (JWT) y gestión de playlists con canciones, más endpoint de géneros.

## Requisitos
- Java: 25.0.1 (probado)
- Maven: 3.x
- (Opcional) Postman para probar endpoints

## Tecnologías
- Spring Boot 3.2.12
- Spring Web
- Spring Security (JWT Bearer)
- Spring Data JPA
- H2 (in-memory)

## Ejecución
### 1) Correr tests
```bash
mvn clean test
```
### 2) Levantar la app
```bash
mvn spring-boot:run
```
### La API inicia en:
```bash
http://localhost:8080
```
### Base de datos (H2)

H2 Console:
```bash
http://localhost:8080/h2-console
```
#### Datos:

JDBC URL: jdbc:h2:mem:playlistsdb

-  User: sa
-  Password: (vacío)

### Credenciales

#### Credenciales por defecto:

-  username: admin
-  password: admin

### Autenticación (JWT)

#### Obtener token:

POST /auth/login

Usar token en el resto de endpoints:
Header: Authorization: Bearer <token>
#### Endpoints
##### Auth
- POST /auth/login

##### Playlists
- POST /lists
- GET /lists
- GET /lists/{listName}
- DELETE /lists/{listName}
##### Genres
-  GET /genres

### Postman

#### Colección Postman:
https://.postman.co/workspace/My-Workspace~3c08a8a0-bb0a-4842-853e-d18763ef3372/collection/36192509-1394e3d0-6082-4547-b76d-08aae4dfe3dc?action=share&creator=36192509&active-environment=36192509-4ccb9a82-c30e-48ea-ab55-8759efd5664b

La colección:
1. Ejecuta login y guarda token automáticamente en environment
2. Incluye ejemplos: listar, crear, consultar por nombre y eliminar

### Notas

Los endpoints (excepto /auth/login) están protegidos con JWT.
Se adjuntan tests de servicio y tests de seguridad con MockMvc.
