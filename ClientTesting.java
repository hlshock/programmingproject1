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

    client.close();
    //TEST 2
    System.out.println("");
    System.out.println("--------- Checkpoint 2: Reliable Communication Part 1 ------------");
    //client connects to socket of network
    Client client2 = new Client("localhost", 4444);

    if(client2.sendMessage("blueberry") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client2.sendMessage("strawberry") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client2.sendMessage("raspberry") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }
    client2.close();
    System.out.println("--------- Checkpoint 3: Reliable Communication Part 2 ------------");

    System.out.println("--------- Checkpoint 4: Large	message	transfer ------------");
    Client client3 = new Client("localhost", 4444);
    if (client3.sendMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent volutpat pellentesque laoreet. Quisque nulla purus, ultrices tristique justo at, porttitor mollis mauris. Sed mollis hendrerit dui eget cursus. Integer semper semper felis, id blandit diam tristique vitae. Nulla congue a elit ac convallis. Nam eu massa ac magna vulputate aliquet. Aliquam in tortor non ante varius rutrum. Nulla pretium massa dolor, eu rhoncus tellus volutpat vehicula. Ut ac viverra lectus, eu blandit diam. Donec a mi nullam.") == true){
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }
  }
}
