package tcp.factorial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FactorialServer {
    public static void main(String[] args) {

        try{
            System.out.println("Server Started, Waiting for clients...");
            ServerSocket ss = new ServerSocket(9806);
            Socket soc = ss.accept();
            System.out.println("Connection Established");

            // read from client
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            int num  = Integer.parseInt(in.readLine());

            // send back to client
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("Factorial of number : "+ calcFactorial(num));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    static int calcFactorial(int num){

        int f = 1;

        for (int i = 1; i <=  num ; i++) {
            f *= i;
        }
        return f;
    }
}
