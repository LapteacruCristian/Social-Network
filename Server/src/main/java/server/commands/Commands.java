package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public abstract class Commands {
    protected PersonDao person=new PersonDao();
}
