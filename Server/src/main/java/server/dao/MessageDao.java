package server.dao;

import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;

public class MessageDao extends DataDao<MessagesEntity,Integer>{
    private EntityManager em;
    public void create(MessagesEntity msg){
        persist(msg);
    }

}
