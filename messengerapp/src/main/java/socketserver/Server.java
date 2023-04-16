package socketserver;

import java.io.IOException;
import java.net.*;

public class Server {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Getting the address of the server
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia.getHostAddress());

        // ServerSocket where Client Socket will connect
        ServerSocket ss = new ServerSocket(5000);

        // This thread will be continuously accepting Clients
        ClientAccepter ca = new ClientAccepter(ss);
        Thread th = new Thread(ca);
        th.start();
    }
}
