import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

  /**
   * clientSocket: the DatagramSocket through which the client communicates
   * receivePacket: DatagramPacket used for receiving responses
   * sendPacket: DatagramPacket used for sending messages
   */
  DatagramSocket clientSocket;
  DatagramPacket sendPacket;
  DatagramPacket receivePacket;

  /**
   * byte arrays used to send messages and receive responses
   */
  byte[] receiveData;

  /**
   * port: stores port number that Server is connected to
   * hostname: stores hostname
   * messageCounter: used to keep track of how many messages are sent
   */
  int port;
  String hostname;
  int messageCounter;

  //testing purposes, set to true to display more tesing output in terminals
  boolean testing = false;

  /**
   * Sets up the client so it can send messages to a single server
  *@param hostname
  *@param port
  */
  public Client(String hostname, int port ){
    try {
      //create socket
      clientSocket = new DatagramSocket();
      //set timeout to 5 seconds
      clientSocket.setSoTimeout(5000);
      clientSocket.connect(InetAddress.getByName(hostname), port);
      this.port = port;
      this.hostname = hostname;
      messageCounter = 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a message to the server
   * @param  message [description]
   * @return true when the message is successfully sent
   */
  public boolean sendMessage(String message)
  {
    boolean result = false;
    receiveData = new byte[260];
    if(message.length() > 255){
      return sendLongMessage(message);
    }
    if(testing)
    {
      System.out.println("Attempting to send Message #" + messageCounter);
      System.out.println("Contents: " + message);
    }
    try {
      //create message to send
      Message messageToSend = new Message(message, messageCounter);
      boolean contSending = true;
      //create packets to send message and recieve response
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      //keep sending until response is received
      while(contSending == true) {
        clientSocket.send(sendPacket);
        //if message is not "end", wait for response
        if(message != "end")
        {
          try {
            //receive response
            clientSocket.receive(receivePacket);
            if(testing) {
              System.out.println("\nResponse received...");
            }
            Message responseMessage = new Message(receivePacket.getData());
            String messageString = responseMessage.getMessageContents().trim();
            String testMessage = message.trim();
            if(testing) {
              System.out.println("Response contents: " + messageString);
              System.out.println("Original Message contents: " + testMessage);
            }
            //test if response is same as message
            boolean match = messageString.equals(testMessage.toUpperCase());
            if(testing) {
              System.out.println("Message/Response Match = " + match);
            }
            if(match)
            {
              result = true;
              messageCounter++;
            }
            contSending = false;
          }
          catch (SocketTimeoutException e) {
            if(testing)
            {
              System.out.println("Response not received within timeout, sending again...");
            }
          }
        }
        else
        {
          result = true;
          contSending = false;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }
    return result;
  }
  /**
   * if a message is longer than 255 characters, it must be split up and sent
   * in sections, this method splits up and sends all the pieces of a long message
   * @param  m full message to send
   * @return   true when whole message is successfully sent in pieces
   */
  private boolean sendLongMessage(String m){
    for (int i = 0; i < m.length(); i+=255){
      if((i + 256) > m.length()) {
        sendMessage(m.substring(i));
      }
      else {
        sendMessage(m.substring(i, i+255));
      }
    }
    return true;
  }
  /**
   * Closes the connection to the server
   * @return true when successfully closed
   */
  public boolean close(){
    boolean result = false;
    try{
      clientSocket.close();
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
