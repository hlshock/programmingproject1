import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

  DatagramSocket serverSocket;
  DatagramPacket packet;

  byte[] receiveData;
  byte[] sendData;

  /**
  *@param hostname
  *@param port
  */
  public Client(String hostname, int port ){
    serverSocket = new DatagramSocket();
    serverSocket.connect(hostname, port);

    }

    public void sendMessage(String message){
      Message messageToSend = new Message(message.toString());
      serverSocket.send(messageToSend);
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
