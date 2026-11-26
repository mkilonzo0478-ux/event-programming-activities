# Week 10 Final Project Capstone: University Management System

Complete Event-Driven Architecture (EDA) implementation of the **Week 10 Capstone: University Management System** using JavaFX, FXML, Properties & Bindings, Background `Task`s, `ScheduledExecutorService`, and MySQL JDBC.

## Learning Outcomes Completed

- [x] **LO1 (EDA Principles):** Decouples the JavaFX UI (`MainView.fxml`, `StudentView.fxml`) from the MySQL data layer (`DatabaseUtil.java`, `sql/university_db.sql`).
- [x] **LO2 (Producer-Consumer Pattern):** Executes `SELECT` and `INSERT` SQL statements off the JavaFX Application Thread via `LoadStudentsTask` and `Task<Void>` so the UI never freezes.
- [x] **LO3 (Scheduled Tasks):** Uses `ScheduledExecutorService` (`scheduleAtFixedRate` every 5 seconds) in `App.java` to print system heartbeats and auto-refresh the dashboard safely via `Platform.runLater()`.
- [x] **LO4 (Event Communication):** Connects components cleanly through `@FXML` action events, observable JavaFX properties (`Student`), `TableColumn` cell value factories, and declarative `BooleanBinding` validation on `saveButton.disableProperty()`.

---

## Project Structure

```text
week10-university-system/
├── pom.xml
├── README.md
├── sql/
│   └── university_db.sql                         # Phase 1: MySQL database & tables script
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── university/
        │           ├── App.java                  # Phase 8: Entry point + 5s ScheduledExecutorService
        │           ├── DatabaseUtil.java         # Phase 3: JDBC connection bridge
        │           ├── LoadStudentsTask.java     # Phase 5: Background SELECT Task<ObservableList<Student>>
        │           ├── MainController.java       # Phase 9: Sidebar navigation + live dashboard stats
        │           ├── Student.java              # Phase 4: Observable JavaFX Property model
        │           └── StudentController.java    # Phase 7: Form bindings, TableView & async INSERT/SELECT
        └── resources/
            └── com/
                └── university/
                    ├── MainView.fxml             # Phase 9: BorderPane shell with sidebar & StackPane
                    └── StudentView.fxml          # Phase 6: Student form, TableView & status bar
```

---

## Step 1: Set Up the MySQL Database

Start your MySQL server (e.g., MySQL Workbench or XAMPP Control Panel -> **MySQL Start**), then run [`sql/university_db.sql`](./sql/university_db.sql):

```powershell
# Using XAMPP MySQL CLI (default root user with empty password):
& "C:\xampp\mysql\bin\mysql.exe" -u root < sql\university_db.sql

# Or in MySQL Workbench: open sql/university_db.sql and execute it.
```

### Configuring Credentials
`DatabaseUtil.java` defaults to:
- **URL:** `jdbc:mysql://localhost:3306/university_db`
- **User:** `root`
- **Password:** `""` (empty password, default for local XAMPP)

You can either edit `PASSWORD` directly in `DatabaseUtil.java` or pass environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`) without modifying code:
```powershell
$env:DB_PASSWORD = "your_password"
```

---

## Step 2: Build and Run

From inside `week10-university-system`:

```powershell
# If mvn is on your PATH:
mvn javafx:run

# Or using the bundled Apache NetBeans Maven binary on this machine:
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" javafx:run
```

*(You can also open `week10-university-system` in **VS Code** or **Apache NetBeans** and run `com.university.App`.)*

---

## Implementation Notes & Handbook Fixes

1. **`StudentView.fxml` Missing `HBox` Import Fixed:**
   - The handbook's `StudentView.fxml` uses `<HBox spacing="10">` for the input row while only importing `javafx.scene.layout.VBox`, which throws `javafx.fxml.LoadException: HBox is not a valid type`. Added `<?import javafx.scene.layout.HBox?>`.
2. **`MainView.fxml` Inline CSS Property Fixed:**
   - Restored the hyphen in `-fx-background-color: #34495e;` (split as `-fx background-color` across a page line-wrap in the guide) so sidebar buttons style cleanly without JavaFX CSS warnings.
3. **Background `Task` Error Handling (`setOnFailed`):**
   - Added `setOnFailed` handlers to `saveTask` and `LoadStudentsTask` so database connection issues or duplicate registration number (`UNIQUE`) violations report clear feedback in `statusLabel` instead of failing silently on worker threads.
4. **Clean `ScheduledExecutorService` Shutdown & Dashboard Auto-Refresh (LO3):**
   - `App.java` uses a daemon thread for the 5-second `ScheduledExecutorService`, invokes `Platform.runLater()` to auto-refresh the Dashboard view, and overrides `Application.stop()` to call `scheduler.shutdownNow()` when the window closes.
