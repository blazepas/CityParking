package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarPark {


    public static void main(String[] args) {
        CarPark carPark = new CarPark();
                carPark.getDrivers();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    DriverList driverList1 = new DriverList();
    DriverList driverList2 = new DriverList();
    DriverList driverList3 = new DriverList();
//        private static DriverList getDrivers(){
    public DriverList getDrivers(){

            Driver driverAcc = new Driver();
            System.out.println("WATCHHHHHH2    "+driverList1);
            System.out.println("Choose what would like to do. Enter 1-to add Driver or 2?");
            Scanner scOptions = new Scanner(System.in);
            String enteredOption = scOptions.nextLine();
            int number=0;
            number=Integer.parseInt(enteredOption);
            System.out.println("WATCHHHHHH1    "+driverList1);

            switch(number){
                case 1:
                      driverAcc.askList();
                    System.out.println("CASEEEEEEEEEE:     "+driverList1);
                    break;
//                    return driverList1;
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
//                Parking meter for vehicle "+driver2.vehiclePlate+" still running
//                String parr = driver.vehicleParkingMeterStatus;
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
                    System.out.println("CASEEEEEEEE2222:        "+driverList2);
//                    return driverList2;
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
//                case 6:
//                    System.out.println(driverList1);
//                    return driverList1;
                default:
                    System.out.println("The rest is implemented below");
            }



            System.out.println("LISTAAAAAAAAAAAA1   "+driverList1);
            System.out.println("LISTAAAAAAAAAAAA2   "+driverList2);


//            DriverList driverListAcc = new DriverList();
//            driverListAcc.newList.containsAll((Collection<?>) driverList1);

//            List<String> newList = Stream.of(driverList1, driverList2)
//                    .flatMap(x -> {
//                        return x.stream();
//                    })
//                    .flatMap(List::stream);
//                    .collect(Collectors.toList());
        driverList3=driverList2;
        System.out.println("Driber3            :"+driverList3);
            return driverList3;
        }


}