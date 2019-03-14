package pl.szczypka.blazej;

import org.junit.Test;
import static org.junit.Assert.*;

public class DriverTest {
Driver driver = new Driver();

    @Test
    public void parkingStatusMeter() {
        //Assertion with empty string is NOT correct
        assertTrue(driver.parkingStatusMeter("").isEmpty());

        //Assertion with space string is NOT correct
        assertNotEquals("start", driver.parkingStatusMeter(" "));

    }

    @Test
    public void createDriver() {
        System.out.println("Testing behaviour with wrong driverType parameter. Allowed only a or b as a String paramater.");
    driver.createDriver("SB11112", "normal");
    }

    @Test
    public void createDriverWithStartTime() {
        //testing behaviour with wrong driverType parameter. Allowed only a or b as a String paramater.
        driver.createDriver("WWA45716", "disable");
    }

}