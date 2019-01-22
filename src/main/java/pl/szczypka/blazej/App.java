package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) {
        DriverList driverList = getDrivers();
        System.out.println(driverList);
        ObjectMapper mapper = new ObjectMapper();
        /**
         * Below function write object to JSON file (mocked database)
         */
        try{
        mapper.writeValue(new FileWriter("result.json"),driverList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

        private static DriverList getDrivers(){
            Driver driver = new Driver("SBIG156", "Regular");
            Driver driver2 = new Driver("WWA2131", "Disabled");
            //start and stop timer both works in the same time!!
            driver.startTimerMethod();
            //driver.stopTimerMethod();
//            driver.parkingStat=driver.parkingStatusMeter(driver.statusFromStart);
            System.out.println("meeeeeterrrr :"+driver.parkingStatusMeter(driver.statusFromStart));

            driver2.startTimerMethod();
            driver2.stopTimerMethod();
            driver2.parkingStatusMeter(driver2.statusFromStop);
//            driver2.parkingStat=driver2.parkingStatusMeter(driver2.statusFromStop);


            //Use method to check how much driver has to pay
            if(driver.parkingStat == false){
                try {
                    driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                boolean parr = driver.parkingStat;
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