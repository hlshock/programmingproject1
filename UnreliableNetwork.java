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

  int dropRate;

  public UnreliableNetwork(int serverPortNum, int drop) {
    System.out.println("Unreliable Network running...");
    this.serverPortNum = serverPortNum;
    dropRate = drop;
    //what port number do we use?
    try{
      networkSocket = new DatagramSocket(4444);
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
      receiveData = new byte[260];
      sendData = new byte[260];
      try {
        //receive message from Client:
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        networkSocket.receive(receivePacket);
        System.out.println("\nMessage received...");
        Message reMessage = new Message(receivePacket.getData());
        System.out.println("Message Contents: " + reMessage.getMessageContents());

        clientPortNum = receivePacket.getPort();
        System.out.println("Received from Port #" + clientPortNum);
        // if message is ending message, forward it (don't drop)
        if(reMessage.getMessageContents().trim().equals("end")) {

          //forard message and exit
          System.out.println("End Message Received, forwarding...");
          //forward message - WORKS
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          continueReceiving = false;
        }
        //drop packet depending on inputted drop rate
        else if(rand.nextInt(100) >= dropRate ) {
          System.out.println("Forwarding Message...");
          //forward message - WORKS
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          //receive packet (containing response) from serverSocket - WORKS
          receivePacket = new DatagramPacket(receiveData, receiveData.length);
          networkSocket.receive(receivePacket);
          System.out.println("\nResponse received...");
          Message reaMessage = new Message(receivePacket.getData());
          System.out.println("Contents: " + reaMessage.getMessageContents());
          serverPortNum = receivePacket.getPort();
          System.out.println("Received from Port #" + serverPortNum);
          //forward response to server
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
          networkSocket.send(sendPacket);
        }
        else {
          System.out.println("Dropped Message...");
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    networkSocket.close();
  }
}
