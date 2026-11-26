package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Phase 7 (LO2 & LO4): Handles UI events, reactive property bindings, and background DB tasks.
 */
public class StudentController {

    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. Connect TableColumns to Student observable properties (Week 8)
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // 2. Bind TableView items to observable list
        studentTable.setItems(studentList);

        // 3. Disable Save button while any required text field is blank (Week 8 Binding)
        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()
                        .or(firstNameField.textProperty().isEmpty())
                        .or(lastNameField.textProperty().isEmpty())
        );

        // 4. Load initial student records asynchronously
        loadStudents();
    }

    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();

        if (reg.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
            statusLabel.setTextFill(Color.FIREBRICK);
            statusLabel.setText("All fields are required.");
            return;
        }

        statusLabel.setTextFill(Color.BLUE);
        statusLabel.setText("Saving student...");

        // Background Task to insert student without blocking the JavaFX Application Thread (LO2)
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students (registration_number, first_name, last_name, email) VALUES (?, ?, ?, ?)";

                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg + "@university.edu");
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        saveTask.setOnSucceeded(e -> {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Student saved successfully!");
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            loadStudents();
        });

        saveTask.setOnFailed(e -> {
            Throwable ex = saveTask.getException();
            statusLabel.setTextFill(Color.FIREBRICK);
            statusLabel.setText("Failed to save student: " + (ex != null ? ex.getMessage() : "Unknown error"));
            if (ex != null) {
                ex.printStackTrace();
            }
        });

        Thread worker = new Thread(saveTask, "save-student-task");
        worker.setDaemon(true);
        worker.start();
    }

    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(e -> {
            studentList.setAll(task.getValue());
            statusLabel.setTextFill(Color.BLUE);
            statusLabel.setText("Loaded " + studentList.size() + " students from database.");
        });

        task.setOnFailed(e -> {
            Throwable ex = task.getException();
            statusLabel.setTextFill(Color.FIREBRICK);
            statusLabel.setText("Database connection error: " + (ex != null ? ex.getMessage() : "Unknown error"));
            if (ex != null) {
                ex.printStackTrace();
            }
        });

        Thread worker = new Thread(task, "load-students-task");
        worker.setDaemon(true);
        worker.start();
    }
}
