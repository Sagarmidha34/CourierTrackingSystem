# Courier Tracking System

A simple courier tracking system built with Java and MySQL.

## Prerequisites

- Java 17 or higher
- Maven
- MySQL Server

## Setup

1.  **Database Setup**
    *   Make sure your MySQL server is running.
    *   Execute the schema script to create the database and tables:
        ```bash
        mysql -u root -p < sql/schema.sql
        ```
    *   *Note: The application expects the database user to be `root` with no password (default in some dev environments). If your config is different, update `src/main/java/util/DBConnection.java`.*

2.  **Build the Project**
    ```bash
    mvn clean compile
    ```

## Running the Application

To run the application, use the following command:

```bash
mvn exec:java
```

## Usage

**Login Credentials (from schema.sql):**

*   **Admin:** `admin` / `admin123`
*   **User:** `user` / `user123`
