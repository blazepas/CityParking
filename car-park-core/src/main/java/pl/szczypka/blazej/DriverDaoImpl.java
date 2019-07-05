package pl.szczypka.blazej;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;


public class DriverDaoImpl implements DriverDao {

    FactoryManager fm = new FactoryManager();

    public EntityManager getEntityMenager() {
        return fm.getEmf().createEntityManager();
    }

    @PersistenceContext
    EntityManager em = getEntityMenager();


    //Hibernate create/add new row in DB
    @Override
    public void createDriverDB(Driver driver) {
        em.getTransaction().begin();
        em.persist(driver);
        em.getTransaction().commit();
    }


    //Hibernate show all DB
    @Override
    public List<Driver> showListDriverDB() {
        List<Driver> driverListDB;
        TypedQuery<Driver> query = em.createQuery("FROM Driver", Driver.class);
        driverListDB = query.getResultList();
        return driverListDB;
    }

    //Hibernate GET one specify parking meter status directly from DB
    @Override
    public List<Driver> showStatusParkingMeterDB(String plateToCheck) {
        List<Driver> driverListDB;

        TypedQuery<Driver> query = em.createQuery("Select d from Driver d Where d.vehiclePlate = '" + plateToCheck + "'", Driver.class);
        driverListDB = query.getResultList();
        System.out.println("Message from the .... DB :))) :" + driverListDB);
        return driverListDB;
    }

    //find id if element exists in database
    public int getDriverIDFromDB(Driver driver){
        Driver driverId = showListDriverDB()
                .stream()
                .filter(o -> o.getVehiclePlate().equals(driver.getVehiclePlate())).findAny().orElse(null);
        return driverId.getId();
    }

//    public Driver getOneDriverFromDB(Driver driver){
//        Driver oneDriver = new Driver();
//        int id = getDriverIDFromDB(driver);
//        oneDriver = em.getReference(Driver.class, id);
//        return oneDriver;
//    }

    public void deleteDriverDB(Driver driver){
        Driver deleteDriver;
        int id = getDriverIDFromDB(driver);
        deleteDriver = em.find(Driver.class, id);
        em.remove(deleteDriver);
    }

    @Override
    public void updateDriverDB(Driver oneDriverUpdate, String parkingStatusDB, String stopTimeDB, long minutesDB, BigDecimal paymentAllHoursDB, double countFromThirdHourDB ) {
        int id = getDriverIDFromDB(oneDriverUpdate);
        Driver comparedDriverDB;
        comparedDriverDB = em.find(Driver.class, id);

        em.getTransaction().begin();
        comparedDriverDB.setVehicleParkingMeterStatus(parkingStatusDB);
        comparedDriverDB.setStopTime(stopTimeDB);
        comparedDriverDB.setMinutes(minutesDB);
        comparedDriverDB.setPaymentForAllHours(paymentAllHoursDB);
        comparedDriverDB.setCountFromThirdHour(countFromThirdHourDB);

        em.getTransaction().commit();
    }

//    @Override
//    public List<Driver> updateViaSQL(String plateToCheck){
//        EntityManager em = getEntityMenager();
//        List<Driver> driverListDB = new LinkedList<>();
//
//        String sql = "Update Driver as d SET  d.vehicleParkingMeterStatus =?2 Where d.vehiclePlate = ?1";
//        Query query = em.createQuery(sql)
//                .setParameter(1, plateToCheck)
//                .setParameter(2, "OFF");
//        driverListDB = query.getResultList();
//
//        return driverListDB;
//    }
}