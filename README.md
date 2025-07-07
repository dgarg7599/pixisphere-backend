# 📸 Pixisphere – AI-Powered Photography Service Marketplace (Backend)

Pixisphere is a full-stack, role-based photography service marketplace that connects clients with verified photographers (partners) across India. This backend system is built using **Java Spring Boot 3**, **JWT Auth**, and **MySQL**, and is fully modular, secure, and ready for production.

---

## ✅ Features Implemented

### 🔐 Authentication & Authorization (JWT-based)
- Signup with email, password, role, and phone
- OTP verification (mocked)
- Role-based login
- JWT token in header (`Authorization: Bearer <token>`)
- Roles: `CLIENT`, `PARTNER`, `ADMIN`

### 📥 Partner Onboarding & Admin Verification
- Partner submits personal details, city, service category, Aadhar number, and sample portfolio URLs
- Admin views pending requests and approves/rejects with a comment

### 🎯 Inquiry / Lead Management
- Client submits an inquiry (category, city, budget, date, optional image)
- Partners are automatically assigned (basic matching)
- Partner dashboard can fetch their assigned leads

### 🖼️ Portfolio Management
- Partner can **Add / View / Edit / Delete** portfolio entries
- Supports image URL, description, and order (`displayOrder`)

### 📊 Admin Dashboard
- Admin can view:
  - Total clients
  - Total partners
  - Pending verifications
  - Total inquiries

---

## 🚀 Upcoming Features (Planned)
- 📅 **Booking Module** (create, update status, assign partner)
- 🧾 CMS Panel (Admin): Manage Categories, Locations, Reviews
- 📦 Docker & Swagger Setup
- 📈 Rate Limiting / Monitoring
- ✅ Unit & Integration Testing

---

## 🧱 Tech Stack

| Layer      | Technology                  |
|------------|-----------------------------|
| Language   | Java 21                     |
| Framework  | Spring Boot 3               |
| Database   | MySQL                       |
| Security   | Spring Security + JWT       |
| Auth       | OTP (mocked) + BCrypt       |
| Build Tool | Maven                       |
| Others     | Lombok, Validation, JPA     |

---

## 📁 Folder Structure

```
backend/
│
├── config/                   # Security config (JWT filters etc.)
├── controller/               # REST API Controllers
├── dto/                      # Request and Response DTOs
├── entity/                   # JPA Entities
├── exception/                # Global Exception Handling
├── repository/               # Spring Data JPA Repos
├── security/                 # JWT Service and Filters
├── service/                  # Business Logic (Layered)
├── application.properties
└── PixisphereApplication.java
```

---

## 🧪 API Endpoints (Summary)

### 🔐 Auth Routes
| Method | Endpoint               | Role      | Description            |
|--------|------------------------|-----------|------------------------|
| POST   | `/api/auth/signup`     | All       | Signup with role       |
| POST   | `/api/auth/verify-otp` | All       | OTP verification       |
| POST   | `/api/auth/login`      | All       | JWT-based login        |

### 👤 Partner Routes
| Method | Endpoint                     | Role    | Description               |
|--------|------------------------------|---------|---------------------------|
| POST   | `/api/partner/onboarding`    | PARTNER | Submit onboarding form    |
| GET    | `/api/partner/portfolio/my`  | PARTNER | Fetch own portfolio       |
| POST   | `/api/partner/portfolio/add` | PARTNER | Add portfolio image       |
| PUT    | `/api/partner/portfolio/update/{id}` | PARTNER | Edit portfolio entry |
| DELETE | `/api/partner/portfolio/delete/{id}` | PARTNER | Delete entry         |
| GET    | `/api/partner/leads`         | PARTNER | Assigned inquiries        |

### 🧑‍💼 Admin Routes
| Method | Endpoint                    | Role   | Description                  |
|--------|-----------------------------|--------|------------------------------|
| GET    | `/api/admin/stats`          | ADMIN  | Dashboard stats              |
| GET    | `/api/admin/verifications`  | ADMIN  | View pending partner verifs  |
| PUT    | `/api/admin/verify/{id}`    | ADMIN  | Approve/Reject partner       |

### 🧑 Client Routes
| Method | Endpoint               | Role   | Description         |
|--------|------------------------|--------|---------------------|
| POST   | `/api/client/inquiry`  | CLIENT | Submit new inquiry  |

---

## ⚙️ Setup Instructions

1. **Clone Repository**
   ```bash
   git clone https://github.com/your-username/pixisphere-backend.git
   cd pixisphere-backend
   ```

2. **Set up MySQL Database**
   ```sql
   CREATE DATABASE pixisphere;
   ```

3. **Configure `.env` or `application.properties`**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/pixisphere
   spring.datasource.username=root
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Use Postman** to test APIs 

---

## 🔌 Postman API Collection

You can test the complete backend using our official Postman collection:

👉 [Click to Download pixisphere.postman_collection.json](pixisphere.postman_collection.json)

### 📋 Includes:
- 🔐 Auth (Signup, OTP Verify, Login)
- 👤 Client Inquiries
- 🤝 Partner APIs
- 📊 Admin Dashboard Stats
- 📸 Portfolio Management


## 👨‍💻 Developer Notes

- **OTP is mocked** — check console logs to verify (not sent via email)
- **JWT** is set in response header: `Authorization: Bearer <token>`
- **Use Role Headers** in Postman to hit protected routes
- Modular service layer makes this project easily extensible

---

## 🧑‍🎓 Author

> **Divyansh Garg**  
> Full-Stack Developer | Backend Java (Spring Boot)  

> Email: gargdivyansh7599@gmail.com
  
> GitHub: https://github.com/dgarg7599

> Linkedin: https://www.linkedin.com/in/divyansh-garg-5a8a8b276/ 

