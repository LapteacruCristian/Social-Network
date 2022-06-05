package server.commands;

import server.ClientHandler;
import server.entities.FriendsEntity;
import server.entities.PersonsEntity;

import java.io.IOException;
import java.util.List;

public class AddFriend extends Commands {
    public String addFriend(List<ClientHandler> clients, PersonsEntity p, String name) throws IOException {
        PersonsEntity pr = person.findByName(name);
        if (pr != null) {
            pr = friend.isFriend(p, pr);
            if (pr == null) {
                for (ClientHandler cl : clients) {
                    if (cl.getPerson()!=null && cl.getPerson().isLogged()) {
                        if (cl.getPerson().getName().equals(name)) {
                            if(!p.getName().equals(name)){
                                cl.getOut().println("[!] " + p.getName() + " added You to his/her friend list");
                               FriendsEntity friends = new FriendsEntity(p, cl.getPerson());
                                friend.create(friends);
                                return "[!] New FriendShip between You and " + name;

                            }
                            else
                                return "[!] I hope that You are already friend with Yourself:)))";

                        }
                    }
                }
                return "[!] Person: " + name + " is not logged now. Try latter...";
            }
            return "[!] You are already friends with: " + name;
        }
        return "[!] Person: " + name + " unknown!";
    }

//    public String friendRequestResponse(List<ClientHandler> clients, PersonsEntity p, String response) {
//        PersonsEntity pr = person.findByName(name);
//        for (ClientHandler cl : clients) {
//            if (cl.getPerson().isLogged()) {
//                if (cl.getPerson().getName().equals(name)) {
//                    if (response.equals("yes".trim())) {
//                        FriendsEntity friends = new FriendsEntity(p, pr);
//                        friend.create(friends);
//                        cl.getOut().println("[!] Friend request accepted");
//                        return "[!] Person " + name + " accepted Your Friend request";
//
//                    } else {
//                        cl.getOut().println("[!] Friend request declined");
//                        return "[!] Person " + name + " declined Your Friend request";
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
