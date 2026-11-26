// Our custom Observer interface
/**
 * Custom Observer interface for refactored DataModel updates.
 *
 * @author Michael Kilonzo
 */
public interface DataObserver {
    void onDataChanged(int newValue);
}
