package server.dao;

import server.entities.PersonsEntity;

import javax.persistence.EntityManager;

public class PersonDao extends DataDao<PersonsEntity,Integer>{

        public void create(PersonsEntity person){
          persist(person);
        }

//        @Override
//        public PersonsEntity findbyId(Integer integer) {
//            return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findById").setParameter(1,integer);
//        }
//
//        public PersonsEntity findByName(String name){
//            return (PersonsEntity) PersistenceUtil.getEntityManager().createNamedQuery("Person.findByName").setParameter(2,name);
//        }
    }

