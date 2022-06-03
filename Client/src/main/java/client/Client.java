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
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {
            // Send a request to the server
            String request;
            String response;
            Scanner s = new Scanner(System.in);
            System.out.println("WELCOME to Social Network!");
            while(true){
                System.out.println("Enter command:");
                request=s.nextLine();
                if(request.equals("exit")){
                    out.println(request);
                    break;
                }
                else{
                    out.println(request);
                    response = in.readLine ();
                    System.out.println(response);
                }
            }
            response = in.readLine ();
            System.out.println(response);
            socket.close();
            s.close();
            in.close();
            out.close();

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}