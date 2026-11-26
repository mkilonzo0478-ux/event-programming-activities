# Week 7: Event Handling in JavaFX

Complete implementation and self-check guide for **Week 7: Event Handling in JavaFX**.

## Activities & Deliverables

| Activity | File(s) | Learning Outcome |
| :--- | :--- | :--- |
| **Activity 1: Event Filters vs. Event Handlers** | `src/main/java/com/example/EventPropagationDemo.java` | #2 — Differentiate between event filters (capturing) and event handlers (bubbling) |
| **Activity 2: Mouse and Key Events** | `src/main/java/com/example/MouseKeyDemo.java` | #1 — Handle action, key, and mouse events |
| **Activity 3: Drag and Drop** | `src/main/java/com/example/DragDropDemo.java` | #3 — Implement drag-and-drop functionality in JavaFX |
| **Activity 4: Custom Events** | `src/main/java/com/example/LoginSuccessEvent.java`, `src/main/java/com/example/CustomEventDemo.java` | #4 — Create and fire custom events in JavaFX |

> **Note on Guide Fixes:**
> - **Activity 2 (`MouseKeyDemo.java`):** Replaced `root.setFocusable(true)` (a Swing method) with JavaFX's `root.setFocusTraversable(true)`.
> - **Activity 3 (`DragDropDemo.java`):** Fixed line-wrap typo `-fx text-fill: white;` to valid JavaFX CSS `-fx-text-fill: white;`.

## How to Run Each Activity

Using Maven (`javafx:run` with `javafx.mainClass`):

```powershell
# Activity 1: Event Filters vs. Event Handlers
mvn javafx:run -Djavafx.mainClass=com.example.EventPropagationDemo

# Activity 2: Mouse and Key Events
mvn javafx:run -Djavafx.mainClass=com.example.MouseKeyDemo

# Activity 3: Drag and Drop
mvn javafx:run -Djavafx.mainClass=com.example.DragDropDemo

# Activity 4: Custom Events
mvn javafx:run -Djavafx.mainClass=com.example.CustomEventDemo
```

---

## Self-Check Answers

### Activity 1: Event Filters vs. Event Handlers
- **Q1: Which phase of event propagation does an Event Filter belong to?**
  - The **Capturing Phase** — it travels top-down from the root node to the target node.
- **Q2: What does the `e.consume()` method do?**
  - It stops the event from propagating any further. If called inside an event filter, the event never reaches the target node or any bubbling-phase handlers.
- **Q3: How do you register an Event Handler (Bubbling phase) in code?**
  - Using `addEventHandler(...)` or convenience methods such as `setOnMouseClicked(...)`, `setOnAction(...)`, etc.

### Activity 2: Mouse and Key Events
- **Q1: Which method handles tracking the mouse's position as it moves without clicking?**
  - `setOnMouseMoved(...)`
- **Q2: Why might a `KeyEvent` not fire when you press a key?**
  - Because the target node does not have **focus**. The node must be focusable (`setFocusTraversable(true)`) and hold focus (`root.requestFocus()`).
- **Q3: How do you check which physical key was pressed in a `KeyEvent`?**
  - Using `e.getCode()` (e.g., `e.getCode() == KeyCode.UP`).

### Activity 3: Drag and Drop
- **Q1: Which event handler must be called on the source to initiate a drag operation?**
  - `setOnDragDetected(...)`
- **Q2: What is the purpose of `event.acceptTransferModes(TransferMode.MOVE)` in the `setOnDragOver` handler?**
  - It informs JavaFX that the target node accepts the dragged data; without it, dropping onto the target is rejected.
- **Q3: How do you extract the data from the dragged item when it is dropped?**
  - By calling `event.getDragboard()` in `setOnDragDropped(...)` and reading the payload (e.g., `db.getString()`).

### Activity 4: Custom Events
- **Q1: Why would you use a Custom Event instead of just calling a method directly?**
  - To achieve **loose coupling**: the source firing the event does not need to know which or how many listeners respond to it, making components modular and reusable.
- **Q2: Which class must your custom event extend?**
  - `javafx.event.Event`
- **Q3: How do you define the type of your custom event?**
  - With a `public static final EventType<...>` field (e.g., `new EventType<>(Event.ANY, "LOGIN_SUCCESS")`).

---

## Final Self-Assessment Checklist

- [x] **Outcome 1:** Handle action, key (`setOnKeyPressed` + `KeyCode`), and mouse (`setOnMouseMoved`) events
- [x] **Outcome 2:** Differentiate between Event Filters (top-down capturing) and Event Handlers (bottom-up bubbling), and use `e.consume()`
- [x] **Outcome 3:** Implement Drag and Drop (`setOnDragDetected`, `Dragboard`, `setOnDragOver`, `setOnDragDropped`)
- [x] **Outcome 4:** Create and fire custom events (`Event`, `EventType`, `fireEvent()`, `addEventHandler()`)
