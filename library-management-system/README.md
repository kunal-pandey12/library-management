# 📚 Library Management System

A REST API based Library Management System built with Spring Boot, MySQL, and Spring Security.

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring Security (Role Based Authentication)
- Spring Data JPA + Hibernate
- MySQL Database
- Lombok
- Maven

## ✨ Features

- 📖 Book Management (CRUD)
- ✍️ Author Management (CRUD)
- 👤 User Management (CRUD)
- 📋 Issue Book (with availability check & duplicate check)
- 🔄 Return Book (with fine calculation ₹10/day for late return)
- 🔍 Book Search Functionality (by Book Name & Author)
- 🔐 Role Based Security (ADMIN / USER)
- 🔑 Register / Signup API
- 🔒 BCrypt Password Encryption
- ⚠️ Global Exception Handling

## 👥 Roles

| Role  | Access |
|-------|--------|
| ADMIN | Books, Authors, Users, Issue, Return |
| USER  | View Books, Issue, Return |

## 🚀 How to Run

1. Clone the repo
```bash
git clone https://github.com/kunal-pandey12/library-management-system.git
```

2. MySQL mein database banao
```sql
CREATE DATABASE library;
```

3. `application.properties` mein update karo
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Run karo
```bash
mvn spring-boot:run
```

## 📡 API Endpoints

### Auth
| Method | URL | Access |
|--------|-----|--------|
| POST | /auth/register | Public |

### Books
| Method | URL | Access |
|--------|-----|--------|
| GET | /books/getAll | USER + ADMIN |
| GET | /books/{id} | USER + ADMIN |
| POST | /books/crate | ADMIN only |
| PUT | /books/{id} | ADMIN only |
| DELETE | /books/{id} | ADMIN only |

### Authors
| Method | URL | Access |
|--------|-----|--------|
| GET | /Author/getAll | USER + ADMIN |
| GET | /Author/{id} | USER + ADMIN |
| POST | /Author/create | ADMIN only |
| PUT | /Author/{id} | ADMIN only |
| DELETE | /Author/{id} | ADMIN only |

### Issues
| Method | URL | Access |
|--------|-----|--------|
| POST | /issue/book | USER + ADMIN |
| GET | /issue/all | USER + ADMIN |
| PUT | /issue/return/{id} | USER + ADMIN |

## 🔐 How to Test (Postman)

1. Register karo
```json
POST /auth/register
{
  "name": "Admin",
  "email": "admin@library.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

2. Postman mein Basic Auth use karo
```
Authorization → Basic Auth
Username → admin@library.com
Password → admin123
```

## 👨‍💻 Author

**Kunal Pandey**  
[GitHub](https://github.com/kunal-pandey12) | [LeetCode](https://leetcode.com/u/pandey_kunal_12/)
