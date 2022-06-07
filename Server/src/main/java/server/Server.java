package server;

import server.dao.PersistenceUtil;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Server
 * Class used to create the server
 */
public class Server {
    private  final int PORT = 8100;
    private static List<ClientHandler> clients=new ArrayList<>();
    private Server(){
    }

    /**
     * Method RunServer() (establish connection between clients)
     * @throws IOException
     */
    public void RunServer() throws IOException {
        ServerSocket serverSocket = null;
        System.out.println("Waiting for clients");
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                ClientHandler client=new ClientHandler(socket,clients);
                clients.add(client);
                client.start();
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
    }
}