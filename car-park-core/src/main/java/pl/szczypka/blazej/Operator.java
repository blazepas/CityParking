package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Local;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;

@Local
public class Operator {
    ObjectMapper objectMapperOperator = new ObjectMapper();
    DriverList driverList = new DriverList();
//    Driver driverOp = new Driver();
    DriverList value = null;

    public DriverList readJSON(){
        //ObjectMapper to read JSON file
        try {
//            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
            value = objectMapperOperator.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }



    public String checkIfDriverTurnOnParkingMeter(String plateNum) {
        Scanner scPlate = new Scanner(plateNum);
        String findVehiclePlate=scPlate.nextLine();
        String meterOut ="";
        for (int i = 0; i < readJSON().getDrivers().size(); i++) {
            if ((readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())) {
//                System.out.println("Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus);
                meterOut = readJSON().getDrivers().get(i).vehicleParkingMeterStatus;
//                meterOut = "Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus;
                System.out.println(meterOut);
            }
        }
        return meterOut;
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


        operator.checkIfDriverTurnOnParkingMeter("WWA7000");
    }


}