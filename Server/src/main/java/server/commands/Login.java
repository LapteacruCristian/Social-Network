package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public class Login extends Commands{
    public PersonsEntity login(String name){
        PersonsEntity pr=person.findByName(name);
        if(pr != null){
            if(!pr.isLogged()){
                pr.setLogged(true);
                person.setIsLogged(true);
                System.out.println(pr);
                this.response= "[!] Login successfully";
                return pr;
            }
           this.response= "[!] Login failed: " + name+ " already logged.";
            return new PersonsEntity();
        }
        this.response= "[!] Login failed: " + name + " unknown person.";
        return new PersonsEntity();
    }

}

