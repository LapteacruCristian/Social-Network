package server.dao;

import server.entities.PersonsEntity;

import java.util.List;

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

        public PersonsEntity findByName(String name){
            return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findByName").setParameter(2,name).getSingleResult();
        }

        public void setIsLogged(){
            PersistenceUtil.getEntityManager().getTransaction().begin();
            PersistenceUtil.getEntityManager().createNamedQuery("Person.setIsLogged");
            PersistenceUtil.getEntityManager().getTransaction().commit();
        }

    }

