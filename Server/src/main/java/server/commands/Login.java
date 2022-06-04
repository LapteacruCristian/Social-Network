package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public class Login extends Commands{
    public String login(PersonsEntity pr){
        PersonsEntity p=person.findByName(pr.getName());
        if(p != null){
            if(!p.isLogged()){
                pr.setLogged(true);
                pr.setId(p.getId());
                person.setIsLogged(true);
                return "[!] Login successfully";
            }
           return "[!] Login failed: " + pr.getName() + " already logged.";
        }
        return "[!] Login failed: " + pr.getName() + " unknown person.";
    }
}

