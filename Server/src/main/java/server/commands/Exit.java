package server.commands;

import server.dao.PersonDao;

public class Exit extends Commands {
    public String exit(String name){
        person.setIsLogged(false);
        return "[!] Goodbye "+ name;
    }

}
