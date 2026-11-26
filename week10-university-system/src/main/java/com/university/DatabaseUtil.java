package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Phase 3 (LO1): Database bridge separating the JavaFX UI from MySQL.
 */
public class DatabaseUtil {

    // Database URL pointing to university_db created in Phase 1
    private static final String URL = System.getenv()
            .getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/university_db");

    // Default MySQL username
    private static final String USER = System.getenv()
            .getOrDefault("DB_USER", "root");

    // CHANGE THIS TO YOUR ACTUAL MYSQL PASSWORD ("" is default for local XAMPP MySQL)
    private static final String PASSWORD = System.getenv()
            .getOrDefault("DB_PASSWORD", "");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
