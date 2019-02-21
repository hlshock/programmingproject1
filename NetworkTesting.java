public class NetworkTesting {

  public static void main(String[] args) {
    System.out.println("Network Checkpoint 2 Testing:");
    DropMessagesNetwork network = new DropMessagesNetwork(4445, 50);
    network.run();
    System.out.println("Network Checkpoint 3 Testing:");
    UnreliableNetwork network2 = new UnreliableNetwork(4445, 50, 50);
    network2.run();
    //UnreliableNetwork network3 = new UnreliableNetwork(4445, 50, 50)
  }
}
