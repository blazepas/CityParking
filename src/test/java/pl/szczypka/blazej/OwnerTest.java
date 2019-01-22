package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pl.szczypka.blazej.DriverList;
import pl.szczypka.blazej.Owner;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class OwnerTest {


    @Test
    public void checkTotalMoneyForGivenDay() {
        ObjectMapper objMap = new ObjectMapper();
        DriverList valFromList = null;

        try {
            valFromList = objMap.readValue(new File("result.json"), DriverList.class);
            List<String> li = new LinkedList<>();

            //Get one date from database and check pattern examples
            String enteredDate = valFromList.getDrivers().get(0).timestamp;
            System.out.println("Example date from database: "+enteredDate);

            //match day, month and year to separate variables
            String[] parse = enteredDate.split("-");
            int x=Integer.parseInt(parse[0]);
            int y=Integer.parseInt(parse[1]);
            int z=Integer.parseInt(parse[2]);
            String dateSlashes = (x+"/"+y+"/"+z);
            String dateDots = (x+"."+y+"."+z);
            String dateSpaces = (x+" "+y+" "+z);
            String dateOneNumber = (x+""+y+""+z);

            //Add incorrect patterns to list
            li.add(dateSlashes);
            li.add(dateDots);
            li.add(dateSpaces);
            li.add(dateOneNumber);
            li.add("January 2019");
            li.add("22 January 2019");
            li.add("22 2019");

            //Check if bad patterns are not matching with right one dd-MM-yyy in database
            for (int i = 0; i<valFromList.getDrivers().size(); i++){
                for(int j = 0; j<li.size(); j++) {
                    assertNotEquals(valFromList.getDrivers().get(i).timestamp, (li.get(j)));
                    System.out.println("This date pattern is not valid: "+li.get(j));
                }

            }


            }catch (Exception e){
            e.printStackTrace();
        }

    }
}
