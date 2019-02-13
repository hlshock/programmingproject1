public class ClientTesting {
  public static void main(String[] args) {
    Client client = new Client("localhost", 4445);
    System.out.println("Client Testing:");
    System.out.println("----------------------- Checkpoint 1 --------------------");
    System.out.println("Sending Message 1...");
    client.sendMessage("apple");
    System.out.println("Sending Message 2...");
    client.sendMessage("banana");
    System.out.println("Sending Message 3...");
    client.sendMessage("orange");
    client.close();
  }
}
