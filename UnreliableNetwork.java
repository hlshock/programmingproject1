import java.io.*;
import java.net.*;
import java.util.Random;

public class UnreliableNetwork {

  DatagramSocket clientSocket;
  DatagramSocket serverSocket;

  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  byte[] receiveData;
  byte[] sendData;

  int clientPortNum;
  int serverPortNum;

  boolean testing = true;

  public UnreliableNetwork(int clientPortNum, int serverPortNum) {
    if(testing) {
      System.out.println("Unreliable Network running...");
    }
    this.clientPortNum = clientPortNum;
    this.serverPortNum = serverPortNum;
    //what port number do we use?
    try {
      clientSocket = new DatagramSocket(clientPortNum);
      serverSocket = new DatagramSocket(serverPortNum);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void run() {
    Random rand = new Random();
    boolean continueReceiving = true;
    //continue looping until we get end message
    while(continueReceiving)
    {
      if(testing) {
        System.out.println("Waiting for messages...");
      }
      receiveData = new byte[256];
      sendData = new byte[256];
      try {
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        if(testing) {
          System.out.println("Message received...");
        }
        //send message half the time
        if(rand.nextInt(11) < 6) {
          if(testing) {
            System.out.println("Forwarding message...");
          }
          sendData = receivePacket.getData();
          //not sure what address to use
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          serverSocket.send(sendPacket);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void close() {
    clientSocket.close();
    serverSocket.close();
  }

}
