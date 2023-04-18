package socketserver;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import javafx.scene.layout.VBox;

public class Client {

    private Socket socket;
    private BufferedWriter bufferedwriter;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error creating client");
            e.printStackTrace();
        }
    }

    public void receiveMessage(VBox vBox) {
        MessageReciever mr = new MessageReciever(socket);
        mr.receive(vBox);
    }

    public void sendMessageToServer(String msg) {
        try {
            bufferedwriter.write(msg);
            bufferedwriter.newLine();
            bufferedwriter.flush();
        } catch (IOException e) {
            System.out.println("Error sending message");
        }
    }

    public void closeEveryThing(Socket socket, BufferedWriter bufferedwriter) {
        if (socket != null) {
            try {
                if (bufferedwriter != null)
                    bufferedwriter.close();

                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
