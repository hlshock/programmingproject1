public class Server {

  DatagramSocket serverSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  /**
   * Sets up the server so it can receive messages from a specified port
   * @param portNum
   */
  public Server(int portNum) {
    serverSocket = new DatagramSocket(portNum);
  }

  public void run() {
    receivePacket = new DatagramPacket();
    sendPacket = new DatagramPacket();
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
