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
        networkSocket.receive(receivePacket);
        clientPortNum = receivePacket.getPort();
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
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
          networkSocket.send(sendPacket);
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
