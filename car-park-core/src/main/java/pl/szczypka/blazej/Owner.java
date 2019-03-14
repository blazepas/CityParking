package pl.szczypka.blazej;

import org.jboss.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Owner extends Operator {
    protected static final Logger log = Logger.getLogger(Owner.class);

    public String checkTotalMoneyForGivenDay(String dateToCheck){
        BigDecimal totalMoney = new BigDecimal(0.0);
        int checkOnlyOneDay = 0;
        String moneyOut = "";

        //ObjectMapper to read JSON file
        try {
            setValue(getObjectMapperOperator().readValue(new File("/home/bsz/IdeaProjects/carpark_final4/carpark/result.json"), DriverList.class));
        } catch (IOException io){
            log.error(io);
        } catch (Exception e) {
            log.error(e);
        }
        try {
            int listSize = getValue().getDrivers().size();
            //Type date to check how much you earned per day
            if (checkOnlyOneDay < 1){
                System.out.println("Enter date in format \"dd-mm-yyyy\" to check how much money you earned per day: ");
                System.out.println(dateToCheck);
                //Compare all Drivers timestamps
                try {
                    for (int i = 0; i<listSize; i++) {
                        if(dateToCheck.equals(getValue().getDrivers().get(i).getTimestamp())) {
                            totalMoney = totalMoney.add(getValue().getDrivers().get(i).getPaymentForAllHours());
                        }
                    }
                } catch (IllegalArgumentException i){
                    log.error(i);
                } catch (Exception e) {
                    log.error(e);
                }

                String totalMoneyRoundNumber = String.format("%.2f", totalMoney);
                moneyOut = "Total money earned in day "+dateToCheck+" is: " +totalMoneyRoundNumber + " PLN";
                checkOnlyOneDay++;
            }
        } catch (IllegalArgumentException i){
            log.error(i);
        } catch (Exception e) {
            log.error(e);
        }
        return moneyOut;
    }
}
