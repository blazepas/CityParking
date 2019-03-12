package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Local;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;

public class Operator {
    ObjectMapper objectMapperOperator = new ObjectMapper();
    DriverList value = null;

    public DriverList readJSON(){
        //ObjectMapper to read JSON file
        try {
            value = objectMapperOperator.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public String checkIfDriverTurnOnParkingMeter(String findVehiclePlate) {
        String meterOut ="";
        for (int i = 0; i < readJSON().getDrivers().size(); i++) {
            if ((readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())) {
                meterOut = readJSON().getDrivers().get(i).vehicleParkingMeterStatus;
                System.out.println(meterOut);
            }
        }
        return meterOut;
    }
}