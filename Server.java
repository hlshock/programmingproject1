import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

  /**
   * serverSocket: the DatagramSocket through which the server communicates
   * receivePacket: DatagramPacket used for receiving messages
   * sendPacket: DatagramPacket used for sending responses
   */
  DatagramSocket serverSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  /**
   * byte arrays used to recieve messages and send responses
   */
  byte[] receiveData;
  byte[] sendData;

  /**
   * counter used so server can distinguish whether or not to "do" the requested
   * action, in our program "doing" a request is simply incrementing this counter
   */
  int serverCounter;

  //testing purposes, set to true to display more tesing output in terminals
  boolean testing = false;

  /**
  * Sets up the server so it can receive messages from a specified port
  * @param portNum: port number to connect to
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
  * Starts the server listening on its port, receives messages and sends responses
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
          if (testing) {
            System.out.println("End message recevied...");
          }
          continueReceiving = false;
        }
        //server checks message's counter
        if(receivedMessage.getSequenceCounter() == serverCounter)
        {
          // increments counter if this is a new message (not received by server before)
          if(testing) {
            System.out.println("Incremented server counter, counter = " + serverCounter);
          }
          serverCounter++;
        }
        //sending response - unsure about this part
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        //reply with uppercase message
        sendData = receivedMessage.getMessageContents().trim().toUpperCase().getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        serverSocket.send(sendPacket);
        if (testing) {
          System.out.println("\nResponse sent...");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    serverSocket.close();
  }
}
