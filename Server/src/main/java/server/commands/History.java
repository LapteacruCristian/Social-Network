package server.commands;

import server.ClientHandler;
import server.dao.FriendDao;
import server.dao.MessageDao;
import server.entities.FriendsEntity;
import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

import java.util.Collections;
import java.util.List;

/**
 * Class History
 * Class used for executing the command to show the history of conversation with between two friends
 */
public class History extends Commands {
    public String showHistory(PersonsEntity p, String friendName) {
        FriendDao friendDao = new FriendDao();
        for (PersonsEntity fr : friendDao.getFriends(p)) {
            if (fr.getName().equals(friendName)) {
                if (!p.getName().equals(friendName)) {
                    MessageDao messageDao = new MessageDao();
                    StringBuilder messageBuilder = new StringBuilder("[!] Conversation with ");
                    messageBuilder.append(friendName);
                    messageBuilder.append("\n");
                    for (String element : messageDao.getMessages(p, fr)) {
                        messageBuilder.append(element);
                        messageBuilder.append("\n");
                    }
                    String messageFinal = messageBuilder.toString();
                    return messageFinal;
                } else
                    return "[!] A conversation with yourself doesn't exists";
            }
        }
        return "[!] Person: " + friendName + " is not a friend!";
    }
}
