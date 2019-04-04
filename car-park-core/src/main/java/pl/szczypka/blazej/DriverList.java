package pl.szczypka.blazej;

import java.util.ArrayList;
import java.util.List;

public class DriverList {

    private List<Driver> driverList = new ArrayList<>();


    //TODO : add to get unmodifable and change saving to use set method
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