package pl.szczypka.blazej.service;


import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
//import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
//import pl.szczypka.blazej.Driver;
import pl.szczypka.blazej.*;
//import pl.szczypka.blazej.Operator;
//import pl.szczypka.blazej.Operator.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
//import java.io.IOException;
//
//import static pl.szczypka.blazej.DriverListToWeb.wholeList;

//@ApplicationPath("/")
@Path("/car")
public class CarParkService {

//    @EJB
//    Operator oper;

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String test(){
        return "Hello";
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
                valueWeb = objectMapperOperatorWeb.readValue(new File("/home/bsz/IdeaProjects/carpark_final 4/carpark/result.json"), DriverList.class);
            } catch (Exception e){
                e.printStackTrace();
            }
            return valueWeb;
        }

    //operator return driver meter status
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
    public DriverList crDr(@FormParam("platenumber") String plateN, @FormParam("drivertyp") String typeDr){
        Driver driWe = new Driver();
        String welc = "hi! oy4";
        return driWe.createDriver(plateN, typeDr);
    }


}