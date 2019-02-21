import java.io.*;
import java.net.*;
import java.util.Random;

public class DropMessagesNetwork {

  /**
  * networkSocket: DatagramSocket that connects to client to receive messages
  * receivePacket & sendPacket: DatagramPackets to send/receive messages and responses
  */
  DatagramSocket networkSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  /**
  * byte arrays used to send and receive data
  */
  byte[] sendData;
  byte[] receiveData;

  /**
  * stores port numbers of client and server communication
  */
  int clientPortNum;
  int serverPortNum;

  //testing purposes, set to true to display more tesing output in terminals
  boolean testing = false;

  // rate at which the network will drop messages
  int dropRate;

  /**
  * Simulates a network by relaying information between the client and server.
  * Drops messages at an inputted rate in order to test Client and Server capabilites
  * @param serverPortNum port number of server
  * @param drop          percent at which to drop messages
  */
  public DropMessagesNetwork(int serverPortNum, int drop) {
    if(testing) {
      System.out.println("Network running (will drop messages " + drop + "% of the time)");
    }
    this.serverPortNum = serverPortNum;
    dropRate = drop;
    try{
      networkSocket = new DatagramSocket(4444);
    } catch (Exception e){
      System.out.println("Exception: " + e);
    }
  }
  /**
  * Runs network, receives both messages and responses and drops messages
  * occasionally
  */
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
        //receive message from Client
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        networkSocket.receive(receivePacket);
        Message reMessage = new Message(receivePacket.getData());
        clientPortNum = receivePacket.getPort();
        if(testing) {
          System.out.println("\nMessage received...");
          System.out.println("Message Contents: " + reMessage.getMessageContents());
          System.out.println("Received from Port #" + clientPortNum);
        }
        // if message is ending message, forward it
        if(reMessage.getMessageContents().trim().equals("end")) {
          if(testing) {
            System.out.println("End Message Received, forwarding...");
          }
          //forward end message
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          continueReceiving = false;
        }
        //drop packet depending on inputted drop rate
        else if(rand.nextInt(100) >= dropRate ) {
          if(testing) {
            System.out.println("Forwarding Message...");
          }
          //forward message
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          //receive packet (containing response) from serverSocket
          receivePacket = new DatagramPacket(receiveData, receiveData.length);
          networkSocket.receive(receivePacket);
          Message reaMessage = new Message(receivePacket.getData());
          serverPortNum = receivePacket.getPort();
          if(testing) {
            System.out.println("\nResponse received...");
            System.out.println("Contents: " + reaMessage.getMessageContents());
            System.out.println("Received from Port #" + serverPortNum);
          }
          //forward response to server
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
          networkSocket.send(sendPacket);
        }
        else {
          if(testing) {
            System.out.println("Dropped Message...");
          }
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    networkSocket.close();
  }
}
