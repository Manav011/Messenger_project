package socketserver;

import java.io.*;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class MessageReciever {
    Socket s;

    MessageReciever(Socket s) {
        this.s = s;
    }

    public void receive(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String a = "";

                    // receving message infinitely until user enters q to quit
                    while (!a.equals("q1u2i3t4")) {
                        BufferedReader br = null;
                        try {

                            // InputStream for receiving message
                            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            a = br.readLine();
                            if (!a.equals("null")) {
                                Client_Controller.addLabel(a, vBox);
                            }

                            // handling exception thrown by getInputStream if socket not found
                        } catch (IOException e) {
                            System.out.println();
                        }
                    }
                } catch (Exception e) {
                    System.out.println();
                }
            }
        }).start();

    }

}
