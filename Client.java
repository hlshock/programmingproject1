import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {


  DatagramSocket serverSocket;
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
      serverSocket = new DatagramSocket();
      //set timeout to 5 seconds
      serverSocket.setSoTimeout(5000);
      serverSocket.connect(InetAddress.getByName(hostname), port);
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
    receiveData = new byte[256];
    if(testing)
    {
      System.out.println("Attempting to send Message #" + messageCounter);
      System.out.println("Contents: " + message);
    }
    try {
      //create message to send
      Message messageToSend = new Message(message, messageCounter);
      boolean contSending = true;
      int counter = 0;
      //create packet to send message
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      //create packet to receive response
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      //keep sending (up to 3 times?) until response is received
      while(contSending == true && counter < 3) {
        //send packet
        serverSocket.send(sendPacket);
        messageCounter++;
        //wait for response
        try {
          serverSocket.receive(receivePacket);
          Message responseMessage = new Message(receivePacket.getData());
          String messageString = responseMessage.getMessageContents().trim();
          String trimmedMessage = message.trim();
          if(testing)
          {
            //System.out.println("Response received from server. ");
            //System.out.println("messageString: " + messageString + " length: " + messageString.length());
            //System.out.println("message: " + message + " length: " + trimmedMessage.length());

            //test if response is same as message
            boolean match = messageString.equals(trimmedMessage);
            System.out.println("match: " + match);
            if(match)
            {
              result = true;
            }
          }
          contSending = false;
        }
        //if response doesn't come within 5 seconds, send message again
        catch (SocketTimeoutException e) {
          if(testing)
          {
            System.out.println("Response not received within timeout, sending again...");
          }
        }
        counter++;
      }
    }
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }
    return result;
  }

  public boolean close(){
    boolean result = false;
    try{
      serverSocket.close();
      result = true;
    }catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  private void run(){

  }
  public static void main(String[] args) {

  }
}
