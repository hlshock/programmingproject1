public class NetworkTesting {

  public static void main(String[] args) {
    System.out.println("Network Checkpoint 2 Testing:");
    UnreliableNetwork network = new UnreliableNetwork(4445, 50);
    network.run();
    System.out.println("Network Checkpoint 3 Testing:");
    DropResponses network2 = new DropResponses(4445, 50);
    network2.run();
  }
}
