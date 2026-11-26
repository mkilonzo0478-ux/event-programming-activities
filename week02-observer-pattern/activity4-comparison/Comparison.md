# Comparison of Event Notification Mechanisms in Java

**Author:** Michael Kilonzo

| Feature | Custom Observer | `java.util.Observer` | `PropertyChangeListener` | JavaFX Bindings |
| :--- | :--- | :--- | :--- | :--- |
| **Push or Pull?** | Both possible | Pull | Push (carries old + new) | Push |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (class) | No | No |
| **Thread-safe?** | Depends on you | No | Yes (with the right setup) | Yes |
| **Auto-generates boilerplate?** | No | No | Yes (via `PropertyChangeSupport`) | Yes |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can be typed to send any data?** | Yes | No (`Object`) | Yes (via generics not typical) | Yes |
| **Recommended today?** | Yes | No | Only for legacy / Swing Beans | Yes |

## Which One Should You Use?

- For a **plain Java project with custom needs** → **Custom Observer** (full control over method signatures, strong compile-time typing, push/pull choice, and zero inheritance constraints).
- For a **JavaFX application** → **JavaFX Bindings** (`ObservableValue` / `Property` bindings with declarative UI reactivity).
- For **legacy code using JavaBeans / Swing bound properties** → **`PropertyChangeListener`** (backed by `PropertyChangeSupport`, automatically delivers `PropertyChangeEvent` with property name, old value, and new value).
- **Never** → **`java.util.Observer` / `java.util.Observable`** (deprecated since Java 9 due to single inheritance, unspecified notification order, error-prone `setChanged()`, and untyped `Object` updates).

---

## Step 4: Self-Check

1. **Which mechanism carries both the old and new value of a property?**
   - `PropertyChangeListener` (via `PropertyChangeEvent.getOldValue()` and `getNewValue()`).
2. **Which mechanism requires a helper class to manage the listener list?**
   - `PropertyChangeListener` via `PropertyChangeSupport` (or `SwingPropertyChangeSupport`).
3. **Which two mechanisms are recommended for modern Java?**
   - Custom Observer and JavaFX Bindings.
4. **Which mechanism is deprecated and should be avoided?**
   - `java.util.Observer` / `java.util.Observable` (deprecated since Java 9).
