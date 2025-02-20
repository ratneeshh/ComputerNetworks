package udpTime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Server Started...");
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(requestPacket);
                System.out.println("Request Received from Client...");

                long t2 = System.currentTimeMillis();
                System.out.println("T2 (Server Receive Time): " + t2);

                long serverTime = System.currentTimeMillis();
                String response = "Current Time: " + new Date(serverTime) + " - " + serverTime;
                byte[] responseBytes = response.getBytes();

                long t3 = System.currentTimeMillis();
                System.out.println("T3 (Server Send Time): " + t3);

                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, requestPacket.getAddress(), requestPacket.getPort());
                socket.send(responsePacket);
                System.out.println("Response Sent to Client...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}