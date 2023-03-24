import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class MessageSender implements Runnable {
    public static ArrayList<MessageSender> clientHandlers = new ArrayList<>();
    public static int clientNo;
    public final Socket socket;
    private final int clientID;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private final String name;

    MessageSender(Socket s) throws IOException {
        socket = s;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        name = bufferedReader.readLine();
        clientHandlers.add(this);
        clientNo++;
        clientID = clientNo;
        sendToAll(name + " has joined the chat");
    }

    @Override
    public void run() {
        String str = "";
        // reading all the information on the InputStream
        while (socket.isConnected() && !str.equals("q")) {
            try {
                str = bufferedReader.readLine();
                if (!str.equals("q")) {
                    // sending the informaion to OutputStream of Clients
                    sendToAll(this.name + ": " + str);
                }
            } catch (IOException e) {
                System.out.println();
            }
        }
        try {
            sendToAll(name + " has left the chat");
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
