package pl.szczypka.blazej;

import java.io.File;
import java.util.Scanner;

public class Owner extends Operator {

    //From JSON file read data (ArrayList with objects saved in JSON)
//    public void checkTotalMoneyForGivenDay(){
    public String checkTotalMoneyForGivenDay(String dateToCheck){
        //ListIterator<Driver> litr = null;
        double totalMoney = 0;
        String yourDateToCheck ="";
        int checkOnlyOneDay = 0;
        String moneyOut = "";
        //ObjectMapper to read JSON file
        try {
            value = objectMapperOperator.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {

            int listSize = value.getDrivers().size();

            //Type date to check how much you earned per that day
            if (checkOnlyOneDay < 1){
                System.out.println("Enter date in format \"dd-mm-yyyy\" to check how much money you earned per day: ");
//                Scanner scanner = new Scanner(System.in);
                Scanner scanner = new Scanner(dateToCheck);
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
                moneyOut = "Total money earned in day "+yourDateToCheck+" is: " +totalMoneyRoundNumber + " PLN";

                //System.out.println(value.getDrivers().get(0).paymentForAllHours); //taking exactly one object Driver from a list
                checkOnlyOneDay++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return moneyOut;
    }


    public static void main(String[] args) {
        Owner owner = new Owner();
        owner.checkTotalMoneyForGivenDay("28-01-2019");
    }

}
