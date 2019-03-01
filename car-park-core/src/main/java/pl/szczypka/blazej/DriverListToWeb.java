package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
//import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
//import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import java.io.File;


public class DriverListToWeb {
//    public static ObjectMapper wholeList(){
    public static DriverList wholeList(){
        ObjectMapper objectMapperOpe = new ObjectMapper();
        com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider jacksonProvider = new com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider();
//        DriverList driverListWeb = new DriverList();
        DriverList valueWeb = null;
//        ObjectMapper valueWeb = null;

        try {
            valueWeb = objectMapperOpe.readValue(new File("/home/bsz/IdeaProjects/carpark_final 4/carpark/result.json"), DriverList.class);
//            valueWeb = jacksonProvider.locateMapper(DriverList.class, PageAttributes.MediaType.APPLICATION_JSON_TYPE);
//            valueWeb = jacksonProvider.locateMapper(DriverList.class, MediaType.APPLICATION_JSON_TYPE);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(valueWeb);
        return valueWeb;

    }
}
