package pl.szczypka.blazej;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver{
    public String vehiclePlate;
    public String driverType;
    public String vehicleParkingMeterStatus ="";
    public String startTime="0";
    public String stopTime ="0";
    public long minutes;
    public String timestamp;
    public double paymentForAllHours;
    public double countedHours;
    public String currency = "PLN";
    public double exchangeRateCurrency;


    //This constructor is mandatory for reading from JSON
    public Driver(){}

    //This constructor is helpful to create another object
    //Validate driverType
    public Driver(String id, String driverType){
        switch(driverType){
            case "a": this.driverType="Regular";
                    break;
            case "b": this.driverType="Disabled";
        }


        this.vehiclePlate =id;
        defaultTimerMethod();
    }


public DriverList askList(){
        DriverList supportList = new DriverList();
        System.out.println("Enter your vehicle plate number: ");
        Scanner takeOptions = new Scanner(System.in);
        String takePlate = takeOptions.nextLine();
        System.out.println("Enter your driver type a-Regular b-Disabled: ");
        takeOptions = new Scanner(System.in);
        String takeType = takeOptions.nextLine();

        System.out.println(">>Creating driver manually<<");
        int i=0;
        if(i<1){
            driverCreate =new Driver(takePlate, takeType);
            i++;
        }
        CarPark carPark = new CarPark();
        carPark.driverList1.getDrivers().add(driverCreate);
        supportList=carPark.driverList1;
        carPark.driverList1.getDrivers().add(driverCreate);

        return supportList;
}

    Driver driverCreate;
    //Method addDriverObj() logic where is possible to add one by one user to database from console
    public Driver addDriverObj(String plateIn, String driverTypeIn) {

        System.out.println("Creating driver");

        int i=0;
        if(i<1){
            driverCreate =new Driver(plateIn, driverTypeIn);
            i++;
        }
        addDriverToListManually(driverCreate);
        return driverCreate;
    }

    public void addDriverToListManually(Driver dri){
        CarPark carPark = new CarPark();
        try {
            carPark.getDrivers().getDrivers().add(dri);

//        System.out.println("W aadDriveer:::   "+carPark.getDrivers().getDrivers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    //This method is used during driver object initualisation. Invoking from constructor.
    public String defaultTimerMethod(){
        Date now = new Date();
        SimpleDateFormat simpleOnlyDateForm = new SimpleDateFormat("dd-MM-yyyy");
        timestamp = simpleOnlyDateForm.format(now);
        System.out.println("Default object initialised::::::::::::::::: "+simpleOnlyDateForm.format(now));
        String startTime = "0";
        System.out.println(startTime);
        parkingStatusMeter("null");
        return vehicleParkingMeterStatus;
    }

    //Start parking meter
    public String startTimerMethod(){
        Date now = new Date();
        SimpleDateFormat simpleOnlyDateForm = new SimpleDateFormat("dd-MM-yyyy");
        timestamp = simpleOnlyDateForm.format(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        startTime = simpleDateFormat.format(now);

        //Status park meter is changed to ON
        System.out.println(startTime);
        parkingStatusMeter("StArT");
        return startTime;

    }

    //Stop parking meter
    public String stopTimerMethod(){
        Date nowStop = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        stopTime = simpleDateFormat.format(nowStop);
        parkingStatusMeter("stop");
        System.out.println(stopTime);
        return stopTime;
    }

    //assign status parking mater
    public String parkingStatusMeter(String param) {
        if(param.toLowerCase().equals("null")){
            vehicleParkingMeterStatus = "doesnt-take-off";
            System.out.println("Parking meter for driver "+ vehiclePlate +" doesn't take off");
        } else if (param.toLowerCase().equals("start")) {
            vehicleParkingMeterStatus = "ON";
            System.out.println("Parking meter for driver "+ vehiclePlate +" is ON");
        } else if (param.toLowerCase().equals("stop")) {
            vehicleParkingMeterStatus = "OFF";
            System.out.println("Parking meter for driver "+ vehiclePlate +" is OFF");
        } else{
            System.out.println("Parameter in parking meter is incorrect");
        }
        return vehicleParkingMeterStatus;
    }

    //Show how much he/she has to pay
    public double howMuchIsToPay(String stopTimeIn, String startTimeIn) throws Exception {
        double startFee = 1;
        double disabledStartFee = 0;
        double secondHourFee = 2;
        double disabledSecondHourFee = 2;
        double muliplyFee = 1.5;
        double muliplyDisapledFee = 1.2;
        double calcEachHour;
        double houreBefore = 2;
        double disabledHoureBefore = 2;

            //create instance to keep one format fo date
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
            //read date to instance format date and add this to instance date

            if(stopTime == "0" || startTime == "0"){
            }else {
                Date date1 = simpleDateFormat1.parse(stopTime);
                Date date2 = simpleDateFormat1.parse(startTime);

                long difference = date1.getTime() - date2.getTime();
                //count seconds to minutes
                System.out.println("Difference in time "+difference/1000/60%60);
                //convert to minutes
                minutes = (difference/1000/60%60)+180; //mocked > added extra 180 minutes to check how it work for 3 hours
                //it is possible to check this with threads for methods start and stop, to create difference in minutes

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
                } else if(driverType == "Disabled") {
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
                return paymentForAllHours;
            }

    //toString is mandatory for reading from JSON
    @Override
    public String toString() {
        return "Driver{" +
                "vehiclePlate=" + vehiclePlate +
                ", driverType='" + driverType + '\'' +
                ", vehicleParkingMeterStatus='" + vehicleParkingMeterStatus + '\'' +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", minutes=" + minutes +
                ", timestamp='" + timestamp + '\'' +
                ", paymentForAllHours=" + paymentForAllHours +
                ", countedHours=" + countedHours +
                ", currency='" + currency + '\'' +
                ", exchangeRateCurrency=" + exchangeRateCurrency +
                '}';
    }
}