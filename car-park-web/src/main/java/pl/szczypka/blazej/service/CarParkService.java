package pl.szczypka.blazej.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.szczypka.blazej.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;

//import java.net.*;


@Path("/car")
public class CarParkService {

//    @EJB
//    Operator oper;

    @POST
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        String msgWelcome = "Hello";
        return msgWelcome;
    }


    @GET
    @Path("/q")
    @Produces(MediaType.APPLICATION_JSON)
    public DriverList readJSONN(){
        return DriverListToWeb.wholeList();
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
        public DriverList readJSON(){
        ObjectMapper objectMapperOperatorWeb = new ObjectMapper();
        DriverList valueWeb = null;
            //ObjectMapper to read JSON file
            try {
                valueWeb = objectMapperOperatorWeb.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
            } catch (Exception e){
                e.printStackTrace();
            }
            return valueWeb;
        }


    @Context
    public UriInfo uriInfo;
    public Response response;

    @POST
    @Path("/redirect")
    public Response myMethod() {
        URI uri = uriInfo.getBaseUriBuilder().path("/car/hello").build();
        return Response.temporaryRedirect(uri).build();
    }


    //switch counter for driver
    @POST
    @Path("/switchcounter")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String startCounter(@FormParam("platee") String plateToChec) throws Exception {
        Driver driverWeb = new Driver();
        String checkPlateStat =  driverWeb.changeParkingMeter(plateToChec);
        return checkPlateStat;
    }

    @POST
    @Path("/debt")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String showDebt(@FormParam("platenum") String plateNumber){
        Driver driverWe = new Driver();
        String due = driverWe.debt(plateNumber);
        return due;
    }

    //operator check counter status
    @POST
    @Path("/operator")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String driverStatusWeb(@FormParam("enteredplate") String plateToCheck){
        Operator operWeb = new Operator();
        String checkPlateStat =  operWeb.checkIfDriverTurnOnParkingMeter(plateToCheck);
        return checkPlateStat;
    }

    //owner return information about earned money per entered day
    @POST
    @Path("/owner")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String moneyForDaywWeb(@FormParam("enteredate") String dateToCheck){
        Owner ownWeb = new Owner();
        String earnedMoney = ownWeb.checkTotalMoneyForGivenDay(dateToCheck);
        return earnedMoney;
    }


    //create new driver
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String crDr(@FormParam("platenumber") String plateN, @FormParam("drivertyp") String typeDr, @FormParam("readytostart") String checkbox) {
        DriverList driverList = new DriverList();
        Driver driWe = new Driver();
        String reply ="";
        try {
            if (checkbox.equals("startimmediately")) {
                driWe.createDriverWithStartTime(plateN, typeDr);
                reply = "Successful. Counter is running.";
            }
        }catch (NullPointerException n){
            System.out.println("Counter will not start automatically");
            driWe.createDriver(plateN, typeDr);
            reply = "Registered Successful. Before you leave parking lot Don't forget to start counter.";
        }
        return reply;
    }


    @POST
    @Path("/startime")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String startParkMeter(){
        Driver driwerSta = new Driver();
        return driwerSta.startTimerMethod();
    }
}