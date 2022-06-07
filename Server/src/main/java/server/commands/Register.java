package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

/**
 * Class Register
 * Class used for executing the command to register a client
 */
public class Register extends Commands {
    public PersonsEntity register(String name) {
        if (person.findByName(name) == null) {
            PersonsEntity pr = new PersonsEntity(name);
            person.create(pr);
            this.response = "[!] Registered successfully";
            return pr;
        }
        this.response = "[!] Already exists an users with the name: " + name;
        return new PersonsEntity();
    }
}
