package pl.szczypka.blazej;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class DriverDaoImpl implements DriverDao{

    FactoryManager fm = new FactoryManager();

    public EntityManager getEntityMenager(){
        return fm.getEmf().createEntityManager();
    }

    //Hibernate create/add new row in DB
    @Override
    public void createDriverDB(Driver driver) {
        EntityManager em = getEntityMenager();
        em.getTransaction().begin();
        em.persist(driver);
        em.getTransaction().commit();
    }


    //Hibernate show all DB
    @Override
    public List<Driver> showListDriverDB() {
        EntityManager em = getEntityMenager();
        List<Driver> driverListDB = new LinkedList<>();
        TypedQuery<Driver> query = em.createQuery("FROM Driver", Driver.class);
        driverListDB = query.getResultList();
        System.out.println(driverListDB);
        return driverListDB;
    }

    //Hibernate GET one specify parking meter status directly from DB
    @Override
    public List<Driver> showStatusParkingMeterDB(String plateToCheck) {
        EntityManager em = getEntityMenager();
        List<Driver> driverListDB = new LinkedList<>();

        TypedQuery<Driver> query =  em.createQuery("Select d from Driver d Where d.vehiclePlate = '"+plateToCheck+"'", Driver.class);
        driverListDB = query.getResultList();
        System.out.println("Message from the .... DB :))) :"+driverListDB);
        return driverListDB;
    }

    //Hibernate update counter status in DB
    @Override
    public List<Driver> updateDriverDB(String plateToCheck) {
        EntityManager em = getEntityMenager();
        List<Driver> driverListDB = new LinkedList<>();

        TypedQuery<Driver> query = em.createQuery("Update Driver SET vehicleParkingMeterStatus = 'OFF' Where vehiclePlate = '" + plateToCheck + "'", Driver.class);
        driverListDB = query.getResultList();
        System.out.println("Update ;-D .... " + driverListDB);
        return driverListDB;
    }
}
