import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

  DatagramSocket serverSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  byte[] receiveData;
  byte[] sendData;

  /**
  * Sets up the server so it can receive messages from a specified port
  * @param portNum
  */
  public Server(int portNum) {
    try {
      serverSocket = new DatagramSocket(portNum);
      receiveData = new byte[255];
      sendData = new byte[255];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
  * Starts the server listening on its port
  */
  public void run() {

    while(true) {
      System.out.println("Server running");
      try {
        //initiliaze DatagramPacket to recieve data from the Client
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        // receive data
        serverSocket.receive(receivePacket);
        System.out.println("Message recieved...");
        String message = new String(receivePacket.getData());
        System.out.println("Message contents: " + message);
        //check for "end" request - do we respond if we get "end"" request?
        if(message.equals("end")) {
          break;
        }
        //sending response - unsure about this part
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();

        String reply = "Reply";
        sendData = reply.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    serverSocket.close();
  }
}
