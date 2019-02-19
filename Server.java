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
    boolean continueReceiving = true;
    if(testing)
    {
      System.out.println("Server running...");
    }
    while(continueReceiving) {
      receiveData = new byte[260];
      sendData = new byte[260];
      try {
        //initiliaze DatagramPacket to recieve data from the Client
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        // receive message from Client
        serverSocket.receive(receivePacket);
        //using Message class, create Message object from received bytes
        Message receivedMessage = new Message(receivePacket.getData());
        if(testing)
        {
          System.out.println("\nMessage recieved...");
          System.out.println("Message contents: " + receivedMessage.getMessageContents());
          System.out.println("length: " + receivedMessage.getMessageContents().length());
          System.out.println("Message counter: " + receivedMessage.getSequenceCounter());
          System.out.println("Server counter: " + serverCounter + "\n");
        }
        //check for "end" request - don't need to send response
        if(receivedMessage.getMessageContents().equals("end")) {
          System.out.println("End message recevied...");
          continueReceiving = false;
        }
        //server checks message's counter
        if(receivedMessage.getSequenceCounter() == serverCounter)
        {
          // increments counter if this is a new message (not received by server before)
          System.out.println("Incremented server counter, counter = " + serverCounter);
          serverCounter++;
        }
        //sending response - unsure about this part
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        //reply with same sentence
        sendData = receivedMessage.getMessageContents().trim().toUpperCase().getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
        System.out.println("\nResponse sent...");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    serverSocket.close();
  }
}
