package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;

public class CarPark {
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
            //To add driver please enter plate number and driver type a=Regular b=Disabled
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
//                System.out.println("Parking meter still running");
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


            if(driver2.vehicleParkingMeterStatus.equals("OFF")){
                try {
                    driver2.howMuchIsToPay(driver2.startTime, driver2.stopTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(driver.vehicleParkingMeterStatus.equals("ON")){
                System.out.println("Parking meter for vehicle "+driver2.vehiclePlate+" still running");
                //
                // lub tutaj przekierowania przerwa BREAK; CONTINUE return?
                // lub tak:
                //driver.howMuchIsToPay(driver.startTimerMethod(), driver.stopTimerMethod("0"));
                //
//                try {
//                    driver2.howMuchIsToPay(driver2.startTime, "0");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            // to musi byc w metodzie ktora jezeli walidacje przejdzie TO wywala ją TĄ metodą do tworzenia obiektów!
            // ????????????????? W konstruktorze tworzenie obiektów?????????????????


            DriverList driverList = new DriverList();
            driverList.getDrivers().add(driver);
            driverList.getDrivers().add(driver2);
            driverList.getDrivers().add(driver10);
            driverList.getDrivers().add(driver11);

            return driverList;
        }

}