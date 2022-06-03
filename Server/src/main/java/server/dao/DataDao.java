package server.dao;

import server.entities.AbstractEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class DataDao
        <T extends AbstractEntity, ID extends Serializable> {
    private EntityManager em=PersistenceUtil.getEntityManager(); //create it somehow

    //public abstract T findbyId(ID id);
    public void persist(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }



}
