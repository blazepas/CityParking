package pl.szczypka.blazej;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class OwnerTest {

    //check with current date
    @Test
    public void checkTotalMoneyForGivenDay() {
        Owner own = new Owner();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        String takeDay = dtf.format(now);
        own.checkTotalMoneyForGivenDay(takeDay);
    }
}