package server.dao;

import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;


public class FriendDao extends DataDao<FriendsEntity,Integer>{
    private EntityManager em;
    public void create(FriendsEntity friends){
        persist(friends);
    }
    public FriendsEntity findbyId(Integer integer) {
        return (FriendsEntity) em.createNamedQuery("Person.findById").setParameter(1,integer);
    }
}
