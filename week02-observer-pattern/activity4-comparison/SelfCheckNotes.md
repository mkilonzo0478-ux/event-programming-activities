# Activity 4: Self-Check Notes

**Author:** Michael Kilonzo

1. **Which mechanism carries both the old and new value of a property?**
   - `PropertyChangeListener` — `PropertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue)` wraps both `evt.getOldValue()` and `evt.getNewValue()` inside the `PropertyChangeEvent` passed to each listener.

2. **Which mechanism requires a helper class to manage the listener list?**
   - `PropertyChangeListener` — uses `java.beans.PropertyChangeSupport` to store listeners, fire events only when the value actually changes, and handle thread-safe listener management.

3. **Which two mechanisms are recommended for modern Java?**
   - **Custom Observer** interfaces (for plain Java / domain-specific architectures) and **JavaFX Properties/Bindings** (for modern JavaFX desktop UIs).

4. **Which mechanism is deprecated and should be avoided?**
   - `java.util.Observer` and `java.util.Observable` (deprecated since Java 9).
