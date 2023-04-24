package socketserver;

import java.io.IOException;
import java.net.*;

public class ClientAccepter implements Runnable {
    private ServerSocket serversocket;

    public ClientAccepter(ServerSocket serversocket) throws IOException {
        this.serversocket = serversocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serversocket.accept();
                System.out.println("Client Accepted");
                MessageSender ia = new MessageSender(socket);
                Thread th = new Thread(ia);
                th.start();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }

}
