package server.commands;

/**
 * Class Help
 * Class used for executing the command to show the possible commands
 */
public class Help {
    public String help() {
        return "[?] Help:\n" +//done
                "[!] register <name>\n" +//done
                "[!] login <name> \n" +//done
                "[!] add friend <name>\n" +//done
                "[!] history <friend name>\n" +//done
                "[!] send to <name>|friends: message\n" +//done
                "[!] online friends\n" +//done
                "[!] logout\n" +//done
                "[!] exit \n";//done
    }
}
