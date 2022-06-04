package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public class Register extends Commands{
    public String register(PersonsEntity pr){
        if(person.findByName(pr.getName()) == null){
            person.create(pr);
            return "[!] Registered successfully";
        }
           return "[!] Already exists an users with the name: "+pr.getName();
    }
}
