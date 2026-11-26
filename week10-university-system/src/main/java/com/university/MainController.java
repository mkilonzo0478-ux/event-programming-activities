package com.university;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Date;

/**
 * Phase 9: Navigation shell controller switching between Dashboard and Student Management views.
 */
public class MainController {

    @FXML private StackPane centerPane;

    private final Label welcomeLabel = new Label("Welcome to the University Dashboard!");
    private final Label statsLabel = new Label("Checking database status...");
    private final Label heartbeatLabel = new Label("Last Heartbeat: Waiting for scheduler...");
    private VBox dashboardBox;

    @FXML
    public void initialize() {
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        heartbeatLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        dashboardBox = new VBox(12, welcomeLabel, statsLabel, heartbeatLabel);
        dashboardBox.setAlignment(Pos.CENTER);

        showDashboard();
    }

    @FXML
    private void showDashboard() {
        centerPane.getChildren().setAll(dashboardBox);
        refreshDashboardStats();
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called on the JavaFX Application Thread (via Platform.runLater) by the 5-second scheduler in App.
     */
    public void onHeartbeat(Date timestamp) {
        heartbeatLabel.setText("Last Auto-Refresh Heartbeat: " + timestamp);
        if (centerPane.getChildren().contains(dashboardBox)) {
            refreshDashboardStats();
        }
    }

    private void refreshDashboardStats() {
        LoadStudentsTask task = new LoadStudentsTask();
        task.setOnSucceeded(e ->
                statsLabel.setText("Total Registered Students: " + task.getValue().size())
        );
        task.setOnFailed(e ->
                statsLabel.setText("Database offline — start MySQL and run sql/university_db.sql")
        );
        Thread worker = new Thread(task, "dashboard-refresh-task");
        worker.setDaemon(true);
        worker.start();
    }
}
