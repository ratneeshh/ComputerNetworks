package tcp.factorial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FactorialClient {

    public static void main(String[] args) {

        try{
            System.out.println("Client Started...");
            Socket soc = new Socket("localhost", 9806);

            // read from keyboard
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a number: ");
            int num  = Integer.parseInt(userInput.readLine());

            // send to server
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println(num);

            // get back from server
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(in.readLine());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
