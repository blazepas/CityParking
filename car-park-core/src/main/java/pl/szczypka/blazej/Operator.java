package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Local;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

@Local
public class Operator {
    ObjectMapper objectMapperOperator = new ObjectMapper();
    DriverList driverList = new DriverList();
    DriverList value = null;

    public DriverList readJSON(){
        //ObjectMapper to read JSON file
        try {
//            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
            value = objectMapperOperator.readValue(new File("/home/bsz/IdeaProjects/carpark_final 4/carpark/result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }



    public String checkIfDriverTurnOnParkingMeter(String plateNum) {
        System.out.println("Enter vehicle plate to check status eg.SBIG156, WAW1517 :");

//        Scanner scPlate = new Scanner(System.in);  //Used when input from console
        Scanner scPlate = new Scanner(plateNum);
        String findVehiclePlate=scPlate.nextLine();
        String meterOut ="";
//        StringBuffer sb = new StringBuffer();
//        PrintStream ps = new PrintStream(System.out);

        for (int i = 0; i < readJSON().getDrivers().size(); i++) {
            if((readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())){
//                System.out.println("Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus);
//                meterOut = readJSON().getDrivers().get(i).vehicleParkingMeterStatus;

                meterOut = "Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus;
            }else{
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


//        operator.checkIfDriverTurnOnParkingMeter("SBIG156");
    }


}