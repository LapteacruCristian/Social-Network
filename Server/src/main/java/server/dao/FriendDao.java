package server.dao;

import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;


public class FriendDao extends DataDao<FriendsEntity,Integer>{
    private EntityManager em;
    public void create(FriendsEntity friends){
        persist(friends);
    }

}
