package socketserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import GUI.Login_Controller;

public class MessageSender implements Runnable {
    private final static ArrayList<MessageSender> clientHandlers = new ArrayList<>();
    private static int clientNo;
    private final Socket socket;
    private final int clientID;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public MessageSender(Socket s) throws IOException {
        socket = s;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        clientHandlers.add(this);
        clientNo++;
        clientID = clientNo;
        // sendToAll(name + ": joined the chat");
    }

    @Override
    public void run() {
        String str = "";
        // reading all the information on the InputStream
        while (socket.isConnected() && !str.equals("q1u2i3t4")) {
            try {
                str = bufferedReader.readLine();
                if (!str.equals("q1u2i3t4")) {
                    // sending the informaion to OutputStream of Clients
                    sendToAll(str);
                }
            } catch (IOException e) {
                System.out.println();
            }
        }
        try {
            // sendToAll(name + ": left the chat");
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
