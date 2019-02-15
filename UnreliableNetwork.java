public class UnreliableNetwork {

  DatagramSocket networkSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  byte[] receiveData;

  public UnreliableNetwork() {
    //what port number should we use?
    networkSocket = new DatagramSocket();
  }
  public void run() {
    //continue looping (this will loop forever, how do we want to close the network?)
    while(true)
    {
      receiveData = byte[256];
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
    }
  }


  public void close() {
    networkSocket.close();
  }

}
