package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket(); //creating a client socket
            InetAddress serverAddress = InetAddress.getByName("localhost");  //fetch ip address
            int serverPort = 9806;
            System.out.println("Client Started");

            //read from keyboard
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string: ");
            String str = userInput.readLine();

            //send to server
            byte[] sendBuffer = str.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Message sent to Server");

            //receive from server
            byte[] recieveFromServer = new byte[1024];
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(recieveFromServer, recieveFromServer.length);
                socket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message Received from Server");
                System.out.println("Length of String: "+receivedData.length());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
