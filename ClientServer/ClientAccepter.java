package MessengerApp.ClientServer;

import java.io.IOException;
import java.net.*;

public class ClientAccepter implements Runnable{
    ServerSocket ss;

    ClientAccepter(ServerSocket ss)
    {
        this.ss = ss;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Socket a = ss.accept();
                System.out.println("Client Accepted");
                MessageSender ia = new MessageSender(a);
                Thread th = new Thread(ia);
                th.start();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }
}
