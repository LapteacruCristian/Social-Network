package server.commands;

import server.dao.PersonDao;
import server.entities.PersonsEntity;

public abstract class Commands {
    protected PersonDao person=new PersonDao();
    protected PersonsEntity prs=new PersonsEntity();

    public PersonsEntity getPrs() {
        return prs;
    }
}
