package pl.szczypka.blazej.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;
import pl.szczypka.blazej.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.net.URI;


@Path("/car")
public class CarParkService {
    protected static final Logger log = Logger.getLogger(CarParkService.class);

    //show whole database
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public DriverList readJSON(){
        ObjectMapper objectMapperOperatorWeb = new ObjectMapper();
        DriverList valueWeb = null;
        //ObjectMapper to read JSON file
        try {
            valueWeb = objectMapperOperatorWeb.readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class);
        } catch (IOException io){
            log.error(io);
            io.getCause();
        } catch (Exception e) {
            log.error(e);
            e.getCause();
        }

        //trigger DAO to show whole DB -- this will be moved to separate method, it is only temporary
        //print DB in console -- only temporary
        DriverDaoImpl impl = new DriverDaoImpl();
        impl.showListDriverDB();


        return valueWeb;
    }

    @Context
    public UriInfo uriInfo;

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

    //display how much is to pay
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

        //trigger DAO to check counter status for plate in DB -- this will be moved to separate method, it is only temporary
        //print DB in console -- only temporary
        DriverDaoImpl impl = new DriverDaoImpl();
        impl.showStatusParkingMeterDB(plateToCheck);

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
    public String crDr(@FormParam("platenumber") String plateN, @FormParam("drivertyp") String typeDr, @FormParam("readytostart") String checkbox){
        Driver driWe = new Driver();
        String reply ="";
        try {
            if (checkbox.equals("startimmediately")) {
                driWe.createDriverWithStartTime(plateN, typeDr);
                reply = "Successful. Counter is running.";
            }
        }catch (NullPointerException n){
            log.error(n);
            n.getCause();
            System.out.println("Counter will not start automatically");
            driWe.createDriver(plateN, typeDr);
            reply = "Successful. Counter will not start automatically. Don't forget to start counter.";
        }
        return reply;
    }
}