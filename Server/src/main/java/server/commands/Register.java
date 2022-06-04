package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public class Register extends Commands{
    public PersonsEntity register(String name){
        System.out.println(person.findByName(name));
        if(person.findByName(name) == null){
            PersonsEntity newPerson=new PersonsEntity(name);
            person.create(newPerson);
            return newPerson;
        }
           return null;
    }
}
