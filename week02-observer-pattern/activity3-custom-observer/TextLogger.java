/**
 * Additional Concrete DataObserver logging value changes.
 *
 * @author Michael Kilonzo
 */
public class TextLogger implements DataObserver {
    @Override
    public void onDataChanged(int newValue) {
        System.out.println("LOG: value changed to " + newValue);
    }
}
