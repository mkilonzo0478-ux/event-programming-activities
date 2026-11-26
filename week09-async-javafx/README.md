# Week 9: Asynchronous Programming in JavaFX

Complete implementation of the **Week 9 Self-Paced Guide: Asynchronous Programming in JavaFX**.

## Learning Outcomes Completed

- [x] **Outcome 1 (LO1):** Identify operations that freeze the UI (`FreezeDemo.java`)
- [x] **Outcome 2 (LO2):** Implement background tasks using `Task` and `Service` (`TaskDemo.java`, `ServiceDemo.java`)
- [x] **Outcome 3 (LO3):** Safely update the UI from background threads (`Platform.runLater()`, `Task` property bindings)
- [x] **Outcome 4 (LO4):** Provide real-time progress feedback to the user (`ProgressBar`, `ProgressIndicator`, `updateProgress()`, `updateMessage()`)

---

## Project Structure

```text
week09-async-javafx/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    ├── FreezeDemo.java    # Activity 1: The Freeze (Blocking the JavaFX Application Thread)
                    ├── TaskDemo.java      # Activity 2: The Fix (Background Task & Safe UI Updates)
                    ├── ProgressDemo.java  # Activity 3: Live Progress (updateProgress, updateMessage & Bindings)
                    └── ServiceDemo.java   # Activity 4: Capstone - Reusable & Cancellable Service
```

---

## How to Run (Maven / VS Code)

From inside `week09-async-javafx`:

```powershell
# Run default Capstone app (ServiceDemo)
mvn javafx:run

# Run specific activities by overriding mainClass:
mvn javafx:run "-Djavafx.mainClass=com.example.FreezeDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.TaskDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.ProgressDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.ServiceDemo"
```

*(In VS Code with the Extension Pack for Java, you can also open any activity file and click **Run** directly above `public static void main`.)*

---

## Implementation Notes & Self-Check Summary

### Activity 1: The Freeze (`FreezeDemo.java`)
- **Q1: Why did the window freeze for 5 seconds?**
  - Because `Thread.sleep(5000)` was executed directly inside the event handler on the **JavaFX Application Thread**. Blocking the UI thread prevents it from processing any new input events (moving or resizing the window) or repainting the screen (including showing `"Processing..."`) until the 5-second sleep finishes.

### Activity 2: The Fix (`TaskDemo.java`)
- **Q1: Why did we use `Platform.runLater()` inside `setOnSucceeded`?**
  - Per the handbook: UI controls cannot be modified from a background thread; `Platform.runLater()` posts UI updates onto the JavaFX Application Thread safely. *(Note: JavaFX `Task` lifecycle callbacks like `setOnSucceeded` are already dispatched on the JavaFX Application Thread, so wrapping in `Platform.runLater()` is safe and reinforces the rule that any off-thread work must marshal UI changes back to the UI thread.)*
- **JavaFX API Fix:** The handbook snippet calls `startButton.setEnabled(false)` / `setEnabled(true)`, which is a Swing (`JButton`) method and does not exist on `javafx.scene.control.Button`. Replaced with JavaFX's `startButton.setDisable(true)` and `startButton.setDisable(false)` (consistent with Activity 4's `ServiceDemo.java`).

### Activity 3: Live Progress (`ProgressDemo.java`)
- **Q1: What is the difference between `updateProgress()` and `Platform.runLater()`?**
  - `updateProgress()` (along with `updateMessage()`) is a built-in `Task` method safe to call from `call()` on a background thread; JavaFX coalesces/batches rapid updates and synchronizes the observable `progressProperty()` with the UI thread automatically. `Platform.runLater()` queues arbitrary `Runnable` blocks onto the UI thread, which can flood the event queue if called inside tight loops.
- **Q2: Why did we call `statusLabel.textProperty().unbind()` at the end?**
  - Because `statusLabel.textProperty()` was bound to `downloadTask.messageProperty()`. Calling `statusLabel.setText("Download Complete!")` without unbinding first throws `java.lang.RuntimeException: A bound value cannot be set.`
- **JavaFX API Fix:** Uses `startButton.setDisable(true)` / `startButton.setDisable(false)` instead of Swing's `setEnabled(...)`.

### Activity 4: The `Service` Class — Capstone (`ServiceDemo.java`)
- **Q1: What is the main difference between `Task` and `Service`?**
  - A `Task` is a one-shot background job that can only be executed once. A `Service` is a reusable factory/controller that creates a fresh `Task` via `createTask()` on each run, allowing background operations to be started, cancelled (`cancel()`), and restarted (`restart()`) multiple times.
- **Q2: How do you check inside the `call()` method if the user clicked "Cancel"?**
  - By checking `isCancelled()` inside the loop (and also noting that `Thread.sleep()` throws `InterruptedException` when `cancel()` interrupts the worker thread), allowing the background task to exit early and stop working immediately.
