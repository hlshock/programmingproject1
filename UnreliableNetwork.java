import java.io.*;
import java.net.*;
import java.util.Random;

public class UnreliableNetwork {

  DatagramSocket networkSocket;

  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  byte[] receiveData;
  byte[] sendData;

  int clientPortNum;
  int serverPortNum;

  boolean testing = true;

<<<<<<< HEAD
  public UnreliableNetwork(int cNum, int sNum) {
    if(testing) {
      System.out.println("Unreliable Network running...");
    }
    clientPortNum = cNum;
    serverPortNum = sNum;
    //what port number do we use?
    try {
      networkSocket = new DatagramSocket(clientPortNum);
    } catch (Exception e)
    {
      e.printStackTrace();
=======
  public UnreliableNetwork(int serverPortNum) {
    if(testing) {
      System.out.println("Unreliable Network running...");
    }
    this.serverPortNum = serverPortNum;
    //what port number do we use?
    try{
      networkSocket = new DatagramSocket(4444);
      // networkSocket.connect(InetAddress.getByName("localhost"),serverPortNum);
    } catch (Exception e){
      System.out.println("Exception: " + e);
>>>>>>> 523730fab162abe1b01acc864b425aec59db66f6
    }
  }

  public void run() {
    Random rand = new Random();
    boolean continueReceiving = true;
    if(testing) {
      System.out.println("Waiting for messages...");
    }
    //continue looping until we get end message
    while(continueReceiving)
    {
      receiveData = new byte[256];
      sendData = new byte[256];
      try {
        //receive message from Client:
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
<<<<<<< HEAD
        //connect to port number of client socket
        networkSocket.connect(InetAddress.getByName("localhost"), clientPortNum);
        networkSocket.receive(receivePacket);
=======
        networkSocket.receive(receivePacket);
        clientPortNum = receivePacket.getPort();
>>>>>>> 523730fab162abe1b01acc864b425aec59db66f6
        if(testing) {
          System.out.println("Message received...");
        }
        //send message half the time
        if(rand.nextInt(11) < 6) {
          if(testing) {
            System.out.println("Forwarding Packet...");
          }
          sendData = receivePacket.getData();
          //not sure what address to use
<<<<<<< HEAD
          networkSocket.connect(InetAddress.getByName("localhost"), serverPortNum);
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
        }
        else
        {
          if(testing) {
            System.out.println("Packet dropped...");
          }
=======
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
          networkSocket.send(sendPacket);
>>>>>>> 523730fab162abe1b01acc864b425aec59db66f6
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void close() {
    networkSocket.close();
  }

}
