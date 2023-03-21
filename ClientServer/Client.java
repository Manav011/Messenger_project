package MessengerApp.ClientServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Server IP address: ");
        String ip = sc.nextLine();
        Socket s = new Socket(ip, 5000);

        PrintWriter bw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        String temp = "";
        while (temp.isBlank()) {
            System.out.print("Enter Name: ");
            temp = sc.nextLine();
        }
        bw.println(temp);
        bw.flush();

        MessageReciever gc = new MessageReciever(s);
        Thread th = new Thread(gc);
        th.start();
        temp = "";
        while (!temp.equals("q")) {
            temp = sc.nextLine();
            bw.println(temp);
            bw.flush();
        }

        sc.close();
    }
}
