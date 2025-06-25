# 💳 Digital Banking - Backend API

A modern and secure digital banking system developed with **Spring Boot**, **JPA**, **Lombok**, and **JWT Authentication**. This project manages customers, bank accounts (current and saving), and operations such as credit and debit.

---

## 🏗️ Project Structure


---

## 🧪 Features

- ✅ Customer management
- ✅ Current and saving bank accounts
- ✅ Account operations: credit and debit
- ✅ Single Table Inheritance for account types
- ✅ H2 database support (can be switched to MySQL)
- 🛡️ JWT-based authentication (to be added)
- 📊 Dashboard (via Angular in frontend)
- 📎 Swagger OpenAPI documentation

---

## ⚙️ Technologies

| Backend     | Description                   |
|-------------|-------------------------------|
| Spring Boot | Framework de base             |
| Spring Data JPA | ORM via Hibernate         |
| H2 / MySQL  | Base de données               |
| Lombok      | Réduction du boilerplate code |
| JWT         | Authentification sécurisée    |
| Swagger     | Documentation API REST        |

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/USERNAME/digital-banking-backend.git
cd digital-banking-backend
```

```bash
mvn clean install
mvn spring-boot:run
```