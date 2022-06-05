package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
                BufferedReader in= new BufferedReader (new InputStreamReader(socket.getInputStream()) );

            ServerConnection server=new ServerConnection(socket);
            String request;
            Scanner s = new Scanner(System.in);
            System.out.println("WELCOME to Social Network!");
            System.out.println("Available commands: login <name>, register <name>, exit");
            new Thread(server).start();
            while(true){
                request=s.nextLine();
                if(request.equals("exit")) {
                    out.println(request);
                    break;
                }
                    out.println(request);
            }
            in.readLine();
            socket.close();
            s.close();

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}