
import java.io.*;
import java.net.Socket;

public class MessageReciever implements Runnable {
    Socket s;

    MessageReciever(Socket s) {
        this.s = s;
    }

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
                        System.out.println(a);
                    }

                    // handling exception thrown by getInputStream if socket not found
                } catch (IOException e) {
                    System.out.println();
                }
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
    }
}
