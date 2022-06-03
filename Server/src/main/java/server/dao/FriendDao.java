package server.dao;

import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;


public class FriendDao extends DataDao<FriendsEntity,Integer>{
    private EntityManager em;
    public void create(FriendsEntity friends){
        persist(friends);
    }
    @Override
    public FriendsEntity findbyId(Integer integer) {
        return (FriendsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1,integer).getSingleResult();
    }
}
