/**
 * After running the server in one terminal window, in a second terminal window
 * run this test program
 */
public class ClientTesting {
  public static void main(String[] args) {
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

    System.out.println("");
    System.out.println("--------- Checkpoint 2: Reliable Communication Part 1 ------------");
    client.close();
  }
}
