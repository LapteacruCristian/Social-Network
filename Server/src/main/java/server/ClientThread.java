package server;
import server.commands.Exit;
import server.commands.Login;
import server.commands.Logout;
import server.commands.Register;
import server.dao.PersonDao;
import server.entities.PersonsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.ECField;
import java.sql.SQLOutput;

public class ClientThread extends Thread {
    private Socket socket ;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    public void run () {
        PersonsEntity person = null;
        try {
            System.out.println("Client " + this.socket.getPort() + " is connected... ");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request=null;
            person = new PersonsEntity();
            while (true) {
                request = in.readLine();
                System.out.println(request);

                if (request == null) {
                    break;
                }
                if (person.isLogged() == false) {
                    if (request.length() > 6 && request.substring(0, 6).equals("login ")) {
                        String name = request.substring(6);
                        Login cmd = new Login();
                        person.setName(name);
                        out.println(cmd.login(person));
                        System.out.println(person);
                        out.println();
                        out.flush();
                    } else if (request.length() > 9 && request.substring(0, 9).equals("register ")) {
                        Register cmd = new Register();
                        String name = request.substring(9);
                        person.setName(name);
                        out.println(cmd.register(person));
                        out.println();
                        out.flush();
                    } else if (request.equals("exit")) {
                        System.out.println("Client exited!");
                        out.println("GoodBye");
                        out.println();
                        out.flush();
                        break;
                    }else if (request.equals("help")) {
                        out.println("[?] Help:");
                        out.println("[!] register <name>");
                        out.println("[!] login <name> ");
                        out.println("[!] add friend <name>");
                        out.println("[!] history <friend name>");
                        out.println("[!] send to <name>|friends: message");
                        out.println("[!] logout");
                        out.println("[!] exit ");
                        out.println();
                        out.flush();
                    }
                    else {
                        out.println("[!] Unknown command! Try: login <name>, register <name>, help, exit");
                        out.println();
                        out.flush();
                    }
                } else {
                    if (request.equals("exit")) {
                        System.out.println("Client exited!");
                        Exit cmd = new Exit();
                        person.setLogged(false);
                        out.println(cmd.exit(person.getName()));
                        out.println();
                        out.flush();
                        break;
                    }
                    else if (request.equals("logout")){
                        Logout cmd= new Logout();
                        person.setLogged(false);
                        out.println(cmd.logout(person.getName()));
                        out.println();
                        out.flush();
                        person=new PersonsEntity();
                    }
                    else if (request.equals("help")) {
                        out.println("[?] Help:");
                        out.println("[!] register <name>");
                        out.println("[!] login <name> ");
                        out.println("[!] add friend <name>");
                        out.println("[!] history <friend name>");
                        out.println("[!] send to <name>|friends: message");
                        out.println("[!] logout");
                        out.println("[!] exit ");
                        out.println();
                        out.flush();
                    }
                    else if (request.length() > 6 && request.substring(0, 6).equals("login ")){
                        out.println("[!] Logout, if You want to login with another account.");
                        out.println();
                        out.flush();
                    }
                    else if(request.length() > 9 && request.substring(0, 9).equals("register ")){
                        out.println("[!] Logout, if You want to register another account.");
                        out.println();
                        out.flush();
                    }
                    else {
                        out.println("Unknown command");
                        out.println();
                        out.flush();
                    }
                }

            }

            this.socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            if (person.isLogged()) {
                PersonDao pd=new PersonDao();
                pd.setIsLogged(false);
            }
            try {
                socket.close();// or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}