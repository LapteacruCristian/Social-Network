package server.commands;

import server.entities.PersonsEntity;

import java.util.List;

public class Friends extends Commands{
    public boolean hasFriends(PersonsEntity p){
        List<PersonsEntity> friends=friend.getFriends(p);
        if(friends.size()>0)
            return true;
        return false;
    }
    public String getOnlineFriends(PersonsEntity p){
        if(hasFriends(p)){
            List<PersonsEntity> friends=friend.getOnlineFriends(p);
            if(friends.size()>0){
                StringBuilder result=new StringBuilder();
                result.append("[!] Online Friends\n");
                for(PersonsEntity f :friends){
                    result.append("[!] "+f.getName()+"\n");
                }
                return result.toString();
            }
            return "[!] All Your friends are not online\n";
        }
        return "[!] You do not have any friends. To add friends use: add friend <name>\n";
    }
}
