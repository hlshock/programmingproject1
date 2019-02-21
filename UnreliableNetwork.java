import java.io.*;
import java.net.*;
import java.util.Random;

public class UnreliableNetwork {

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
  byte[] receiveData;
  byte[] sendData;

  /**
  * stores port numbers of client and server communication
  */
  int clientPortNum;
  int serverPortNum;

  //testing purposes, set to true to display more tesing output in terminals
  boolean testing = false;


  /**
  * dropResponsesRate: rate at which the network will drop responses
  * dropMessagesRate: rate at which the network will drop messages
  */
  int dropResponsesRate;
  int dropMessagesRate;

  /**
  * Simulates a network by relaying information between the client and server.
  * Drops messages and responses at an inputted rate in order to test Client and Server capabilites
  * @param serverPortNum port number of server
  * @param dropResponsesRate percent at which to drop responses
  * @param dropMessagesRate percent at which to drop messages
  */
  public UnreliableNetwork(int serverPortNum, int dropResponsesRate, int dropMessagesRate) {
    if(testing) {
      System.out.println("Network running (will drop messages " + dropMessagesRate + "% of the time and drop responses + " + dropResponsesRate+ "% of the time)");
    }
    this.serverPortNum = serverPortNum;
    this.dropResponsesRate = dropResponsesRate;
    this.dropMessagesRate = dropMessagesRate;
    try{
      networkSocket = new DatagramSocket(4444);
    } catch (Exception e){
      System.out.println("Exception: " + e);
    }
  }

  /**
  * Runs network, receives both messages and responses and drops messages and responses
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
          //forward message
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          continueReceiving = false;
        }
        //forward message
        else if(rand.nextInt(100) >= dropMessagesRate ) {
          if(testing) {
            System.out.println("Forwarding Message...");
          }
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
          //drop/forward response depending on inputted drop rate
          if(rand.nextInt(100) >= dropResponsesRate ) {
            //forward response to server
            if(testing) {
              System.out.println("Forwarding Response to Client...");
            }
            sendData = receivePacket.getData();
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
            networkSocket.send(sendPacket);
          }
          else {
            if(testing) {
              System.out.println("Dropped Response...");
            }
          }
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
