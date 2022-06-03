package server;


import org.eclipse.persistence.jpa.PersistenceProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonUtil {
    private static EntityManagerFactory emf;
    private static SingletonUtil instance;

    static public EntityManagerFactory entityManager() {
        if (instance == null) {
            instance = new SingletonUtil();
            instance.emf = Persistence.createEntityManagerFactory("SocialNetwork");
        }
        return instance.emf;

    }
}
