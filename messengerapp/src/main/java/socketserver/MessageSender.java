package socketserver;

import GUI.Login_Controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class MessageSender implements Runnable {
    private final static ArrayList<MessageSender> clientHandlers = new ArrayList<>();
    private static int clientNo;
    private final Socket socket;
    private final int clientID;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private  String name;

    public MessageSender(Socket s) throws IOException {
        socket = s;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        this.name = Login_Controller.name;
        System.out.println("in cost "+name);
        clientHandlers.add(this);
        clientNo++;
        clientID = clientNo;
        sendToAll(this.name + ": joined the chat");
    }

    @Override
    public void run() {
        String str = "";
//        name=Login_Controller.getName();
        // reading all the information on the InputStream
        while (socket.isConnected() && !str.equals("q")) {
            try {
                str = bufferedReader.readLine();
                if (!str.equals("q")) {
                    // sending the informaion to OutputStream of Clients
                    System.out.println("in message sended"+name);
                    sendToAll(this.name + ": " + str);
                    System.out.println("in message sended"+name);
                }
            } catch (IOException e) {
                System.out.println();
            }
        }
        try {
            sendToAll(name + ": left the chat");
            socket.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    // sending message to all the Clients connected to Server except the Client who
    // sent
    public void sendToAll(String str) {
        for (MessageSender clientHandler : clientHandlers) {
            if (this.clientID != clientHandler.clientID) {
                clientHandler.printWriter.println(str);
                clientHandler.printWriter.flush();
            }
        }
    }
}
