package server;
import server.commands.*;
import server.dao.PersonDao;
import server.entities.PersonsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket ;
    private BufferedReader in;
    private PrintWriter out;
    private PersonsEntity person;
    private List<ClientHandler> clients;
    public ClientHandler(Socket socket,List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.clients=clients;
        this.person = new PersonsEntity();
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(),true);
    }
    @Override
    public void run () {
        try {
            System.out.println("Client " + this.socket.getPort() + " is connected... ");
            String request=null;
            while (true) {
                request = in.readLine();
                System.out.println(request);

                if (request == null) {
                    break;
                }
                if (person.isLogged() == false) {
                    if (request.length() > 6 && request.startsWith("login ")) {
                        String name = request.substring(6).trim();
                        Login cmd = new Login();
                        person.setName(name);
                        out.println(cmd.login(person));
                        System.out.println(person);
                    } else if (request.length() > 9 && request.startsWith("register ")) {
                        Register cmd = new Register();
                        String name = request.substring(9).trim();
                        person.setName(name);
                        out.println(cmd.register(person));
                    } else if (request.equals("exit")) {
                        System.out.println("Client exited!");
                        out.println("GoodBye");
                        break;
                    }else if (request.equals("help")) {
                       out.println(new Help().help());
                    }
                    else {
                        out.println("[!] Unknown command! Try: login <name>, register <name>, help, exit");
                    }
                } else {
                    if (request.equals("exit")) {
                        System.out.println("Client exited!");
                        Exit cmd = new Exit();
                        person.setLogged(false);
                        out.println(cmd.exit(person.getName()));
                        break;
                    }
                    else if(request.startsWith("send to ") && request.length()>8){
                        int lastChOfName=request.indexOf(":");
                        String to=request.substring(8,lastChOfName).trim();
                        for (ClientHandler client:clients){
                            if(client.person.getName().equals(to))
                                if(!person.getName().equals(to)){
                                    client.out.println("["+person.getName()+"] "+request.substring(lastChOfName+1).trim());
                                }else{
                                    out.println("[!] Error: You are trying to send a message to yourself.");
                                }
                        }
                    }
                    else if (request.equals("logout")){
                        Logout cmd= new Logout();
                        person.setLogged(false);
                        out.println(cmd.logout(person.getName()));
                        person=new PersonsEntity();
                    }
                    else if (request.equals("help")) {
                        out.println(new Help().help());
                    }
                    else if (request.length() > 6 && request.startsWith("login ")){
                        out.println("[!] You are logged already. For login ---  Logout first.");
                    }
                    else if(request.length() > 9 && request.startsWith("register ")){
                        out.println("[!] You are logged already. For register --- logout first.");
                    }
                    else {
                        out.println("[!] Unknown command");
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