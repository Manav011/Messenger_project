package socketserver;

import java.io.*;
import java.net.Socket;

import Encryption.Encryptdecrypt;
import javafx.scene.layout.VBox;

import static socketserver.Client_Controller.addLabel;

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
                    while (!a.equals("q")) {
                        BufferedReader br = null;
                        try {

                            // InputStream for receiving message
                            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            a = br.readLine();
                            if (!a.equals("null")) {
                                Encryptdecrypt encdec =new Encryptdecrypt("1234567890123456");
//                                String decryptedd_msg=encdec.decrypt(a);
//                                System.out.println(decryptedd_msg);
                                addLabel(a, vBox);
                            }

                            // handling exception thrown by getInputStream if socket not found
                        } catch (IOException e) {
                            System.out.println();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println();
                }
            }
        }).start();

    }

}
