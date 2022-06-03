package server.dao;

import server.entities.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

import static server.SingletonUtil.entityManager;

public abstract class DataDao
        <T extends AbstractEntity, ID extends Serializable> {
    private EntityManager em; //create it somehow

    public abstract T findbyId(ID id);
    public void persist(T entity) {
        javax.persistence.EntityManager em = entityManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }



}
