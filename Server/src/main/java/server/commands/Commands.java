package server.commands;

import server.dao.FriendDao;
import server.dao.MessageDao;
import server.dao.PersonDao;
import server.entities.PersonsEntity;

/**
 * Abstract Class Commands
 */
public abstract class Commands {
    protected PersonDao person = new PersonDao();
    protected FriendDao friend = new FriendDao();
    protected MessageDao message = new MessageDao();
    protected String response = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
