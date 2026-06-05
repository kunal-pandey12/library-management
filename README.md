# 📚 Library Management System

A full-stack Library Management System built with **Spring Boot**, **MySQL**, **Spring Security**, and a responsive **Thymeleaf + Bootstrap UI**.

> 🔐 Role-based access control | 📖 Book & Author Management | 💰 Fine Calculation | 🖥️ Web Dashboard

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Spring Boot 3.2.5 |
| Security | Spring Security (Role-Based Authentication) |
| ORM | Spring Data JPA + Hibernate |
| Database | MySQL |
| Frontend | Thymeleaf, Bootstrap 5, HTML, CSS |
| Tools | Maven, Lombok, Postman |

---

## ✨ Features

### 🔐 Security & Auth
- Role-Based Access Control (**ADMIN / USER**)
- BCrypt Password Encryption
- Register / Signup API
- Role-based Navigation Bar (Admin & User see different views)

### 📖 Library Operations
- 📚 Book Management (CRUD)
- ✍️ Author Management (CRUD)
- 👤 User Management (CRUD)
- 📋 Issue Book (with availability check & duplicate issue prevention)
- 🔄 Return Book (with fine calculation — ₹10/day for late return after 7 days)
- 🔍 Book Search (by Book Name & Author)

### 🖥️ UI & Dashboard
- Responsive Web UI built with Thymeleaf + Bootstrap 5
- Admin Dashboard with panels for Books, Authors, Users, and Issue management
- Role-specific views — Admin sees full controls, User sees limited access
- Clean Navigation Bar with logout support

### ⚠️ Exception Handling
- Global Exception Handling
- Duplicate book issue prevention
- Unauthorized role access blocked

---

## 👥 Roles & Access

| Role | Access |
|------|--------|
| **ADMIN** | Books, Authors, Users, Issue, Return, Dashboard |
| **USER** | View Books, Issue Book, Return Book, Own Dashboard |

---

## 🖼️ Screenshots

### Admin Dashboard
![Dashboard](Screenshot%20(321).png)

### Books Management
![Books](Screenshot%20(324).png)

### Issue Books
![Issue](Screenshot%20(327).png)

### Author Management
![Authors](Screenshot%20(328).png)

### User Management
![Users](Screenshot%20(329).png)

---

## 📡 API Endpoints

### 🔑 Auth
| Method | URL | Access |
|--------|-----|--------|
| POST | `/auth/register` | Public |

### 📚 Books
| Method | URL | Access |
|--------|-----|--------|
| GET | `/books/getAll` | USER + ADMIN |
| GET | `/books/{id}` | USER + ADMIN |
| POST | `/books/create` | ADMIN only |
| PUT | `/books/{id}` | ADMIN only |
| DELETE | `/books/{id}` | ADMIN only |

### ✍️ Authors
| Method | URL | Access |
|--------|-----|--------|
| GET | `/Author/getAll` | USER + ADMIN |
| GET | `/Author/{id}` | USER + ADMIN |
| POST | `/Author/create` | ADMIN only |
| PUT | `/Author/{id}` | ADMIN only |
| DELETE | `/Author/{id}` | ADMIN only |

### 📋 Issue & Return
| Method | URL | Access |
|--------|-----|--------|
| POST | `/issue/book` | USER + ADMIN |
| GET | `/issue/all` | USER + ADMIN |
| PUT | `/issue/return/{id}` | USER + ADMIN |

---

## 🚀 How to Run

### Prerequisites
- Java 17+
- MySQL
- Maven

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/kunal-pandey12/library-management-system.git
cd library-management-system
```

**2. Create MySQL database**
```sql
CREATE DATABASE library;
```

**3. Update `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. Open in browser**
```
http://localhost:8084/dashboard
```

---

## 🔐 How to Test APIs (Postman)

**1. Register a user**
```json
POST /auth/register
{
  "name": "Admin",
  "email": "admin@library.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

**2. Use Basic Auth in Postman**
```
Authorization → Basic Auth
Username → admin@library.com
Password → admin123
```

---

## 📁 Project Structure

```
src/main/java/com/csf/library/
├── Controller/        # REST Controllers
├── Model/             # Entity classes (Book, Author, User, Issue)
├── Repo/              # JPA Repositories
├── Service/           # Business Logic
├── Security/          # Spring Security Config
└── Exception/         # Global Exception Handling

src/main/resources/
├── templates/         # Thymeleaf HTML templates
└── application.properties
```

---

## 👨‍💻 Author

**Kunal Pandey**  
📧 kunalpandey.12dev@gmail.com  
🔗 [GitHub](https://github.com/kunal-pandey12) | [LinkedIn](https://www.linkedin.com/in/kunal-pandey-2023792a4/) | [LeetCode](https://leetcode.com/u/pandey_kunal_12/)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
