package pl.szczypka.blazej;

import org.junit.Test;
import pl.szczypka.blazej.Driver;

import static org.junit.Assert.*;

public class DriverTest {
//DriverTest driverTest = new DriverTest();
Driver driver = new Driver();

    @Test
    public void parkingStatusMeter() {

        //Assertion with empty string is NOT correct
        assertTrue(!driver.parkingStatusMeter(""));

        //Assertion with space string is NOT correct
        assertTrue(!driver.parkingStatusMeter(" "));

        //Assertion with numbers and special characters is NOT correct
        assertTrue(!driver.parkingStatusMeter("start12@!"));

        //Assertion with string "false" is NOT correct
        assertTrue(!driver.parkingStatusMeter("false"));

        //Assertion with "stop" gives fales status
        assertTrue(!driver.parkingStatusMeter("stop"));

        //Assertion with lower and upper case will be recognised properly
        assertTrue(driver.parkingStatusMeter("StArT"));

        //Assertion with "start" gives true status
        assertTrue(driver.parkingStatusMeter("start"));


    }


}