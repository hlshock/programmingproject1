/**
 * After running the server in one terminal window, in a second terminal window
 * run this test program
 */
public class ClientTesting {
  public static void main(String[] args) {
    Client client = new Client("localhost", 4445);
    System.out.println("CLIENT:");

    System.out.println("---------- Checkpoint 1: Basic Communication -----------------");
    System.out.println("Sending Message 1...");
    System.out.println("contents: " + "apple");
    client.sendMessage("apple");
    System.out.println("Sending Message 2...");
    System.out.println("contents: " + "banana");
    client.sendMessage("banana");
    System.out.println("Sending Message 3...");
    System.out.println("contents: " + "orange");
    client.sendMessage("orange");

    System.out.println("");
    System.out.println("--------- Checkpoint 2: Reliable Communication Part 1 ------------");
    client.close();
  }
}
