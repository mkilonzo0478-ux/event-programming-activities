// ============================================================
// SUBJECT INTERFACE
// ============================================================
// Defines the operations that any "publisher" must support.
/**
 * Subject (publisher) contract for managing and notifying observers.
 *
 * @author Michael Kilonzo
 */
public interface Subject {
    // Add an observer to the notification list.
    void registerObserver(Observer o);

    // Remove an observer from the list.
    void removeObserver(Observer o);

    // Notify every registered observer of a state change.
    void notifyObservers();
}
