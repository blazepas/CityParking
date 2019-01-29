package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Scanner;

public class Operator {
    ObjectMapper objectMapperOperator = new ObjectMapper();
    DriverList driverList = new DriverList();
    DriverList value = null;

    public DriverList readJSON(){
        //ObjectMapper to read JSON file
        try {
            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public void checkIfDriverTurnOnParkingMeter() {
        System.out.println("Enter vehicle plate to check status eg.SBIG156, WAW1517 :");
        Scanner scPlate = new Scanner(System.in);
        String findVehiclePlate=scPlate.nextLine();

        for (int i = 0; i < readJSON().getDrivers().size(); i++) {
            if((readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())){
                System.out.println("Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus);
            }else{
            }
        }
    }


    public static void main(String[] args) {
        Operator operator = new Operator();
        ObjectMapper objectMapperOperator = new ObjectMapper();
        //Reading JSON file to variable value - this is DriverList type
        DriverList value = null;
        try {
            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(value);


        operator.checkIfDriverTurnOnParkingMeter();
    }
}