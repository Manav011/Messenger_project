import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GroupChat implements Runnable{
    Socket s;
    GroupChat(Socket s)
    {
        this.s = s;
    }
    @Override
    public void run()
    {
        try {
            String a = "";
            while (!a.equals("q")) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    a = br.readLine();
                    if (!a.equals("null")) {
                        System.out.println(a);
                    }
                } catch (IOException e) {
                    System.out.println();
                }
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
    }
}
