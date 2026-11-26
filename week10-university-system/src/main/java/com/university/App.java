package com.university;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Phase 8 (LO3): Main JavaFX entry point with a 5-second ScheduledExecutorService heartbeat.
 */
public class App extends Application {

    private ScheduledExecutorService scheduler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load MainView.fxml and obtain controller reference
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();

        // 2. Configure and show the primary Stage
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // 3. LO3: Schedule a background task to auto-refresh every 5 seconds
        scheduler = Executors.newScheduledThreadPool(1, runnable -> {
            Thread thread = new Thread(runnable, "system-heartbeat-scheduler");
            thread.setDaemon(true);
            return thread;
        });

        Runnable heartbeat = () -> {
            Date now = new Date();
            System.out.println("System Heartbeat: Checking for updates at " + now);
            Platform.runLater(() -> mainController.onHeartbeat(now));
        };

        scheduler.scheduleAtFixedRate(heartbeat, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
