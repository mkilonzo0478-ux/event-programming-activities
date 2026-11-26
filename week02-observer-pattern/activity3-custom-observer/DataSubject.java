// Our custom Subject interface
/**
 * Custom Subject interface for refactored DataModel notifications.
 *
 * @author Michael Kilonzo
 */
public interface DataSubject {
    void addObserver(DataObserver o);
    void removeObserver(DataObserver o);
    void notifyObservers();
}
