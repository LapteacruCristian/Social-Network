package server.commands;

public class Help {
    public String help(){
        return  "[?] Help:\n" +
                "[!] register <name>\n" +
                "[!] login <name> \n" +
                "[!] add friend <name>\n" +
                "[!] history <friend name>\n" +
                "[!] send to <name>|friends: message\n"+
                "[!] online friends\n" +
                "[!] logout\n"+
                "[!] exit \n";
    }
}
