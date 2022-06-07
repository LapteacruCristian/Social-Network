package server.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class PersistenceUtil
 * Class used to connection with database
 */
public class PersistenceUtil {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    static public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SocialNetwork");
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEntityManager() {
        if (em != null) {
            em.close();
            em = null;
        }
    }

    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }
}
