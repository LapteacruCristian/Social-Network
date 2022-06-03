package server.dao;

import server.SingletonUtil;
import server.entities.PersonsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static server.SingletonUtil.entityManager;

public class PersonDao extends DataDao<PersonsEntity,Integer>{
        private EntityManager em;


        public void create(PersonsEntity person){
            em = entityManager().createEntityManager();
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            em.close();
        }

        @Override
        public PersonsEntity findbyId(Integer integer) {
            return (PersonsEntity) em.createNamedQuery("Person.findById").setParameter(1,integer);
        }

        public PersonsEntity findByName(String name){
            return (PersonsEntity) em.createNamedQuery("Person.findByName").setParameter(2,name);
        }
    }

