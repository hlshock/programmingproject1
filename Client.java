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
  byte[] sendData;

  int port;
  String hostname;

  /**
  *@param hostname
  *@param port
  */
  public Client(String hostname, int port ){
    try {
      serverSocket = new DatagramSocket();
      serverSocket.setSoTimeout(5000);
      serverSocket.connect(InetAddress.getByName(hostname), port);
      this.port = port;
      this.hostname = hostname;
      receiveData = new byte[256];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message){
    boolean contSending = true;
    int counter = 0;
    while(contSending == true && counter < 3)
      Message messageToSend = new Message(message.toString());
      //create packet to send
      sendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      serverSocket.send(sendPacket);

      //wait for response
      try{
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String response = new String(receivePacket.getData());
      System.out.println("From Server: " + response);
      contSending = false;
    } catch (SocketTimeoutException e){
      System.out.println("Exception: " e);

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
