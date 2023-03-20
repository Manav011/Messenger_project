import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
