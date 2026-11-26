package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Phase 5 (LO2 - Producer-Consumer Pattern):
 * Runs the SELECT query on a background thread so the JavaFX UI never freezes.
 */
public class LoadStudentsTask extends Task<ObservableList<Student>> {

    @Override
    protected ObservableList<Student> call() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("registration_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                students.add(student);
            }
        }

        return students;
    }
}
