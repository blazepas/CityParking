package pl.szczypka.blazej;

import java.util.ArrayList;
import java.util.List;

public class DriverList {
    private List<Driver> driverList = new ArrayList<>();


    public List<Driver> getDrivers(){
        return driverList;
    }
    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    @Override
    public String toString() {
        return "DriverList{" +
                "driverList=" + driverList +
                '}';
    }
}
