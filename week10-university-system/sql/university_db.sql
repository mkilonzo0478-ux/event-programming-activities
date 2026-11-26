-- Phase 1: University Management System Database Schema (Week 10 Capstone)
CREATE DATABASE IF NOT EXISTS university_db;
USE university_db;

-- Table 1: Programs (Degrees offered)
CREATE TABLE IF NOT EXISTS programs (
    program_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for the program
    program_name VARCHAR(100) NOT NULL,        -- Name of the degree
    required_units INT NOT NULL                -- How many units to graduate
);

-- Insert sample programs (if table is empty)
INSERT INTO programs (program_name, required_units)
SELECT * FROM (
    SELECT 'Bachelor of Computer Science' AS program_name, 56 AS required_units
    UNION ALL
    SELECT 'Diploma in IT', 24
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM programs);

-- Table 2: Students
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique ID for the student
    registration_number VARCHAR(20) UNIQUE NOT NULL,  -- School ID (e.g., S101)
    first_name VARCHAR(50) NOT NULL,                  -- First name
    last_name VARCHAR(50) NOT NULL,                   -- Last name
    email VARCHAR(100) UNIQUE NOT NULL,               -- Email address
    program_id INT,                                   -- Links student to a program
    FOREIGN KEY (program_id) REFERENCES programs(program_id)
);
