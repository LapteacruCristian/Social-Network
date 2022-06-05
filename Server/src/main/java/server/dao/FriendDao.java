package server.dao;

import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;
import java.util.List;


public class FriendDao extends DataDao<FriendsEntity,Integer>{
    private EntityManager em;
    public void create(FriendsEntity friends){
        persist(friends);
    }
    @Override
    public FriendsEntity findbyId(Integer integer) {
        return (FriendsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1,integer).getSingleResult();
    }

    public List<PersonsEntity> getOnlineFriends(PersonsEntity person){
        return (List<PersonsEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Friend.getOnlineFriends").setParameter(2,person).getResultList();
    }

    public List<PersonsEntity> getFriends(PersonsEntity person){
        return (List<PersonsEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Friend.getFriends").setParameter(2,person).getResultList();
    }

    public PersonsEntity isFriend(PersonsEntity person1, PersonsEntity person2){
        try{
            return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Friend.isFriend").setParameter(2,person1).setParameter(3,person2).getSingleResult();
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return null;
    }
}
