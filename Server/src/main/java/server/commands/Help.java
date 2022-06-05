package server.commands;

public class Help {
    public String help(){
        return  "[?] Help:\n" +//done
                "[!] register <name>\n" +//done
                "[!] login <name> \n" +//TREBUIE DE FACUT SA APARA MESAJELE CARE LE-A PRIMIT CAT TIMP ERA OFFLINE
                "[!] add friend <name>\n" +//done
                "[!] history <friend name>\n" +//TREBUIE DE IMPLEMENTAT
                "[!] send to <name>|friends: message\n"+//done
                "[!] online friends\n" +//done
                "[!] logout\n"+//done
                "[!] exit \n";//done
    }
}
