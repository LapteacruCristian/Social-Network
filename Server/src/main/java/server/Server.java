package server;

import server.dao.FriendDao;
import server.dao.MessageDao;
import server.dao.PersistenceUtil;
import server.dao.PersonDao;
import server.entities.FriendsEntity;
import server.entities.MessagesEntity;
import server.entities.PersonsEntity;

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
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        EntityManager em= PersistenceUtil.getEntityManager();

        Server server = new Server();
        server.RunServer();
        //To test get messages
//        PersonsEntity p1 = new PersonsEntity("TestMess1");
//        PersonsEntity p2 = new PersonsEntity("TestMess2");
//        PersonDao pd = new PersonDao();
//        pd.create(p1);
//        pd.create(p2);
//        System.out.println("Persoane creeate");
//
//        MessagesEntity m1 = new MessagesEntity("Buna, sunt persoana 1",p1, p2);
//        MessagesEntity m2 = new MessagesEntity("Cum treaba? sunt persoana 1",p1, p2);
//        MessageDao md =new MessageDao();
//        md.create(m1);
//        md.create(m2);
//        System.out.println("mesaje trimise");
//
//        System.out.println(pd.getMessages(p1,p2));

        //To test the onlineFriends
//        PersonsEntity p1 = new PersonsEntity("persoanaTest1");
//        PersonsEntity p2 = new PersonsEntity("persoanaTest2");
//        PersonDao pd = new PersonDao();
//        pd.create(p1);
//        pd.create(p2);
//        System.out.println("Persoane creeate");
//
//        FriendsEntity f = new FriendsEntity(p1,p2);
//        FriendDao fd = new FriendDao();
//        fd.create(f);
//        System.out.println("Prieteni adaugati");
//
//        System.out.println(pd.getOnlineFriends(p1));

        //To test onlineUsers
//       PersonsEntity p = new PersonsEntity("ci853o7");
//       PersonDao p1=new PersonDao();
//       p1.create(p);
//       System.out.println(p1.getOnlineUsers());
//       System.out.println("pers creata");

    }
}