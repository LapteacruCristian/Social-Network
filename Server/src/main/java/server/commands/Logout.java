package server.commands;

public class Logout extends Commands{
    public String logout(String name){
        person.setIsLogged(false);
        return "[!] Logout successfully. GoodBye "+ name;
    }
}
