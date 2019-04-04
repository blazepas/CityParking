package pl.szczypka.blazej;

import java.util.List;

public interface DriverDao {
    void createDriverDB(Driver driver);
    List<Driver> updateDriverDB(String plateToCheck);
    List<Driver> showListDriverDB();
    List<Driver> showStatusParkingMeterDB(String plateToCheck);
}
