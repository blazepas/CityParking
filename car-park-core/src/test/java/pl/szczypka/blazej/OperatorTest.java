package pl.szczypka.blazej;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
public class OperatorTest {

    @Test
    //Check if there are no incorrect dates in database

    public void checkTotalMoneyForGivenDay() {
        ObjectMapper objMap = new ObjectMapper();
        DriverList valFromList = null;
        try {
            valFromList = objMap.readValue(new File("result.json"), DriverList.class);
            List<String> checklist = new LinkedList<>();

            //Get one date from database and check pattern examples
            String enteredDate = valFromList.getDrivers().get(0).getTimestamp();
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

            //Add incorrect patterns to checklist
            checklist.add(dateSlashes);
            checklist.add(dateDots);
            checklist.add(dateSpaces);
            checklist.add(dateOneNumber);
            checklist.add("January 2019");
            checklist.add("22 January 2019");
            checklist.add("22 2019");

            //Check if bad patterns are not matching with right one dd-MM-yyy in database
            for (int i = 0; i<valFromList.getDrivers().size(); i++){
                for(int j = 0; j<checklist.size(); j++) {
                    assertNotEquals(valFromList.getDrivers().get(i).getTimestamp(), (checklist.get(j)));
                    System.out.println("Check with wrong pattern:    "+checklist.get(j)+"    OK it does not exists in database");
                }
            }
            }catch (Exception e){
            e.printStackTrace();
        }
    }
}