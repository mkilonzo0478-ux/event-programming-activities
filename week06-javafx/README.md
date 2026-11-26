# Week 6: Introduction to Modern JavaFX

Complete deliverables for **Week 6 Self-Paced Guide: Introduction to Modern JavaFX**.

## Project Structure

- [pom.xml](./pom.xml) — Maven configuration with OpenJFX 21 (`javafx-controls`, `javafx-fxml`) and `javafx-maven-plugin`
- [App.java](./src/main/java/com/example/App.java) — **Activity 1:** Programmatic JavaFX Hello World (Stage, Scene, VBox, Button event on JavaFX Application Thread)
- [LayoutDemo.java](./src/main/java/com/example/LayoutDemo.java) — **Activity 2:** Programmatic JavaFX Layouts (`BorderPane`, `HBox`, `VBox`, `GridPane`)
- [login.fxml](./src/main/resources/com/example/login.fxml) — **Activity 3:** Declarative FXML Login View (`VBox`, `Label`, `TextField`, `PasswordField`, `Button`)
- [LoginController.java](./src/main/java/com/example/LoginController.java) & [FXMLApp.java](./src/main/java/com/example/FXMLApp.java) — **Activity 4:** MVC Controller with `@FXML` field injection and `onAction="#handleLoginButtonAction"`
- [DashboardApp.java](./src/main/java/com/example/DashboardApp.java) — **Activity 5:** Capstone SaaS Dashboard (`BorderPane`, styled header, sidebar navigation, KPI metric cards)

## How to Run

From the `week06-javafx` directory:

> **Note:** If `mvn` is not on your global `PATH`, use the bundled NetBeans Maven binary:
> `& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd"` instead of `mvn`.

```powershell
# Activity 1: First JavaFX Window (default mainClass)
mvn clean javafx:run

# Activity 2: JavaFX Layouts Demo
mvn clean javafx:run "-Djavafx.mainClass=com.example.LayoutDemo"

# Activity 3 & 4: FXML Login App with Controller
mvn clean javafx:run "-Djavafx.mainClass=com.example.FXMLApp"

# Activity 5: Capstone SaaS Dashboard
mvn clean javafx:run "-Djavafx.mainClass=com.example.DashboardApp"
```

*(Note: In PowerShell, wrap `-Djavafx.mainClass=...` in quotes so PowerShell does not split the property on `.`.)*

## Self-Check Answers

### Activity 1 Self-Check
- **Q1: What is the difference between a Stage and a Scene?**
  `Stage` is the top-level OS window (including the title bar and window controls), whereas `Scene` holds the scene graph of visual UI `Node` components displayed inside that window. A `Stage` displays one `Scene` at a time, though scenes can be swapped dynamically.
- **Q2: What is the name of the JavaFX main thread?**
  `JavaFX Application Thread`. Like Swing's Event Dispatch Thread (EDT), all live UI node modifications and event handlers execute on this thread.

### Activity 4 Self-Check
- **Q1: What does `@FXML` do?**
  `@FXML` marks controller fields and event-handler methods so `FXMLLoader` can inject the matching FXML elements (via `fx:id`) and bind `onAction` attributes even when the fields/methods are `private`.
- **Q2: Why do we use FXML instead of writing everything in Java?**
  It enforces separation of concerns (MVC): UI layout and structure live in declarative XML (`login.fxml`, editable visually in Scene Builder), while business logic and event handling stay clean and modular in Java (`LoginController.java`).

## Final Self-Assessment Checklist

- [x] **Outcome 1:** Created a basic JavaFX application (`Stage`, `Scene`, root `Node`) and configured `javafx:run`.
- [x] **Outcome 2:** Designed UI layouts using both programmatic panes (`HBox`, `VBox`, `GridPane`, `BorderPane`) and declarative `FXML`.
- [x] **Outcome 3:** Verified the JavaFX architecture and single-threaded UI model (`JavaFX Application Thread`).
- [x] **Outcome 4:** Built functional JavaFX applications including an `@FXML`-wired Login form and the Capstone SaaS Dashboard.
