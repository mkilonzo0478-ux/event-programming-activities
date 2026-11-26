# Limitations of java.util.Observer and java.util.Observable

**Author:** Michael Kilonzo

## Limitation 1: Single Inheritance
Explain: Why is it a problem that `Observable` is a CLASS, not an INTERFACE?

Your answer: Because Java supports only single class inheritance (`extends`), making `Observable` a concrete class forces any subject such as `LegacyWeatherData` to spend its one superclass slot on `Observable`. If the class already needs to extend another domain base class (e.g., `SensorDevice`, `JPanel`, or an entity superclass), it cannot become `Observable` without introducing an awkward wrapper/delegate class. By contrast, a custom `Subject` interface lets any class become observable while remaining free to extend any superclass.

## Limitation 2: Cannot Change the Notification Order
Explain: In what order are observers notified? Can you control this?

Your answer: In `java.util.Observable`, the Javadoc explicitly states that the notification order is unspecified (in practice, `Vector`-backed iteration historically notified observers in reverse registration order, and subclasses cannot override `notifyObservers()` cleanly to enforce priority or registration order). Because the observer list is private inside `Observable` and notification order is not guaranteed by contract, you cannot reliably control or customize the sequence in which observers receive updates.

## Limitation 3: setChanged() is Confusing
Explain: Why must you call `setChanged()` before `notifyObservers()`?

Your answer: `Observable` maintains an internal boolean `changed` flag and checks `hasChanged()` inside `notifyObservers()`, doing nothing unless `setChanged()` was called first. Moreover, `setChanged()` is `protected`, so external code cannot invoke it, and forgetting to call `setChanged()` in a subclass causes `notifyObservers()` to fail silently with no warning or error. This makes bugs difficult to spot and prevents composition without subclassing `Observable`.

## Limitation 4: Pull Model by Default
Explain: How does the observer get the data? Is this efficient?

Your answer: By default, calling `notifyObservers()` without an argument passes `null` as `arg` and only sends a reference to the `Observable` object (`update(Observable o, Object arg)`). Each observer must check `instanceof`, downcast `o` to the concrete subject (`LegacyWeatherData`), and invoke public getter methods (`getTemperature()`, `getHumidity()`) to pull the data. This increases coupling to the concrete subject, forces extra getter calls across every observer, and lacks compile-time type safety (even when pushing an `Object arg`, it is untyped `Object`).

## Limitation 5: Deprecated Since Java 9
Explain: Why did Java deprecate these classes?

Your answer: Both `java.util.Observer` and `java.util.Observable` were deprecated in Java 9 because the event model they support is too limited and inflexible: `Observable` is a class rather than an interface, `setChanged()` is `protected`, notification order is unspecified, the `update(Observable, Object)` signature is not type-safe, it does not carry rich event metadata (like old vs. new values), and the API is awkward to extend or use in concurrent/reactive applications. The JDK recommends `java.beans` (`PropertyChangeListener`/`PropertyChangeSupport`), `java.util.concurrent.Flow` for reactive streams, or custom typed Observer interfaces instead.

---

## Step 4: Self-Check

1. **Why can't you extend another class if you already extend `Observable`?**
   - Java only allows a class to directly `extend` one superclass (single implementation inheritance). Once `LegacyWeatherData extends Observable`, it cannot extend any other class.

2. **What is the difference between the push model and the pull model?**
   - In the **push model**, the subject sends the updated state directly as parameters to `update(...)` (e.g., `update(temperature, humidity, pressure)`). In the **pull model**, the subject merely notifies the observer that something changed (e.g., `update(Observable o, Object arg)` with `null`), and the observer must query the subject via getter methods to retrieve the fields it needs.

3. **Why did Java deprecate these classes?**
   - They were deprecated in Java 9 because `Observable` forces single-inheritance coupling, `setChanged()` is `protected` and error-prone, `update(Observable, Object)` lacks type safety, notification order is unspecified, and modern alternatives (`PropertyChangeSupport`, custom typed interfaces, `Flow`, or JavaFX bindings) provide cleaner, richer, and safer event notification.
