package server.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManagerFactory emf=null;
    private static EntityManager em=null;
    static public EntityManager getEntityManager() {
        if (emf == null) {
            System.out.println("T1");
            emf = Persistence.createEntityManagerFactory("SocialNetwork");
        }
        if(em==null){
            System.out.println("T2");

            em=emf.createEntityManager();
        }
        System.out.println("T3");

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
