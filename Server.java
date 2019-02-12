public class Server {

  DatagramSocket serverSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  byte[] receiveData;
  byte[] sendData;

  /**
   * Sets up the server so it can receive messages from a specified port
   * @param portNum
   */
  public Server(int portNum) {
    serverSocket = new DatagramSocket(portNum);
    receiveData = new byte[255];
    sendData = new byte[255];
  }
  /**
   * Starts the server listening on its port
   */
  public void run() {

    while(true) {
      //initiliaze DatagramPacket to recieve data from the Client
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      // receive data
      serverSocket.receive(receivePacket);
      String message = new String(receivePacket.getData());
      //check for closing request
      if(message == "close")
        break;
    }
    serverSocket.close();
  }
}
