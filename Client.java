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
    serverSocket = new DatagramSocket;
    serverSocket.connect("localhost", port);
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
