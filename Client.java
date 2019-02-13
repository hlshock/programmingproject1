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
      receiveData = new byte[256];
      messageCounter = 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message)
  {
    System.out.println("Attempting to send: " + message);
    try {
      //create message to send
      Message messageToSend = new Message(message, messageCounter);
      boolean contSending = true;
      int counter = 0;
      //create packet to send
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      //create packet to receive response
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      //keep sending (up to 3 times?) until response is received
      while(contSending == true && counter < 3) {
        //send packet
        serverSocket.send(sendPacket);
        //wait for response
        try {
          serverSocket.receive(receivePacket);
          System.out.println("Message #" + messageCounter +  " successfully sent");
          contSending = false;
        }
        //if response doesn't come within 5 seconds, send message again
        catch (SocketTimeoutException e) {
          System.out.println("Response not received within timeout, sending again...");
        }
        counter++;
      }
      //get contents in a string
      String response = new String(receivePacket.getData());
      System.out.println("Response from Server: " + response);
    }
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }
    messageCounter++;
  }

  public void close(){
    try{
      serverSocket.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  private void run(){

  }
  public static void main(String[] args) {

  }
}
