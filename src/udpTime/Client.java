package udpTime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client Started...");
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            byte[] requestBuffer = "Get Time".getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestBuffer, requestBuffer.length, serverAddress, serverPort);

            long t1 = System.currentTimeMillis();
            System.out.println("T1 (Client Send Time): " + t1);

            socket.send(requestPacket);
            System.out.println("Request Sent to Server...");

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            long t4 = System.currentTimeMillis();
            System.out.println("T4 (Client Receive Time): " + t4);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server Response: " + response);

            long rtt = t4 - t1;
            System.out.println("Round-Trip Time (RTT): " + rtt + " ms");

            // Extract server time from response
            String[] parts = response.split("-");
            long serverTime = Long.parseLong(parts[1].trim());

            // Estimate clock drift
            long clockDrift = serverTime - (t1 + rtt / 2);
            System.out.println("Estimated Clock Drift: " + clockDrift + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}