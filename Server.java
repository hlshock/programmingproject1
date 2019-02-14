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

  int serverCounter;

  boolean testing = true;

  /**
  * Sets up the server so it can receive messages from a specified port
  * @param portNum
  */
  public Server(int portNum) {
    try {
      serverSocket = new DatagramSocket(portNum);

      serverCounter = 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
  * Starts the server listening on its port
  */
  public void run() {
    //continues looping until message containing "end" is received
    while(true) {
      receiveData = new byte[255];
      sendData = new byte[255];

      if(testing)
      {
        System.out.println("Server running...");
      }
      try {
        //initiliaze DatagramPacket to recieve data from the Client
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        // receive message from Client
        serverSocket.receive(receivePacket);
        //using Message class, create Message object from received bytes
        Message receivedMessage = new Message(receivePacket.getData());
        if(testing)
        {
          System.out.println("Message #" + receivedMessage.getSequenceCounter() + " recieved");
          System.out.println("contents: " + receivedMessage.getMessageContents());
        }
        //server checks message's counter?
        //then increments counter (from checkpoint #3)
        serverCounter++;
        //sending response - unsure about this part
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        //reply with same sentence
        sendData = receivedMessage.getMessageContents().trim().getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
        //check for "end" request
        if(receivedMessage.getMessageContents().equals("end")) {
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    serverSocket.close();
  }
}
