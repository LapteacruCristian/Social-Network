package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private SocialNetwork socialNetwork;
    private boolean isLogged;
    private Socket socket ;
    public ClientThread(Socket socket,SocialNetwork socialNetwork) {
        this.socket = socket;
        this.socialNetwork=socialNetwork;
    }
    public void run () {
        try {
            System.out.println("Client " + this.socket.getPort()+" is connected... ");
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // Send the response to the oputput stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request;
            Person person=new Person();
            while(true) {
                request = in.readLine();
                if(request==null) {
                    break;
                }
                if(!person.isLogged()){
                    if(request.length()>6&&request.substring(0,6).equals("login ")){
                        System.out.println(socialNetwork.getPersons());

                        if(socialNetwork.login(request.substring(6))){
                            person=new Person(request.substring(6),true);
                            out.println("Autentificare cu SUCCES");
                            out.flush();
                        }
                        else{
                            out.println("Autenficare ESUATA: persoana necunoscuta sau deja conectata");
                            out.flush();
                        }

                    }
                    else if(request.length()>9&&request.substring(0,9).equals("register ")){
                        person=new Person(request.substring(9),false);

                        out.println(socialNetwork.register(person));
                        out.flush();
                    }
                    else if(request.equals("stop")) {
                        System.out.println("Server stopped!");
                        out.println("Server stopped!");
                        out.flush();
                        break;
                    }
                    else if(request.equals("exit")){
                        System.out.println("Client exited!");
                        out.println("Goodbye!");
                        out.flush();
                        break;
                    }
                    else
                    {
                        out.println("Comenzi disponibile momentat: login <name>, register <name>, exit");
                        out.flush();
                    }
                }
                else {
                     if(request.equals("exit")){
                         System.out.println("Client exited!");
                        out.println("Goodbye!");
                        out.flush();
                        break;
                    }
                    else if(request.length()>7&&request.substring(0,7).equals("friend ")){
                        System.out.println(person.getName());
                        if(socialNetwork.addFriend(person,request.substring(7))){
                            out.println("Friends added! Your friends: "+ person.getFriends());
                        }
                        else out.println("ERROR! One of the names does not exist!");
                    }
                    else
                    {
                        System.out.println(request);
                        out.println("Comanda necunoscuta");
                        out.flush();
                    }
                }
            }
            person.setLogged(false);
            this.socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }
}