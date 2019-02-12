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
        String message = new String(receivePacket.getData());
        //check for closing request - do we respond if we get closing request?
        if(message == "close") {
          break;
        }
        //sending response
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        //What do we reply?
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
