package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
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
    public double countFromThirdHour;
    public String currency = "PLN";
    public double exchangeRateCurrency;
    /////////////////// this is added to list
    public DriverList driverList1 = new DriverList();
//    public Operator operat = new Operator();

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


public DriverList createDriver(String plateNum, String driverType){
    ObjectMapper mapper = new ObjectMapper();
        DriverList newDriver = new DriverList();

        System.out.println(">>Creating driver manually<<");
        int i=0;
        if(i<1){
            driverCreate =new Driver(plateNum, driverType);
            i++;
        }
        driverList1.getDrivers().add(driverCreate);
        newDriver=driverList1;

    System.out.println(newDriver);
    try{
        mapper.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"),driverList1);
    }catch (Exception e){
        e.printStackTrace();
    }

        return driverList1;
}



    public DriverList createDriverWithStartTime(String plateNum, String driverType){
        ObjectMapper mapper = new ObjectMapper();
        DriverList newDriver = new DriverList();

        System.out.println(">>Creating driver manually<<");
        int i=0;
        if(i<1){
            driverCreate =new Driver(plateNum, driverType);
            i++;
        }

        driverCreate.startTimerMethod();
        driverList1.getDrivers().add(driverCreate);
        newDriver=driverList1;

        System.out.println(newDriver);
        try{
            mapper.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"),driverList1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return driverList1;
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



    //This method is used during driver object initialisation. Invoking from constructor.
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

    public static String driTyp = "a";
    public String due="";
    Operator operat = new Operator();
    //switch counter
    public String changeParkingMeter(String plateNum) throws Exception {
        ObjectMapper objectMap = new ObjectMapper();

        operat.readJSON();
        String findVehiclePlate=plateNum;
        String meterOut ="";
        String reply = "";
        reply = "Pay your debt, and click Start to run new parking counter";
//        if()
        for (int i = 0; i < operat.value.getDrivers().size(); i++) {
            if((operat.value.getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())){
                meterOut = operat.value.getDrivers().get(i).vehicleParkingMeterStatus;
                System.out.println(meterOut);

                //Turn it ON
                if(meterOut.equals("doesnt-take-off")){
                    String newTimeStamp = startTimerMethod();
                    operat.value.getDrivers().get(i).startTime = newTimeStamp;
                    operat.value.getDrivers().get(i).vehicleParkingMeterStatus = parkingStatusMeter("start");
                    try{
                        objectMap.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"),operat.value);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    reply = "Parking meter is running";
                }
                //Turn it Off
                if(meterOut.equals("ON")){
                    String newTimeStamp = stopTimerMethod();
                    operat.value.getDrivers().get(i).vehicleParkingMeterStatus = parkingStatusMeter("stop");
                    operat.value.getDrivers().get(i).stopTime = newTimeStamp;
                    driTyp = operat.value.getDrivers().get(i).driverType;

                    String str1 = operat.value.getDrivers().get(i).startTime;
                    String str2 = operat.value.getDrivers().get(i).stopTime;
                    System.out.println(str2 +" "+str1);
                    System.out.println(operat.value.getDrivers().get(i).vehiclePlate);

                    operat.value.getDrivers().get(i).howMuchIsToPay(str2, str1);

                    try{
                        objectMap.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"),operat.value);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    reply = "Parking meter is OFF. " + "Debt to pay is "+ operat.value.getDrivers().get(i).paymentForAllHours;
                    due = String.valueOf(operat.value.getDrivers().get(i).paymentForAllHours);

                }
                if(meterOut.equals("OFF")){
                    System.out.println("You already stopped the couner");
                }
            }else{
                System.out.println("not included");
            }
        }
        return reply;
    }

    //display Dept
    public String debt(String plateNum){
        Scanner scPlate = new Scanner(plateNum);
        String findVehiclePlate=scPlate.nextLine();
        double debt=0.0;
        for (int i = 0; i < operat.readJSON().getDrivers().size(); i++) {
            if ((operat.readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())) {
//                System.out.println("Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus);
                debt = operat.readJSON().getDrivers().get(i).paymentForAllHours;
//                meterOut = "Status for car "+findVehiclePlate.toUpperCase()+" is: "+readJSON().getDrivers().get(i).vehicleParkingMeterStatus;

            }
        }

        return "Debt to pay is "+debt+" "+currency;

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
//        operat.readJSON();

        //create instance to keep one format fo date
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        //read date to instance format date and add this to instance date

        if(stopTime == "0" || startTime == "0"){
        }else {
            Date date1 = simpleDateFormat1.parse(stopTimeIn);
            Date date2 = simpleDateFormat1.parse(startTimeIn);

            System.out.println(date2);
            System.out.println(stopTimeIn);

            long difference = date1.getTime() - date2.getTime();
//            long difference = date1.getTime() - date2.getTime();
            System.out.println(difference);

            //count seconds to minutes
            System.out.println("Difference in time "+difference/1000/60%60);
            //convert to minutes
//            minutes = (difference/1000/60%60)+180; //mock > added extra 180 minutes to check how it work for 3 hours
            minutes = (difference/(1000*60)%60)+180; //mock > added extra 180 minutes to check how it work for 3 hours
            //it is possible to check this with threads for methods start and stop, to create difference in minutes
            System.out.println(minutes);
            //Calculating Fee for Regular driver
//            if(driverType == "Regular"){

            if(driTyp.equals("Regular")){
                if(minutes<=60){
                    System.out.println("Payment for one hour is "+startFee);
                }
                if(minutes>60 && minutes<=120){

                    System.out.println("Payment for two hours is "+(startFee+secondHourFee));
                }
                if(minutes>120){
                    countFromThirdHour =(minutes-120)/60;
                    paymentForAllHours = 3;
                    for(int x = 0; countFromThirdHour >x; x++){
                        houreBefore = houreBefore *muliplyFee;
                        paymentForAllHours = paymentForAllHours + houreBefore;
                    }
                    System.out.println("Total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                }
                //Calculate fee for the car park
                //Fee for Disability
//            } else if(driverType == "Disabled") {
            } else if(driTyp == "Disabled") {
                if(minutes<=60){
                    System.out.println("Disabled payment for one hour = "+disabledStartFee);
                }
                if(minutes>60 && minutes<=120){
                    System.out.println("Disabled payment for two hours = "+(disabledStartFee + disabledSecondHourFee));
                }
                if(minutes>120){
                    countFromThirdHour =(minutes-120)/60;
                    paymentForAllHours = disabledStartFee + disabledSecondHourFee;
                    for(int x = 0; countFromThirdHour >x; x++){
                        disabledHoureBefore = disabledHoureBefore *muliplyDisapledFee;
                        paymentForAllHours = paymentForAllHours + disabledHoureBefore;
                    }
                    System.out.println("Disabled total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                }
            }
        }
        System.out.println("Payment for all hours "+paymentForAllHours);
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
                ", countFromThirdHour=" + countFromThirdHour +
                ", currency='" + currency + '\'' +
                ", exchangeRateCurrency=" + exchangeRateCurrency +
                '}';
    }
}