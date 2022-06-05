package server;
import server.commands.*;
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
    private boolean addFriend=false;
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
                        person=cmd.login(name);
                        System.out.println(person);
                        out.println(cmd.getResponse());
                    } else if (request.length() > 9 && request.startsWith("register ")) {
                        Register cmd = new Register();

                        String name = request.substring(9).trim();
                        person=cmd.register(name);
                        out.println(cmd.getResponse());
                    } else if (request.equals("exit".trim())) {
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
                    if (request.equals("exit".trim())) {
                        System.out.println("Client exited!");
                        Exit cmd = new Exit();
                        person.setLogged(false);
                        out.println(cmd.exit(person.getName()));
                        person=null;

                        break;
                    }
                    else if(request.startsWith("add friend ") && request.length()>11){
                        String name=request.substring(11);
                        AddFriend cmd =new AddFriend();
                        out.println(cmd.addFriend(clients,person,name));
                    }
                    else if(request.startsWith("send to ") && request.length()>8 && request.contains(":")){
                        int lastChOfName=request.indexOf(":");
                        String to=request.substring(8,lastChOfName).trim();
                        String msg=request.substring(lastChOfName+1).trim();
                        Send cmd=new Send();
                        out.println(cmd.send(clients,person,to,msg));
                    }
                    else if(request.equals("online friends".trim())){
                            Friends cmd=new Friends();
                            out.println(cmd.getOnlineFriends(person));
                    }
                    else if (request.equals("logout".trim())){
                        Logout cmd= new Logout();
                        person.setLogged(false);
                        out.println(cmd.logout(person.getName()));
                        person=new PersonsEntity();
                    }
                    else if (request.equals("help".trim())) {
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
            try {
                socket.close();// or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public PersonsEntity getPerson() {
        return person;
    }

    public void setPerson(PersonsEntity person) {
        this.person = person;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public void setAddFriend(boolean addFriend) {
        this.addFriend = addFriend;
    }
}