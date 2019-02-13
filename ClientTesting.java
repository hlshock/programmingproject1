public class ClientTesting {
  public static void main(String[] args) {
    System.out.println("Client Testing:");
    Client client = new Client("localhost", 4445);
    client.sendMessage("Message #1", 1);
    //client.sendMessage("Message #2", 2);
    client.close();
  }
}
