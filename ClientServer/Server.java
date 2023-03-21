package MessengerApp.ClientServer;

import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia.getHostAddress());
        ServerSocket ss = new ServerSocket(5000);
        ClientAccepter ca = new ClientAccepter(ss);
        Thread th = new Thread(ca);
        th.start();
    }
}
