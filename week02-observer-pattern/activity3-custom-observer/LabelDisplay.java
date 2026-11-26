/**
 * Concrete DataObserver displaying the numeric value as a label.
 *
 * @author Michael Kilonzo
 */
public class LabelDisplay implements DataObserver {
    @Override
    public void onDataChanged(int newValue) {
        System.out.println("Label shows: " + newValue);
    }
}
