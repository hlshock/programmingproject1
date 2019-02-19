public class NetworkTesting {

  public static void main(String[] args) {
    System.out.println("Network Checkpoint 2 Testing:");
    UnreliableNetwork network = new UnreliableNetwork(4445, 50);
    network.run();
  }
}
