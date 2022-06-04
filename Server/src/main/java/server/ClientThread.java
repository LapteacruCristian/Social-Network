package server;
import server.commands.Exit;
import server.commands.Login;
import server.commands.Register;
import server.entities.PersonsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.ECField;

public class ClientThread extends Thread {
    private Socket socket ;
    public ClientThread(Socket socket) {
        this.socket = socket;
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
            PersonsEntity person=new PersonsEntity();
            while(true) {
                request = in.readLine();
                if(request==null) {
                    break;
                }
                if(person.isLogged()==false){
                    System.out.println("NU1 " + request);
                    if(request.length()>6&&request.substring(0,6).equals("login ")){
                        String name=request.substring(6);
                        Login cmd=new Login();
                        person=cmd.login(name);
                        System.out.println(person);
                        if(person!=null){
                            person.setLogged(true);
                            System.out.println((person.isLogged()));
                            out.println("[!] Autentificare cu SUCCES");
                        }
                        else{
                            out.println("[!] Autenficare ESUATA: " + name +" persoana necunoscuta sau deja autentificata.");
                        }
                        out.flush();
                    }
                    else if(request.length()>9&&request.substring(0,9).equals("register ")){
                         Register cmd=new Register();
                         String name=request.substring(9);
                         person=cmd.register(name);
                         System.out.println(person);
                         if(person!=null) {
                             out.println("[!] Registered succesfully");
                         }
                         else
                             out.println(  "[!] Already exists an users with the name: "+ name);
                         out.flush();
                    }

                    else if(request.equals("exit")){
                        System.out.println("Client exited!");
                        Exit cmd= new Exit();
                        person.setLogged(false);
                        out.println(cmd.exit(person.getName()));
                        out.flush();
                        break;
                    }
                    else
                    {
                        out.println("[!] Comanda nerecunoscuta! Incercati: login <name>, register <name>, exit");
                        out.flush();
                    }
                }
                else{
                     if(request.equals("exit")){
                         System.out.println("Client exited!");
                         Exit cmd= new Exit();
                         person.setLogged(false);
                         out.println(cmd.exit(person.getName()));
                         out.flush();
                         break;

                    }
                    else
                    {
                        out.println("Comenda nerecunoscuta");
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
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }
}