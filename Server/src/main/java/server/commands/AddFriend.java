package server.commands;

import server.ClientHandler;
import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import java.io.IOException;
import java.util.List;

/**
 * Class AddFriend
 * Class used to execute the command to add friends
 */
public class AddFriend extends Commands {
    public String addFriend(List<ClientHandler> clients, PersonsEntity p, String name) throws IOException {
        PersonsEntity prs = person.findByName(name);

        if (prs != null) {
            PersonsEntity pr = friend.isFriend(p, prs);
            if (pr == null) {
                for (ClientHandler cl : clients) {
                    if (cl.getPerson() != null && cl.getPerson().isLogged()) {
                        if (cl.getPerson().getName().equals(name)) {
                            if (!p.getName().equals(name)) {
                                cl.getOut().println("[!] " + p.getName() + " added You to his/her friend list");
                                FriendsEntity friends = new FriendsEntity(p, cl.getPerson());
                                friend.create(friends);
                                return "[!] New FriendShip between You and " + name;

                            } else
                                return "[!] I hope that You are already friend with Yourself:)))";
                        }
                    }
                }
                if (!p.getName().equals(name)) {
                    FriendsEntity friends = new FriendsEntity(p, prs);
                    friend.create(friends);
                    return "[!] New FriendShip between You and " + name;

                } else
                    return "[!] I hope that You are already friend with Yourself:)))";
            }
            return "[!] You are already friends with: " + name;
        }
        return "[!] Person: " + name + " unknown!";
    }
}
