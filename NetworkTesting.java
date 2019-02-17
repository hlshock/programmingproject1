public class NetworkTesting {

  public static void main(String[] args) {
    System.out.println("Network Testing:");
    UnreliableNetwork network = new UnreliableNetwork(4445, 4446);
    network.run();
  }
}
