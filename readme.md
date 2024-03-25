# Business Management System

## Overview

This project aims to solve the pain point of small business owners by providing a system to keep track of customer orders. The system allows customers to interact with a Telegram bot to view order details, place orders, and check product availability. Business owners can manage orders, update inventory, and add new products via a frontend dashboard.

## Features

- **Customer Interaction**: Customers can interact with the Telegram bot to view order descriptions, pictures, costs, and available stock. They can also place orders for products.
  
- **Database Integration**: Orders placed by customers are updated in the MySQL database, which also manages inventory updates.
  
- **Business Order Management**: Business owners can access a frontend dashboard to view and manage orders. They can edit or delete orders (if customers change their minds) and mark orders as complete (if delivered).
  
- **Product Management**: Business owners can add new products they are selling or update the inventory of existing products via a form.

## Technologies Used

- **Frontend**:
  - Angular
  
- **Backend**:
  - Spring Boot
  
- **Database**:
  - MySQL
  
- **Integration**:
  - Telegram Bot API

## Proposed ER Diagram

![ER Diagram](./images/er-diagram.png)

## Proposed SQL Schema

![SQL Schema](./images/sql-schema.png)