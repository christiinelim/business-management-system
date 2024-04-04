# Business Management System

## Project Introduction
This project aims to solve the pain points of small business owners by providing a system to keep track of customer orders. The system allows customers to interact with a Telegram bot to view order details, place orders, and check product availability. Business owners can manage orders, update inventory, and add new products via a frontend dashboard.

## Software Requirements Specifications 
This section describes the requirements for the software project. The created software must be
able to perform the following functional requirements (abbreviated as FR):

**FR 1: Product Management**
- FR 1.1: Business owners can add new products for sale.
- FR 1.2: Business owners can update product information, such as name, description, and price.
- FR 1.3: Business owners can delete product listings.

**FR 2: Order Management**
- FR 2.1: Business owners can view orders placed by customers.
- FR 2.2: Business owners can update order status (paid/unpaid, completed/pending).
- FR 2.3: Business owners can add, delete, or update items within orders.
- FR 2.4: Customers can view their order details and track order status through the Telegram bot.

**FR 3: Customer Interaction via Telegram Bot**
- FR 3.1: Customers can access the Telegram bot to place orders.
- FR 3.2: Customers can select items they want to order from the bot's menu.
- FR 3.3: Customers can check product availability through the Telegram bot.
- FR 3.4: Business owners can integrate the Telegram bot with their social media platforms for customer orders.

## Frontend Design

**Homepage**
![Homepage](./images/frontend/home.png)

**Sign Up Page**
![Sign Up Page](./images/frontend/sign-up.png)

**Verification Page**
![Verification Page](./images/frontend/verification.png)

**Login Page**
![Login Page](./images/frontend/login-page.png)

**Forgot Password Page**
![Forgot Password Page](./images/frontend/forgot-password.png)

**Product Listing Page**
![Listings Page](./images/frontend/listings.png)

**Consolidation of Orders**
![Orders Page](./images/frontend/orders.png)

**Order Details**
![Orders Detail](./images/frontend/order-detail.png)


## Telegram Bot

![Telegram Bot Prototype](./images/telegram-bot/telegram-prototype-1.png)

![Telegram Bot Prototype](./images/telegram-bot/telegram-prototype-2.png)

![Telegram Bot Prototype](./images/telegram-bot/telegram-prototype-3.png)

![Telegram Bot Prototype](./images/telegram-bot/telegram-prototype-4.png)

## Technologies Used

- **Frontend**:
  - Angular
  
- **Backend**:
  - Spring Boot
  
- **Database**:
  - MySQL
  
- **Integration**:
  - Telegram Bot 
    - Telegram Bot API
    - Node.js
    - Express
  - Axios
  - JSON Web Token

## ER Diagram
![ER Diagram](./images/er-diagram.png)

## SQL Schema
![SQL Schema](./images/sql-schema.png)

## Future Implementations

### 1. Sales Data Analysis Dashboard
Introduce a dashboard feature to analyze sales data effectively. This dashboard will provide insights into sales performance by comparing current month orders with the previous month. It will break down sales by items to identify which products generate the most profit, allowing for informed decision-making and strategic planning.

### 2. Two-Factor Authentication (2FA)
Enhance security measures by implementing Two-Factor Authentication (2FA) to protect user accounts. This additional layer of security requires users to verify their identity through a second factor, mobile device, before gaining access to the system.
