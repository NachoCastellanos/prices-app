# Prices App

A Spring Boot application that returns the applicable price based on a given **brand**, **product**, and **date/time**. The project uses an **in-memory H2** database preloaded with example data, and it provides a **REST API** to query the highest-priority price in effect at a specific date/time.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
    - [Build and Run](#build-and-run)
    - [Database Initialization](#database-initialization)
- [REST Endpoints](#rest-endpoints)
    - [GET /prices](#get-prices)
- [Testing](#testing)
- [Conventional Commits](#conventional-commits)

---

## Overview

This application is designed to answer queries about final product prices based on:
- **Brand ID**
- **Product ID**
- **Application Date (with time)**

It applies the rules:
1. Only prices whose validity range covers the specified date are considered.
2. Among those, the price with the highest **priority** is returned.

The data is stored in an **H2** in-memory database, using the table `PRICES`. Some example records are inserted at startup (via `data.sql`), and a single REST endpoint is exposed to retrieve the final price.

---

## Features

1. **Spring Boot** for rapid application development.
2. **H2** in-memory database, with an example data set.
3. **Spring Data JPA** for data access.
4. **REST endpoint** that accepts parameters and returns a JSON with the correct price.
5. **Integration tests** demonstrating five main scenarios.

---

## Project Structure


```bash
├── prices-app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.example.prices_app
│   │   │   │       ├── entity          # Price entity
│   │   │   │       ├── repository      # PriceRepository
│   │   │   │       ├── service         # PriceService logic
│   │   │   │       ├── controller      # PriceController REST endpoint
│   │   │   │       └── PricesAppApplication.java  # Main app
│   │   │   ├── resources
│   │   │       ├── application.properties
│   │   │       └── data.sql            # Database insertion script
│   │   └── test
│   │       └── java
│   │           └── com.example.prices_app
│   │               └── PriceIntegrationTest.java
├── pom.xml
├── README.md
```


---

## Getting Started

### Build and Run

1. **Clone** this repository:
```bash
git clone https://github.com/your-username/prices-app.git
cd prices-app
```
2 **Run** :

```bash
mvn spring-boot:run
```

### Database Initialization
   The database is automatically initialized at startup using **data.sql**. The **PRICES** table contains the following columns:
   ```roomsql
   CREATE TABLE PRICES (
       ID BIGINT PRIMARY KEY,
       BRAND_ID INT,
       PRODUCT_ID INT,
       PRICE_LIST INT,
       START_DATE TIMESTAMP,
       END_DATE TIMESTAMP,
       PRIORITY INT,
       PRICE DECIMAL,
       CURRENCY VARCHAR(3)
   );
   ```

You can access to the database by this url:

http://localhost:8080/h2-console

Example rows inserted via data.sql:
```roomsql
INSERT INTO PRICES (ID, BRAND_ID, PRODUCT_ID, PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY)
VALUES
(1, 1, 35455, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
(2, 1, 35455, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR');
 ```

---

## REST Endpoints
The application exposes the following REST API:

### GET /prices
Description: Retrieves the applicable price for a product based on brand, product, and date/time.
Parameters:

http://localhost:8080/api/prices/search?brandId=1&productId=35455&applicationDate=2020-06-14%2010:00:00

http://localhost:8080/api/prices/search?brandId=1&productId=35455&applicationDate=2020-06-14%2010:00:00

1. **brandId** (required): Brand ID.
2. **productId** (required): Product ID.
3. **applicationDate** (required): Date and time to apply in yyyy-MM-dd'T'HH:mm:ss format.
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.5,
  "curr": "EUR"
}

 ```
---

## Testing
Key test scenarios include:

1. Query for a price at a date covered by one price range.

2. Query for a price at a date covered by overlapping ranges with different priorities.

3. Query for a price outside all ranges.

---

## Conventional commits
This project follows the Conventional Commits standard for commit messages to ensure consistent and meaningful messages.
