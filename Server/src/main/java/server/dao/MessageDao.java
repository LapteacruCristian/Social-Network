package server.dao;

import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Class MessageDao
 * Class used to execute the queries from MessagesEntity
 */
public class MessageDao extends DataDao<MessagesEntity, Integer> {
    private EntityManager em;

    public void create(MessagesEntity msg) {
        persist(msg);
    }

    @Override
    public MessagesEntity findbyId(Integer integer) {
        return (MessagesEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1, integer).getSingleResult();
    }

    public List<String> getMessages(PersonsEntity sender, PersonsEntity receiver) {
        return (List<String>) PersistenceUtil.getEntityManager().createNamedQuery("Person.getMessages").setParameter(3, sender).setParameter(4, receiver).getResultList();
    }

    public List<String> getUnseenMessages(PersonsEntity receiver) {
        return (List<String>) PersistenceUtil.getEntityManager().createNamedQuery("Person.getUnseenMessages").setParameter(4, receiver).getResultList();
    }

    public List<MessagesEntity> getMyMessages(PersonsEntity receiver) {
        return (List<MessagesEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Person.getMyMessages").setParameter(4, receiver).getResultList();
    }

}
