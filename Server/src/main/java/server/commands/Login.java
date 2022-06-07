package server.commands;

import server.dao.MessageDao;
import server.dao.PersonDao;
import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

/**
 * Class Login
 * Class used for executing the command to log in a client
 */
public class Login extends Commands {
    public PersonsEntity login(String name) {
        PersonsEntity pr = person.findByName(name);
        if (pr != null) {
            if (!pr.isLogged()) {
                pr.setLogged(true);
                person.setIsLogged(true);
                System.out.println(pr);
                //
                StringBuilder unreadMessages = new StringBuilder();
                unreadMessages.append("[!] Login successfully\n");
                MessageDao messageDao = new MessageDao();
                if (messageDao.getUnseenMessages(pr).isEmpty()) {
                    this.response = unreadMessages.toString();
                } else {
                    unreadMessages.append("[!] Unseen messages:\n");
                    for (String element : messageDao.getUnseenMessages(pr)) {
                        unreadMessages.append(element);
                        unreadMessages.append("\n");
                    }
                    for (MessagesEntity m : messageDao.getMyMessages(pr)) {
                        m.setSeen(true);
                    }
                    this.response = unreadMessages.toString();
                }
                return pr;
            }
            this.response = "[!] Login failed: " + name + " already logged.";
            return new PersonsEntity();
        }
        this.response = "[!] Login failed: " + name + " unknown person.";
        return new PersonsEntity();
    }

}

