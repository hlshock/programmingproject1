/**
 * After running the server in one terminal window, in a second terminal window
 * run this test program
 * Port 4445 for direct server client Communication
 * Port 4446 for unreliable network
 */
public class ClientTesting {
  public static void main(String[] args) {
    //TEST 1
    Client client = new Client("localhost", 4445);
    System.out.println("CLIENT:");
    System.out.println("---------- Checkpoint 1: Basic Communication -----------------");
    if(client.sendMessage("pineapple") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client.sendMessage("banana") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client.sendMessage("orange") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }

    if(client.sendMessage("end") == true)
    {
      System.out.println("Message 3: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 3: " + "FAILURE");
    }
    client.close();
    //TEST 2
    System.out.println("");
    System.out.println("--------- Checkpoint 2: Reliable Communication Part 1 ------------");
    //client connects to socket of network
    Client client2 = new Client("localhost", 4446);

    if(client.sendMessage("blueberry") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client.sendMessage("strawberry") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client.sendMessage("raspberry") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }


  }
}
