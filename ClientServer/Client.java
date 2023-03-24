import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Server IP address: ");
        String ip = sc.nextLine();

        // creating the socket(like a tunnel) by which the user will connect to the
        // server it requires the network ID(IP which we stored in ip) where server is
        // connected
        Socket s = new Socket(ip, 5000);

        // creating an obejct of Printewriter which will be writing or sending
        // information in the outputstream it will take a OutputStream as an argument
        // OutputStreamWriter will be writing
        PrintWriter bw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        String temp = "";

        // storing name of the Client
        while (temp.isEmpty()) {
            System.out.print("Enter Name: ");
            temp = sc.nextLine();
        }
        bw.println(temp);
        bw.flush();

        // this thread will be continuously checking InputStream for any information by
        // the Server
        MessageReciever gc = new MessageReciever(s);
        Thread th = new Thread(gc);
        th.start();

        temp = "";

        // here we will be continuously taking information from Client and writing it on
        // the OutputStream towards Server
        while (!temp.equals("q")) {
            temp = sc.nextLine();
            bw.println(temp);
            bw.flush();// flush will clear the stream if any element is present there
        }

        sc.close();
    }
}
