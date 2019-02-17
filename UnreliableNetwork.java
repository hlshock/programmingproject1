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
        //connect to port number of client socket
        networkSocket.connect(InetAddress.getByName("localhost"), clientPortNum);
        networkSocket.receive(receivePacket);
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
          networkSocket.connect(InetAddress.getByName("localhost"), serverPortNum);
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
        }
        else
        {
          if(testing) {
            System.out.println("Packet dropped...");
          }
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
