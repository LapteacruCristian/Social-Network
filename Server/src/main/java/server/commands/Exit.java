package server.commands;

import server.dao.PersonDao;

/**
 * Class Exit
 * Class used for executing the exit command
 */
public class Exit extends Commands {
    public String exit(String name) {
        person.setIsLogged(false);
        return "[!] Goodbye " + name;
    }

}
