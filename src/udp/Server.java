package udp;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("UDP Server is running");
            System.out.println("Waiting for Clients...");
            DatagramSocket socket = new DatagramSocket(9806);

            byte[] recieveBuffer = new byte[1024]; //datapackets is sent in bytes, so to receive it

            //receive from client
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);
                socket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message Received from client");

                //send back to client
                String responseData = receivedData;
                byte[] responseDataBytes = responseData.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, receivePacket.getAddress(), receivePacket.getPort());
                socket.send(responsePacket);
                System.out.println("Response sent to client");
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }




    }
}
