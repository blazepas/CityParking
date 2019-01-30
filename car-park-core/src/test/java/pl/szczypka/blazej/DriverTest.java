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


}