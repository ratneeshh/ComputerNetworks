package tcp2.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) {

        try {
            System.out.println("Client Started");
            Socket soc = new Socket("localhost", 9806);

            // read the keyboard
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string: ");
            String str = userInput.readLine();

            // send to server
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println(str);

            // get back from server
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(in.readLine());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
}
