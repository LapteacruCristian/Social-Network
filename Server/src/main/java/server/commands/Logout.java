package server.commands;

/**
 * Class Logout
 * Class used for executing the command to log out a client
 */
public class Logout extends Commands {
    public String logout(String name) {
        person.setIsLogged(false);
        return "[!] Logout successfully. GoodBye " + name;
    }
}
