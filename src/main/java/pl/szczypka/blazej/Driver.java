package pl.szczypka.blazej;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver{
    public String id;
    public String driverType;
//    public boolean parkingStat;
    public String vehicleParkingMeterStatus ="";
//    String statusFromStart = "";
//    String statusFromStop = "";
    public String string1="0";
    public String string2="0";
    public long minutes;
    public String timestamp;
    public double paymentForAllHours;
    public double countedHours;
    public String currency = "PLN";
    public double exchangeRateCurrency;

    //This constructor is mandatory for reading from JSON
    public Driver(){}

    //This constructor is helpfull to create another object
    public Driver(String id, String driverType){
        this.id=id;
        this.driverType=driverType;
        defaultTimerMethod();
    }

    //This method is created only to show invoking from constructor. It is possible to do it when variable is initialised
    public String defaultTimerMethod(){

        vehicleParkingMeterStatus = "ddddddddddddddddddddddd";


        Date now = new Date();
        SimpleDateFormat simpleOnlyDateForm = new SimpleDateFormat("dd-MM-yyyy");
        timestamp = simpleOnlyDateForm.format(now);
        System.out.println("Default::::::::::::::::: "+simpleOnlyDateForm.format(now));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String startTime = "0";

        System.out.println(startTime);
        string2 = startTime;
//        String statusFromStart = "null";
        parkingStatusMeter("null");

        return vehicleParkingMeterStatus;
    }

    //Start parking meter
    public String startTimerMethod(){
        Date now = new Date();
        SimpleDateFormat simpleOnlyDateForm = new SimpleDateFormat("dd-MM-yyyy");
        timestamp = simpleOnlyDateForm.format(now);
        System.out.println("Dataaaaaaaaaaaa: "+simpleOnlyDateForm.format(now));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String startTime = simpleDateFormat.format(now);

        //Status park meter is changed to ON

        System.out.println(startTime);
        string2 = startTime;
//        String statusFromStart = "start";
        parkingStatusMeter("StArT");
        return startTime;

    }

    //Stop parking meter
    public String stopTimerMethod(){
        Date nowStop = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String stopTime = simpleDateFormat.format(nowStop);

        //Status park meter is changed to OFF
//        statusFromStop = "stop";
        parkingStatusMeter("stop");
        System.out.println(stopTime);
        string1 = stopTime;
        return stopTime;
    }

    //assign status whether parking mater is ON or OFF
//    public boolean parkingStatusMeter(String param) {
    public String parkingStatusMeter(String param) {
        if(param.toLowerCase().equals("null")){
            vehicleParkingMeterStatus = "doesnt-take-off";
            System.out.println("Parking meter for driver "+id+" doesn't take off");
        } else if (param.toLowerCase().equals("start")) {
            vehicleParkingMeterStatus = "ON";
//            parkingStat = true;
            System.out.println("Parking meter for driver "+id+" is ON");
        } else if (param.toLowerCase().equals("stop")) {
            vehicleParkingMeterStatus = "OFF";
//            parkingStat = false;
            System.out.println("Parking meter for driver "+id+" is OFF");
        } else{
            System.out.println("Parameter in parking meter is incorrect");
        }

//        System.out.println("Status meter: "+ parkingStat);
//        vehicleParkingMeterStatus = String.valueOf(parkingStat);
//        return parkingStat;

        return vehicleParkingMeterStatus;
    }

    //Show how much he/she has to pay
    public double howMuchIsToPay(String stopTime, String startTime) throws Exception {
//        string1 = stopTime;
//        string2 = startTime;

        double startFee = 1;
        double disabledStartFee = 0;
        double secondHourFee = 2;
        double disabledSecondHourFee = 2;
        double muliplyFee = 1.5;
        double muliplyDisapledFee = 1.2;
        double calcEachHour;
        double houreBefore = 2;
        double disabledHoureBefore = 2;


            //utworzenie instancji do formatowania daty
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
            //wczytanie zmiennej typu string do instancji formatowania daty i całość przypisanie do instancji daty.

            if(string1 == "0" || string2 == "0"){
                //
                //
                System.out.println("HERE I AM !!!!!!!!!!!!!!!!!!!!!!!!!!");
            }else {
                Date date1 = simpleDateFormat1.parse(string1);
                Date date2 = simpleDateFormat1.parse(string2);

                long difference = date1.getTime() - date2.getTime();
                //count seconds to minutes
                System.out.println("Difference in time "+difference/1000/60%60);
                //Convert to minutes
                minutes = (difference/1000/60%60)+180; //mock > added extra 180 minutes to check how it work for 3 hours

                //Calculating Fee for Regular driver
                if(driverType == "Regular"){
                    if(minutes<=60){
                        System.out.println("Peyment for one hour is "+startFee);
                    }
                    if(minutes>60 && minutes<=120){
                        System.out.println("Peyment for two hours is "+(startFee+secondHourFee));
                    }
                    if(minutes>120){
                        countedHours=(minutes-120)/60;
                        paymentForAllHours = 3;
                        for(int x=0; countedHours>x;x++){
                            houreBefore = houreBefore *muliplyFee;
                            paymentForAllHours = paymentForAllHours + houreBefore;
                        }
                        System.out.println("Total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                    }
                    //Calculate fee for the car park
                    //Fee for Disability
                } else {
                    if(minutes<=60){
                        System.out.println("Disabled peyment for one hour = "+disabledStartFee);
                    }
                    if(minutes>60 && minutes<=120){
                        System.out.println("Disabled peyment for two hours = "+(disabledStartFee + disabledSecondHourFee));
                    }
                    if(minutes>120){
                        countedHours=(minutes-120)/60;
                        paymentForAllHours = disabledStartFee + disabledSecondHourFee;
                        for(int x=0; countedHours>x;x++){
                            disabledHoureBefore = disabledHoureBefore *muliplyDisapledFee;
                            paymentForAllHours = paymentForAllHours + disabledHoureBefore;
                        }
                        System.out.println("Disabled total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                    }
                }

            }
            //
            // If date2 == "0" skip caluculation in Driver howMuch
        //




                return paymentForAllHours;
            }


    //toString is mandatory for reading from JSON

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverType='" + driverType + '\'' +
                ", vehicleParkingMeterStatus='" + vehicleParkingMeterStatus + '\'' +
//                ", statusFromStart='" + statusFromStart + '\'' +
//                ", statusFromStop='" + statusFromStop + '\'' +
                ", string1='" + string1 + '\'' +
                ", string2='" + string2 + '\'' +
                ", minutes=" + minutes +
                ", timestamp='" + timestamp + '\'' +
                ", paymentForAllHours=" + paymentForAllHours +
                ", countedHours=" + countedHours +
                ", currency='" + currency + '\'' +
                ", exchangeRateCurrency=" + exchangeRateCurrency +
                '}';
    }


//    @Override
//    public String toString() {
//        return "Driver{" +
//                "id=" + id +
//                ", driverType='" + driverType + '\'' +
//                ", parkingStat=" + parkingStat +
//                ", statusFromStart='" + statusFromStart + '\'' +
//                ", statusFromStop='" + statusFromStop + '\'' +
//                ", string1='" + string1 + '\'' +
//                ", string2='" + string2 + '\'' +
//                ", minutes=" + minutes +
//                ", timestamp='" + timestamp + '\'' +
//                ", paymentForAllHours=" + paymentForAllHours +
//                ", countedHours=" + countedHours +
//                ", currency='" + currency + '\'' +
//                ", exchangeRateCurrency=" + exchangeRateCurrency +
//                '}';
//    }
}