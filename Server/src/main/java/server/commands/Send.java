package server.commands;

import server.ClientHandler;
import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

import java.util.List;

public class Send extends Commands {
    public String send(List<ClientHandler> clients, PersonsEntity p, String to, String msg) {
        if(to.equals("friends".trim())){
            List<PersonsEntity> friends=friend.getFriends(p);
            if(friends.size()>0){
                for(PersonsEntity f:friends){
                    MessagesEntity mess = new MessagesEntity(msg, p, f);
                    if(f.isLogged()){
                        for(ClientHandler client:clients){
                            if(client.getPerson()!=null && client.getPerson().equals(f)){
                                mess.setSeen(true);
                                client.getOut().println("[" + p.getName() + "] " + msg);
                                break;
                            }
                        }
                    }
                    this.message.create(mess);
                }
                return "[!] Message successfully sent to all your friends";
            }else{
                return "[!] You do not have any friends.";
            }
        }

        PersonsEntity checkReceiver=person.findByName(to);
        if(checkReceiver==null){
            return "[!] Receiver unknown!";
        }
        if(!checkReceiver.isLogged()){
            if(friend.isFriend(p,checkReceiver)!=null){
                MessagesEntity mess = new MessagesEntity(msg, p, checkReceiver);
                mess.setSeen(false);
                this.message.create(mess);
                return "[!] Message successfully sent";
            }else{
                return "[!] You are not friend with: " + to;
            }

        }
        for (ClientHandler client : clients) {
            if (client.getPerson()!=null && client.getPerson().getName().equals(to)) {
                if (!p.getName().equals(to)) {
                    if (friend.isFriend(p, client.getPerson()) != null) {
                        MessagesEntity mess = new MessagesEntity(msg, p, client.getPerson());
                        if (client.getPerson().isLogged()) {
                            mess.setSeen(true);
                        }
                        this.message.create(mess);
                        client.getOut().println("[" + p.getName() + "] " + msg);
                        return "[!] Message successfully sent";
                    } else {
                        return "[!] You are not friend with: " + to;
                    }
                } else {
                    return "[!] Error: You are trying to send a message to yourself.";
                }
            }
        }
        return "[!] Error at sending the message to " + to;
    }
}
