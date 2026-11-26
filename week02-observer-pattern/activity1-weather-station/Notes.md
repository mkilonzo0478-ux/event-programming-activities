# Activity 1: Self-Check Notes

**Author:** Michael Kilonzo

1. **What is the role of the `Subject` interface?**
   - The `Subject` interface defines the standard contract that any observable publisher must implement: `registerObserver(Observer o)`, `removeObserver(Observer o)`, and `notifyObservers()`. This allows observers and client code to interact with a subject through an abstraction rather than a concrete class.

2. **What happens if you call `notifyObservers()` without registering any observers?**
   - The `observers` list is initialized as an empty `ArrayList<>()` in `WeatherData`'s constructor, so the `for (Observer o : observers)` loop simply performs zero iterations and returns safely without throwing a `NullPointerException` or printing any display updates.

3. **What is the difference between `registerObserver()` and `removeObserver()`?**
   - `registerObserver(Observer o)` subscribes an observer by appending it to the `observers` list so it will receive future `update(...)` notifications. `removeObserver(Observer o)` unsubscribes that observer by removing it from the `observers` list so it stops receiving notifications and can be garbage-collected if no longer referenced elsewhere.

4. **Why is the `observers` list stored as an instance variable?**
   - Each `WeatherData` instance needs to maintain its own independent state and its own list of subscribed observers across method calls (`registerObserver`, `removeObserver`, `notifyObservers`). If it were a local variable, the subscriptions would be lost as soon as a method finished; if it were `static`, all `WeatherData` instances would improperly share the same subscriber list.
