# Project Management App

## 📌 Overview
Project Management App is a full-stack web application built using **Spring Boot** (backend) and **React** (frontend). The application allows users to manage projects efficiently by providing different access levels based on their subscription plans (Free, Monthly, and Annual).

## 🚀 Features
- **User Authentication** (Sign Up, Login, Logout)
- **Role-Based Access Control (RBAC)**
- **Project Creation, Editing, and Deletion**
- **Task Management** (Assign, Update, and Track Progress)
- You can invite your projects friend with spring boot starter email
- You can chat with your friends in the project
- You can add new issue or create a new issue
- **Subscription Plans**
  - **Free Plan**: Limited features
  - **Monthly Plan**: Extended functionality
  - **Annual Plan**: Full access to all features
- **Real-Time Updates** (if WebSockets or polling is implemented)
- **Responsive UI** for seamless usage across devices

## 🛠️ Tech Stack
### Frontend:
- React.js (with Hooks & Context API)
- shadcn ui
- React Router for navigation
- Axios for API requests
- Tailwind CSS / Bootstrap for styling (if used)

### Backend:
- Spring Boot (Java)
- Spring Security (JWT Authentication)
- Spring Data JPA (Hibernate)
- MySQL Database
- Lombok for cleaner code
- Stripe


## 📮 API Documentation (Postman)
For detailed API documentation and testing, you can use our **Postman Workspace**:  
[🔗 https://www.postman.com/cryosat-pilot-33785563/workspace/projectmanagment/collection/31334076-841d81fd-14eb-47b3-9d11-495afedb0be0?action=share&creator=31334076 ]

### How to Use:
1. Click the link above to access the Postman workspace.
2. Import the collection into your **Postman** application.
3. Update the environment variables (e.g., `BASE_URL`, `TOKEN`).
4. Start testing the API endpoints with ease.

This workspace includes:
✅ Pre-configured API endpoints  
✅ Sample request & response formats  
✅ Authentication setup with JWT tokens  
✅ Environment variables for easy testing  

Happy testing! 🚀


