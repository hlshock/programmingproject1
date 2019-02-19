import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  DatagramSocket clientSocket;
  DatagramPacket sendPacket;
  DatagramPacket receivePacket;

  byte[] receiveData;

  int port;
  String hostname;
  int messageCounter;

  boolean testing = true;

  /**
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
      //create packet to send message
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      //create packet to receive response
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      //keep sending until response is received
      while(contSending == true) {
        //send packet
        clientSocket.send(sendPacket);
        //if message is not "end", wait for response
        if(message != "end")
        {
          try {
            //receive response
            clientSocket.receive(receivePacket);
            System.out.println("\nResponse received...");
            Message responseMessage = new Message(receivePacket.getData());
            String messageString = responseMessage.getMessageContents().trim();
            String testMessage = message.trim();
            System.out.println("Response contents: " + messageString);
            System.out.println("Original Message contents: " + testMessage);
            //test if response is same as message
            boolean match = messageString.equals(testMessage.toUpperCase());
            System.out.println("Message/Response Match = " + match);
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

  private boolean sendLongMessage(String m){
      for (int i = 0; i < m.length(); i+=255){
        sendMessage(m.substring(i, i+255));
      }
      return true;
  }

  public boolean close(){
    boolean result = false;
    try{
      clientSocket.close();
      result = true;
    }catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
