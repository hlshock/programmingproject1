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
    try{
    serverSocket = new DatagramSocket();
  }catch (Exception e) {
	    System.err.println("Exception:  " + e);
	    e.printStackTrace();
	}
    }

    public void sendMessage(String message){
      Message message = new Message(message);

    }
    public void close(){

    }
    private void run(){

    }
    public static void main(String[] args) {

    }
}
