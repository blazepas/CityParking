package pl.szczypka.blazej;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityManager");

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        FactoryManager.emf = emf;
    }
}
