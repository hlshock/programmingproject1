import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

  DatagramSocket serverSocket;
  DatagramPacket packet;

  byte[] receiveData;
  byte[] sendData;

  int port;

  /**
  *@param hostname
  *@param port
  */
  public Client(String hostname, int port ){
    serverSocket = new DatagramSocket();
    serverSocket.connect(InetAddress.getByName(hostname), port);
    this.port = port;
    }

    public void sendMessage(String message){
      Message messageToSend = new Message(message.toString());
      //create packet to send
      clientSendPacket = new DatagramPacket(messageToSend.getBytes(), messageToSend.getBytes().length, InetAddress.getByName(hostname), port);
      serverSocket.send(clientSendPacket);
    }
    public void close(){
      try{
        serverSocket.close();
      }catch (Exception e) {
        e.printStackTrace();
      }

      return true;
    }
    private void run(){

    }
    public static void main(String[] args) {

    }
}
