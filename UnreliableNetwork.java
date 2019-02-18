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
        System.out.println("Message received...");
        Message reMessage = new Message(receivePacket.getData());
        System.out.println("Contents: " + reMessage.getMessageContents());
        clientPortNum = receivePacket.getPort();
        System.out.println("Port of socket received from: " + clientPortNum);
        //drop packet depending on inputted drop rate
        if(rand.nextInt(100) >= dropRate ) {
          System.out.println("Forwarding Packet...");
          //forward message - WORKS
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), serverPortNum);
          networkSocket.send(sendPacket);
          //receive packet (containing response) from serverSocket - WORKS
          receivePacket = new DatagramPacket(receiveData, receiveData.length);
          networkSocket.receive(receivePacket);
          System.out.println("Response received...");
          Message reaMessage = new Message(receivePacket.getData());
          System.out.println("Contents: " + reaMessage.getMessageContents());
          serverPortNum = receivePacket.getPort();
          System.out.println("Port of socket received from: " + serverPortNum);
          //forward response to server
          sendData = receivePacket.getData();
          sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), clientPortNum);
          networkSocket.send(sendPacket);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

    public void close() {
      networkSocket.close();
    }

  }
