package server;

import server.dao.FriendDao;
import server.dao.MessageDao;
import server.dao.PersonDao;
import server.entities.FriendsEntity;
import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

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
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.RunServer();

//       PersonsEntity p = new PersonsEntity("ci853o7");
//       PersonDao p1=new PersonDao();
//       p1.create(p);
//       System.out.println("pers creata");
//
//       PersonsEntity k = new PersonsEntity("c50494i");
//       PersonDao k1=new PersonDao();
//       k1.create(k);
//       System.out.println("pers creata");
//        System.out.println(k.getId());
//
//       MessagesEntity m = new MessagesEntity("Salut ce faci?",k, p);
//       MessageDao m1 =new MessageDao();
//       m1.create(m);
//       System.out.println("mesaj trimis");
//
//       FriendsEntity f = new FriendsEntity(k,p);
//       FriendDao f1 = new FriendDao();
//       f1.create(f);
//       System.out.println("Prieteni adaugati ");
//       System.out.println(k.isLogged());
//       p.setLogged(true);
//       p1.setIsLogged();
//      p1.findByName("Dan");
//        System.out.println("1");
//       System.out.println(k1.findAll());
    }
}