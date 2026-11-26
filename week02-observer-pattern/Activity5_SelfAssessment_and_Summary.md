# Activity 5: Final Self-Assessment, Summary & Final Reflection

**Author:** Michael Kilonzo

---

## Part 1: Self-Assessment Answers

### Learning Outcome 1: Design and Implement the Observer Pattern

1. **Draw the UML diagram of the Observer pattern. Label `Subject`, `Observer`, `ConcreteSubject`, `ConcreteObserver`.**

   ```text
   +-----------------------------------+          observes / notifies          +-------------------------+
   |           <<interface>>           | ------------------------------------> |      <<interface>>      |
   |              Subject              |                                       |        Observer         |
   +-----------------------------------+                                       +-------------------------+
   | + registerObserver(o: Observer)   |                                       | + update(data...)       |
   | + removeObserver(o: Observer)     |                                       +-------------------------+
   | + notifyObservers()               |                                                    ^
   +-----------------------------------+                                                    |
                     ^                                                                      | implements
                     | implements                                                           |
   +-----------------------------------+                                       +-------------------------+
   |          ConcreteSubject          |                                       |    ConcreteObserver     |
   |          (WeatherData)            | <------------------------------------ |  (CurrentConditions,    |
   +-----------------------------------+        registers / unregisters        |   Statistics, Forecast) |
   | - observers: List<Observer>       |                                       +-------------------------+
   | - state (temp, humidity, pressure)|                                       | + update(data...)       |
   | + setMeasurements(...)            |                                       +-------------------------+
   +-----------------------------------+
   ```

2. **Write the Java code (from memory) for a `Subject` interface.**

   ```java
   public interface Subject {
       void registerObserver(Observer o);
       void removeObserver(Observer o);
       void notifyObservers();
   }
   ```

3. **Why does the `Subject` need a list of observers?**
   - A subject often needs to broadcast state changes to multiple independent observers at the same time (one-to-many dependency) and allow observers to subscribe or unsubscribe dynamically at runtime. Storing a `List<Observer>` lets the subject maintain those subscribers and loop through all of them in `notifyObservers()`.

---

### Learning Outcome 2: Evaluate Legacy `java.util.Observer`

4. **List three problems with `java.util.Observable`.**
   1. **Class instead of interface:** Because `Observable` is a class and Java supports only single inheritance, a subject cannot extend any other superclass.
   2. **Protected and error-prone `setChanged()`:** You must remember to call `setChanged()` before `notifyObservers()`, or notifications silently fail; external helpers cannot call it directly.
   3. **Unspecified notification order & weak typing:** The notification order cannot be controlled, and `update(Observable o, Object arg)` relies on untyped `Object` or pull-based downcasting (`instanceof`).

5. **Why was this API deprecated?**
   - It was deprecated in Java 9 because its design is inflexible, non-extensible, lacks type safety, does not support rich event metadata (such as old/new values), and has largely been superseded by `java.beans.PropertyChangeListener`, `java.util.concurrent.Flow`, JavaFX properties, and custom Observer interfaces.

6. **What is the "pull model" and why is it harder to use?**
   - In the **pull model**, the subject sends only a minimal notification (e.g., passing itself without the changed fields), forcing each observer to inspect `instanceof`, downcast the subject, and call getter methods to pull the data it needs. It is harder to use because it couples observers to the concrete subject's getter API and adds boilerplate and extra method calls.

---

### Learning Outcome 3: Create a Custom Event Notification System

7. **What methods must a custom `Subject` interface provide?**
   - Registration (`registerObserver` / `addObserver`), unregistration (`removeObserver`), and notification dispatch (`notifyObservers`).

8. **How does the `Subject` notify all observers?**
   - When its state changes (e.g., inside `setMeasurements(...)` or `setValue(...)`), the subject calls `notifyObservers()`, which iterates over the `observers` collection and invokes the observer contract method (e.g., `o.update(...)` or `o.onDataChanged(value)`) on each registered observer.

9. **How do you prevent a memory leak when observers are no longer needed?**
   - Explicitly unsubscribe them by calling `removeObserver(observer)` when they are closed, disposed, or no longer needed (or use weak references where appropriate), because as long as the long-lived subject holds a strong reference in its `observers` list, the garbage collector cannot reclaim the observer.

---

### Learning Outcome 4: Compare Different Mechanisms

10. **Name four event notification mechanisms in Java.**
    1. Custom Observer interfaces (`Subject` / `Observer`)
    2. Legacy `java.util.Observer` and `java.util.Observable` (deprecated)
    3. `java.beans.PropertyChangeListener` with `PropertyChangeSupport`
    4. JavaFX Properties and Bindings

11. **Which one is best for JavaFX?**
    - **JavaFX Properties and Bindings** (`ObservableValue`, `ChangeListener`, and declarative property binding).

12. **Which one is deprecated?**
    - **`java.util.Observer` and `java.util.Observable`** (deprecated since Java 9).

---

## Part 2: Self-Assessment Score
- **Score:** 12 / 12 — Excellent! All four Week 2 learning outcomes (#1–#4) are mastered and verified with working Java code.

---

## Part 3: One-Page Summary of the Observer Pattern

### Core Concept
The **Observer Pattern** is a behavioral design pattern that defines a **one-to-many dependency** between objects so that when one object (the **Subject** or publisher) changes state, all of its dependents (**Observers** or subscribers) are notified and updated automatically.

### Key Roles
- **`Subject` (Interface):** Declares `registerObserver`, `removeObserver`, and `notifyObservers`.
- **`ConcreteSubject`:** Owns the observable state and a `List<Observer>`; notifies subscribers whenever state changes.
- **`Observer` (Interface):** Declares the notification callback (`update(...)` / `onDataChanged(...)`).
- **`ConcreteObserver`:** Implements the `Observer` interface to react to updates (e.g., refreshing a display or writing a log) without the subject knowing its concrete type.

### Push vs. Pull Notification
- **Push Model:** The subject passes updated values directly into `update(temp, humidity, pressure)` — convenient and decoupled from concrete getters.
- **Pull Model:** The subject passes only a reference to itself, and observers query getters — flexible when observers need different subsets of state, though more coupled unless mediated by an interface.

### Architectural Benefits & Best Practices
1. **Loose Coupling:** Subjects depend only on the `Observer` abstraction; new observers (like `TextLogger`) can be added without modifying `DataModel` (Open/Closed Principle).
2. **Runtime Flexibility:** Observers can attach and detach dynamically.
3. **Avoid Legacy APIs:** Never use `java.util.Observer`/`Observable` in new code; prefer custom typed interfaces for plain Java, `PropertyChangeSupport` for JavaBeans/Swing, and JavaFX Bindings for JavaFX.
4. **Lifecycle Hygiene:** Always call `removeObserver(...)` when an observer's lifecycle ends to prevent listener memory leaks.

---

## Part 4: Final Reflection

The Observer pattern is a behavioral design pattern that establishes a one-to-many relationship between a subject holding state and any number of observers that react automatically whenever that state changes, which is essential because it eliminates tight coupling between data models and their consumers. Rather than hard-coding calls to specific display or logger classes inside a model, the subject depends only on a lightweight observer interface, allowing new behaviors to be plugged in or removed at runtime without altering existing code. This maps directly onto event-driven programming, where event sources maintain lists of registered listeners and dispatch event notifications whenever user actions, timers, or state updates occur. Building the pattern from scratch in Activities 1 and 3 and comparing it against `java.util.Observer` and `PropertyChangeListener` clarified why interface-based designs avoid single-inheritance traps and silent notification bugs. For my own plain Java projects, I would choose a custom typed Observer interface because it gives full compile-time type safety, clean push parameters, and zero framework dependencies, whereas for a JavaFX desktop application I would use JavaFX Properties and Bindings for built-in declarative UI updates.
