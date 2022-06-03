package server.dao;

import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;

public class MessageDao extends DataDao<MessagesEntity,Integer>{
    private EntityManager em;
    public void create(MessagesEntity msg){
        persist(msg);
    }
    @Override
    public MessagesEntity findbyId(Integer integer) {
        return (MessagesEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1,integer).getSingleResult();
    }

}
