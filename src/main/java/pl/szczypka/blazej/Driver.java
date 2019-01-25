package pl.szczypka.blazej;
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


//    public String validateDriverType(String driverTypeIn){
//
//        if(driverTypeIn.equals("Regular")){
//            driverType = driverTypeIn;
//        } else  if (driverTypeIn.equals("Regular")){
//            driverType = driverTypeIn;
//        } else {
//            System.out.println("!!!!!!!!!!!");
//        }
//        return driverType;
//    }


    //This constructor is mandatory for reading from JSON
    public Driver(){}

    //This constructor is helpfull to create another object
//    public Driver(String id, String driverType){
    //Validate driverType
    public Driver(String id, String driverType){
//        this(id, driverType, "Musta");
//    String driverType="";
        switch(driverType){
            case "a": this.driverType="Regular";
                    break;
            case "b": this.driverType="Disabled";
        }


//        if(driverType.equals("Regular")){
//            this.driverType = driverType;
//        } else  if (driverType.equals("Disabled")){
//            this.driverType = driverType;
//        } else {
//            System.out.println("!!!!!Incorect Driver Type!!!!!!");
//            try {
//                throw new IllegalAccessException("Driver type can be only Regular or Disabled");
//            }catch (Exception e){
////                e.printStackTrace();
//            }
//        }

        this.vehiclePlate =id;
//        this.driverType=driverType;
        defaultTimerMethod();
    }

//    Driver driver;
//
//switch(charopt){
//        case 1:
//            //other code
//            myPet = new Dog(dogName);
//
//    public DostawcaKotow(String imie) {
//        this.imie = imie;
//    }
    // wylosuj różne zwierzeta - koty albo tygrysy
    // niech imionami będą kolejne liczby

//          for (int i = 0; i < tab.length; i++) {
//        tab[i] = stworzKota();
//        tab[i].name += "_" + i + " od " + imie;
//    }
//
//        return tab;

//public DriverList askList(){
public void askList(){
//        DriverList aaa = new DriverList();
        System.out.println("Enter your vehicle plate number: ");
        Scanner takeOptions = new Scanner(System.in);
        String takePlate = takeOptions.nextLine();
        System.out.println("Enter your driver type a-Regular b-Disabled: ");
        takeOptions = new Scanner(System.in);
        String takeType = takeOptions.nextLine();

        System.out.println(">>Creating driver<<");
        int i=0;
        if(i<1){
            d=new Driver(takePlate, takeType);
            i++;
        }
        CarPark carPark = new CarPark();
//        aaa.getDrivers().add(d);
//    System.out.println("DRIIIIIIIIII:     "+aaa);
//        carPark.getDrivers().getDrivers().add(d);
//    carPark.getDrivers().getDrivers().add(d);
//    carPark2.driverList1.getDrivers().add(d);
    carPark.driverList1.getDrivers().add(d);
    System.out.println("W aadDriveer:::   "+carPark.getDrivers().getDrivers());
//    System.out.println("W aadDriveer:::   "+carPark2.getDrivers().getDrivers());
//        return aaa;
}


//                driverList.getDrivers().add(driverAcc.addDriverObj(takePlate,takeType));
//        System.out.println("LLLLLL<>>>>>>>>>>>  :   "+driverList);

//    public void askDriver(){
//        System.out.println("Enter your vehicle plate number: ");
//        Scanner takeOptions = new Scanner(System.in);
//        String takePlate = takeOptions.nextLine();
//        System.out.println("Enter your driver type a-Regular b-Disabled: ");
//        takeOptions = new Scanner(System.in);
//        String takeType = takeOptions.nextLine();
//        addDriverObj(takePlate,takeType);
////                driverList.getDrivers().add(driverAcc.addDriverObj(takePlate,takeType));
////        System.out.println("LLLLLL<>>>>>>>>>>>  :   "+driverList);
//    }

    Driver d;
    //Method addDriverObj() is begining to change logic where is possible to add one by one user to database from console
    public Driver addDriverObj(String plateIn, String driverTypeIn) {

        System.out.println("Creating driver");

        int i=0;
        if(i<1){
            d=new Driver(plateIn, driverTypeIn);
//            System.out.println(d);
//            d.vehiclePlate=plateIn;
//            d.driverType=driverTypeIn;
            i++;
        }
//        static Driver dd = d;
        addDriverToListManually(d);
        return d;
    }

    public void addDriverToListManually(Driver dri){
//        System.out.println("Driver adding to list");
//        DriverList driverList = new DriverList();
        CarPark carPark = new CarPark();
//        driverList.getDrivers().add(dri);
//        System.out.println(dri);
//        System.out.println(driverList);
//        carPark
//        driverList.getDrivers().add(d);
        carPark.getDrivers().getDrivers().add(dri);
        System.out.println("W aadDriveer:::   "+carPark.getDrivers().getDrivers());
        //to ponizej to inna lista!!!! do edycji ten rejon!!!
//        return driverList;
    }





    //This method is used during driver object initualisation. Invoking from constructor.
    public String defaultTimerMethod(){
        Date now = new Date();
        SimpleDateFormat simpleOnlyDateForm = new SimpleDateFormat("dd-MM-yyyy");
        timestamp = simpleOnlyDateForm.format(now);
        System.out.println("Default object initialise::::::::::::::::: "+simpleOnlyDateForm.format(now));

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
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
        System.out.println("Dataaaaaaaaaaaa: "+simpleOnlyDateForm.format(now));
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

            //utworzenie instancji do formatowania daty
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
            //wczytanie zmiennej typu string do instancji formatowania daty i całość przypisanie do instancji daty.

            if(stopTime == "0" || startTime == "0"){
                //
                //
                System.out.println("-=Display this=-");
            }else {
                Date date1 = simpleDateFormat1.parse(stopTime);
                Date date2 = simpleDateFormat1.parse(startTime);

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