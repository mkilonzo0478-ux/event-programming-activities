# Week 8: Properties and Bindings in JavaFX

Complete implementation of the **Week 8 Self-Paced Guide: Properties and Bindings in JavaFX**.

## Learning Outcomes Completed

- [x] **Outcome 1:** Use JavaFX properties (`StringProperty`, `IntegerProperty`) to create observable data models
- [x] **Outcome 2:** Implement `ChangeListener` and `InvalidationListener` on properties
- [x] **Outcome 3:** Create unidirectional (`.bind()`) and bidirectional (`.bindBidirectional()`) bindings between UI and model
- [x] **Outcome 4:** Build a fully reactive UI that updates automatically with bindings

---

## Project Structure

```text
week08-javafx-bindings/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    ├── Person.java              # Activity 1: Observable Properties (Data Model)
                    ├── ListenerDemo.java        # Activity 2: ChangeListener vs. InvalidationListener
                    ├── UnidirectionalDemo.java  # Activity 3: Unidirectional Binding
                    ├── BidirectionalDemo.java   # Activity 4: Bidirectional Binding
                    └── ReactiveFormApp.java     # Activity 5: Capstone - Reactive Registration Form
```

---

## How to Run (Maven / VS Code)

From inside `week08-javafx-bindings`:

```powershell
# Run default Capstone app (ReactiveFormApp)
mvn javafx:run

# Run specific activities by overriding mainClass:
mvn javafx:run "-Djavafx.mainClass=com.example.ListenerDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.UnidirectionalDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.BidirectionalDemo"
mvn javafx:run "-Djavafx.mainClass=com.example.ReactiveFormApp"
```

*(In VS Code with the Extension Pack for Java, you can also open any activity file and click **Run** directly above `public static void main`.)*

---

## Implementation Notes & Self-Check Summary

### Activity 1: Observable Properties (`Person.java`)
- **Why `StringProperty` instead of `String`?** `String` is immutable and cannot notify observers when replaced. `StringProperty` wraps the value in an observable object that notifies listeners and bound UI controls automatically.
- **Purpose of `nameProperty()`:** Returns the `StringProperty` instance itself so UI controls or listeners can attach to it (e.g., `label.textProperty().bind(person.nameProperty())`).

### Activity 2: `ChangeListener` vs. `InvalidationListener` (`ListenerDemo.java`)
- **`InvalidationListener` vs. `ChangeListener`:** `InvalidationListener` only signals that the property became invalid ("something changed"), whereas `ChangeListener` eagerly evaluates the property and supplies both `oldValue` and `newValue`.
- **Setting the same value twice:** Neither listener fires when `person.setName("Bob")` is called a second time with the identical value `"Bob"`.
- *Note:* `Platform.exit()` is included at the end of `start()` so the console demo exits cleanly without leaving the JavaFX toolkit running in the background.

### Activity 3: Unidirectional Binding (`UnidirectionalDemo.java`)
- **Unidirectional flow:** Data flows one way (`nameField.textProperty()` -> `greetingLabel.textProperty()`).
- **Calling `setText(...)` on a bound property:** Throws `java.lang.RuntimeException: A bound value cannot be set.` unless `.unbind()` is called first.

### Activity 4: Bidirectional Binding (`BidirectionalDemo.java`)
- **`bind()` vs. `bindBidirectional()`:** `bind()` syncs target from source only; `bindBidirectional()` keeps both properties synchronized in either direction.
- **Why `NumberStringConverter`?** Converts between `Slider.valueProperty()` (`DoubleProperty` / `Property<Number>`) and `TextField.textProperty()` (`StringProperty`).

### Activity 5: Capstone — Reactive Registration Form (`ReactiveFormApp.java`)
- **Why bind `disableProperty()` instead of `KeyListener` `if`-statements?** Declarative bindings automatically stay synchronized with all input fields without manual event wiring or stale state bugs.
- **Role of `.not()`:** Inverts the boolean expression so `submitBtn` is **disabled** (`true`) whenever the email does **not** contain `"@"`.
- **JavaFX API Fix:** JavaFX's `StringExpression` / `StringProperty` class does not define a `.contains(String)` method in OpenJFX 17/21. To ensure clean compilation while preserving `.not()`, the `"@"` check uses `Bindings.createBooleanBinding(() -> emailField.getText() != null && emailField.getText().contains("@"), emailField.textProperty()).not()`.
