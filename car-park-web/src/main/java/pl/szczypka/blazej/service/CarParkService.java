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
import javax.ws.rs.core.MediaType;

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
        return "Hello My World";
    }


    @GET
    @Path("/q")
    @Produces(MediaType.APPLICATION_JSON)
    public DriverList readJSONN(){
        return DriverListToWeb.wholeList();
    }



    @GET
    @Path("/l")
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

    //mocked operator return carpark meter status
    @GET
    @Path("/operator")
    @Produces("text/plain")
    public String driverParkingMeterWeb(){
        Operator operWeb = new Operator();
        String checkPlateStat =  operWeb.checkIfDriverTurnOnParkingMeter("SBIG156");
        return checkPlateStat;
    }

    //owner return information about earned m oney per mocked day
    @GET
    @Path("/owner")
    @Produces("text/plain")
    public String moneyForGivenDaywWeb(){
        Owner ownWeb = new Owner();
        String earnedMoney = ownWeb.checkTotalMoneyForGivenDay("28-01-2019");
        return earnedMoney;
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public DriverList addDriverWeb(){
        Driver driverWeb = new Driver();
        DriverList addDriver = driverWeb.createDriver("SB43372", "a");
        System.out.println(addDriver);
        return addDriver;
    }

}
