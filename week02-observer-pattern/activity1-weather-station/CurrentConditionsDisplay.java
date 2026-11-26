/**
 * Concrete Observer displaying current temperature and humidity.
 *
 * @author Michael Kilonzo
 */
public class CurrentConditionsDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("  [Current] Temp: " + temperature + "°C, "
                + "Humidity: " + humidity + "%");
    }
}
