import java.io.IOException;
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

  /**
  *@param hostname
  *@param port
  */
  public Client(String hostname, int port ){
    try {
      serverSocket = new DatagramSocket();
      serverSocket.connect(InetAddress.getByName(hostname), port);
      this.port = port;
      this.hostname = hostname;
      receiveData = new byte[256];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message){
    try {
      Message messageToSend = new Message(message);
      //create packet to send
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      serverSocket.send(sendPacket);

      //wait for response
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String response = new String(receivePacket.getData());
      System.out.println("Response from Server: " + response);
    } catch (Exception e) {
      e.printStackTrace();
    }

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
