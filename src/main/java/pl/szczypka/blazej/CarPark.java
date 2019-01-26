package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarPark {


    public static void main(String[] args){
        CarPark carPark = new CarPark();
        try {
            carPark.getDrivers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    DriverList driverList1 = new DriverList();
    DriverList driverList2 = new DriverList();
    DriverList driverList3 = new DriverList();

//        private static DriverList getDrivers(){
    public DriverList getDrivers() throws IOException {

            Driver driverAcc = new Driver();
            System.out.println("Choose what would like to do. Enter 2-to let system to generate car park full simulation or 1-to only add Driver manually");
            Scanner scOptions = new Scanner(System.in);
            String enteredOption = scOptions.nextLine();
            int number=0;
            number=Integer.parseInt(enteredOption);

            switch(number){
                case 1:
                    driverList1=driverAcc.askList();
                    System.out.println("CASEEEEEEEEEE:     "+driverList1);

//                    DriverList driverList = getDrivers();
//                    System.out.println(driverList);
                    ObjectMapper mapper = new ObjectMapper();
                    /**
                     * Below function write object to JSON file (mocked database)
                     */
                    try{
                        mapper.writeValue(new FileWriter("result.json"),driverList1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
//                    DriverList driverList = getDrivers();
//                    ObjectMapper mapper = new ObjectMapper();
//                    System.out.println(driverList);

                    /**

                     * Below function write object to JSON file (mocked database)

                     */
//                    try{
//                        mapper.writeValue(new FileWriter("result.json"),driverList);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }

                    //read array list and write to string
//                    String newVehicles="";
//                    for(int i = 0; i<driverList1.getDrivers().size(); i++){
//                        String oneNewVehicle = String.valueOf(driverList1.getDrivers().get(i));
//                        newVehicles = newVehicles + oneNewVehicle;
//                    }
                    // append string with new entries to JSON
                    // update JSON
//                    BufferedWriter bw = null;
//                    try {
//                        bw = new BufferedWriter(new FileWriter("result.json", true));
//                        bw.write(newVehicles);
//                        bw.newLine();
//                        bw.flush();
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }



                    /**

                     * Below function write object to JSON file (mocked database)

                     */
//                    FileWriter file = new FileWriter("result.json");

//                    try{
//                        BufferedWriter bw = null;
//                        try {

//                            bw = new BufferedWriter(new FileWriter("result.json", true));
//                            bw.write(mapper.readValue(new File("result.json"), DriverList.class));
//                            bw.newLine();
//                            bw.flush();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        mapper.writeValue(new FileWriter("result.json"),driverList1);
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }

                    break;

                case 2:
                    Driver driver = new Driver("SBIG156", "a");
                    Driver driver2 = new Driver("WWA2131", "b");
                    Driver driver10 = new Driver("WAW1517", "a");
                    Driver driver11 = new Driver("SK9145H", "ZZZZZZZ");
                    //start and stop timer both works in the same time!!
                    driver.startTimerMethod();

                    driver2.startTimerMethod();
                    driver2.stopTimerMethod();


                    //Use method to check how much driver has to pay
                    if(driver.vehicleParkingMeterStatus.toUpperCase().equals("OFF")){
                        try {
                            driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if(driver.vehicleParkingMeterStatus.toUpperCase().equals("ON")){
                        System.out.println("Parking meter for vehicle "+driver.vehiclePlate+" still running");

                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(driver2.vehicleParkingMeterStatus.equals("OFF")){
                        try {
                            driver2.howMuchIsToPay(driver2.startTime, driver2.stopTime);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if(driver.vehicleParkingMeterStatus.equals("ON")){
                        System.out.println("Parking meter for vehicle "+driver2.vehiclePlate+" still running");
                    }

                    driverList2.getDrivers().add(driver);
                    driverList2.getDrivers().add(driver2);
                    driverList2.getDrivers().add(driver10);
                    driverList2.getDrivers().add(driver11);

                    //read array list and write to string
                    //String newVehiclesFromList2="";
                    //for(int i = 0; i<driverList2.getDrivers().size(); i++){
                    //    String oneNewVehicle = String.valueOf(driverList2.getDrivers().get(i));
                    //    newVehiclesFromList2 = newVehiclesFromList2 + oneNewVehicle;
                    //}
                    // append string with new entries to JSON
                    // update JSON
                    //BufferedWriter bw2 = null;
                    //try {
                    //    bw2 = new BufferedWriter(new FileWriter("result.json", true));
                    //    bw2.write(newVehiclesFromList2);
                    //    bw2.newLine();
                    //    bw2.flush();
                    //}catch (Exception e){
                    //    e.printStackTrace();
                    //}

                    // write file JSON
                    ObjectMapper mapper2 = new ObjectMapper();
                    try{
                        mapper2.writeValue(new FileWriter("result.json"),driverList2);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("CASEEEEEEEE2222:        "+driverList2);


            break;
                case 3:
                    System.out.println("This option is not implemented, that could be 'Manually Start Park Meter' Other objects will be created automatically");
                    break;
                case 4:
                    System.out.println("This option is not implemented, that could be 'Manually Stop Park Meter'");
                    break;
                case 5:
                    System.out.println("This option is not implemented, that could be 'Manually check how much is to pay'");
                    break;
                default:
                    System.out.println("The rest is implemented below");
            }


        System.out.println("LISTAAAAAAAAAAAA1   "+driverList1);
            System.out.println("LISTAAAAAAAAAAAA2   "+driverList2);

            return driverList2;
        }
}