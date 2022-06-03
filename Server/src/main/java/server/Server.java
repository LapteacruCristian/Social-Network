package server;

import server.dao.PersonDao;
import server.entities.PersonsEntity;
import server.dao.PersistenceUtil;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private  final int PORT = 8100;
    private Server(){

    }

    public void RunServer() throws IOException {
        ServerSocket serverSocket = null;
        System.out.println("Waiting for clients");
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                //new ClientThread(socket,socialNetwork).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        Server server = new Server();
//        server.RunServer();

        PersonsEntity p= new PersonsEntity(1,"Cristi");
        PersonDao p1=new PersonDao();
        p1.create(p);

    }
}