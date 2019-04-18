package pl.szczypka.blazej;

import java.math.BigDecimal;
import java.util.List;

public interface DriverDao {
    void createDriverDB(Driver driver);
    void updateDriverDB(Driver oneDriverUpdate, String parkingStatusDB, String stopTimeDB, long minutesDB, BigDecimal paymentAllHoursDB, double countFromThirdHourDB);
    List<Driver> showListDriverDB();
    List<Driver> showStatusParkingMeterDB(String plateToCheck);
}
