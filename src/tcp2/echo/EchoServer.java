package tcp2.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {

        try {
            System.out.println("Waiting for clients.....");
            ServerSocket ss = new ServerSocket(9806);
            Socket soc = ss.accept();
            System.out.println("Connection Established");


            // get from client
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = in.readLine();
            System.out.println("Message Recieved");

            // send to client
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("Server says: "+str);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
