package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Scanner;

public class Owner {
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
        String readStatus="";
        String searchedPlate="WFA2131";
        for (int i = 0; i < readJSON().getDrivers().size(); i++) {
//            readStatus = readJSON().getDrivers().get(i).vehiclePlate;
//            System.out.println(readStatus);
            //find driver car
            if((readJSON().getDrivers().get(i).vehiclePlate).equals(searchedPlate)){
                System.out.println("Status for car "+searchedPlate+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus);
            }else{
//                System.out.println("There is no such a car in the car park");
            }
        }
    }

    //From JSON file read data (ArrayList with objects saved to JSON)
    public void checkTotalMoneyForGivenDay(){
        //ListIterator<Driver> litr = null;
        double totalMoney = 0;
//        DriverList value = null;
        String yourDateToCheck ="";
        int checkOnlyOneDay = 0;
//        //ObjectMapper to read JSON file
        try {
            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
//            //ObjectMapper to read JSON file
//            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);

            //From DriveList use method to get drivers and iterate using listIterator
            //litr=value.getDrivers().listIterator();
            int listSize = value.getDrivers().size();

            //Type date to check how much you earned per that day
            if (checkOnlyOneDay < 1){
                System.out.println("Enter date in format \"dd-mm-yyyy\" to check how much money you earned per day: ");
                Scanner scanner = new Scanner(System.in);
                yourDateToCheck=scanner.nextLine();

                System.out.println(yourDateToCheck);

                //Compare all Drivers timestamps
                try {
                    for (int i = 0; i<listSize; i++) {
                        if(yourDateToCheck.equals(value.getDrivers().get(i).timestamp)) {
                            totalMoney = totalMoney + value.getDrivers().get(i).paymentForAllHours;
                        }
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

            String totalMoneyRoundNumber = String.format("%.2f", totalMoney);
            System.out.println("Total money earned in day "+yourDateToCheck+" is: " +totalMoneyRoundNumber + " PLN");

            //System.out.println(value.getDrivers().get(0).paymentForAllHours); //taking exactly one object one Driver from a list
                checkOnlyOneDay++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Owner owner = new Owner();
        ObjectMapper objectMapperOperator = new ObjectMapper();


        //Reading JSON file to variable value - this is DriverList type
        DriverList value = null;
        try {
            value = objectMapperOperator.readValue(new File("result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(value);
        //Calling method to check how much money was collected per one day

        owner.checkTotalMoneyForGivenDay();
        owner.checkIfDriverTurnOnParkingMeter();
    }
}
