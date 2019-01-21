package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) {

        DriverList driverList = getDrivers();
//        System.out.println(driverList);
        ObjectMapper mapper = new ObjectMapper();
        /**
         * Write object to JSON
         */

        try{
        mapper.writeValue(new FileWriter("result.json"),driverList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
        private static DriverList getDrivers(){
            Driver driver = new Driver(5, "Regular");
            Driver driver2 = new Driver(6, "Disabled");
            //start and stop timer both works in the same time!!
            driver.startTimerMethod();
//            driver.stopTimerMethod();

            driver2.startTimerMethod();
            driver2.stopTimerMethod();

            //Use method to check how much driver has to pay
            if(driver.parkingStat == false){
                try {
                    driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("Parking meter still running");
                //
                // lub tutaj przekierowania przerwa BREAK; CONTINUE return?
                // lub tak:
                //driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod("0"));
                //
                try {
//                    driver.howMuchIsToPay(driver.string2, "0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if(driver2.parkingStat == false){
                try {
                    driver2.howMuchIsToPay(driver2.string2, driver2.string1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("Parking meter still running");
                //
                // lub tutaj przekierowania przerwa BREAK; CONTINUE return?
                // lub tak:
                //driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod("0"));
                //
                try {
                    driver2.howMuchIsToPay(driver2.string2, "0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            DriverList driverList = new DriverList();
            driverList.getDrivers().add(driver);
            driverList.getDrivers().add(driver2);

            return driverList;
        }
}