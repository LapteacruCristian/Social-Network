package server.dao;

import server.entities.PersonsEntity;

import java.sql.SQLException;
import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

public class PersonDao extends DataDao<PersonsEntity,Integer>{
        public void create(PersonsEntity person){
          persist(person);
        }

        public List<PersonsEntity> findAll(){
            return (List<PersonsEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Person.findAll").getResultList();
        }

        @Override
        public PersonsEntity findbyId(Integer integer) {
            return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1,integer).getSingleResult();
        }

        public PersonsEntity findByName(String name) {

            try{
                return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findByName").setParameter(2,name).getSingleResult();

            }
            catch(Exception e){
                System.out.println(e.getMessage());

            }
            return null;
        }

        public void setIsLogged(boolean state){
            PersistenceUtil.getEntityManager().getTransaction().begin();
            PersistenceUtil.getEntityManager().createNamedQuery("Person.setIsLogged").setParameter(3,state);
            PersistenceUtil.getEntityManager().getTransaction().commit();
        }

        public List<PersonsEntity> getOnlineUsers(){
            return (List<PersonsEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Person.getOnlineUsers").getResultList();
        }

        public List<PersonsEntity> getOnlineFriends(PersonsEntity person){
            return (List<PersonsEntity>) PersistenceUtil.getEntityManager().createNamedQuery("Person.getOnlineFriends").setParameter(2,person).getResultList();
        }



    }

