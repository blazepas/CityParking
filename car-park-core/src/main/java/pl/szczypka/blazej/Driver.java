package pl.szczypka.blazej;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver{
    //all saved in List
    private String vehiclePlate;
    private String driverType;
    private String vehicleParkingMeterStatus ="";
    private String startTime="0";
    private String stopTime ="0";
    private long minutes;
    private String timestamp;
    private BigDecimal paymentForAllHours = new BigDecimal(0.0);
    private double countFromThirdHour;
    private String currency = "PLN";
    private double exchangeRateCurrency;


    private DriverList driverList = new DriverList();
    protected static final Logger log = Logger.getLogger(Driver.class);
    Operator operat = new Operator();

    //This constructor is mandatory for reading from JSON
    public Driver(){}

    //This constructor is helpful to create another object
    //Validate driverType
    public Driver(String id, String driverType){
            switch (driverType) {
                case "a":
                    this.driverType = "Regular";
                    break;
                case "b":
                    this.driverType = "Disabled";
                    break;
            }
        this.vehiclePlate=id;
        defaultTimerMethod();
    }

    public DriverList createDriver(String plateNum, String driverType){
        ObjectMapper mapper = new ObjectMapper();
        if(driverType.equals("a") || driverType.equals("b")) {
            //Read database and load to List
            try {
                driverList = mapper.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
            }catch (IOException io){
                log.error(io);
            } catch (Exception e) {
                log.error(e);
            }
            //Check if plate number is not in a List (not in database)
            for (int i = 0; i < driverList.getDrivers().size(); i++) {
                System.out.println(plateNum.toUpperCase());
                if ((driverList.getDrivers().get(i).vehiclePlate.toUpperCase()).equals(plateNum.toUpperCase())) {
                    System.out.println("This car exist in database.");
                    return driverList;
                }
            }
            //Create new driver
            System.out.println(">>Creating driver manually<<");
            driverCreate = new Driver(plateNum, driverType);
            driverList.getDrivers().add(driverCreate);
            try {
                mapper.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), driverList);
            } catch (IOException io){
                log.error(io);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                throw new IllegalArgumentException("driver type cannot be different than a-normal or b-disable");

            }catch (IllegalArgumentException i){
                log.error(i);
            }catch (Exception e){
                log.error(e);
            }
        }

        return driverList;
    }



    public DriverList createDriverWithStartTime(String plateNum, String driverType) {
        ObjectMapper mapper = new ObjectMapper();
        if(driverType.equals("a") || driverType.equals("b")) {
            try {
                driverList = mapper.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
            }catch (IOException io){
                log.error(io);
            } catch (Exception e) {
                log.error(e);
            }
            for (int i = 0; i < driverList.getDrivers().size(); i++) {
                if ((driverList.getDrivers().get(i).vehiclePlate.toUpperCase()).equals(plateNum.toUpperCase())) {
                    System.out.println("This car exist in database.");
                    return driverList;
                }
            }
            System.out.println(">>Creating driver manually with counter<<");
            driverCreate = new Driver(plateNum, driverType);
            driverCreate.startTimerMethod();
            driverList.getDrivers().add(driverCreate);
            try {
                mapper.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), driverList);
            }catch (IOException io){
                log.error(io);
            } catch (Exception e) {
                log.error(e);
            }
        }else{
        try {
            throw new IllegalArgumentException("Driver type cannot be different than a-normal or b-disable");
        }catch (IllegalArgumentException i ){
            log.error(i);
        }catch (Exception e) {
            log.error(e);
        } }
        return driverList;
    }

    private Driver driverCreate;

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
    public String startTimerMethod() {
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
    public String stopTimerMethod() throws IllegalArgumentException, Exception{
        Date nowStop = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            stopTime = simpleDateFormat.format(nowStop);
            parkingStatusMeter("stop");
            System.out.println(stopTime);
        return stopTime;
    }
    //driver type
    private static String driTyp = "";
    //switch counter
    public String changeParkingMeter(String plateNum) throws Exception {
        ObjectMapper objectMap = new ObjectMapper();
        operat.readJSON();
        String findVehiclePlate=plateNum;
        String meterOut ="";
        String reply = "";
        reply = "Pay your debt, and click Start to run new parking counter";
        for (int i = 0; i < operat.getValue().getDrivers().size(); i++) {
            if((operat.getValue().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())){
                meterOut = operat.getValue().getDrivers().get(i).vehicleParkingMeterStatus;
                //Turn it ON
                if(meterOut.equals("doesnt-take-off")){
                    String newTimeStamp = startTimerMethod();
                    operat.getValue().getDrivers().get(i).startTime = newTimeStamp;
                    operat.getValue().getDrivers().get(i).vehicleParkingMeterStatus = parkingStatusMeter("start");
                    try{
                        objectMap.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"),operat.getValue());
                    }catch (IOException io){
                        log.error(io);
                    }catch (Exception e) {
                        log.error(e);
                    }
                    reply = "Parking meter is running";
                }
                //Turn it Off
                if(meterOut.equals("ON")){
                    String newTimeStamp = stopTimerMethod();
                    operat.getValue().getDrivers().get(i).vehicleParkingMeterStatus = parkingStatusMeter("stop");
                    operat.getValue().getDrivers().get(i).stopTime = newTimeStamp;
                    driTyp = operat.getValue().getDrivers().get(i).driverType;
                    String str1 = operat.getValue().getDrivers().get(i).startTime;
                    String str2 = operat.getValue().getDrivers().get(i).stopTime;
                    operat.getValue().getDrivers().get(i).howMuchIsToPay(str2, str1);

                    try {
                        objectMap.writeValue(new FileWriter("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), operat.getValue());
                    }catch(IOException io){
                        log.error(io);
                    }catch (Exception e){
                        log.error(e);

                    }

                    reply = "Parking meter is OFF. " + "Debt to pay is "+ operat.getValue().getDrivers().get(i).paymentForAllHours;
                }
                if(meterOut.equals("OFF")){
                    System.out.println("You already stopped the counter");
                }
            }
        }
        return reply;
    }

    //display Dept
    public String debt(String findVehiclePlate){
        BigDecimal debt =new BigDecimal(0.0);
        for (int i = 0; i < operat.readJSON().getDrivers().size(); i++) {
            if ((operat.readJSON().getDrivers().get(i).vehiclePlate.toUpperCase()).equals(findVehiclePlate.toUpperCase())) {
                debt = BigDecimal.valueOf(operat.readJSON().getDrivers().get(i).paymentForAllHours.byteValueExact());
            }
        }
        return "Debt to pay is "+debt+" "+currency;
    }

    //assign status parking mater
    public String parkingStatusMeter(String param) {
        if(param.toLowerCase().equals("null")){
            vehicleParkingMeterStatus = "doesnt-take-off";
            System.out.println("Parking meter for driver " +" doesn't take off");
        } else if (param.toLowerCase().equals("start")) {
            vehicleParkingMeterStatus = "ON";
            System.out.println("Parking meter for driver " +" is ON");
        } else if (param.toLowerCase().equals("stop")) {
            vehicleParkingMeterStatus = "OFF";
            System.out.println("Parking meter for driver " +" is OFF");
        } else{
            System.out.println("Parameter in parking meter is incorrect");
        }
        return vehicleParkingMeterStatus;
    }

    //Show how much he/she has to pay
    public BigDecimal howMuchIsToPay(String stopTimeIn, String startTimeIn) throws Exception {
        double startFee = 1;
        double disabledStartFee = 0;
        double secondHourFee = 2;
        double disabledSecondHourFee = 2;
        double multiplyFee = 1.5;
        double multiplyDisabledFee = 1.2;
        double hourBefore = 2;
        double disabledHourBefore = 2;

        //keep date in one format
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        try {
            if (stopTime == "0" || startTime == "0") {
                throw new IllegalArgumentException("You didn't start time, it equals 0");
            }
            if (stopTime == "" || startTime == "") {
                throw new IllegalArgumentException("Time value is empty");
            }

        else {
            Date date1 = simpleDateFormat1.parse(stopTimeIn);
            Date date2 = simpleDateFormat1.parse(startTimeIn);
            long difference = date1.getTime() - date2.getTime();
            //count seconds to minutes
            //convert to minutes
            minutes = (difference/(1000*60)%60);
            //Calculating Fee for Regular driver
            if(driTyp.equals("Regular")){
                if(minutes<=60){
                    System.out.println("Payment for one hour is "+startFee);
                    paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(startFee));
                }
                if(minutes>60 && minutes<=120){
                    System.out.println("Payment for two hours is "+(startFee+secondHourFee));
                    paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(startFee)).add(BigDecimal.valueOf(secondHourFee));
                }
                if(minutes>120){
                    countFromThirdHour =(minutes-120)/60;
                    paymentForAllHours = BigDecimal.valueOf(3.0);
                    for(int x = 0; countFromThirdHour >x; x++){
                        hourBefore = hourBefore *multiplyFee;
                        paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(hourBefore));
                    }
                    System.out.println("Total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                }
            } else if(driTyp.equals("Disabled")) {
                if(minutes<=60){
                    System.out.println("Disabled payment for one hour = "+disabledStartFee);
                    paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(disabledStartFee));
                }
                if(minutes>60 && minutes<=120){
                    System.out.println("Disabled payment for two hours = "+(disabledStartFee + disabledSecondHourFee));
                    paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(disabledStartFee)).add(BigDecimal.valueOf(disabledSecondHourFee));
                }
                if(minutes>120){
                    countFromThirdHour =(minutes-120)/60;
                    paymentForAllHours = BigDecimal.valueOf(disabledStartFee + disabledSecondHourFee);
                    for(int x = 0; countFromThirdHour >x; x++){
                        disabledHourBefore = disabledHourBefore *multiplyDisabledFee;
                        paymentForAllHours = paymentForAllHours.add(BigDecimal.valueOf(disabledHourBefore));
                    }
                    System.out.println("Disabled total payment for "+ minutes/60 + " hours is " + paymentForAllHours+" " + currency);
                }
            }
        }} catch(IllegalArgumentException i) {
            log.error(i);
            } catch (Exception e){
            log.error(e);
        }

        System.out.println("Payment for all hours "+paymentForAllHours);

        return paymentForAllHours;
    }


    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getVehicleParkingMeterStatus() {
        return vehicleParkingMeterStatus;
    }

    public void setVehicleParkingMeterStatus(String vehicleParkingMeterStatus) {
        this.vehicleParkingMeterStatus = vehicleParkingMeterStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPaymentForAllHours() {
        return paymentForAllHours;
    }

    public void setPaymentForAllHours(BigDecimal paymentForAllHours) {
        this.paymentForAllHours = paymentForAllHours;
    }

    public double getCountFromThirdHour() {
        return countFromThirdHour;
    }

    public void setCountFromThirdHour(double countFromThirdHour) {
        this.countFromThirdHour = countFromThirdHour;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRateCurrency() {
        return exchangeRateCurrency;
    }

    public void setExchangeRateCurrency(double exchangeRateCurrency) {
        this.exchangeRateCurrency = exchangeRateCurrency;
    }

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