y-- Update schema.sql with user authentication table

CREATE DATABASE IF NOT EXISTS courier_db;

USE courier_db;

CREATE TABLE IF NOT EXISTS couriers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(100) NOT NULL,
    receiver VARCHAR(100) NOT NULL,
    origin VARCHAR(100),
    destination VARCHAR(100),
    status VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'user') NOT NULL
);

-- Sample admin and user
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'admin'),
('user', 'user123', 'user')
ON DUPLICATE KEY UPDATE username=username;
