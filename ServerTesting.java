public class ServerTesting {

  public static void main(String[] args) {
    System.out.println("Server Testing:");
    Server server = new Server(4445);
    server.run();
  }
}
