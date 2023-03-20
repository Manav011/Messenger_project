import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                ClientHandler ia = new ClientHandler(a);
                Thread th = new Thread(ia);
                th.start();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }
}
