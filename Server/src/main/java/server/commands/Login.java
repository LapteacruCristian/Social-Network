package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public class Login extends Commands{
    public PersonsEntity login(String name){
        System.out.println(person.findByName(name));
        PersonsEntity p=person.findByName(name);
        if(p != null && !p.isLogged()){
            p.setLogged(true);
            System.out.println(p.isLogged());
            person.setIsLogged(true);
            return p;
        }
        return null;
    }
    }

