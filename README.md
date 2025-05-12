# DailyFinance API Automation (Rest Assured + TestNG)

This project automates REST API testing for the [DailyFinance](https://dailyfinance.roadtocareer.net) platform using **Rest Assured** and **TestNG**. It covers core functionalities such as user registration, login, user management, and item CRUD operations.

---

## ✅ Features Covered

### 👤 User Management
- Register a new user
- Admin login
- Get user list (admin)
- Search user by ID (admin)
- Edit user info (eg:firstname, phone)
- Login as any user

### 📦 Item Management
- Get item list
- Add new item (user)
- Edit item name (user)
- Delete item (user)

---

## 🚀 Tech Stack

- Java 17+
- Postman
- Rest Assured
- TestNG
- Faker (for random test data)
- Gradle
- JSON Config 

---

### 📁 Project Structure

-POM(Project Object Model) Architecture

### Postman Collection

 https://documenter.getpostman.com/view/42772182/2sB2jAc8HJ

### SetUp

- 1. Clone the repo:
   ```bash
   git clone https://github.com/afsanakashpia/RestAssured.git
   cd RestAssured

### 🧪 How to Run
  
2.Run it using the following command on terminal and pass Admin credentials through command
 
  ``` gradle clean test -Pemail="Admin_username" -Ppassword="password" ```

### Test Case

https://docs.google.com/spreadsheets/d/1SIn7uDo7JhnqYXGY1_WRKCqWqR_meXPqHJLAJftY2uU/edit?usp=sharing

### Allure Report

<img width="941" alt="REstAssuredpture" src="https://github.com/user-attachments/assets/5e55f473-abd4-4f88-8201-ee0be9e81064" />


