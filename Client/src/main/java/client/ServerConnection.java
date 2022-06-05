package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements  Runnable{
    private Socket socket;
    private BufferedReader in;
    public  ServerConnection(Socket socket) throws IOException {
        this.socket=socket;
        this.in = new BufferedReader (new InputStreamReader(socket.getInputStream()) );
    }
    @Override
    public void run() {
        String response=null;
            try {
                while(true) {
                    response=in.readLine();
                    if (response==null)
                        break;
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
